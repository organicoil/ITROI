package ua.nure.publisher.transaction;


import java.sql.SQLException;

/**
 * Interface Transaction with generic method
 *
 */

public interface Transaction<T> {
    T execute() throws SQLException;
}
