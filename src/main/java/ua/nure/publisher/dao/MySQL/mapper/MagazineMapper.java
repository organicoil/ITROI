package ua.nure.publisher.dao.MySQL.mapper;

import ua.nure.publisher.entity.Magazine;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MagazineMapper {
    /**
     * Sets magazine`s attributes
     *
     */
    public Magazine map(ResultSet rs) throws SQLException {
        Magazine magazine = new Magazine();
        magazine.setId(rs.getInt("id"));
        magazine.setName(rs.getString("name"));
        magazine.setDescription(rs.getString("description"));
        magazine.setPrice(rs.getDouble("price"));
        magazine.setQuantityInMounth(rs.getInt("quantityInMounth"));
        magazine.setTheme(rs.getString("theme"));
        magazine.setByteImage(rs.getBytes("image"));
        return magazine;
    }
}
