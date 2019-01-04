package ua.nure.itroi.task8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = getBooks(request);
        request.setAttribute("books", books);
        request.getRequestDispatcher("/task8.jsp").forward(request, response);
    }

    private List<Book> getBooks(HttpServletRequest request) {
        String booksParam = request.getParameter("books");
        if (booksParam != null) {
            String[] booksStrs = booksParam.split("\\|");

            return Arrays.stream(booksStrs)
                    .map(this::parseBook)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private Book parseBook(String bookStr) {
        String[] bookStlit = bookStr.split(";");
        return new Book(bookStlit[0], Double.parseDouble(bookStlit[1]), bookStlit[2]);
    }

}
