package ua.nure.publisher.dao.MySQL.mapper;

import ua.nure.publisher.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {

    /**
     * Sets order`s attributes
     *
     */
    public Order map(ResultSet rs) throws SQLException {
        int k = 0;
        Order order = new Order();
        order.setId(rs.getInt(++k));
        order.setStatus(rs.getString(++k));
        order.setDate(rs.getString(++k));
        order.setUser_id(rs.getInt(++k));
        order.setPrice(rs.getDouble(++k));
        order.setFirstMounth(rs.getString(++k));
        order.setSecondMounth(rs.getString(++k));
        order.setFirstYear(rs.getInt(++k));
        order.setSecondYear(rs.getInt(++k));
        return order;
    }
}
