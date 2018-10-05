package ua.nure.publisher.dao.MySQL;

public class Const {

    /**
     * SQL constants
     *
     *
     */

    public static final String GET_MAGAZINES_BY_THEME = "SELECT * FROM magazines WHERE theme=? ";
    public static final String GET_ALL_MAGAZINES = "SELECT * FROM magazines" ;
    public static final String ORDER_BY_PRICE = " ORDER BY price ";
    public static final String ORDER_BY_NAME = " ORDER BY name ";
    public static final String FIND_BY_NAME = "SELECT * FROM magazines WHERE name = ? ";
    public static final String GET_MAGAZINE_BY_ID = "SELECT * FROM magazines WHERE id=?";
    public static final String CREATE_USER = "INSERT INTO users(`name`,`surname`,`login`,`password`,`email`,`role`,`isBan` ) VALUES(?,?,?,?,?,?,?)";
    public static final String GET_ROLE_BY_ID = "SELECT role FROM users WHERE id = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static final String ADD_PRODUCT_TO_ORDER = "INSERT INTO `order_magazine`(`orders_id`,`magazines_id`,`firstMounth`,`secondMounth`, `firstYear`,`secondYear`) VALUES(?,?,?,?,?,?)";
    public static final String CREATE_ORDER = "INSERT INTO `orders`(`order_status`,`date`,`users_id`,`price`, `firstMounth`,`secondMounth`,`firstYear`,`secondYear`) VALUES(?,?,?,?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE `users` SET `login`= ?,`name`= ?,`surname`= ?,`email`= ?, `walletBalance` = ?, `isBan`= ? WHERE id = ?";
    public static final String CREATE_MAGAZINE = "INSERT INTO `magazines`(`name`,`description`,`price`,`quantityInMounth`,`theme`,`image`) VALUES(?,?,?,?,?,?)";
    public static final String GET_ALL_USERS = "SELECT * FROM `users` WHERE role!='ADMIN'";
    public static final String GET_USER_BY_ID = "SELECT * FROM `users` WHERE id= ?";
    public static final String UPDATE_MAGAZINE = "UPDATE `magazines` SET `name`= ?,`description`= ?,`price`= ?,`quantityInMounth`= ?,`image`= ?, `theme` = ? WHERE id = ?";
    public static final String DELETE_MAGAZINE = "DELETE  FROM `magazines` WHERE id= ?";
    public static final String GET_ORDER_BY_ID = " SELECT * FROM `orders`  where `users_id` = ? ";
    public static final String GET_ID_MAGAZINES = "SELECT m.id FROM publisher.magazines m, orders o, order_magazine om where o.id=om.orders_id and om.magazines_id = m.id and o.id = ?";
    public static final String SET_ORDER_STATUS = " UPDATE `orders` SET `order_status` = ? where `id` = ? ";
}

