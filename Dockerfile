# Use a imagem OpenJDK 11 como base
FROM openjdk:18-jdk-slim AS build



# Defina o diretório de trabalho como /app
WORKDIR /app

# Execute o comando para construir o seu projeto (caso necessário)
RUN ./gradlew build
# RUN mvn package (se estiver usando Maven)

# Copie o arquivo JAR do seu projeto para dentro do contêiner
COPY target/gestao-residuos-automacao-1.0.0.jar app/gestao-residuos-automacao-1.0.0.jar

# Defina a porta que o contêiner vai expor
EXPOSE 8080

# Comando a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "gestao-residuos-automacao-1.0.0.jar"]
