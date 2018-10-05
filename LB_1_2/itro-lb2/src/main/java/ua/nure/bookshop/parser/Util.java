package ua.nure.bookshop.parser;

import ua.nure.bookshop.entity.Book;
import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.entity.Category;

/**
 * Created by iryna.subota on 12.02.2017.
 */

public class Util {

    public static BookShop createBookShop() {
        BookShop bs = new BookShop();
        Book book = new Book();
        bs.addBook(book);
        book.setId(3);
        book.setTitle("The Emperor's new mind");
        book.addAuthor("Roger Penrose");
        book.setCategory(Category.MONOGRAPH);
        book.setISBN("ISBN-12345-1234");
        book.setPrice(150);
        return bs;
    }
}
