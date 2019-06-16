package part3.lesson15.dao;

import part3.lesson15.entity.User;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertUser(User user) {
        final String INSERT_USER = "insert into \"USER\"(name,birthday,\"login_ID\",city,email,description)\n" +
                "values(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_USER)) {
            pstmt.setString(1, user.getName());
            Date birthday = null;
            if (user.getBirthday() != null) {
                birthday = new Date(user.getBirthday().getTime());
            }
            pstmt.setDate(2, birthday);
            pstmt.setString(3, user.getLoginID());
            pstmt.setString(4, user.getCity());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getDescription());
            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean insertUsers(List<User> userList) {
        final String INSERT_USER = "insert into \"USER\"(name,birthday,\"login_ID\",city,email,description)\n" +
                "values(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_USER)) {
            for (User user : userList) {
                pstmt.setString(1, user.getName());
                Date birthday = null;
                if (user.getBirthday() != null) {
                    birthday = new Date(user.getBirthday().getTime());
                }
                pstmt.setDate(2, birthday);
                pstmt.setString(3, user.getLoginID());
                pstmt.setString(4, user.getCity());
                pstmt.setString(5, user.getEmail());
                pstmt.setString(6, user.getDescription());
                pstmt.addBatch();
            }
            int[] result = pstmt.executeBatch();
            return Arrays.stream(result).sum() == userList.size();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User getUser(String name, String loginID) {
        User user = null;
        final String SELECT_USER = "select * from \"USER\" where name = ? and \"login_ID\" = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(SELECT_USER)) {
            pstmt.setString(1, name);
            pstmt.setString(2, loginID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(name);
                user.setBirthday(rs.getDate("birthday"));
                user.setLoginID(loginID);
                user.setCity(rs.getString("city"));
                user.setEmail(rs.getString("email"));
                user.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
