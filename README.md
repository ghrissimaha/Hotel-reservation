# Hotel Management API

Spring Boot 3.2, Java 17, Spring Data JPA, Bean Validation, Lombok and MySQL.

## Run

Create (or allow the URL to create) `hotel_db` on MySQL port `3307`, then run:

```shell
mvn spring-boot:run
```

The defaults are `DB_USERNAME=root`, `DB_PASSWORD=root`, and port `8080`. Override
`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, or `PORT` with environment variables.
Hibernate creates the current schema and `data.sql` loads three room types, five
rooms, two employees, three guests, two reservations and two payments.

## API

All endpoints are under `/api`: each of `/room-types`, `/rooms`, `/employees`,
`/guests`, `/reservations`, and `/payments` supports `GET`, `GET /{id}`, `POST`,
`PUT /{id}`, and `DELETE /{id}`. Additional operations are:

- `GET /rooms?checkIn=YYYY-MM-DD&checkOut=YYYY-MM-DD` for available rooms
- `PATCH /rooms/{id}/assign-employee/{employeeId}` for room assignment
- `GET /employees/{id}/rooms` for assigned rooms
- `GET /reservations/guest/{guestId}` for guest reservations
- `POST /reservations/{id}/cancel` and `GET /reservations/{id}/payment`

Dates must be ordered, rooms under maintenance and overlapping active
reservations are rejected. A reservation has at most one payment, and room types
cannot be deleted while rooms reference them. Validation and business failures
are returned consistently by the global exception handler.
