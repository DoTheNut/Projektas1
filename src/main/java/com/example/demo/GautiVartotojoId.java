package com.example.demo;

import java.sql.*;

public class GautiVartotojoId {

    private static final String URL = "jdbc:mysql://localhost:3306/dienynas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static int gautiVartotojoId(String vartotojoVardas, String slaptazodis) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT vartotojo_id FROM vartotojai WHERE username = ? AND password = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vartotojoVardas);
            statement.setString(2, slaptazodis);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("vartotojo_id");
            }

            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
