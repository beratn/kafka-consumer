FROM ubuntu:latest

RUN \
# Update
apt-get update -y && \
# Install Java
apt-get install default-jre -y

ADD ./target/com.teb.kafka-consumer-1.0-SNAPSHOT.jar kafka-consumer.jar

EXPOSE 8080

CMD java -jar kafka-consumer.jar