FROM eclipse-temurin:21-jdk-jammy

# definition des arguments pour l'exemple
ARG USERNAME
ARG PASSWORD

# variables d'environnement
ENV APP_HOME=/app

ENV SPRING_PROFILES_ACTIVE=ci

# Créer un répertoire pour l'application
WORKDIR $APP_HOME

# Exposer le port sur lequel l'application va tourner
EXPOSE 9000

# Installer netcat Suppression du cache APT après l’installation de netcat :
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

# Copie du fichier JAR recuperer de l'artefact de votre projet dans le conteneur
COPY api2026-0.0.1-SNAPSHOT.jar /app/app.jar

# copie du script wait for it
# le script permet de s'assurer de la disponibilité de la BDD
COPY infra/script/wait-for-it.sh /app/wait-for-it.sh

# rend le script exécutable
RUN chmod +x /app/wait-for-it.sh

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://api:9000/actuator/health || exit 1

# execution du script, puis de la BDD et enfin de l'API
ENTRYPOINT ["./wait-for-it.sh", "mysql_server", "3306", "java", "-jar", "app.jar"]