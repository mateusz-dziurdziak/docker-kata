FROM java:8u111-jdk

COPY src/main/resources/quotes.txt /quotes/
COPY build/libs/step_5.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]