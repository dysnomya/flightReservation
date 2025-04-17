# System rezerwacji lotów

## Co zrobiłam?
- Stworzyłam system rezerwacji lotów w oparciu o architekturę REST API.
- Użyłam frameworka Spring Boot do stworzenia backendu aplikacji, obsługującego CRUD (Create, Read, Update, Delete) dla lotów, pasażerów i rezerwacji. 
- Klasa Flight została rozbudowana o możliwość przypisania miejsc siedzących, co pozwala na monitorowanie dostępności poszczególnych miejsc w samolocie.
- Klasa Reservation umożliwia tworzenie rezerwacji, przypisując pasażerowi miejsce, lot i umożliwiając późniejsze modyfikacje.
- Dodałam walidację dla danych wejściowych, a także odpowiednią obsługę błędów i wyjątków.
- Wykonałam obszerne testy jednostkowe z wykorzystaniem biblioteki JUnit 5 oraz mockito dla FlightService oraz FlightController.

## Jak uruchomić projekt?

Aby uruchomić system rezerwacji lotów na swoim komputerze, postępuj według poniższych kroków:

1. Instalacja zależności `./mvnw clean install`
2. Uruchom aplikację Spring Boot: `./mvnw spring-boot:run`
3. Aplikacja powinna wystartować na porcie 8080 (domyślnie).
4. Testowanie aplikacji:
   - Po uruchomieniu serwera możesz zacząć testować endpointy za pomocą Postman lub cURL.
   - Oto przykładowe operacje na REST API:
     - Tworzenie nowego lotu: POST /flights
     - Dodawanie nowego pasażera: POST /passengers
     - Rezerwowanie miejsca: POST /reservations
     - Odczytywanie lotów: GET /flights
     - Usuwanie rezerwacji: DELETE /reservations/{reservationId}

## Loty (`/flights`)

### GET

