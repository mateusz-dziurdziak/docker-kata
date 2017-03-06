FROM java:8u111-jdk

COPY src/main/resources/quotes.txt /quotes/
COPY build/libs/step_4-all.jar .

VOLUME /quotes

ENTRYPOINT ["java", "-jar", "step_4-all.jar"]
