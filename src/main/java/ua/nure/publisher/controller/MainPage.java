package ua.nure.publisher.controller;


import ua.nure.publisher.DBException;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.service.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/MainPage")
public class MainPage extends HttpServlet {

    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        List<Magazine> magazines;

        String nameSort = request.getParameter("nameSort");
        String priceSort = request.getParameter("priceSort");
        String themeSort = request.getParameter("themeSort");
        String all = request.getParameter("all");
        String search = request.getParameter("search");
        request.setAttribute("search", search);
        request.setAttribute("all", all);
        request.setAttribute("namesort", nameSort);
        request.setAttribute("pricesort", priceSort);
        request.setAttribute("themeSort", themeSort);
        if (search != null && !search.isEmpty()) {
            magazines = magazineService.findByName(search);
        } else {
            magazines = magazineService.getAllMagazines(nameSort, priceSort, themeSort);
        }
        request.setAttribute("magazines", magazines);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

}
