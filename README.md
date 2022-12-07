# Bank Management System

This is a simple bank management system that allows you to create a bank account, deposit money, withdraw money, and check your balance.

## How to run

1. Clone the repository
2. Open the project in your IDE
3. Install the dependencies by running `mnv clean install`
4. Run the project
5. Open your browser and go to `localhost:8080/swagger-ui.html` to see the API documentation

## Requirements

- Java 17
- Maven
- PostgresSql

## API Documentation

The API documentation can be found at `localhost:8080/swagger-ui.html`

## Database

The database is a PostgresSql database. The database name is `bank_management_system` and the username is `_yourUserName_` and the password is `_yourPassword_`. You can change the database name, username, and password in the `application.properties` file.

## Endpoints

### Create Customer

`POST /v1/customer`

#### Request

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "address": {
    "addressLine": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "nationalId": "string"
}
```

#### Response

```json
{
  "firstName": "string",
  "lastName": "string",
  "nationalId": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "isActive": true,
  "address": {
    "addressLine": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "accounts": []
}
```

### Update Customer

`PUT /v1/customer/{id}`

#### Request

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "address": {
    "addressLine": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  }
}
```

#### Response

```json
{
  "firstName": "string",
  "lastName": "string",
  "nationalId": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "isActive": true,
  "address": {
    "addressLine": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "accounts": [
    {
      "accountNumber": "string",
      "balance": 0,
      "isActive": true,
      "accountType": "SAVINGS",
      "accountStatus": "ACTIVE",
      "accountLimit": 0,
      "accountLimitType": "DAILY",
      "accountLimitStatus": "ACTIVE",
      "accountLimitAmount": 0,
      "accountLimitStartDate": "2022-12-07",
      "accountLimitEndDate": "2022-12-07"
    }
  ]
}
```

### Delete Customer

`DELETE /v1/customer/{id}`

#### Response

```json
{
  "firstName": "string",
  "lastName": "string",
  "nationalId": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "isActive": false,
  "address": {
    "addressLine": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "accounts": [
    {
      "accountNumber": "string",
      "balance": 0,
      "isActive": false,
      "accountType": "SAVINGS",
      "accountStatus": "INACTIVE",
      "accountLimit": 0,
      "accountLimitType": "DAILY",
      "accountLimitStatus": "INACTIVE",
      "accountLimitAmount": 0,
      "accountLimitStartDate": "2022-12-07",
      "accountLimitEndDate": "2022-12-07"
    }
  ]
}
```

### Get Customer ById

`GET /v1/customer/getCustomerById/{id}`

#### Response

```json
{
  "firstName": "string",
  "lastName": "string",
  "nationalId": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "isActive": true,
  "address": {
    "addressLine": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "accounts": [
    {
      "accountNumber": "string",
      "balance": 0,
      "isActive": true,
      "accountType": "SAVINGS",
      "accountStatus": "ACTIVE",
      "accountLimit": 0,
      "accountLimitType": "DAILY",
      "accountLimitStatus": "ACTIVE",
      "accountLimitAmount": 0,
      "accountLimitStartDate": "2022-12-07",
      "accountLimitEndDate": "2022-12-07"
    }
  ]
}
```

### Get Customer By NationalId

`GET /v1/customer/{nationalId}`

#### Response

```json
{
  "firstName": "string",
  "lastName": "string",
  "nationalId": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "isActive": true,
  "address": {
    "addressLine": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "accounts": [
    {
      "accountNumber": "string",
      "balance": 0,
      "isActive": true,
      "accountType": "SAVINGS",
      "accountStatus": "ACTIVE",
      "accountLimit": 0,
      "accountLimitType": "DAILY",
      "accountLimitStatus": "ACTIVE",
      "accountLimitAmount": 0,
      "accountLimitStartDate": "2022-12-07",
      "accountLimitEndDate": "2022-12-07"
    }
  ]
}
```

### Get All Customers

`GET /v1/customer`

#### Response

```json
[
  {
    "firstName": "string",
    "lastName": "string",
    "nationalId": "string",
    "email": "string",
    "password": "string",
    "phone": "string",
    "isActive": true,
    "address": {
      "addressLine": "string",
      "city": "string",
      "state": "string",
      "country": "string",
      "zipCode": "string"
    },
    "accounts": [
      {
        "accountNumber": "string",
        "balance": 0,
        "isActive": true,
        "accountType": "SAVINGS",
        "accountStatus": "ACTIVE",
        "accountLimit": 0,
        "accountLimitType": "DAILY",
        "accountLimitStatus": "ACTIVE",
        "accountLimitAmount": 0,
        "accountLimitStartDate": "2022-12-07",
        "accountLimitEndDate": "2022-12-07"
      }
    ]
  }
]
```

## Account

### Create Account

`POST /v1/account`

#### Request

```json
{
  "accountType": "SAVINGS",
  "accountLimitType": "DAILY",
  "customerId": 0
}
```

#### Response

```json
{
  "accountNumber": "string",
  "balance": 0,
  "isActive": true,
  "accountType": "SAVINGS",
  "accountStatus": "ACTIVE",
  "accountLimit": 0,
  "accountLimitType": "DAILY",
  "accountLimitStatus": "ACTIVE",
  "accountLimitAmount": 0,
  "accountLimitStartDate": "2022-12-07",
  "accountLimitEndDate": "2022-12-07"
}
```

### Update Account

`PUT /v1/account/{accountNumber}`

#### Request

```json
{
  "accountType": "SAVINGS",
  "accountLimitType": "DAILY"
}
```

#### Response

```json
{
  "accountNumber": "string",
  "balance": 0,
  "isActive": true,
  "accountType": "SAVINGS",
  "accountStatus": "ACTIVE",
  "accountLimit": 0,
  "accountLimitType": "DAILY",
  "accountLimitStatus": "ACTIVE",
  "accountLimitAmount": 0,
  "accountLimitStartDate": "2022-12-07",
  "accountLimitEndDate": "2022-12-07"
}
```

### Delete Account

`DELETE /v1/account/{accountNumber}`

#### Response

```json
{
  "accountNumber": "string",
  "balance": 0,
  "isActive": true,
  "accountType": "SAVINGS",
  "accountStatus": "ACTIVE",
  "accountLimit": 0,
  "accountLimitType": "DAILY",
  "accountLimitStatus": "ACTIVE",
  "accountLimitAmount": 0,
  "accountLimitStartDate": "2022-12-07",
  "accountLimitEndDate": "2022-12-07"
}
```

### Get All Accounts

`GET /v1/account`

#### Response

```json
[
  {
    "accountNumber": "string",
    "balance": 0,
    "isActive": true,
    "accountType": "SAVINGS",
    "accountStatus": "ACTIVE",
    "accountLimit": 0,
    "accountLimitType": "DAILY",
    "accountLimitStatus": "ACTIVE",
    "accountLimitAmount": 0,
    "accountLimitStartDate": "2022-12-07",
    "accountLimitEndDate": "2022-12-07"
  }
]
```

### Get Account By Account Number

`GET /v1/account/{accountNumber}`

#### Response

```json
{
  "accountNumber": "string",
  "balance": 0,
  "isActive": true,
  "accountType": "SAVINGS",
  "accountStatus": "ACTIVE",
  "accountLimit": 0,
  "accountLimitType": "DAILY",
  "accountLimitStatus": "ACTIVE",
  "accountLimitAmount": 0,
  "accountLimitStartDate": "2022-12-07",
  "accountLimitEndDate": "2022-12-07"
}
```

### Add Money To Account

`POST /v1/account/add/{accountNumber}`

#### Response

```json
{
  "accountNumber": "string",
  "balance": 0,
  "isActive": true,
  "accountType": "SAVINGS",
  "accountStatus": "ACTIVE",
  "accountLimit": 0,
  "accountLimitType": "DAILY",
  "accountLimitStatus": "ACTIVE",
  "accountLimitAmount": 0,
  "accountLimitStartDate": "2022-12-07",
  "accountLimitEndDate": "2022-12-07"
}
```

### Withdraw Money From Account

`POST /v1/account/withdraw/{accountNumber}`

#### Response

```json
{
  "accountNumber": "string",
  "balance": 0,
  "isActive": true,
  "accountType": "SAVINGS",
  "accountStatus": "ACTIVE",
  "accountLimit": 0,
  "accountLimitType": "DAILY",
  "accountLimitStatus": "ACTIVE",
  "accountLimitAmount": 0,
  "accountLimitStartDate": "2022-12-07",
  "accountLimitEndDate": "2022-12-07"
}
```

### Transfer Money From Account

`POST /v1/account/transfer/{accountNumber}`

#### Request

```json
{
  "fromAccountNumber": "string",
  "toAccountNumber": "string",
  "amount": 0
}
```

#### Response

```json
{
  "accountNumber": "string",
  "balance": 0,
  "isActive": true,
  "accountType": "SAVINGS",
  "accountStatus": "ACTIVE",
  "accountLimit": 0,
  "accountLimitType": "DAILY",
  "accountLimitStatus": "ACTIVE",
  "accountLimitAmount": 0,
  "accountLimitStartDate": "2022-12-07",
  "accountLimitEndDate": "2022-12-07"
}
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [PostgreSQL](https://www.postgresql.org/) - Database
* [Swagger](https://swagger.io/) - API Documentation
* [Docker](https://www.docker.com/) - Containerization
