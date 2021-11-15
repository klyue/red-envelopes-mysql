FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/local/redenvelopesmysql
ADD ./target/red-envelopes-mysql-0.0.1-SNAPSHOT.jar /usr/local/redenvelopesmysql/red-envelopes-mysql.jar
EXPOSE 8090
CMD ["java", "-jar", "/usr/local/redenvelopesmysql/red-envelopes-mysql.jar"]