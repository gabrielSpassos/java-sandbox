#!/usr/bin/env bash
set -e

echo "🧹 Stopping and removing containers..."
docker compose -f docker-compose.yml down --remove-orphans

echo "🧹 Removing named volumes..."
docker volume rm -f k6-stress-test_java_k6_stress_test_poc_postgres_data || true
docker volume rm -f k6-stress-test_java_k6_stress_test_poc_grafana_data || true

echo "🧹 Removing custom network..."
docker network rm k6-stress-test_java-k6-stress-test-poc || true

echo "✅ Cleanup complete."
