package ua.nure.publisher.service;

import ua.nure.publisher.dao.OrderDAO;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Order;
import ua.nure.publisher.transaction.TransactionPool;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    /**
     * Business layer for order
     */
    private OrderDAO orderDAO;
    private TransactionPool transactionPool;

    public OrderServiceImpl(OrderDAO orderDAO, TransactionPool transactionPool) {
        this.orderDAO = orderDAO;
        this.transactionPool =  transactionPool;
    }

    @Override
    public int tryToCreate(List<Magazine> magazineList, Order order) {

        return transactionPool.execute(()-> {
            order.setId(orderDAO.create(order));
            System.out.println(order.getId());
            System.out.println(magazineList);
            orderDAO.addMagazinessToOrder(magazineList, order);
            return order.getId();

        });
    }


    /**
     * Get order by id.
     *
     * @param id to get order.
     * @return order
     */
    @Override
    public List<Order> getOrdersByUserId(int id) {
        return transactionPool.execute(()-> orderDAO.getById(id));
    }

    /**
     * set order status
     *
     * @param status to change order status.
     * @param id_order to get order by id
     * @return new status of order
     */
    @Override
    public String changeStatus(String status,int id_order) {
        return transactionPool.execute(()-> orderDAO.setOrderStatus(status,id_order));
    }
}
