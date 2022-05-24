Created with `Java 17.0.3.1` and `Spring Boot 2.7.0`

API tested with `Postman`

Can be launched using `Maven 3.8.5` with command:

```
mvn clean spring-boot:run
```

Examples of API calls with `curl 7.83.1 for Windows`:

- Create task:

```
curl.exe -H "Content-Type: application/json" -d '{\"base\": \"2\",\"exponent\": \"4\"}' http://localhost:8080/tasks
```

- Check status and result of given task:

```
curl.exe http://localhost:8080/tasks/1
```

- Check status and result of all tasks:

```
curl.exe http://localhost:8080/tasks
```
