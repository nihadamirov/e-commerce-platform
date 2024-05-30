FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /target/Euphoria_Ecommerce-0.0.1-SNAPSHOT.jar Euphoria_Ecommerce.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Euphoria_Ecommerce.jar"]