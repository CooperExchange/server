FROM maven:3.8.7-eclipse-temurin-19 AS build
ADD . /project
WORKDIR /project
RUN mvn -e package

FROM eclipse-temurin:latest
COPY --from=build /project/target/cex-0.0.1-SNAPSHOT.jar /app/rps.jar
ENTRYPOINT ["java","-jar","/app/rps.jar"]