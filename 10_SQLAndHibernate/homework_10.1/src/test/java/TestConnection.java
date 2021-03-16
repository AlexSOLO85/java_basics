import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.JdbcConnect;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestConnection {
    private static Connection connection;

    @Before
    public void init() throws SQLException {
        connection = JdbcConnect.getNewConnection();
    }

    @Test
    @DisplayName("Проверка подключения к БД")
    public void should_get_jdbc_connection() throws SQLException {
        try(Connection connection = JdbcConnect.getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }
}