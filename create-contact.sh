#!/bin/bash

# Contact Management API - Create Contact Script
# This script creates a new contact using the REST API

# Server base URL
BASE_URL="http://localhost:8080"

# Create a contact
curl -X POST $BASE_URL/api/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phoneNumber": "+1-555-1234",
    "address": "123 Main St",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001"
  }'

echo ""
echo "Contact created successfully!"

