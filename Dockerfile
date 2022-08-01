FROM docker.io/library/openjdk:15.0.1
COPY ./target/forex-rate-negotiation-acl-*-SNAPSHOT.jar /forex-rate-negotiation-acl.jar
EXPOSE 8080
CMD ["java", "-jar", "/forex-rate-negotiation-acl.jar"]