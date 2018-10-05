package ua.nure.publisher.transaction;

import java.sql.Connection;

public class ThreadLocaleHandler {


    /**
     * Set one connection to database
     *
     */

    public static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static void setConnecion(Connection connection){
        threadLocal.set(connection);
    }

    public static Connection getConnection(){
        return threadLocal.get();
    }
}
