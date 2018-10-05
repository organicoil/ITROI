package ua.nure.publisher.controller;

import ua.nure.publisher.entity.User;
import ua.nure.publisher.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getUsers")
public class GetUsers extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {

        userService = (UserService) getServletContext().getAttribute("userService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
    }
}