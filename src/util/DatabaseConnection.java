package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void initializeDatabase() {
        String sqlFilePath = "C:/Users/Administrator/IdeaProjects/School Management System/src/resources/images/db/class.sql";

        try (Statement stmt = getConnection().createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));

            String[] sqlStatements = sql.split(";");

            //execute each sql statement INDIVIDUALLY
            for (String sqlStaments : sqlStatements) {
                if(!sqlStaments.trim().isEmpty()){
                    stmt.executeUpdate(sqlStaments.trim());
                }

            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}