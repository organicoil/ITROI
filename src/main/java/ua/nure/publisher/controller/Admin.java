package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
public class Admin extends HttpServlet {

    private UserService userService;
    private OrderService orderService;
    private MagazineService magazineService;
    private static final Logger LOG = Logger.getLogger(Admin.class);

    @Override
    public void init() throws ServletException {

        userService = (UserService) getServletContext().getAttribute("userService");
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        List<Magazine> magazines =new ArrayList<>();;

        if (request.getParameter("login") != null && !request.getParameter("login").isEmpty()) {
            request.setAttribute("login", request.getParameter("login"));
            User user = userService.getUserByLogin(request.getParameter("login"));
            if (user != null) {
                users.add(user);
                orders = orderService.getOrdersByUserId(user.getId());
                magazines = new ArrayList<>(orders.size());
                for (int i = 0; i < orders.size(); i++) {
                    List<Integer> magazines_id = magazineService.getMagazinesFromOrders(orders.get(i).getId());
                    for (int id : magazines_id) {
                        magazines.add(magazineService.getMagazineById(id));
                    }
                }
            }
        } else {
            users = userService.getAllUsers();
        }
        request.setAttribute("users", users);
        request.setAttribute("orders", orders);
        request.setAttribute("magazines", magazines);
        request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int userId = Integer.parseInt(request.getParameter("id"));
            LOG.debug(userId);
            if (!userService.getRoleByUserId(userId).equals("ADMIN")) {
                if (request.getParameter("banUser") != null) {
                    int banUser = Integer.parseInt(request.getParameter("banUser"));
                    System.out.println(banUser);
                    userService.changeBanUser(userId, banUser);
                }
            }

        response.sendRedirect("/getUsers");
    }
}