| Endpoint                      | Przykład                                                | Opis                                            |
|-------------------------------|---------------------------------------------------------|-------------------------------------------------|
| `GET /flights`                | [/flights](http://localhost:8080/flights)               | Zwraca listę wszystkich dostępnych lotów.       |
| `GET /flights{FLIGHT NUMBER}` | [/flights/NY1001](http://localhost:8080/flights/NY1001) | Zwraca szczegóły lotu na podstawie jego numeru. |

Przykładowe wykonania curl:
```bash
curl http://localhost:8080/flights
```

```bash
curl http://localhost:8080/flights/NY1001
```

### POST

| Endpoint      | Opis                                              |
|---------------|---------------------------------------------------|
| `POST /flights` | Tworzy nowy lot na podstawie przekazanych danych. |

**Przykładowe dane wejściowe (`JSON`):**
```JSON
{
    "flightNumber": "NY1001",
    "from": "New York",
    "to": "London",
    "departureDate": "2025-05-16 14:30",
    "durationInMinutes": 420,
    "roundTrip": false,
    "seats": [
        {"seatNumber": "A1"},
        {"seatNumber": "A2"},
        {"seatNumber": "B1"},
        {"seatNumber": "B2"},
        {"seatNumber": "C1"},
        {"seatNumber": "C2"}
    ]
}
```

**Przykładowe wykonanie curl:**
```bash
curl -X POST http://localhost:8080/flights \
  -H "Content-Type: application/json" \
  -d '{
    "flightNumber": "NY1001",
    "from": "New York",
    "to": "London",
    "departureDate": "2025-05-16 14:30",
    "durationInMinutes": 420,
    "roundTrip": false,
    "seats": [
        {"seatNumber": "A1"},
        {"seatNumber": "A2"},
        {"seatNumber": "B1"},
        {"seatNumber": "B2"},
        {"seatNumber": "C1"},
        {"seatNumber": "C2"}
    ]
}'
```

### PUT

| Endpoint                       | Opis                                            |
|--------------------------------|-------------------------------------------------|
| `PUT /flights/{FLIGHT NUMBER}` | Aktualizuje dane lotu częściowo lub całkowicie. |

**Przykładowe dane wejściowe (`JSON`):**
```JSON
{
    "durationInMinutes": 435,
    "roundTrip": true
}
```
**Przykładowe wykonanie curl:**
```bash
curl -X PUT http://localhost:8080/flights/NY1001 \
  -H "Content-Type: application/json" \
  -d '{
    "durationInMinutes": 435,
    "roundTrip": true
  }'
```

### DELETE

| Endpoint                          | Opis                         |
|-----------------------------------|------------------------------|
| `DELETE /flights/{FLIGHT NUMBER}` | Usuwa lot o podanym numerze. |

**Przykładowe wykonanie curl:**
```bash
curl -X DELETE http://localhost:8080/flights/NY1001
```




## PASAŻEROWIE (`/passengers`)

### GET

| Endpoint              | Przykład                                            | Opis                                  |
|-----------------------|-----------------------------------------------------|---------------------------------------|
| `GET /passengers`     | [/passengers](http://localhost:8080/passengers)     | Zwraca listę wszystkich pasażerów.    |
| `GET /passengers{ID}` | [/passengers/3](http://localhost:8080/passengers/3) | Zwraca dane pasażera o określonym ID. |

Przykładowe wykonania curl:
```bash
curl http://localhost:8080/passengers
```

```bash
curl http://localhost:8080/passengers/1
```

### POST

| Endpoint           | Opis                                                    |
|--------------------|---------------------------------------------------------|
| `POST /passengers` | Tworzy nowego pasażera na podstawie przesłanych danych. |

**Przykładowe dane wejściowe (`JSON`):**
```JSON
{
  "name": "Anna",
  "surname": "Kowalska",
  "email": "anna.kowalska@example.com",
  "phoneNumber": "+48123456789"
}
```

**Przykładowe wykonanie curl:**
```bash
curl -X POST http://localhost:8080/passengers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Anna",
    "surname": "Kowalska",
    "email": "anna.kowalska@example.com",
    "phoneNumber": "+48123456789"
}'
```

### PUT

| Endpoint               | Opis                                    |
|------------------------|-----------------------------------------|
| `PUT /passengers/{ID}` | Aktualizuje dane pasażera o podanym ID. |

**Przykładowe dane wejściowe (`JSON`):**
```JSON
{
    "durationInMinutes": 435,
    "roundTrip": true
}
```
**Przykładowe wykonanie curl:**
```bash
curl -X PUT http://localhost:8080/passengers/1 \
  -H "Content-Type: application/json" \
  -d '{
    "email": "nowy.email@example.com",
    "phone": "+48987654321"
  }'
```

### DELETE

| Endpoint                  | Opis                         |
|---------------------------|------------------------------|
| `DELETE /passengers/{ID}` | Usuwa pasażera o podanym ID. |

**Przykładowe wykonanie curl:**
```bash
curl -X DELETE http://localhost:8080/passengers/1
```


## REZERWACJE

### GET

| Endpoint                | Przykład                                                | Opis                                           |
|-------------------------|---------------------------------------------------------|------------------------------------------------|
| `GET /reservations`     | [/reservations](http://localhost:8080/reservations)     | Zwraca listę wszystkich dokonanych rezerwacji. |
| `GET /reservations{ID}` | [/reservations/3](http://localhost:8080/reservations/1) | Zwraca dane konkretnej rezerwacji.             |

Przykładowe wykonania curl:
```bash
curl http://localhost:8080/reservations
```

```bash
curl http://localhost:8080/reservations/1
```

### POST

| Endpoint           | Opis                                                    |
|--------------------|---------------------------------------------------------|
| `POST /reservations` | Tworzy nową rezerwację dla pasażera na wskazany lot i miejsce. System sprawdza, czy miejsce nie jest już zajęte i wysyła maila z potwierdzeniem. |

**Przykładowe dane wejściowe (`JSON`):**
```JSON
{
  "flightNumber": "NY1001",
  "seatNumber": "A2",
  "passengerId": 3,
  "hasDeparted": false
}
```

**Przykładowe wykonanie curl:**
```bash
curl -X POST http://localhost:8080/reservations \
  -H "Content-Type: application/json" \
  -d '{
  "flightNumber": "NY1001",
  "seatNumber": "A2",
  "passengerId": 3,
  "hasDeparted": false
}'
```

### PUT

| Endpoint                 | Opis                                                                                                        |
|--------------------------|-------------------------------------------------------------------------------------------------------------|
| `PUT /reservations/{ID}` | Umożliwia aktualizację danych rezerwacji, np. zmiana miejsca, lotu, pasażera czy tego czy pasażer wyleciał. |

**Przykładowe dane wejściowe (`JSON`):**
```JSON
{
  "seatNumber": "B1"
}
```
**Przykładowe wykonanie curl:**
```bash
curl -X PUT http://localhost:8080/reservations/RES12345 \
  -H "Content-Type: application/json" \
  -d '{
  "seatNumber": "B1"
}'
```

### DELETE

| Endpoint                    | Opis                                                           |
|-----------------------------|----------------------------------------------------------------|
| `DELETE /reservations/{ID}` | Usuwa rezerwację o podanym numerze. Miejsce zostaje zwolnione. |

**Przykładowe wykonanie curl:**
```bash
curl -X DELETE http://localhost:8080/reservations/RES12345
```
