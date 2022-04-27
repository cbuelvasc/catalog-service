# Catalog Service
This README documents the necessary steps to get your application up and running.

## Assumptions

All these configurations were made using:

* Eclipse IDE 2021‑09.
* IntelliJ IDEA 2020.2.3 (Ultimate Edition).

## Prerequisites

* Install Java 17, download it
  from [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
* You can use the Spring Boot Gradle Wrapper or Install Gradle. Download it
  from [here](https://gradle.org/install/).

## Postman Collection
* The Postman collection is located in the postman directory


## Useful Commands

| Gradle Command	                            | Description                                                  |
|:----------------------------------------------|:-------------------------------------------------------------|
| `./gradlew bootRun`                           | Run the application.                                         |
| `./gradlew build`                             | Build the application.                                       |
| `./gradlew build -x test`                     | Build the application and omit test.                         |
| `./gradlew test`                              | Run all tests.                                               |
| `./gradlew test --tests <Name Class Test>`    | Run a specific class of tests.                               |
| `./gradlew bootJar`                           | Package the application as a JAR.                            |
| `./gradlew bootBuildImage`                    | Package the application as a container image.                |
| `./gradlew bootBuildImage -x test`            | Package the application as a container image and omit test.  |


* Run your SpringBoot project and test from the Java CLI:
```bash
$ java -jar build/libs/catalog-service-0.0.1-SNAPSHOT.jar
```

## Docker

### Execute with Docker Compose

1. Create image
```bash
$ ./gradlew bootBuildImage
```
2. Ingress the docker directory
```bash
$ cd docker
```
3. Execute the next command
```bash
$ docker-compose up -d
```

4. Run the following command to disable the services when you are done with execution
```bash
$ docker-compose down
```

### Github Docker Registry
```bash
$ docker tag config-service:0.0.1-SNAPSHOT <username>/catalog-service:0.0.1-SNAPSHOT
```

```bash
$ docker push <username>/catalog-service:0.0.1-SNAPSHOT
```

* Create image and push to registry
```bash
$ ./gradlew bootBuildImage --imageName ghcr.io/<your_github_username>/catalog-service --publishImage -PregistryUrl=ghcr.io -PregistryUsername=<your_github_username> -PregistryToken=<your_github_token>
```

### Clear Docker

* Delete all docker images
```bash
docker rmi $(docker images -a -q)
``` 

* Remove all local volumes
```bash
docker volume prune 
``` 

* Remove all local system
```bash
docker system prune
``` 

* Remove all local network
```bash
docker network prune
``` 

## REST API

### Configuration

| Endpoint	                                                                                    | Method   | Req. body  | Status | Resp. body     | Description    		   	                                  |
|:---------------------------------------------------------------------------------------------:|:--------:|:----------:|:------:|:--------------:|:----------------------------------------------------------|
| `/api/prices/`                                                                                | `GET`    |            | 200    |                | Get all prices.                                           |
| `/api/prices/{id}`                                                                            | `GET`    |            | 200    |                | Get a price by id.                                        |
| `/api/prices/currentPrice?applicationDate={date}&productId={productId}&brandId={brandId}`     | `GET`    |            | 200    |                | Get a price by a specific date, product id and brand id.  |


### Monitoring

| Endpoint	                  | Method   | Status | Description    		   	                        |
|:---------------------------:|:--------:|:------:|:-----------------------------------------------:|
| `/actuator/info`            | `GET`    | 200    | Get info of service.                            |
| `/actuator/health`          | `GET`    | 200    | Get health of service.                          |
| `/actuator/health/readiness`| `GET`    | 200    | Get readiness state of service.                 |
| `/actuator/health/liveness` | `GET`    | 200    | Get liveness state of service.                  |
| `/actuator/flyway`          | `GET`    | 200    | Get config of flyway.                           |
| `/actuator/prometheus`      | `GET`    | 200    | Get Prometheus metrics of service.              |
| `/actuator/metrics`         | `GET`    | 200    | Get all metrics of service.                     |


