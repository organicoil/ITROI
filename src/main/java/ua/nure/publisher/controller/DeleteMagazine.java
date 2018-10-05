package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.DBException;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.service.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteMagazine")
public class DeleteMagazine extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditProfile.class);
    private MagazineService magazineService;

    public void init() throws ServletException {

        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        int id = Integer.parseInt(request.getParameter("id"));
        LOG.debug(id);
        Magazine magazine = magazineService.getMagazineById(id);
        magazineService.delete(magazine);
        response.sendRedirect("/MainPage");
    }
}
