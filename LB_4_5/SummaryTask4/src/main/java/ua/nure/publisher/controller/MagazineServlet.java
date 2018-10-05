package ua.nure.publisher.controller;

import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.service.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MagazineServlet")
public class MagazineServlet extends HttpServlet {

    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Magazine magazine = magazineService.getMagazineById(id);
        request.setAttribute("magazine", magazine);
        request.getRequestDispatcher("/magazine.jsp").forward(request, response);
    }

}
