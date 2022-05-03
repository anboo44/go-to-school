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
- Create database for gts-core-service: gts_core

## Micro-Services

#### I. Common-lib

- Module name: `gts-common-lib`
- Contain base package for another services: annotation, constant, dto, exception, util, protobuf
- Using google.protobuf lib to convert `.proto` file to `.java` file
- This module is compiled with another modules

#### II. Discovery-service

- Module name: `gts-discovery-service`
- Using `eureka-server` for exploring child services
- Service starts at fixed port: `8711`
- View all registered services at local: `http://localhost:8711`
- Run `DiscoveryApplication.java` to start service

#### III. Gateway-service

- Module name: `gts-gateway-service`
- Using `spring-cloud-gateway`
- Service starts at fixed port: `8080`
- Info: all requests will be authenticated at gateway, before coming to destination service
- Run `GatewayApplication.java` to start service

#### IV. Core-service

- Module name: `gts-core-service`
- Info: Process main business logic for `teacher`, `student`, `classroom`
- Support REST API & gRPC
- Tomcat server & gRPC server starts at dynamic port, the port will be determined at runtime
- View Open API specification at: `http://localhost:<port>/core/swagger-ui/index.html`
- Running:
  + Run `CoreApplication.java` to start service
  + Test API: `http://localhost:<port>/core/api/v1/hello` (if run through gateway, please set `<port>` to 8080)
  + Test gRPC: Using Postman, choose gRPC request. Set server URL: `http://localhost:<port>`. Choose `classroom.proto` (in common-lib). Select valid method. Finally, click Invoke

#### V. Report-service

- Module name: `gts-report-service`
- Info: Connect to `gts-core-service` to provide report through rest apis
- Report-service connect Core-service by using RestAPI & gRPC
- When client call apis to get report, there are message as action log sent to kafka
- Running:
  + Need start Core-service & Kafka (view setup step) at first 
  + Run `ReportApplication.java` to start service
  + Start a kafka consumer on the terminal. Read the events from topic `report-logger` (view setup step for get detail)
  + Test API: `http://localhost:<port>/core/api/v1/report/student` (if run through gateway, please set `<port>` to 8080)

#### VI. Logger-service

- Module name: `gts-logger-service`
- Info: Receive messages from report-service via kafka and then, show them on console.
- Running:
  + Start Kafka & Core-service & Report-service at first
  + Run `LoggerApplication.java` to start service
  + Make an API caller to `Report-service`. And then, view `logger-service`'s console to see event message.
  
#### VII. Auth-service
Updating ...