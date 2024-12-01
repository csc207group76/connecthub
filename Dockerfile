# This configuration is serverless. Can optionally configure a server on apache to keep the server up.

# Stage 1: Build front end
FROM node:20-alpine3.19 AS builder

WORKDIR /frontend

COPY package.json package-lock.json webpack.common.js webpack.prod.js ./
COPY js ./js
COPY styles ./styles

RUN npm ci
RUN npm run build

# Stage 2: Build back end
FROM maven:3.9.9-ibm-semeru-23-jammy AS production

WORKDIR /backend

COPY pom.xml ./
COPY src ./src
COPY --from=builder ./frontend/static /backend/src/main/resources/static

RUN mvn clean package

# Stage 3: Minimizing build
FROM amazoncorretto:17-alpine3.19-jdk AS final

WORKDIR /app

COPY --from=production /backend/target/*.jar /app/WebServer.jar

# Since the application uses Dotenv to access env files, it wants to find a env file at run time.
# However .env files shouldn't be committed, and so I'm not sure if there's a better solution.
# This is a dummy .env file, and the enviornment variables are set in the container.
RUN touch /app/.env

EXPOSE 8080

CMD ["java", "-jar", "WebServer.jar"]