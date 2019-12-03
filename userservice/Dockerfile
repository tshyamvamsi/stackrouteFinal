FROM java:8-jre
WORKDIR /usr/src
ENV MYSQL_DATABASE=matchdb
ENV MYSQL_USERNAME=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_URL=jdbc:mysql://localhost:3306/matchdb
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java", "-jar", "userservice-1.0.jar"]
