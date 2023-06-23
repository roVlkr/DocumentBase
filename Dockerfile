FROM eclipse-temurin:17-jre-alpine
VOLUME /upload
ENV UPLOAD_FOLDER_PATH = /upload
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
