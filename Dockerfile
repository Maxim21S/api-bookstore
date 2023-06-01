FROM gradle:8.1.1-jdk17-alpine AS cache
RUN mkdir -p /home/gradle/cache
ENV GRADLE_USER_HOME /home/gradle/cache
WORKDIR /home/gradle/code
COPY build.gradle ./
RUN gradle dependencies

FROM gradle:8.1.1-jdk17-alpine AS build
COPY --from=cache /home/gradle/cache /home/gradle/.gradle
WORKDIR /home/gradle/code
COPY src ./src
COPY build.gradle settings.gradle ./
RUN gradle build

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /home/gradle/code/build/libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
