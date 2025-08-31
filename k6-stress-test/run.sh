#!/bin/bash

echo "Building project..."
./mvnw clean install

echo "Start Project..."
docker-compose build
docker-compose up