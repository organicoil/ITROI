package ua.nure.publisher.dao.MySQL.mapper;

import ua.nure.publisher.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    /**
     * Sets user`s attributes
     *
     */
    public void unMap(User user, PreparedStatement ps) throws SQLException {
        User u = new User();
        int k = 0;
        ps.setInt(++k, user.getId());
        ps.setString(++k, user.getLogin());
        ps.setString(++k, user.getPassword());
        ps.setString(++k, user.getName());
        ps.setString(++k, user.getSurname());
        ps.setString(++k, user.getEmail());
        ps.setString(++k, user.getRole());
        ps.setInt(++k,user.getBan()?1:0);
     //   ps.setInt(++k, user.getWalletBalance());
        //ps.setInt(++k, Integer.parseInt(user.getEmail()));
    }

    public User map(ResultSet rs) throws SQLException {
        User user = new User();
        int k = 0;
        user.setId(rs.getInt(++k));
        user.setName(rs.getString(++k));
        user.setSurname(rs.getString(++k));
        user.setLogin(rs.getString(++k));
        user.setPassword(rs.getString(++k));
        user.setEmail(rs.getString(++k));
        user.setRole(rs.getString(++k));
        user.setBan(rs.getBoolean(++k));
        user.setWalletBalance(rs.getInt(++k));
        return user;
    }
}
