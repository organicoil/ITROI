package ua.nure.publisher.dao;

import ua.nure.publisher.entity.Magazine;

import java.sql.SQLException;
import java.util.List;

public interface MagazineDAO {

    /**
     * DAO for Magazine
     *
     */

    List<Magazine> getAllMagazines(String name, String price, String theme) throws SQLException;
    List<Magazine> findByName(String search);
    Magazine getMagazineById(int id);
    int create(Magazine magazine) throws SQLException;
    int update(Magazine entity) throws SQLException;
    int delete(Magazine magazine) throws SQLException;
    List<Integer> getMagazineIdByOrderId(int id);

}
