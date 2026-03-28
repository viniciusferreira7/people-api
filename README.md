# people-api

> ⚠️ **Work In Progress** - This project is currently under active development.

A REST API for managing people, built with Quarkus and MySQL.

## Tech Stack

- **Quarkus 3.32.3** — Supersonic Subatomic Java Framework
- **Hibernate ORM with Panache** — persistence via active record pattern
- **MySQL** — relational database
- **SmallRye OpenAPI** — API documentation with Swagger UI
- **Jackson** — JSON serialization

## Endpoints

| Method | Path              | Description          |
|--------|-------------------|----------------------|
| POST   | `/api/users`      | Create a user        |
| GET    | `/api/users`      | List users (paginated) |
| GET    | `/api/users/{id}` | Find user by UUID    |

### Query parameters for `GET /api/users`

| Param       | Default | Description              |
|-------------|---------|--------------------------|
| `page`      | `1`     | Page number (1-based)    |
| `page_size` | `10`    | Number of items per page |

## Running in dev mode

Requires Docker (Dev Services will start a MySQL container automatically).

```shell
./mvnw quarkus:dev
```

- API: <http://localhost:8080/api/users>
- Swagger UI: <http://localhost:8080/q/swagger-ui>
- Dev UI: <http://localhost:8080/q/dev>

## Packaging

```shell
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

Über-jar:

```shell
./mvnw package -Dquarkus.package.jar.type=uber-jar
java -jar target/*-runner.jar
```

## Native executable

```shell
./mvnw package -Dnative
# or, without GraalVM installed:
./mvnw package -Dnative -Dquarkus.native.container-build=true
./target/people-api-1.0.0-SNAPSHOT-runner
```
