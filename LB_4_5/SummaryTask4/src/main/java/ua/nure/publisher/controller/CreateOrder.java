package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.DBException;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Order;
import ua.nure.publisher.entity.User;
import ua.nure.publisher.service.MagazineService;
import ua.nure.publisher.service.OrderService;
import ua.nure.publisher.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/createOrder")
public class CreateOrder extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CreateMagazine.class);
    private MagazineService magazineService;
    private OrderService orderService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        User user = (User) request.getSession().getAttribute("user");
        LOG.debug(user);
        List<Magazine> magazines = new ArrayList<>(orders.size());
        for (int i = 0; i < orders.size(); i++) {
            magazines.add(magazineService.getMagazineById(orders.get(i).getMagazine_id()));
        }
        System.out.println(magazines);

        double price = 0;
        for (Order order : orders) {
            price += order.getPrice();


        }
        if (price < user.getWalletBalance()) {
            user.setWalletBalance(user.getWalletBalance() - price);
            try {
                userService.update(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (Order order : orders) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                order.setDate(dateFormat.format(date));
                order.setUser_id(user.getId());
                order.setStatus("IN_PROGRESS");
                orderService.tryToCreate(magazines, order);

            }
            orders.clear();
            response.sendRedirect("/MainPage");
        } else {
            request.getSession().setAttribute("err2", "Wallet Balance is too low");
            response.sendRedirect("/getMagazines");
        }


    }
}
