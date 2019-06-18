package part3.lesson15.dao;

import part3.lesson15.entity.Role;
import part3.lesson15.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRoleDao {
    private Connection connection;

    public UserRoleDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertUserRole(User user, Role role) {
        final String INSERT_USER_ROLE = "insert into \"USER_ROLE\"(user_id,role_id)\n" +
                "values(?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_USER_ROLE)) {
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, role.getId());
            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
