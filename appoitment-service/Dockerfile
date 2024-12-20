FROM bitnami/java:11

WORKDIR /

COPY  target/appointment-service-0.0.1-SNAPSHOT.jar /appointment.jar

CMD ["java", "-jar", "appointment.jar"]