package com.gabrielspassos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnector {

    private final Connection connection;

    public MySqlConnector(String host, String port, String schema, String username, String password)  {
        this.connection = getMySqlConnection(host, port, schema, username, password);
    }

    public boolean insert(String key, String value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MAP (KEY, VALUE) VALUES (?, ?)");
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, value);
            return preparedStatement.execute();
        } catch (Exception e) {
            System.out.println("Failed to insert");
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        try {
            String value = null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MAP WHERE KEY = ?");
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                value = resultSet.getString("VALUE");
            }
            return value;
        } catch (Exception e) {
            System.out.println("Failed to insert");
            throw new RuntimeException(e);
        }
    }

    private Connection getMySqlConnection(String host, String port, String schema, String username, String password) {
        try {
            String connectionUrl = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", host, port, schema);
            return DriverManager.getConnection(connectionUrl, username, password);
        } catch (Exception e) {
            System.out.println("Failed to create mysql connection");
            throw new RuntimeException(e);
        }

    }
}
