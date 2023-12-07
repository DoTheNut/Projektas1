package com.example.demo;

import java.sql.*;

public class Autentifikavimas {

    public static String gautiVartotojoRole(String vartotojoVardas, String slaptazodis) {
        String url = "jdbc:mysql://localhost:3306/dienynas";
        String naudotojas = "root";
        String slaptazodisDB = "";

        try (Connection connection = DriverManager.getConnection(url, naudotojas, slaptazodisDB)) {
            String sql = "SELECT role FROM vartotojas WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vartotojoVardas);
            statement.setString(2, slaptazodis);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("role"); // Grąžiname vartotojo rolę
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
