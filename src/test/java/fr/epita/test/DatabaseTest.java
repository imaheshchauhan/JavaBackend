package fr.epita.test;
import fr.epita.service.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection connection = DBConnection.getConnection();
            System.out.println("Database connection established");

            // Execute a test query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            while (resultSet.next()) {
                System.out.println("Result: " + resultSet.getInt(1));
            }

            // Close the database connection
            connection.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            System.err.println("Error: Failed to connect to the database");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
