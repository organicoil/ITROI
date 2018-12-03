package ua.nure.publisher.dao.MySQL;

import org.apache.log4j.Logger;
import ua.nure.publisher.DBException;
import ua.nure.publisher.dao.MySQL.mapper.OrderMapper;
import ua.nure.publisher.dao.OrderDAO;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Order;
import ua.nure.publisher.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final Logger LOG = Logger.getLogger(OrderDAOImpl.class);
    private OrderMapper orderMapper = new OrderMapper();
    /**
     * add magazines to order.
     *
     * @param magazines,order to add magazine
     * @throws SQLException
     * @return true if magazine was added to order
     */
    @Override
    public boolean addMagazinessToOrder(List<Magazine> magazines, Order order) {
        Connection connection = ThreadLocaleHandler.getConnection();
        boolean result = true;
        try (PreparedStatement ps = connection.prepareStatement(Const.ADD_PRODUCT_TO_ORDER)) {
            for (Magazine entry : magazines) {
                int k = 0;
                ps.setInt(++k, order.getId());
                ps.setInt(++k, entry.getId());
                ps.setString(++k, order.getFirstMounth());
                ps.setString(++k, order.getSecondMounth());
                ps.setInt(++k, order.getFirstYear());
                ps.setInt(++k, order.getSecondYear());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }
        return result;
    }
    /**
     * set order status
     *
     * @param status to change order status.
     * @param id_order to get order by id
     * @return new status of order
     * @throws SQLException
     */
    @Override
    public String setOrderStatus(String status, int id_order) {
        Connection connection = ThreadLocaleHandler.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(Const.SET_ORDER_STATUS)) {
            int k = 0;
            ps.setString(++k, status);
            ps.setInt(++k, id_order);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }
        return status;
    }
    /**
     * Create order.
     *
     * @param entity order to create.
     * @return id of new order
     * @throws SQLException
     */
    @Override
    public int create(Order entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(Const.CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
            int k = 0;
            ps.setString(++k, entity.getStatus());
            ps.setString(++k, entity.getDate());
            ps.setInt(++k, entity.getUser_id());
            ps.setDouble(++k, entity.getPrice());
            ps.setString(++k, entity.getFirstMounth());
            ps.setString(++k, entity.getSecondMounth());
            ps.setInt(++k, entity.getFirstYear());
            ps.setInt(++k, entity.getSecondYear());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                entity.setId(id);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }

        return id;
    }
    /**
     * Get order by id.
     *
     * @param id to get order.
     * @return order
     * @throws SQLException
     */
    @Override
    public List<Order> getById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(Const.GET_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(orderMapper.map(rs));
            }
        } catch (SQLException e) {
           LOG.error(e);
            throw new DBException(e);

        }
        return orders;
    }

}

