package itroi;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@WebService(endpointInterface = "itroi.BookService")
public class BookServiceImpl implements BookService {

    private AtomicInteger nextId = new AtomicInteger();
    private final List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        String address = "http://localhost:10000/BookService";
        Endpoint.publish(address, new BookServiceImpl());
    }

    @Override
    public Book getBook(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Book> getBooks(String bookName) {
        return books.stream().filter(book -> book.getName().equals(bookName)).collect(Collectors.toList());
    }

    @Override
    public int addBook(Book book) {
        int id = nextId.incrementAndGet();
        book.setId(id);
        synchronized (books) {
            books.add(book);
        }
        return id;
    }

    @Override
    public int removeBook(int id) {
        synchronized (books) {
            return books.remove(getBook(id)) ? 0 : id;
        }
    }

    @Override
    public void updateBook(Book book) {
        synchronized (books) {
            if (books.stream().filter(book1 -> book.getId() == book1.getId()).count() == 1) {
                books.remove(getBook(book.getId()));
                books.add(book);
            }
        }
    }
}
