# Rest Hook API
Describes a CRUD service for the management of REST Hooks. The app runs on port 8080. The interaction with it can be done through any REST API client like Postman or curl. 

## API endpoints

### Getting a list of subscriptions `GET /subscriptions`
```
curl -i -H 'Accept: application/json' http://localhost:8080/subscriptions
```

### Getting a subscription by ID `GET /subscriptions/1`
```
curl -i -H 'Accept: application/json' http://localhost:8080/subscriptions/1
```

### Creating a subscription `POST /subscriptions`
```
curl -i -H 'Content-type: application/json' -X POST -d '{"url":"subscriptions/66","topicId":"fsdfdfsdfdsf","hookUri":"http://localhost:8080/subscriptions/66","topicName":"hook.66","topicDescription":"New topic 66"}' http://localhost:8080/subscriptions
```

### Updating a subscription `PUT /subscriptions/1`
```
curl -i -H 'Content-type: application/json' -X PUT -d '{"url":"subscriptions/77","topicId":"777777777","hookUri":"http://localhost:8080/subscriptions/77","topicName":"hook.77","topicDescription":"Topic updated to 77"}' http://localhost:8080/subscriptions/1
```

### Removing a subscription `DELETE /subscriptions/1`
```
curl -i -H 'Accept: application/json' -X DELETE http://localhost:8080/subscriptions/1
```

## Running the app
```
./mvnw spring-boot:run
```

## Running tests
```
./mvnw tests
```

## Running the app with Docker Compose
```
docker compose up
```



