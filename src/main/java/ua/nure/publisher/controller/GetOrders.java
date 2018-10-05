package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Order;
import ua.nure.publisher.entity.User;
import ua.nure.publisher.service.MagazineService;
import ua.nure.publisher.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/getOrders")
public class GetOrders extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GetOrders.class);

    private OrderService orderService;
    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {

        orderService = (OrderService) getServletContext().getAttribute("orderService");
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders2 = orderService.getOrdersByUserId(user.getId());
        LOG.debug(orders2);
        List<Magazine> magazines = new ArrayList<>(orders2.size());
        for (int i = 0; i < orders2.size(); i++) {
            List<Integer> magazines_id = magazineService.getMagazinesFromOrders(orders2.get(i).getId());
            for (int id : magazines_id) {
                magazines.add(magazineService.getMagazineById(id));
            }
        }

        System.out.println(magazines);
        request.getSession().setAttribute("magazines", magazines);
        request.setAttribute("orders2", orders2);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
