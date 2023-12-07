package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GautiPazymius {

    private static final String URL = "jdbc:mysql://localhost:3306/dienynas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static List<Pazymiai> getPazymiaiByVartotojoVardas(String vartotojoVardas) {
        List<Pazymiai> pazymiai = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM pazymiai WHERE studentas_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vartotojoVardas);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                pazymiai.add(new Pazymiai(
                        resultSet.getDouble("pazymys"),
                        resultSet.getString("studentas_id"),
                        resultSet.getString("kursas_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pazymiai;
    }
}
