FROM openjdk:17-oracle
ENV APP_HOME ~/桌面/Garbage_Classifier_Backend
WORKDIR $APP_HOME
#COPY build/libs/*.jar app.jar
EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
CMD ["mvn spring-boot:run"]