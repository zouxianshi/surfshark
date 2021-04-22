FROM java:15
ADD target/*.jar surfshark.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/surfshark.jar.jar"]