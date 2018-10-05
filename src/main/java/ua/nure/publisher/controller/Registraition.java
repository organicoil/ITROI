package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.dao.UserValidator;
import ua.nure.publisher.entity.User;
import ua.nure.publisher.service.UserService;

import javax.servlet.Registration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Registration")
public class Registraition extends HttpServlet {

    private UserValidator userValidator;
    private UserService userService;
    private static final Logger LOG = Logger.getLogger(Registration.class);


    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        userValidator = new UserValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("password_confirmation");
        LOG.debug(login);
        LOG.debug(name);
        LOG.debug(email);
        System.out.println(login);
        String err = userValidator.validate(login, name, surname, email, password, repassword);
        if (err != null) {
            LOG.error(err);
            request.getSession().setAttribute("err", err);
            response.sendRedirect("/registration.jsp");
            return;
        }
        if (userService.isExist(login)) {
            LOG.error("User already exist");
            request.getSession().setAttribute("err", "User already exist");
            response.sendRedirect("/registration.jsp");
            return;
        } else {

            User user = new User();
            user.setEmail(email);
            user.setSurname(surname);
            user.setName(name);
            System.out.println(email);
            user.setPassword(password);
            user.setRole("REGISTR_CLIENT");
            user.setLogin(login);
            user.setBan(false);
            try {
                if (userService.create(user) != 0) {
                    request.getSession().setAttribute("user", user);

                }
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        response.sendRedirect("/MainPage");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}