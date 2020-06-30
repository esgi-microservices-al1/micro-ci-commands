FROM maven:latest

WORKDIR /api

LABEL MICROSERVICE_NAME=commands

COPY ./microservices /api

RUN mvn install && mvn package
