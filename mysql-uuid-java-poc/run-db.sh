#!/bin/bash

echo "Create MySQL Container with Podman"
CONTAINER_ID=$(podman run --rm --detach --env MYSQL_ROOT_PASSWORD=pass -p 3306:3306 --name mysql_uuid_poc arm64v8/mysql:latest)
echo "Connect to mysql client: "
echo "mysql -uroot -ppass -h127.0.0.1 -P3306"

sleep 10
echo "Creating person mydb... "
echo "CREATE DATABASE mydb;" | mysql -uroot -ppass -h127.0.0.1 -P3306
echo ".........."

sleep 3
echo "Creating person table.. "
echo "use mydb; CREATE TABLE IF NOT EXISTS person (
      id INT NOT NULL AUTO_INCREMENT,
      uuid VARCHAR(36) NOT NULL,
      first_name VARCHAR(255) NOT NULL,
      last_name VARCHAR(255) NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY(id)
    )" | mysql -uroot -ppass -h127.0.0.1 -P3306

sleep 3
echo "Creating pet table.. "
echo "use mydb; CREATE TABLE IF NOT EXISTS pet (
      id VARCHAR(36) NOT NULL,
      name VARCHAR(255) NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY(id)
    )" | mysql -uroot -ppass -h127.0.0.1 -P3306

echo ".........."

sleep 5
echo "Inserting data into person table... "
echo "use mydb; insert into person (uuid,first_name,last_name) values (uuid(),'Gabriel','Passos');" | mysql -uroot -ppass -h127.0.0.1 -P3306
echo "use mydb; insert into person (uuid,first_name,last_name) values (uuid(),'Joao','Ninguem');" | mysql -uroot -ppass -h127.0.0.1 -P3306

echo "Inserting data into pet table... "
echo "use mydb; insert into pet (id,name) values (uuid(),'Mel');" | mysql -uroot -ppass -h127.0.0.1 -P3306
echo "use mydb; insert into pet (id,name) values (uuid(),'Toby');" | mysql -uroot -ppass -h127.0.0.1 -P3306
