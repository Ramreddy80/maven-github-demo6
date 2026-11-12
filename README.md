# Maven + GitHub + GitHub Actions + Docker Hub CI/CD Pipeline

[![Java CI with Maven and Docker Hub](https://github.com/Ramreddy80/maven-github-demo6/actions/workflows/maven.yml/badge.svg)](https://github.com/Ramreddy80/maven-github-demo6/actions/workflows/maven.yml)

## Project Description

This project demonstrates an end-to-end automated DevOps CI/CD pipeline featuring a Java 17 Maven application, automated testing, containerization with Docker, and continuous integration and deployment with GitHub Actions and Docker Hub.

---

## Technologies Used

- **Java**: OpenJDK 17 (Eclipse Temurin)
- **Build Tool**: Apache Maven 3.9+
- **Testing**: JUnit Jupiter 5.11.0
- **Containerization**: Docker
- **Base Image**: `eclipse-temurin:17-jre-alpine`
- **VCS & Hosting**: GitHub (`Ramreddy80/maven-github-demo6`)
- **CI/CD Automation**: GitHub Actions (`.github/workflows/maven.yml`)
- **Container Registry**: Docker Hub (`ramreddy80/maven-github-demo6`)

---

## Project Structure

```text
maven-github-demo/
├── .github/
│   └── workflows/
│       └── maven.yml          # GitHub Actions CI/CD pipeline
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── App.java      # Application entrypoint
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── AppTest.java  # Unit tests with JUnit 5
├── .dockerignore              # Docker build ignore rules
├── .gitignore                # Git ignore rules
├── Dockerfile                 # Multi-stage/lightweight JRE container definition
├── mvnw                       # Maven wrapper script (Unix)
├── mvnw.cmd                   # Maven wrapper script (Windows)
├── pom.xml                    # Project Object Model configuration
└── README.md                  # Project documentation
```

---

## Requirements

- **Java Development Kit (JDK)**: Java 17 or higher
- **Apache Maven**: Version 3.9+ (or use the included Maven wrapper `./mvnw` / `mvnw.cmd`)
- **Docker**: Docker Engine 20+ (for local container builds)
- **Git**: Version 2.28+

---

## How to Build the Project

Run Maven clean and package to compile, run tests, and generate the executable JAR:

```bash
mvn clean package
```

Or using the Maven wrapper:

```bash
# Linux/macOS
./mvnw clean package

# Windows
.\mvnw.cmd clean package
```

The resulting executable JAR will be located at:
```text
target/maven-github-demo-1.0-SNAPSHOT.jar
```

---

## How to Run the JAR

After packaging, run the executable JAR using Java 17:

```bash
java -jar target/maven-github-demo-1.0-SNAPSHOT.jar
```

Expected output:
```text
=================================================
Hello from Maven GitHub Demo App v1.0-SNAPSHOT!
Java Version : 17.0.x
Java Vendor  : Eclipse Adoptium
OS Name      : ...
Addition Demo: 5 + 7 = 12
Application executed successfully!
=================================================
```

---

## How to Build the Docker Image

To build the Docker image locally:

```bash
docker build -t ramreddy80/maven-github-demo6:1.0 .
docker tag ramreddy80/maven-github-demo6:1.0 ramreddy80/maven-github-demo6:latest
```

---

## How to Run the Docker Container

Run the container using either tag:

```bash
# Run tag 1.0
docker run --rm ramreddy80/maven-github-demo6:1.0

# Run tag latest
docker run --rm ramreddy80/maven-github-demo6:latest
```

---

## Links and Artifacts

- **GitHub Repository**: [https://github.com/Ramreddy80/maven-github-demo6](https://github.com/Ramreddy80/maven-github-demo6)
- **Docker Hub Repository**: [https://hub.docker.com/r/ramreddy80/maven-github-demo6](https://hub.docker.com/r/ramreddy80/maven-github-demo6)
- **Docker Image Name**: `ramreddy80/maven-github-demo6`
- **Docker Tags**: `1.0`, `latest`

---

## GitHub Actions Workflow Explanation

The CI/CD pipeline is defined in `.github/workflows/maven.yml` and consists of two sequential jobs:

1. **`maven-build`**:
   - Triggers on every `push` and `pull_request` targeting the `main` branch.
   - Runs on `ubuntu-latest`.
   - Checks out repository source code via `actions/checkout@v4`.
   - Configures JDK 17 (Temurin) with Maven dependency caching using `actions/setup-java@v4`.
   - Runs `mvn clean package` to compile source code and execute all JUnit 5 test cases.
   - Verifies the existence of `target/maven-github-demo-1.0-SNAPSHOT.jar`.
   - Uploads the validated JAR as an artifact for the downstream job.

2. **`docker-publish`**:
   - Depends explicitly on `maven-build` (`needs: maven-build`).
   - Executes only when the triggering event is a `push` to `main`.
   - Downloads the pre-built JAR from the build stage.
   - Sets up Docker Buildx via `docker/setup-buildx-action@v3`.
   - Authenticates to Docker Hub using GitHub repository secrets:
     - `DOCKERHUB_USERNAME`
     - `DOCKERHUB_TOKEN`
   - Builds and publishes the Docker image tagged with both `1.0` and `latest` using `docker/build-push-action@v6`.

### Configuring Docker Hub Secrets in GitHub

To enable automated Docker Hub publishing, add the following secrets in the GitHub repository:

1. Navigate to: **GitHub Repository → Settings → Secrets and variables → Actions**.
2. Click **New repository secret**.
3. Add:
   - **Name**: `DOCKERHUB_USERNAME` | **Value**: `ramreddy80`
   - **Name**: `DOCKERHUB_TOKEN` | **Value**: `<Your Docker Hub Personal Access Token>`
