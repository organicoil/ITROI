package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/clearCart")
public class ClearCart extends HttpServlet {


    private static final Logger LOG = Logger.getLogger(ClearCart.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> cart = (List<Order>) request.getSession().getAttribute("orders");
        cart.clear();
        LOG.debug("Clearing cart");
        response.sendRedirect("basket.jsp");
    }
}
