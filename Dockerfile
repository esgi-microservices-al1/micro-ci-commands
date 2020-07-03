FROM maven:3.6.3-jdk-11

RUN mkdir /api

WORKDIR /api

COPY ./microservices .

RUN mvn install && mvn package

EXPOSE 8080

CMD ["java", "-jar", "./target/*.jar"]
