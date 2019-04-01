Simple poc API using micronaut with java to register expenses

* Java 8
* H2
* Lombok

```GET  http://localhost:8080/expense/```

```GET  http://localhost:8080/expense/{id}```

```POST  http://localhost:8080/expense --data ""```

```DELETE  http://localhost:8080/expense/{id}```

```PUT  http://localhost:8080/expense/{id}```


curl -s -X POST -H 'Accept: application/json' -H 'Content-Type: application/json' --data '{"username":"sherlock","password":"password"}' http://localhost:8080/login
