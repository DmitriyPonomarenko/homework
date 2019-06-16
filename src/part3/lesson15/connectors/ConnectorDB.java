package part3.lesson15.connectors;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorDB {

    public static Connection getConnection() throws IOException, SQLException {
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url, user, password);
        }
    }
}
