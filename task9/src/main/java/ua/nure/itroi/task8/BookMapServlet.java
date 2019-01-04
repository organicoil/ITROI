package ua.nure.itroi.task8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/bookMap")
public class BookMapServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Book, Integer> books = getBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/task9.jsp").forward(request, response);
    }

    private Map<Book, Integer> getBooks() {
        Map<Book, Integer> books = new LinkedHashMap<>();
        books.put(new Book("name1", 111, "author1"), 1);
        books.put(new Book("name2", 222, "author2"), 3);
        books.put(new Book("name3", 333, "author3"), 5);
        books.put(new Book("name4", 444, "author4"), 7);
        return books;
    }

}
