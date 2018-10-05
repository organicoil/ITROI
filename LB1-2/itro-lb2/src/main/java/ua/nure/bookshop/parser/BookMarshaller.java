package ua.nure.bookshop.parser;

import ua.nure.bookshop.entity.BookShop;

import java.io.IOException;

/**
 * Created by iryna.subota on 12.02.2017.
 */
public interface BookMarshaller {

    void marshal(BookShop bookShop, String filePath) throws IOException;
}
