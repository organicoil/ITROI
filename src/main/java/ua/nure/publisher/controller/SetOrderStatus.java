package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SetOrderStatus")
public class SetOrderStatus extends HttpServlet {


    private OrderService orderService;

    private static final Logger LOG = Logger.getLogger(AddToCart.class);

    @Override
    public void init() throws ServletException {

        orderService = (OrderService) getServletContext().getAttribute("orderService");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String status = request.getParameter("status");
        int id_order = Integer.parseInt(request.getParameter("id_order"));
        LOG.debug(status);
        LOG.debug(id_order);
        orderService.changeStatus(status, id_order);
        response.sendRedirect("/admin");
    }
}
