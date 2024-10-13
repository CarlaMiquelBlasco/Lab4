# Dockerizing PostgreSQL and Building a Dockerized Application

### First Experiment: Using a Dockerized PostgreSQL Application

#### Overview of What I Did, Issues Faced, and Resolutions

**1. What I did:**
   I switched from H2 database to PostgreSQL in the experiment from lab 4: [Code to Lab4](https://github.com/CarlaMiquelBlasco/Lab4)

   To achieve this, I first configured a PostgreSQL Docker container using the following command:

   ```bash
   docker run -p 5432:5432 \
   -e POSTGRES_USER=carlamiquel2001 \
   -e POSTGRES_PASSWORD=password \
   -e POSTGRES_DB=dbtest \
   -d --name my-postgres --rm postgres
   ```

   Then, I updated my `build.gradle.kts` file to include the PostgreSQL JDBC dependency:

   ```kotlin
   implementation("org.postgresql:postgresql:42.7.4")
   ```

   Then, I modified the `persistence.xml` to point to PostgreSQL, updating the dialect, driver, and connection properties:

   ```xml
   <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
   <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
   <property name="hibernate.connection.url" value="jdbc:postgresql://127.0.0.1:5432/dbtest"/>
   <property name="hibernate.connection.username" value="jpa_client"/>
   <property name="hibernate.connection.password" value="secret"/>
   ```
   
   Finally, I configured Hibernate with the following properties in `persistence.xml` to generate the SQL scripts for database initialization:

   ```xml
   <property name="jakarta.persistence.schema-generation.scripts.create-target" value="schema_up.sql"/>
   <property name="jakarta.persistence.schema-generation.scripts.drop-target" value="schema_down.sql"/>
   ```

   By doing all this modifications, I could run the application and the tests with the following:

   ```bash
   ./gradlew run
   ./gradlew test
   ```
   
**2. Issues Faced and Resolutions**:

   **Permission Denied for Table**  
   After the schema was generated, I encountered a permission issue: `ERROR: permission denied for table customer`.  
   **Resolution**: I granted the required privileges for the `jpa_client` user:

   ```sql
   CREATE USER jpa_client WITH PASSWORD 'secret';
   GRANT ALL PRIVILEGES ON DATABASE dbtest TO jpa_client;
   ```

---

### Second Experiment: Building a Dockerized Application

#### Overview of What I Did, Issues Faced, and Resolutions

**1. What I did**: I containerized the Spring Boot application that provides a REST API for a Poll app. That is, I containerized the application from Lab 2: [Code to Lab2](https://github.com/CarlaMiquelBlasco/Lab_2_7)

   I wrote a multi-stage Dockerfile to containerize the application. The Dockerfile used a Gradle image for building the app and a lightweight JRE image for running it.

  The file is the following:

   ```dockerfile
   # Stage 1: Build the application using Gradle
   FROM gradle:7.5.1-jdk17 AS build
   
   # Set the working directory inside the container
   WORKDIR /app
   
   # Copy only the necessary files to cache dependencies
   COPY build.gradle.kts settings.gradle.kts /app/
   
   # Download Gradle dependencies to cache them in this layer
   RUN gradle build --no-daemon || return 0
   
   # Copy the rest of the application code
   COPY . /app/
   
   # Build the Spring Boot application
   RUN gradle bootJar --no-daemon
   
   # Stage 2: Package the application in a minimal image
   FROM eclipse-temurin:17-jre-alpine AS runtime
   
   # Create a non-root user to run the app
   RUN addgroup -S appgroup && adduser -S appuser -G appgroup
   
   # Set the working directory
   WORKDIR /app
   
   # Copy the built JAR file from the build stage
   COPY --from=build /app/build/libs/*.jar /app/app.jar
   
   # Change ownership to the non-root user
   RUN chown -R appuser:appgroup /app
   
   # Switch to the non-root user
   USER appuser
   
   # Expose the port that the Spring Boot application will run on
   EXPOSE 8080
   
   # Start the application
   CMD ["java", "-jar", "/app/app.jar"]
   ```
  
   Explanation:

   - In the first stage, I built the Spring Boot application using Gradle and JDK 17.

   - In the second stage, I copied the JAR file into a lightweight JRE container and configured a non-root user for security.

   Finally, I used the following commands to build and run the docker image and to run the container:

   ```bash
   docker build -t poll-app .
   docker run -p 8080:8080 poll-app
   ```


**2. Issues Faced and Solutions**

   **Java 21 Compatibility**  
   Initially, I developed the app using Java 21, but during containerization, I encountered an error indicating that the Docker runtime only supported Java 17.  
   **Resolution**: I resolved this by downgrading the Java version in the `build.gradle.kts` file to 17:

   ```kotlin
   java {
       toolchain {
           languageVersion.set(JavaLanguageVersion.of(17))
       }
   }
   ```

   After this change, both the build and runtime environments were compatible with Java 17.

