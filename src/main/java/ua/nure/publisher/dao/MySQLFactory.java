package ua.nure.publisher.dao;

import ua.nure.publisher.dao.MySQL.MagazineDAOImpl;
import ua.nure.publisher.dao.MySQL.OrderDAOImpl;
import ua.nure.publisher.dao.MySQL.UserDAOImpl;

public class MySQLFactory implements AbstractFactory {

    /**
     * DAO MySQL
     * @return implementations for user,magazine and order
     */


    @Override
    public UserDAO getUserDAO(){
        return new UserDAOImpl();
    }

    @Override
   public MagazineDAO getMagazineDAO(){
        return new MagazineDAOImpl();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }

}
