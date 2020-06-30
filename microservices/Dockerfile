FROM maven:3.6.3-jdk-11

WORKDIR /usr/app/tmp

COPY mvnw ./
COPY mvnw.cmd ./
COPY pom.xml ./
COPY src/ ./src/

RUN mvn package \
&& cp ./target/app.jar /usr/app/

WORKDIR /usr/app/

#RUN rm -rf tmp

ENTRYPOINT ["java","-jar","./app.jar"]
