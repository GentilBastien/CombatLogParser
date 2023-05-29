package org.bastien.addon.tmp;

import org.bastien.addon.localdb.DBAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StockerToBDD {
    public static void main(String[] args) throws FileNotFoundException {
        Path abilityFile = Path.of("D:\\Users\\basti\\Desktop\\abilities.txt");
        BufferedReader reader = new BufferedReader(new FileReader(abilityFile.toFile()));

        Connection connection = DBAccess.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();

            // Disable auto-commit to enable batch processing
            connection.setAutoCommit(false);

            // Insert multiple rows using batch processing
            String insertQuery = "INSERT INTO abilities (id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            reader.lines()
                    .forEach(line -> {
                                try {
                                    preparedStatement.setLong(1, getId(line));
                                    preparedStatement.setString(2, getName(line));
                                    preparedStatement.addBatch();
                                } catch (SQLException ignored) {
                                }
                            }
                    );
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Batch insert completed successfully.");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Failed to rollback the transaction.");
                ex.printStackTrace();
            }
            System.out.println("Failed to execute the batch insert.");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the statement.");
                e.printStackTrace();
            }
        }
    }

    private static long getId(String source) {
        Pattern pattern = Pattern.compile("\\{(\\d+)}");
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        throw new RuntimeException("PROBLEM -> " + source);
    }

    private static String getName(String source) {
        Pattern pattern = Pattern.compile("\\[(.*?)\\s*\\{\\d+}]");
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("PROBLEM -> " + source);
    }

    record Row(long id, String name) {
    }
}
