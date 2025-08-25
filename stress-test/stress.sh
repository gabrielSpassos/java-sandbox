#!/bin/bash

echo "Stress Testing project..."
./mvnw clean gatling:test -Dgatling.conf.file=src/test/resources/gatling.conf
