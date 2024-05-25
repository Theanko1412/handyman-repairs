# handyman-repairs

## Description

Backend for full stack project for [Information Systems course](https://www.fer.unizg.hr/en/course/infsys)


Spring Boot + React [frontend](https://github.com/Theanko1412/handyman-repairs-fe)


## Usage


### Docker

```gradle
./gradlew bootJar
```

```bash
cd support/docker/handyman-repairs
docker-compose up -d
```


### IDE

prereq: postgres running on ```5555``` with ```user = user``` and ```password = password ``` (dockercompose.yml)

Running:
```bash
./gradlew bootRun
```

Testing:
```bash
./gradlew test
```


## Endpoints


Postman collection inside ```support/postman``` folder

## Users

### Handyman
email: ```adam.wilson@example.com```
password: ```password```

### Customer
email: ```john.doe@example.com```
password: ```password```
