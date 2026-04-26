# Ecommerce API

## Overview
This project is a simple API made using Spring Boot. It is used to manage products like adding, viewing, updating, and deleting. The data is only stored in memory, so it will reset when the app restarts.

---

## API Endpoints

- GET /api/v1/products – get all products
- GET /api/v1/products/{id} – get product by id
- GET /api/v1/products/filter – filter products
- POST /api/v1/products – add new product
- PUT /api/v1/products/{id} – update product
- PATCH /api/v1/products/{id} – update some fields
- DELETE /api/v1/products/{id} – delete product

---

## HTTP Status Codes

These are the status codes used in the project:

- 200 OK – request is successful
- 201 Created – product is created
- 204 No Content – product is deleted
- 404 Not Found – product not found
- 400 Bad Request – wrong input
- 500 Internal Server Error – server error

These codes help show what happened after sending a request.

---

## Testing

The API was tested using Postman.

Example:
http://localhost:8080/api/v1/products

---

## Note

The data will reset when the application is restarted because it does not use a database.

---

## Authors

- John patrick ofilanda
- Francis anthony aludo 