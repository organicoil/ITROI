package ua.nure.publisher.service;

import ua.nure.publisher.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    /**
     * Service layer for user`s functions
     */
    int create(User user) throws SQLException;

    boolean isExist(final String login);

    User tryToLogin(String login, String password);

    String getRoleByUserId(int id);

    int update(User entity) throws SQLException;

    User getUserById(int id) throws SQLException;

    List<User> getAllUsers();

    void changeBanUser(int userId, int userBan);

    User getUserByLogin(String login);
}
