package ua.nure.publisher.dao;

import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {


     /**
      * DAO for order
      *
      */
     boolean addMagazinessToOrder(List<Magazine> magazines, Order order);
     int create(Order entity) throws SQLException;
     List<Order>  getById(int id);
     String setOrderStatus(String status, int id_order);
}
