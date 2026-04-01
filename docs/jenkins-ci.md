# Jenkins CI For BloodWave

This guide configures a complete CI pipeline for this repository.

## What The Pipeline Does

1. Checkout source from SCM.
2. Validate Java/Maven/Docker availability.
3. Run unit tests with Maven.
4. Publish JUnit test reports.
5. Generate and archive JaCoCo coverage artifacts.
6. Package the application jar.
7. Build Docker image with 2 tags:
   - latest
   - BUILD_NUMBER-shortGitSha (example: 42-a1b2c3d4)
8. Optionally push image to a registry.

## Jenkins Prerequisites

1. Jenkins LTS installed.
2. JDK 17 available on the Jenkins agent.
3. Docker installed on the Jenkins agent (if image build/push is enabled).
4. Agent user allowed to run Docker commands.
5. Recommended plugins:
   - Pipeline
   - Git
   - JUnit
   - Credentials Binding
   - ANSI Color

## Run Jenkins With Docker Compose

From the project root, start all services (database, redis, app, pgadmin, jenkins):

```bash
docker compose up -d --build
```

Service URLs:

- Jenkins: http://localhost:8081
- Application: http://localhost:8080
- PgAdmin: http://localhost:9090

To stop:

```bash
docker compose down
```

To stop and remove volumes:

```bash
docker compose down -v
```

## Jenkins Credentials

If you want image push, create credentials:

1. Type: Username with password
2. ID: dockerhub-creds (or any ID)
3. Username/password: your registry account

Use the same ID in pipeline parameter DOCKER_CREDENTIALS_ID.

## Pipeline Parameters

- RUN_DOCKER_BUILD: true/false
- PUSH_IMAGE: true/false
- DOCKER_REGISTRY: default docker.io
- DOCKER_REPOSITORY: default bloodwave/bloodwave-app
- DOCKER_CREDENTIALS_ID: default dockerhub-creds

## Job Configuration (Pipeline From SCM)

1. New Item -> Pipeline.
2. Pipeline definition: Pipeline script from SCM.
3. SCM: Git.
4. Repository URL: your BloodWave repository.
5. Branch: your branch (example: */main).
6. Script Path: Jenkinsfile.
7. Save and Build Now.

Alternative (local workspace already mounted in Jenkins container):

1. New Item -> Pipeline.
2. Pipeline definition: Pipeline script.
3. Script: `load 'Jenkinsfile'` is not required.
4. Simply use the default "Pipeline script from SCM" with your Git repo.

## Optional Trigger On Every Push

Choose one:

1. GitHub webhook (recommended).
2. Poll SCM (example: H/5 * * * *).

## Docker Push Setup

If PUSH_IMAGE=true, configure in Jenkins:

1. Manage Jenkins -> Credentials -> Global -> Add Credentials.
2. Kind: Username with password.
3. ID: dockerhub-creds (or match parameter DOCKER_CREDENTIALS_ID).
4. Use your Docker Hub (or registry) account.

In Build with Parameters:

1. Set PUSH_IMAGE=true.
2. Set DOCKER_REGISTRY (example: docker.io).
3. Set DOCKER_REPOSITORY (example: youruser/bloodwave-app).

## Manual Local Equivalent

```bash
./mvnw -B -ntp clean test jacoco:report
./mvnw -B -ntp -DskipTests package
docker build -t bloodwave/bloodwave-app:local -t bloodwave/bloodwave-app:latest .
```

## Expected Outputs

- Test reports: target/surefire-reports
- Coverage site: target/site/jacoco/index.html
- App jar: target/BloodWave-0.0.1-SNAPSHOT.jar
- Docker image: <registry>/<repository>:latest and :<build-sha>

## Troubleshooting

1. Error "docker: command not found" in Jenkins:
   - Rebuild Jenkins service with `docker compose build jenkins`.
2. Error "permission denied /var/run/docker.sock":
   - Ensure Docker daemon is running on host.
   - Ensure compose mounts `/var/run/docker.sock:/var/run/docker.sock`.
3. JUnit reports missing:
   - Verify tests actually ran and `target/surefire-reports` exists.
