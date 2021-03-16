package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class SqlQuery {
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_AVG = "avgMonth";

    private SqlQuery() {
    }

    public static void sqlQuery() {
        String sql = "SELECT name,\n" +
                "AVG(MONTH(Subscriptions.subscription_date))/(TIMESTAMPDIFF(MONTH,\n" +
                "MIN(Subscriptions.subscription_date),\n" +
                "MAX(Subscriptions.subscription_date))) AS avgMonth\n" +
                "FROM Courses\n" +
                "JOIN Subscriptions ON Courses.id = Subscriptions.course_id\n" +
                "GROUP BY Courses.name\n" +
                "ORDER BY Courses.name";
        resultQuery(sql);
    }

    private static void resultQuery(String sql) {
        try (Connection connection = JdbcConnect.getNewConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String courseName = resultSet.getString(COLUMN_LABEL_NAME);
                double avgMonth = Double.parseDouble(resultSet.getString(COLUMN_LABEL_AVG));
                System.out.printf("Курс: %-35s ---> среднее количество покупок в месяце: %.2f\n", courseName, avgMonth);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}