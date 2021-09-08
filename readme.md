# Estike

Requirements:
- Java 11
- Maven

Run:
```
 ./mvnw spring-boot:run
```
Locally, it uses a H2 database, the file 
is located at `/tmp/estike.db` by default.
It is initialized with some sample data.

See the available endpoints at
`localhost:8080/swagger-ui.html`