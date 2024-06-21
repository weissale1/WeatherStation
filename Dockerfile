FROM arm32v7/eclipse-temurin
ARG JAR_FILE=build/libs/\*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

