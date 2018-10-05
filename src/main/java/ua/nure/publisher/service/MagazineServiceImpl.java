package ua.nure.publisher.service;

import ua.nure.publisher.dao.MagazineDAO;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.transaction.TransactionPool;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class MagazineServiceImpl implements MagazineService {

    /**
     * Business layer for magazine
     */

    private MagazineDAO magazineDAO;
    private TransactionPool transactionPool;

    public MagazineServiceImpl(MagazineDAO magazineDAO, TransactionPool transactionPool) {
        this.magazineDAO = magazineDAO;
        this.transactionPool = transactionPool;
    }


    /**
     * Get all magazines
     * @param nameSort to find magazine by name
     * @param priceSort to find magazine by price
     * @param themeSort to find magazine by category
     * @return list of magazines
     */
    @Override
    public List<Magazine> getAllMagazines(String nameSort, String priceSort, String themeSort) {
        return parseImagesToBase64(
                transactionPool.execute(() -> magazineDAO.getAllMagazines(nameSort, priceSort, themeSort)));
    }


    /**
     * Parse image to bytes
     * @return magazine
     */
    public Magazine parseImageToBase64(Magazine magazine) {
        if (magazine.getByteImage() != null) {
            magazine.setImgInBase64(Base64.getEncoder().encodeToString(magazine.getByteImage()));
        }
        return magazine;
    }

    @Override
    public int update(Magazine entity) {
        return transactionPool.execute(() -> magazineDAO.update(entity));
    }

    @Override
    public int delete(Magazine magazine) {
        return transactionPool.execute(() -> magazineDAO.delete(magazine));
    }


    /**
     * Create magazine.
     * @param magazine to create magazine
     * @return of new magazine`s id
     */

    @Override
    public int addMagazine(Magazine magazine) {
        return transactionPool.execute(() -> magazineDAO.create(magazine));
    }

    /**
     * Find magazine by name.
     *
     * @param search to find magazine
     * @return list of magazines
     */
    @Override
    public List<Magazine> findByName(String search) {
        return parseImagesToBase64(
                transactionPool.execute(() -> magazineDAO.findByName(search)));
    }

    /**
     * Get magazine by id.
     *
     * @param id to find magazine
     * @return magazine
     */
    @Override
    public Magazine getMagazineById(int id) {
        return parseImageToBase64(
                transactionPool.execute(() -> magazineDAO.getMagazineById(id)));
    }

    /**
     * Parse images to bytes
     * @return list of magazines
     */
    @Override
    public List<Magazine> parseImagesToBase64(List<Magazine> magazines) {
        if (magazines != null && !magazines.isEmpty()) {
            for (Magazine magazine : magazines) {
                parseImageToBase64(magazine);
            }
            return magazines;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Get magazine id by order id.
     *
     * @param id to find magazine id
     * @return list of magazines id
     */

    @Override
    public List<Integer> getMagazinesFromOrders(int id) {
        return transactionPool.execute(() -> magazineDAO.getMagazineIdByOrderId(id));
    }
}

