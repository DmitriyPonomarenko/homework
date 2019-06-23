package part3.lesson15.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part3.lesson15.entity.Role;
import part3.lesson15.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRoleDao {

    private Connection connection;

    private static Logger logger = LogManager.getLogger(UserRoleDao.class);

    public UserRoleDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertUserRole(User user, Role role) {
        final String INSERT_USER_ROLE = "insert into \"USER_ROLE\"(user_id,role_id)\n" +
                "values(?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_USER_ROLE)) {
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, role.getId());
            boolean result = pstmt.executeUpdate() == 1;
            logger.log(Level.INFO, "insert " + role + " for " + user);
            return result;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't insert user's role", e);
            return false;
        }
    }
}
