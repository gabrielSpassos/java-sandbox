#!/bin/bash

curl -X POST "http://localhost:8080/v1/orders" \
  -H "Content-Type: application/json" \
  -d '{
    "productName": "notebook",
    "amount": 199.90
  }'