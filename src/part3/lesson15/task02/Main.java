package part3.lesson15.task02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part3.lesson15.connectors.ConnectorDB;
import part3.lesson15.dao.UserDao;
import part3.lesson15.entity.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 */
public class Main {

    private static UserDao userDao;

    public static void main(String[] args) {
        try (Connection connection = ConnectorDB.getConnection()) {
            userDao = new UserDao(connection);
            insertUser();
            insertUsers();
        } catch (Exception e) {
            Logger logger = LogManager.getLogger(Main.class);
            logger.error("Can't connect to DB", e);
        }
    }

    /**
     * a)      Используя параметризированный запрос
     */
    private static void insertUser() {
        User user = new User();
        user.setName("name1");
        user.setLoginID("login1");
        userDao.insertUser(user);
    }

    /**
     * b)      Используя batch процесс
     */
    private static void insertUsers() {
        List<User> userList = new ArrayList<>();
        for (int i = 2; i <= 10; i++) {
            User user = new User();
            user.setName("name" + i);
            user.setLoginID("login" + i);
            userList.add(user);
        }
        userDao.insertUsers(userList);
    }
}
