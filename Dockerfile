FROM openjdk:11
ADD ./target/deeplink-converter-1.0.0-SNAPSHOT.jar /usr/src/deeplink-converter-1.0.0-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "deeplink-converter-1.0.0-SNAPSHOT.jar"]