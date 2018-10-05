package ua.nure.publisher.dao.MySQL;

import org.apache.log4j.Logger;
import ua.nure.publisher.dao.MySQL.mapper.UserMapper;
import ua.nure.publisher.dao.UserDAO;
import ua.nure.publisher.entity.User;
import ua.nure.publisher.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    /**
     * Implementation User DAO
     */

    private UserMapper userMapper = new UserMapper();
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);

    /**
     * Update user.
     *
     * @param entity to update user
     * @return user`s id
     * @throws SQLException
     */
    @Override
    public int update(User entity) throws SQLException {
        Connection connection = ThreadLocaleHandler.getConnection();
        int key = 0;
        try (PreparedStatement ps = connection.prepareStatement(Const.UPDATE_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;

            ps.setString(++k, entity.getLogin());
            ps.setString(++k, entity.getName());
            ps.setString(++k, entity.getSurname());
            ps.setString(++k, entity.getEmail());
            ps.setDouble(++k, entity.getWalletBalance());
            ps.setInt(++k, entity.getBan()?1:0);
            ps.setInt(++k, entity.getId());
            System.out.println(ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * Get user by id.
     *
     * @param id to find user
     * @return user
     * @throws SQLException
     */

    @Override
    public User getUserById(int id) throws SQLException {
        Connection connection = ThreadLocaleHandler.getConnection();
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(Const.GET_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = userMapper.map(rs);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new SQLException(e);
        }
        return user;
    }

    /**
     * Get all users from database.
     * @return list of users
     * @throws SQLException
     */

    @Override
    public List<User> getAllUsers() {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(Const.GET_ALL_USERS);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(userMapper.map(rs));
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return users;
    }

    /**
     * Get user role by user id.
     * @param id to find user role
     * @return user`s role
     * @throws SQLException
     */
    @Override
    public String getRoleByUserId(int id) throws SQLException {
        String role = " ";
        Connection connection = ThreadLocaleHandler.getConnection();
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(Const.GET_ROLE_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new SQLException(e);
        }
        return role;
    }

    /**
     * Get user by user id.
     * @param login to find user by login
     * @param password to find user by password
     * @return user
     * @throws SQLException
     */
    @Override
    public User getByLoginAndPassword(String login, String password) throws SQLException {
        Connection connection = ThreadLocaleHandler.getConnection();
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(Const.GET_USER_BY_LOGIN_AND_PASSWORD)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = userMapper.map(rs);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new SQLException(e);
        }
        return user;

    }

    /**
     * Get user by login.
     * @param login to find user by login
     * @return user
     * @throws SQLException
     */
    @Override
    public User getByLogin(String login) throws SQLException {
        Connection connection = ThreadLocaleHandler.getConnection();
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(Const.GET_USER_BY_LOGIN)) {
            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = userMapper.map(rs);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new SQLException(e);
        }
        return user;
    }

    /**
     * Create user.
     * @param user
     * @return new user`s id
     * @throws SQLException
     */
    public int create(User user) throws SQLException {
        Connection connection = ThreadLocaleHandler.getConnection();
        int key = 0;
        try (PreparedStatement ps = connection.prepareStatement(Const.CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setString(++k, user.getName());
            ps.setString(++k, user.getSurname());
            ps.setString(++k, user.getLogin());
            ps.setString(++k, user.getPassword());
            ps.setString(++k, user.getEmail());
            ps.setString(++k, user.getRole());
            ps.setInt(++k, user.getBan()?1:0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
                user.setId(key);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new SQLException(e);
        }
        return key;
    }
}
