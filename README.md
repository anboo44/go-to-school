## GO-TO-SCHOOL

### Service Information
```
1. Discovery Service: using Eureka Service for exploring child services
2. Gateway Service: using Spring Cloud Gateway for routing
3. Auth Service: using OAuth2 protocol. It contains model `User`. Username & Password for login
4. Core Service: It contains models: `Student`, `Teacher`, `Classroom`. A student / teacher is only in one classroom. A classroom has many students, one teacher.
5. Report Service: Make reports about Classroom
6. Logger Service: log result from `Report Service` and requester. Using kafka to handle event
7. UI Service: display data. Using ReactJs.
```

### Setup

Comming soon

