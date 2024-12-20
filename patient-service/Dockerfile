FROM bitnami/java:11

WORKDIR /

COPY  target/patient-service-0.0.1-SNAPSHOT.jar /patient.jar

CMD ["java", "-jar", "patient.jar"]