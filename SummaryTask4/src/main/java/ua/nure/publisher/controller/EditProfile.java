package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.User;
import ua.nure.publisher.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ProfileEditor")
public class EditProfile extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditProfile.class);
    private UserService userService;

    public void init() throws ServletException {

        userService = (UserService) getServletContext().getAttribute("userService");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");


        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (login != null && !login.isEmpty())
            user.setLogin(login);
        if (name != null && !name.isEmpty())
            user.setName(name);
        if (email != null && !email.isEmpty())
            user.setEmail(email);
        if (surname != null && !surname.isEmpty())
            user.setSurname(surname);
        if (password != null && !password.isEmpty())
            user.setPassword(password);

        try {
            userService.update(user);
            request.getSession().setAttribute("user", user);
        } catch (SQLException ex) {
            LOG.error("cannot update user profile", ex);


        }

        response.sendRedirect("profile.jsp");
    }
}



