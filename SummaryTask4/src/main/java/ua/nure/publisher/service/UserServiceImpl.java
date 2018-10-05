package ua.nure.publisher.service;

import ua.nure.publisher.dao.UserDAO;
import ua.nure.publisher.entity.User;
import ua.nure.publisher.transaction.TransactionPool;

import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl implements UserService {

    /**
     * Business layer for user
     */

    @Override
    public User tryToLogin(String login, String password) {

        return transactionPool.execute(() -> userDAO.getByLoginAndPassword(login, password));
    }


    private TransactionPool transactionPool;
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO, TransactionPool transactionPool) {
        this.userDAO = userDAO;
        this.transactionPool = transactionPool;
    }


    /**
     * Update user.
     *
     * @param entity to update user
     * @return user`s id
     */

    @Override
    public int update(User entity) throws SQLException {
        return transactionPool.execute(() -> userDAO.update(entity));
    }

    /**
     * Get user by id.
     *
     * @param id to find user
     * @return user
     */

    @Override
    public User getUserById(int id) throws SQLException {
        return transactionPool.execute(() -> userDAO.getUserById(id));
    }

    /**
     * Ban user
     * @param userId to detect user
     * @param userBan to set ban
     */
    @Override
    public void changeBanUser(int userId, int userBan) {
        transactionPool.execute(() -> {
            User user = userDAO.getUserById(userId);
            user.setBan(userBan == 1);
            userDAO.update(user);
            return null;
        });
    }
    /**
     * Get all users from database.
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        return transactionPool.execute(() -> userDAO.getAllUsers());
    }
    /**
     * Get user by login.
     * @param login to find user by login
     * @return if user is exists
     */
    @Override
    public boolean isExist(final String login) {
        User user = transactionPool.execute(() -> userDAO.getByLogin(login));
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Create user.
     * @param user
     * @return new user`s id
     */
    @Override
    public int create(User user) throws SQLException {
        return transactionPool.execute(() -> userDAO.create(user));

    }
    /**
     * Get user role by user id.
     * @param id to find user role
     * @return user`s role
     */
    public String getRoleByUserId(int id) {
        return transactionPool.execute(() -> userDAO.getRoleByUserId(id));

    }
    /**
     * Get user by login.
     * @param login to find user by login
     * @return user
     */
    @Override
    public User getUserByLogin(String login) {
        return transactionPool.execute(() -> userDAO.getByLogin(login));
    }

}

