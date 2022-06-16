FROM eclipse-temurin:17
MAINTAINER genix.es
COPY genix-admin.jar genix-admin.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "genix-admin.jar"]
