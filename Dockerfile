FROM maven:3.6.3-jdk-11 as builder

COPY ./microservices /api

WORKDIR /api

RUN mvn dependency:go-offline && mvn package -Dmaven.test.skip=true

FROM openjdk:11

USER root

COPY --from=builder /api/target/app.jar /usr/src/app.jar

RUN  chmod +x  /usr/src/app.jar

EXPOSE 8899

CMD ["java", "-jar", "/usr/src/app.jar"]
