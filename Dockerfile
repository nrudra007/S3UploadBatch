FROM openjdk:17

WORKDIR /app

COPY ./target/S3UploadBatch.jar /app

#EXPOSE 8091
#CMD ["java", "-jar", "S3UploadBatch.jar"]