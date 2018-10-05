package ua.nure.publisher.service;


import ua.nure.publisher.entity.Magazine;

import java.util.List;

public interface MagazineService {

    /**
     * Service layer for magazine`s functions
     */

    List<Magazine> getAllMagazines(String nameSort, String priceSort, String themeSort);

    List<Magazine> parseImagesToBase64(List<Magazine> magazines);

    Magazine parseImageToBase64(Magazine magazine);

    List<Magazine> findByName(String search);

    Magazine getMagazineById(int id);

    int addMagazine(Magazine magazine);

    int update(Magazine entity);

    int delete(Magazine magazine);

    List<Integer> getMagazinesFromOrders(int id);
}
