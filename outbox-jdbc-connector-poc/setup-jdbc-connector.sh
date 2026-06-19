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
echo "Waiting for Kafka Connect REST API"
echo "======================================"

until curl -s $CONNECT_URL/connectors > /dev/null; do
  echo "Waiting for Kafka Connect..."
  sleep 5
done

echo "======================================"
echo "Waiting for JDBC Plugin"
echo "======================================"

until curl -s $CONNECT_URL/connector-plugins | grep -q "JdbcSourceConnector"; do
  echo "Waiting for JDBC plugin..."
  sleep 5
done

echo "======================================"
echo "Deleting old connector if exists"
echo "======================================"

delete_status=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE \
  $CONNECT_URL/connectors/outbox-source)

if [ "$delete_status" = "204" ]; then
  echo "Old connector deleted"
elif [ "$delete_status" = "404" ]; then
  echo "No existing connector found"
else
  echo "Failed to delete existing connector. HTTP status: $delete_status"
  exit 1
fi

echo "======================================"
echo "Registering JDBC Source Connector"
echo "======================================"

curl -sS --fail-with-body -X POST $CONNECT_URL/connectors \
  -H "Content-Type: application/json" \
  -d @jdbc-source.json

echo ""
echo "======================================"
echo "Connector Registered Successfully"
echo "======================================"
