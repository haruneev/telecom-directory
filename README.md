# Telecom API

This project provides interfaces to manage the phone numbers associated with customers
## Features
* **Get all phone numbers**
* **Get all phone numbers of a customer**
* **Activate a phone number**

## Installation
### Prerequisites
* Java 17 or higher.
* Maven
* Spring Boot.

### 1. Clone the repository:

```
git clone https://github.com/haruneev/telecom-directory.git
cd telecom-directory

```
### 2. Build the project using Maven:

```
mvn clean install
```

### 3. Run the application:

```
mvn spring-boot:run
```

The application will be up and running at http://localhost:8080
Once the application is running on http://localhost:8080, you can use **Swagger UI** (http://localhost:8080/swagger-ui/index.html) to explore and test the 
available API endpoints listed below.You can find the interface spec at http://localhost:8080/telecom-spec

## API Endpoints

| **HTTP Method** | **Endpoint**                 | **Description**                                       | **Example**          |
|-----------------|------------------------------|-------------------------------------------------------|----------------------|
| **GET**         | `/phoneNumbers`              | Get all phone numbers.                               | /phoneNumbers        |
| **GET**         | `/phoneNumbers/{customerName}` | Get all phone numbers of a specific customer.         | /phoneNumbers/George |
| **POST**        | `/activate/{phoneNumber}`    | Activate a phone number.                             | /activate/0987654390 |

Below is the sample data used in this project:

## Phone Numbers

The following table shows the phone numbers associated with each customer:

| **Customer Name** | **Phone Numbers**                  |
|-------------------|------------------------------------|
| **Alex**          | 0987654390, 0127654391, 0137654392 |
| **George**        | 0988888399, 0127684400             |
| **Fabio**         | 1122334455                         |


