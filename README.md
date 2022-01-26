`docker run --name deeplink-converter-db -d  -p 5432:5432  -e "POSTGRES_DB=deeplink-converter-db"  -e "POSTGRES_USER=dbuser" -e "POSTGRES_PASSWORD=fib0112358" postgres:12.2`

`docker run -p 6379:6379 -d --name deeplink-converter-redis redis redis-server`

[Swagger API Document](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
