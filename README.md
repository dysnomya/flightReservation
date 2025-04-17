# System rezerwacji lotów

## Co zrobiłam?
- Stworzyłam system rezerwacji lotów w oparciu o architekturę REST API.
- Użyłam frameworka Spring Boot do stworzenia backendu aplikacji, obsługującego CRUD (Create, Read, Update, Delete) dla lotów, pasażerów i rezerwacji. 
- Klasa Flight została rozbudowana o możliwość przypisania miejsc siedzących, co pozwala na monitorowanie dostępności poszczególnych miejsc w samolocie.
- Klasa Reservation umożliwia tworzenie rezerwacji, przypisując pasażerowi miejsce, lot i umożliwiając późniejsze modyfikacje.
- Dodałam walidację dla danych wejściowych, a także odpowiednią obsługę błędów i wyjątków.
- Wykonałam parę testów jednostkowych z wykorzystaniem biblioteki JUnit 5 oraz mockito dla klasy FlightService.
- Dodałam funkcjonalność ustawiania we wszystkich rezerwacjacg danego lotu, że wylot się odbył.

## Jak uruchomić projekt?

Aby uruchomić system rezerwacji lotów na swoim komputerze, postępuj według jednej z poniższych opcji:

### 1. Instalacja zależności i uruchomienie aplikacji

W terminalu katalogu głównego aplikacji (/flightReservation) uruchom poniższe polecenie:
```bash
.\mvnw clean install
```

Następnie użyj poniższego polecenia, aby uruchomić aplikację:
```bash
.\mvnw spring-boot:run
```

### 2. Uruchomienie przez plik jar ()

Znajdując się w katalogu, w którym znajduje się plik jar, uruchom poniższe polecenie:
```bash
java -jar flightReservation-1.0.0.jar
```


## Jak używać aplikacji?
Po uruchomieniu aplikacji (zarówno przez plik JAR, jak i Mavena), aplikacja będzie dostępna lokalnie pod adresem [http://localhost:8080](http://localhost:8080)
<br>
Możesz teraz testować REST API za pomocą narzędzi takich jak Postman czy curl. Poniżej przedstawione są wszystkie opcje użycia, jakie zawiera aplikacja.

## Loty (`/flights`)

### Edge Cases

#### Edge Case 1. Usunięcie lotu, który ma już przypisane rezerwacje

W przypadku, gdy użytkownik próbuje usunąć lot, który ma przypisane rezerwacje, system usunie wszystkie rezerwacje związane z danym lotem

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

| Endpoint                              | Opis                                                |
|---------------------------------------|-----------------------------------------------------|
| `POST /flights`                       | Tworzy nowy lot na podstawie przekazanych danych.   |
| `POST /flights/{FLIGHT NUMBER}/seats` | Dodaj siedzenie do lotu wskazanego przez numer lotu |

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

**Przykładowe dane wejściowe (`JSON`) dla dodawania siedzeń:**
```JSON
{
    "seatNumber": "Z3"
}
```
**Przykładowe wykonanie curl:**
```bash
curl -X POST http://localhost:8080/flights/NY1001/seats \
  -H "Content-Type: application/json" \
  -d '{
    "seatNumber": "Z3"
}'
```


### PUT

| Endpoint                                | Opis                                            |
|-----------------------------------------|-------------------------------------------------|
| `PUT /flights/{FLIGHT NUMBER}`          | Aktualizuje dane lotu częściowo lub całkowicie. |

**Przykładowe dane wejściowe (`JSON`)**
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

| Endpoint                                | Opis                          |
|-----------------------------------------|-------------------------------|
| `DELETE /flights/{FLIGHT NUMBER}`       | Usuwa lot o podanym numerze.  |
| `DELETE /flights/{FLIGHT NUMBER}/seats` | Usuwa podane siedzenie z lotu |

**Przykładowe wykonanie curl:**
```bash
curl -X DELETE http://localhost:8080/flights/NY1001
```

**Przykładowe dane wejściowe (`JSON`) dla usuwania siedzeń:**
```JSON
{
    "seatNumber": "Z3"
}
```
**Przykładowe wykonanie curl:**
```bash
curl -X DELETE http://localhost:8080/flights/NY1001/seats \
  -H "Content-Type: application/json" \
  -d '{
    "seatNumber": "Z3"
}'
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

### Edge Cases

#### Edge Case 1. Rezerwacja na lot, który już się odbył

Jeśli użytkownik próbuje zarezerwować miejsce na locie, który już się odbył,
aplikacja powinna zwrócić błąd 400 Bad Request z odpowiednim komunikatem. Na przykład:
```terminaloutput
Flight has already departed...
```

### Edge Case 2. Rezerwacja na już zajęte miejsce

Jeśli użytkownik próbuje zarezerwować miejsce, które jest już zajęte przez inną osobę,
system powinien zwrócić błąd z informacją, że dane miejsce jest niedostępne. Przykładowy komunikat:

```terminaloutput
Constraint violation: Duplicate or invalid reference.
```

### Edge Case 3. Próba anulowania rezerwacji na locie, który już się odbył
Jeśli użytkownik próbuje anulować rezerwację na locie, który już się odbył,
system powinien zwrócić błąd, informując, że rezerwacje na przeszłe loty nie mogą zostać anulowane.

```terminaloutput
You cannot cancel a reservation on a flight that has already departed
```

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

| Endpoint                                     | Opis                                                                                                        |
|----------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| `PUT /reservations/{ID}`                     | Umożliwia aktualizację danych rezerwacji, np. zmiana miejsca, lotu, pasażera czy tego czy pasażer wyleciał. |
| `PUT /reservations/departed/{FLIGHT NUMBER}` | Ustawia we wszystkich rezerwacjach danego lotu, że wylot się odbył                                          |

**Przykładowe dane wejściowe (`JSON`):**
```JSON
{
  "seatNumber": "B1"
}
```
**Przykładowe wykonanie curl:**
```bash
curl -X PUT http://localhost:8080/reservations/1 \
  -H "Content-Type: application/json" \
  -d '{
  "seatNumber": "B1"
}'
```

**Przykładowe wykonanie dla ustawiania odbycia lotu:**
```bash
curl -X PUT http://localhost:8080/reservations/departed/NY1001
```


### DELETE

| Endpoint                    | Opis                                                           |
|-----------------------------|----------------------------------------------------------------|
| `DELETE /reservations/{ID}` | Usuwa rezerwację o podanym numerze. Miejsce zostaje zwolnione. |

**Przykładowe wykonanie curl:**
```bash
curl -X DELETE http://localhost:8080/reservations/1
```
