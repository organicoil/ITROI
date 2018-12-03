package ua.nure.publisher.dao;

import ua.nure.publisher.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {


    /**
     * DAO for user
     *
     */

    int create(User user) throws SQLException;

    User getByLogin(String login) throws SQLException;

    User getByLoginAndPassword(String login, String password) throws SQLException;

    String getRoleByUserId(int id) throws SQLException;

    int update(User user) throws SQLException;

    User getUserById(int id) throws SQLException;

    List<User> getAllUsers();
}
