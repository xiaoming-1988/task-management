# Task Management Application
Restful api style Task-ManagementWeb-Application.
To show the basic impl of a backend application mit Spring Boot,Spring Data JPA, Maven, Postgres and Docker.

The frontend ist in another repository [task-app](https://github.com/xiaoming-1988/task-app)

## Prerequisite
- maven
- docker
- jdk 17+

## Getting Started

## Run the Application

- To get the db image

```bash
docker pull postgres
```

- To build the task management application docker image

```bash
mvn clean package  -DskipTests
docker build -t task-management-server .
```

- To start the Application with docker compose
```bash
docker compose up
```

- To connect to the db, and run the init sql [schema.sql](./src/test/resources/schema.sql)

- Now the backend api server should be up to use, try the curl below or the
swagger ui [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


```bash
curl http://localhost:8080/api/v1/task/all 
```


- you can start up the frontend app to use the webpage from repository [task-app](https://github.com/xiaoming-1988/task-app)

## Help

## License

Apache 2.0, see [LICENSE](LICENSE).
