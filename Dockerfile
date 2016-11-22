from errordeveloper/oracle-jdk

# Copy your built jar into the docker image
COPY target/leaderboard-*.jar /home/app.jar

# Run it
ENTRYPOINT ["java", "-jar", "/home/app.jar"]
