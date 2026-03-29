# Transactions Application

A simple **Spring Boot + Angular** application for viewing and adding financial transactions.

The backend exposes a REST API for managing transactions stored in a CSV file, while the Angular frontend provides a table view and modal form to add new transactions.

---

# 1. Prerequisites

Before running the application, install the following tools.

## Install Java 21

Download and install **Java 21 JDK**.

Verify installation:

```
java -version
```

Expected output should contain:

```
java version "21"
```

---

## Install Node.js (required for Angular)

Download Node.js LTS:

https://nodejs.org

Verify installation:

```
node -v
npm -v
```

---

## Install Angular CLI

Install Angular CLI globally:

```
npm install -g @angular/cli
```

Verify:

```
ng version
```

---

# 2. Clone the Repository

```
git clone <repository-url>
cd <repository-folder>
```

Project structure:

```
backend/     -> Spring Boot API
frontend/    -> Angular application
```

---

# 3. Run the Backend (Spring Boot)

Navigate to the backend folder:

```
cd backend
```

Run the application:

```
./mvnw spring-boot:run
```

Or if Maven is installed:

```
mvn spring-boot:run
```

The API will start on:

```
http://localhost:8080
```

Transactions are stored in:

```
backend/transactions.csv
```

If the file does not exist it will be created automatically.

---

# 4. Run the Frontend (Angular)

Open another terminal and navigate to the frontend folder:

```
cd frontend
```

Install dependencies:

```
npm install
```

Start the Angular development server:

```
ng serve
```

Open the application in your browser:

```
http://localhost:4200
```

---

# 5. API Documentation

Base URL:

```
http://localhost:8080/api/transactions
```

---

## Get All Transactions

**Endpoint**

```
GET /api/transactions
```

**Response Example**

```
[
  {
    "transactionDate": "2026-03-28",
    "accountNumber": "7289-3445-1121",
    "accountHolderName": "John Doe",
    "amount": 150.75,
    "status": "Settled"
  },
  {
    "transactionDate": "2026-03-27",
    "accountNumber": "7289-3445-1121",
    "accountHolderName": "Jane Smith",
    "amount": 200.50,
    "status": "Pending"
  }
]
```

---

## Add Transaction

**Endpoint**

```
POST /api/transactions
```

**Request Body**

```
{
  "accountNumber": "7289-3445-1121",
  "accountHolderName": "John Doe",
  "amount": 150.75,
  "status": "Settled"
}
```

**Response**

```
Transaction added.
```

---

# 6. Testing

## Backend Tests

Navigate to the backend directory:

```
cd backend
```

Run tests:

```
./mvnw test
```

This will execute the JUnit tests for the transaction service.

---

## Manual Testing (API)

You can test the API using:

* Postman
* curl
* browser (GET requests)

Example:

```
curl http://localhost:8080/api/transactions
```

Add transaction:

```
curl -X POST http://localhost:8080/api/transactions \
-H "Content-Type: application/json" \
-d '{
  "accountNumber":"7289-3445-1121",
  "accountHolderName":"John Doe",
  "amount":100.50,
  "status":"Pending"
}'
```

---

## Testing Through the UI

1. Start backend and frontend servers.
2. Open:

```
http://localhost:4200
```

3. Verify functionality:

* Transactions are displayed in the table.
* Click **Add Transaction**.
* Fill in the form fields.
* Submit the form.

Expected behavior:

* Transaction is saved via the API.
* Table refreshes and displays the new transaction.

---

# 7. Notes

* Transactions are persisted in a **CSV file** instead of a database for simplicity.
* The backend performs basic validation on transaction input.
* The frontend communicates with the backend using HTTP REST requests.

---
