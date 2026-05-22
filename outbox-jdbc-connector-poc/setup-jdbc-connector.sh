#!/bin/bash

set -e

CONNECT_CONTAINER="outbox-kafka-connect"
CONNECT_URL="http://localhost:8083"

echo "======================================"
echo "Installing JDBC Connector"
echo "======================================"

docker exec -i $CONNECT_CONTAINER \
  confluent-hub install \
  --no-prompt confluentinc/kafka-connect-jdbc:latest

echo "======================================"
echo "Restarting Kafka Connect"
echo "======================================"

docker restart $CONNECT_CONTAINER

echo "======================================"
echo "Waiting for Kafka Connect to start"
echo "======================================"

until curl -s $CONNECT_URL/connectors > /dev/null; do
  echo "Waiting for Kafka Connect..."
  sleep 5
done

echo "======================================"
echo "Registering JDBC Source Connector"
echo "======================================"

curl -X POST $CONNECT_URL/connectors \
  -H "Content-Type: application/json" \
  -d @jdbc-source.json

echo ""
echo "======================================"
echo "Connector Registered Successfully"
echo "======================================"
