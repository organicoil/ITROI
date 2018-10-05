package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.dao.UserValidator;
import ua.nure.publisher.entity.User;
import ua.nure.publisher.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/Authorization")
public class Authorization extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Authorization.class);

    private UserService userService;

    private UserValidator userValidator;

    @Override
    public void init() throws ServletException {

        userService = (UserService) getServletContext().getAttribute("userService");
        userValidator = new UserValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            LOG.debug(request.getParameter("login"));
            if(request.getSession().getAttribute("user")!=null){
                request.setAttribute("err", "You are already logged in");
            }
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String err = userValidator.validate(login,password);
            if(err != null){
                LOG.debug(err);
                request.getSession().setAttribute("err",err);
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
                return;
            }
        //byte[] bytesOfPassword = password.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] thedigest = md.digest();
            password = DatatypeConverter.printHexBinary(thedigest);
            System.out.println(password);
        } catch (NoSuchAlgorithmException e) {
            request.getRequestDispatcher("/commonError.jsp").forward(request,response);
            return;
        }
            User user = userService.tryToLogin(login,password);
            if(user == null){
                request.getSession().setAttribute("err","Incorrect login or password");
                request.getSession().setAttribute("login",login);
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
                return;
            }

            request.getSession().setAttribute("user", user);
            response.sendRedirect("/MainPage");



        }
    }



