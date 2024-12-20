FROM bitnami/java:11

WORKDIR /

COPY  target/MedicalRecord-service-0.0.1-SNAPSHOT.jar /recordmed.jar

CMD ["java", "-jar", "recordmed.jar"]