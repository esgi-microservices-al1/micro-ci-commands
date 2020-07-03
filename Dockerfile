FROM maven:3.6.3-jdk-11

CMD mkdir /api

WORKDIR /api

COPY ./microservices /api

RUN mvn install && mvn package

RUN java -jar /api/target/*.jar
