package ua.nure.publisher.transaction;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionPool {
    private DataSource ds;

    public TransactionPool(DataSource ds){
        this.ds = ds;
    }

    /**
     * Transaction for database
     *
     */

    /**
     * Execute transaction
     * @param transaction
     * @return result of transaction
     */
    public <T> T execute(Transaction<T> transaction){
        Connection connection = null;
        T result = null;
        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            ThreadLocaleHandler.setConnecion(connection);
            result = transaction.execute();
            connection.commit();
        } catch (SQLException e){
            rollbackConnection(connection);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public void closeConnection(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void rollbackConnection(Connection connection){
        try {
            if (connection != null){
                connection.rollback();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
