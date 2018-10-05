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


@WebServlet("/editWallet")
public class EditWallet extends HttpServlet {

    private UserService userService;

    private static final Logger LOG = Logger.getLogger(EditWallet.class);

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String amount = req.getParameter("amount");
        double sum = Double.parseDouble(amount);
        if (sum < 0){
            sum = 0;
            req.getSession().setAttribute("walletMessage", "wrong value");
            res.sendRedirect("wallet.jsp");
        }
        else{
            User user = (User) req.getSession().getAttribute("user");
            System.out.println(user);
            user.setWalletBalance(user.getWalletBalance() + sum);
            try {
                userService.update(user);
            } catch (SQLException e) {
                LOG.error("Failed to update wallet balance for user with login:" + user.getLogin(), e);
            }

            req.getSession().setAttribute("user", user);
            res.sendRedirect("/profile.jsp");
        }
    }

}
