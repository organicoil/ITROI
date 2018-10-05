package ua.nure.publisher.controller;

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

@WebServlet("/getMagazines")
public class GetMagazines extends HttpServlet {

    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        if (orders.size() != 0) {
            List<Magazine> magazines = new ArrayList<>(orders.size());
            for (int i = 0; i < orders.size(); i++) {
                magazines.add(magazineService.getMagazineById(orders.get(i).getMagazine_id()));
            }

            request.setAttribute("magazines", magazines);
           // request.setAttribute("orders", orders);
        }
        request.getRequestDispatcher("/basket.jsp").forward(request, response);
    }

}


