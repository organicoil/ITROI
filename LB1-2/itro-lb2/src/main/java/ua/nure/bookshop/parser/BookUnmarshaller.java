package ua.nure.bookshop.parser;

import ua.nure.bookshop.entity.BookShop;

/**
 * Created by iryna.subota on 12.02.2017.
 */
public interface BookUnmarshaller {

    BookShop unmarshal(String filePath);
}
