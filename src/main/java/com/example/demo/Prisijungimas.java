package com.example.demo;

import java.sql.*;

public class Prisijungimas {

    public static boolean patikrintiPrisijungima(String vartotojoVardas, String slaptazodis) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dienynas", "root", "");

            String sql = "SELECT * FROM vartotojai WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vartotojoVardas);
            statement.setString(2, slaptazodis);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                connection.close();
                return true;
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
