## GO-TO-SCHOOL

####There is an example for SpringBoot Micro-services

### Service Raw Information
```
1. Discovery Service: using Eureka Service for exploring child services
2. Gateway Service: using Spring Cloud Gateway for routing
3. Auth Service: using OAuth2 protocol. It contains model `User`. Username & Password for login
4. Core Service: It contains models: `Student`, `Teacher`, `Classroom`. A student / teacher is only in one classroom. A classroom has many students, one teacher.
5. Report Service: Make reports about Classroom
6. Logger Service: log result from `Report Service`. Using kafka to handle event
7. UI Service: display data. Using ReactJs.
```

<img src="/document/system-architecture.png" alt="drawing" width="200" style="display: block; margin: auto"/>

```text
Explain:
1. Request to system, it is through gateway-service
2 & 3. Gateway-service fetches public_key from auth-service via rest api for authenticate & authorize. If success, request goes to destination, else reject with code 401 or 403.
4. Request goes to service destination for business via Rest API
5. If request comes report-service, report-service will contact core-service via Rest & gRPC.
6. After report-service processes request successfully, it pushes one message to kafka-server
7. Logger-service receives message from kafka-server
```

## Setup

I. Install Kafka for event streaming

- Download & install kafka: 
  + If Os is Windows, follow: https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/
  + Another Os, follow: https://kafka.apache.org/quickstart
- Zookeeper starts at port: 2181
- Kafka starts at port: 9092

II. Install postgres database

- Download & install: https://www.postgresql.org/download/
- Setting username & password. Default username: postgres
- Postgres starts at port: 5432
- Create database for gts-core-service: gts_core, gts_auth

III. JDK
- Require JDK 11+

IV. Gradle

- If use IntelIJ IDEA, skip this step. IntelIJ supports to build `spring-native package` for GraalVM
- Install Gradle follow guideline at here: https://www.tutorialspoint.com/gradle/gradle_installation.htm

V. Docker

- if on local, recommend to use `Docker Desktop`
- Install docker by using `google-search`

## Microservices

Please run all services by below ordered. All services can be built into `native-image` by `spring-native`

#### I. Common-lib

- Module name: `gts-common-lib`
- Contain base package for another services: annotation, constant, dto, exception, util, protobuf
- Using google.protobuf lib to convert `.proto` file to `.java` file
- This module is compiled with another modules

#### II. Discovery-service

- Module name: `gts-discovery-service`
- Using `eureka-server` for exploring child services
- Service starts at fixed port: `8761`
- View all registered services at local: `http://localhost:8711`
- Run `DiscoveryApplication.java` to start service

#### III. Gateway-service

- Module name: `gts-gateway-service`
- Using `spring-cloud-gateway`
- Service starts at fixed port: `8080`
- Info: all requests will be authenticated at gateway, before coming to destination service
- Run `GatewayApplication.java` to start service

#### IV. Auth-service

- Module name: `gts-auth-service`
- Info: Apply OAuth2 with Jwt for authentication
- Service starts at fixed port: `8000`
- Change DB config at [application.properties](/auth-service/src/main/resources/application.properties)
- Add postman guideline for auth-service to view at here: https://h.readthedocs.io/en/latest/api/authorization/#client-credentials
- Springboot support 3 ways for tokenStore
  + InMemoryTokenStore: AccessToken is stored in `ConcurrentHashMap`(memory). No use for distributed system because sync.
  + JdbcTokenStore: AccessToken is stored in DB
  + RedisTokenStore: AccessToken is stored in redis server
  + JwtTokenStore: Authorizaton Server doesn't store AccessToken. Using public key for validating. So, Jwt is invalid when it is expired. Please set short-time for it.
- Running:
  + POST /oauth/token: for login. Body request: grant_type: password, username, password
  + GET  /oauth/check_token?token=...
  + POST /oauth/token: check token. Body request: grant_type: refresh_token, username, password, refresh_token
  + Running `AuthApplication.java` to start service

#### V. Core-service

- Module name: `gts-core-service`
- Info: Process main business logic for `teacher`, `student`, `classroom`
- Support REST API & gRPC
- Change DB config at [application.properties](/core-service/src/main/resources/application.properties)
- Tomcat server & gRPC server starts at dynamic port, the port will be determined at runtime
- View Open API specification at: `http://localhost:<port>/core/swagger-ui/index.html`
- Running:
  + Run `CoreApplication.java` to start service
  + Test API: `http://localhost:<port>/core/api/v1/hello` (if run through gateway, please set `<port>` to 8080)
  + Test gRPC: Using Postman, choose gRPC request. Set server URL: `http://localhost:<port>`. Choose `classroom.proto` (in common-lib). Select valid method. Finally, click Invoke

#### VI. Report-service

- Module name: `gts-report-service`
- Info: Connect to `gts-core-service` to provide report through rest apis
- Report-service connect Core-service by using RestAPI & gRPC
- When client call apis to get report, there are message as action log sent to kafka
- Apply `SpringBatch` to clear `ServiceInstanceCache`: run every 5 minutes.
  - SpringBatch flow: 
    - Create `Tasklet` -> add it to `Step` -> Add to `Job`. Using `JobLaucher` to run job.
    - Push `JobLaucher` & `Job`to `JobDetail` -> Add to `Trigger` with one scheduler -> Running.
    - Can use `Reader`, `Processor`, `Writer` flow for JDBC working.
    - View detail at: https://docs.spring.io/spring-batch/docs/current/reference/html/readersAndWriters.html
- Running:
  + Need start Core-service & Kafka (view setup step) at first 
  + Run `ReportApplication.java` to start service
  + Start a kafka consumer on the terminal. Read the events from topic `report-logger` (view setup step for get detail)
  + Test API: `http://localhost:<port>/core/api/v1/report/student` (if run through gateway, please set `<port>` to 8080)

#### VII. Logger-service

- Module name: `gts-logger-service`
- Info: Receive messages from report-service via kafka and then, show them on console.
- Running:
  + Start Kafka & Core-service & Report-service at first
  + Run `LoggerApplication.java` to start service
  + Make an API caller to `Report-service`. And then, view `logger-service`'s console to see event message.

## Build & Run all services

- Running by docker (easily way):
  + Turn on docker app
  + Go to project_path, run command: `./docker-native.sh`
  + Source code will be packed into `native-image`
- Running by jar file:
  + Run command: `./gradlew :<service_name>:build`
    
    ex: ./gradlew :auth-service:build
  + Go to build_folder inside service and run command: `java -jar <service_name>.jar`
    
    ex: java -jar gts-auth-service.jar
  + Remember DB setting before start service by above guideline.

- Test:
  + Using postman, view all services: http://localhost:8761 (may be take time, please refresh page)
  + You need login at first, authentication info: use grant_type: password and username,password,client_id,client_secret at [here](/auth-service/src/main/java/com/uet/gts/auth/initData/DataFactory.java)
  + You can use postman collection for import to Postman app, at [here](/document/postman)
  + Enjoy result ^^

## TODO
- Add caching for `GET API` or `Get data from DB` by using Redis
- Add cloud-load-balancer dependence for call api from report service to core service
- Create external service, impl SSO to follow: https://shekhargulati.com/2018/02/15/single-sign-on-in-spring-boot-applications-with-spring-security-oauth
- Add system architecture image
- Add websocket
- Add prometheus
- Add circus breaker