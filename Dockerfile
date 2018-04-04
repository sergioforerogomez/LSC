FROM Java:latest
WORKDIR /usr/src/gateway
RUN java -jar server-0.0.1-SNAPSHOT.jar 
ADD server-0.0.1-SNAPSHOT.jar /home/ubuntu/LSC/LSC/server/target/
EXPOSE 12345
