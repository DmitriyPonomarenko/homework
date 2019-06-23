package part3.lesson15.connectors;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * отвечает за создание соединения с БД
 */
public class ConnectorDB {

    public static Connection getConnection() throws IOException, SQLException {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url, user, password);
        }
    }
}
