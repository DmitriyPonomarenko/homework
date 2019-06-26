package part3.lesson15.task04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part3.lesson15.connectors.ConnectorDB;
import part3.lesson15.dao.UserDao;
import part3.lesson15.dao.UserRoleDao;
import part3.lesson15.entity.Role;
import part3.lesson15.entity.RoleName;
import part3.lesson15.entity.User;

import java.sql.Connection;
import java.sql.Savepoint;

/**
 * Перевести connection в ручное управление транзакциями
 * Выполнить 2-3 SQL операции на ваше усмотрение, между sql операциями установить точку сохранения (SAVEPOINT A)
 */
public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectorDB.getConnection()) {
            UserDao userDao = new UserDao(connection);
            connection.setAutoCommit(false);
            User user = new User();
            user.setName("name11");
            user.setLoginID("login11");
            userDao.insertUser(user);
            user = userDao.getUser(user.getName(), user.getLoginID());
            Savepoint savepointA = connection.setSavepoint("A");
            //намеренно ввести некорректные данные на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
            user.setId(0);
            Role role = new Role(RoleName.CLIENTS);
            UserRoleDao userRoleDao = new UserRoleDao(connection);
            if (!userRoleDao.insertUserRole(user, role)) {
                connection.rollback(savepointA);
            }
            connection.commit();
        } catch (Exception e) {
            Logger logger = LogManager.getLogger(Main.class);
            logger.error("DB error", e);
        }
    }
}
