FROM eclipse-temurin:21-jdk-jammy

# variables d'environnement
ENV APP_HOME=/app

# Créer un répertoire pour l'application
WORKDIR $APP_HOME

# Exposer le port sur lequel l'application va tourner
EXPOSE 9000

# Installer netcat
RUN apt-get update && apt-get install -y netcat

# Copie du fichier JAR recuperer de l'artefact de votre projet dans le conteneur
COPY api2026-0.0.1-SNAPSHOT.jar /app/app.jar

# copie du script wait for it
# le script permet de s'assurer de la disponibilité de la BDD
COPY infra/script/wait-for-it.sh /app/wait-for-it.sh

# rend le script exécutable
RUN chmod +x /app/wait-for-it.sh

# execution du script, puis de la BDD et enfin de l'API
ENTRYPOINT ["./wait-for-it.sh", "mysql_server", "3306", "java", "-jar", "app.jar"]