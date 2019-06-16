package part3.lesson15.task03;

import part3.lesson15.connectors.ConnectorDB;
import part3.lesson15.dao.UserDao;
import part3.lesson15.entity.User;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = ConnectorDB.getConnection()) {
            UserDao userDao = new UserDao(connection);
            User user = userDao.getUser("name1", "login1");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
