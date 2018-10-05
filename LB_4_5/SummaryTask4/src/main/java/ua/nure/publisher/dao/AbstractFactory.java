package ua.nure.publisher.dao;

public interface AbstractFactory {
    /**
     * DAO Factory
     *
     */
    UserDAO getUserDAO();
    MagazineDAO getMagazineDAO();
    OrderDAO getOrderDAO();
}
