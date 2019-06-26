package part3.lesson15.task03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part3.lesson15.connectors.ConnectorDB;
import part3.lesson15.dao.UserDao;
import part3.lesson15.entity.User;

import java.sql.Connection;

/**
 * Сделать параметризированную выборку по login_ID и name одновременно
 */
public class Main {

    public static void main(String[] args) {
        try (Connection connection = ConnectorDB.getConnection()) {
            UserDao userDao = new UserDao(connection);
            User user = userDao.getUser("name1", "login1");
            System.out.println("User = " + user);
        } catch (Exception e) {
            Logger logger = LogManager.getLogger(Main.class);
            logger.error("Can't connect to DB", e);
        }
    }
}
