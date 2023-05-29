package org.bastien.addon.localdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBAccess {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.sqlite3");
            } catch (SQLException e) {
                System.err.println("Could not get a connection.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.err.println("Could not close the connection.");
            e.printStackTrace();
        }
    }
}
