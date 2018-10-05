package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Order;
import ua.nure.publisher.service.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddToCart.class);

    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {

        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String min_mounth = request.getParameter("min_mounth");
        String max_mounth = request.getParameter("max_mounth");
        int id = Integer.parseInt(request.getParameter("id"));
        Magazine magazine = magazineService.getMagazineById(id);
        LOG.debug(id);
        LOG.debug(min_mounth);
        LOG.debug(max_mounth);
        int maxMonth = Integer.parseInt(max_mounth);
        int minMonth = Integer.parseInt(min_mounth);
        int diff = maxMonth - minMonth;
        int min_year = Integer.parseInt(request.getParameter("min_year"));
        int max_year = Integer.parseInt(request.getParameter("max_year"));
        Order order = new Order();
        order.setSecondYear(max_year);
        order.setSecondMounth(max_mounth);
        order.setFirstYear(min_year);
        order.setFirstMounth(min_mounth);
        order.setMagazine_id(Integer.parseInt(request.getParameter("id")));
        double totalPrice = magazine.getPrice() * magazine.getQuantityInMounth() * diff;
        order.setPrice(totalPrice);
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        if (orders == null) {
            orders = new ArrayList<>();
            request.getSession().setAttribute("orders", orders);
        }
        orders.add(order);
        response.sendRedirect("/MainPage");

    }
}

