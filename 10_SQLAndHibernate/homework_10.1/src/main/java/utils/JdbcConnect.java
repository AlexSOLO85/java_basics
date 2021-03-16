package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JdbcConnect {
    private final static String URL = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=Asia/Yekaterinburg";
    private final static String USER = "root";
    private final static String PASSWORD = "test";

    private JdbcConnect() {
    }

    public static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}