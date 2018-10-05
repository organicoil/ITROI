package ua.nure.publisher.service;

import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Order;

import java.util.List;

public interface OrderService {

    /**
     * Service layer for order`s functions
     *
     */

    int tryToCreate(List<Magazine> magazines, Order order);

    List<Order> getOrdersByUserId(int id);

    String changeStatus(String status, int id_order);
}
