package itroi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface BookService {

    @WebMethod(action = "getBook")
    Book getBook(@WebParam(name = "id") int id);

    @WebMethod(operationName = "getAllBooks", action = "getAllBooks")
    List<Book> getBooks();

    @WebMethod(operationName = "getBooksByName", action = "getBooksByName")
    List<Book> getBooks(@WebParam(name = "bookName") String bookName);

    @WebMethod(action = "addBook")
    int addBook(@WebParam(name = "book") Book book);

    @WebMethod(action = "removeBook")
    int removeBook(@WebParam(name = "id") int id);

    @WebMethod(action = "updateBook")
    void updateBook(@WebParam(name = "book") Book book);
}
