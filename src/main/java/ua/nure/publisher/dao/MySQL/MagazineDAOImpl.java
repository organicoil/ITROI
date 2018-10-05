package ua.nure.publisher.dao.MySQL;


import org.apache.log4j.Logger;
import ua.nure.publisher.DBException;
import ua.nure.publisher.dao.MagazineDAO;
import ua.nure.publisher.dao.MySQL.mapper.MagazineMapper;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.publisher.dao.MySQL.Const.ORDER_BY_NAME;
import static ua.nure.publisher.dao.MySQL.Const.ORDER_BY_PRICE;


public class MagazineDAOImpl implements MagazineDAO {

    /**
     * Implementation Magazine DAO
     */

    private static final Logger LOG = Logger.getLogger(MagazineDAOImpl.class);
    private MagazineMapper magazineMapper = new MagazineMapper();


    /**
     * update magazine.
     * @param entity to update magazine
     * @return id of user
     * @throws SQLException
     */
    @Override
    public int update(Magazine entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int key = 0;
        try (PreparedStatement ps = connection.prepareStatement(Const.UPDATE_MAGAZINE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setString(++k, entity.getName());
            ps.setString(++k, entity.getDescription());
            ps.setDouble(++k, entity.getPrice());
            ps.setInt(++k, entity.getQuantityInMounth());
            ps.setBytes(++k, entity.getImg());
            ps.setString(++k, entity.getTheme());
            ps.setInt(++k, entity.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);

        }
        return key;
    }

    /**
     * delete magazine.
     * @param magazine to delete magazine
     * @return id of deleted magazine
     * @throws SQLException
     */

    @Override
    public int delete(Magazine magazine)  {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(Const.DELETE_MAGAZINE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, magazine.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                magazine.setId(id);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }
        return id;
    }
    /**
     * Create magazine.
     * @param entity to create magazine
     * @return of new magazine`s id
     * @throws SQLException
     */

    @Override
    public int create(Magazine entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(Const.CREATE_MAGAZINE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setString(++k, entity.getName());
            ps.setString(++k, entity.getDescription());
            ps.setDouble(++k, entity.getPrice());
            ps.setInt(++k, entity.getQuantityInMounth());
            ps.setString(++k, entity.getTheme());
            ps.setBytes(++k, entity.getImg());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                entity.setId(id);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }
        return id;
    }

    /**
     * Get all magazines or find magazines by parameters.
     *
     * @param name to find magazine by name
     * @param price to find magazine by price
     * @param theme to find magazine by category
     * @return list of magazines
     */

    @Override
    public List<Magazine> getAllMagazines(String name, String price, String theme) throws SQLException {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Magazine> magazines = new ArrayList<>();

        if (theme != null && !theme.isEmpty()) {

            PreparedStatement ps = connection.prepareStatement(Const.GET_MAGAZINES_BY_THEME);
            ps.setString(1, theme);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(magazineMapper.map(rs));
            }
            return magazines;
        }
        if (name != null && !name.isEmpty()) {

            PreparedStatement ps = connection.prepareStatement(Const.GET_ALL_MAGAZINES + ORDER_BY_NAME + name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(magazineMapper.map(rs));
            }
            return magazines;
        }

        if (price != null && !price.isEmpty()) {

            PreparedStatement ps = connection.prepareStatement(Const.GET_ALL_MAGAZINES + ORDER_BY_PRICE + price);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(magazineMapper.map(rs));
            }
            return magazines;
        } else {
            PreparedStatement ps = connection.prepareStatement(Const.GET_ALL_MAGAZINES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(magazineMapper.map(rs));
            }


        }
        return magazines;
    }

    /**
     * Find magazine by name.
     *
     * @param search to find magazine
     * @return list of magazines
     * @throws SQLException
     */
    @Override
    public List<Magazine> findByName(String search) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Magazine> magazines = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(Const.FIND_BY_NAME);
            ps.setString(1, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazines.add(magazineMapper.map(rs));
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }
        return magazines;
    }

    /**
     * Get magazine by id.
     *
     * @param id to find magazine
     * @return magazine
     * @throws SQLException
     */

    @Override
    public Magazine getMagazineById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        Magazine magazine = new Magazine();
        try {
            PreparedStatement ps = connection.prepareStatement(Const.GET_MAGAZINE_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magazine = magazineMapper.map(rs);
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }
        return magazine;
    }

    /**
     * Get magazine id by order id.
     *
     * @param id to find magazine id
     * @return list of magazines id
     * @throws SQLException
     */

    @Override
    public List<Integer> getMagazineIdByOrderId(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Integer> ids = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(Const.GET_ID_MAGAZINES);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
        }
        return ids;
    }
}


