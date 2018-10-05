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


@WebServlet("/EditMagazine")
public class EditMagazine extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditProfile.class);
    private MagazineService magazineService;

    public void init() throws ServletException {

        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Magazine magazine = magazineService.getMagazineById(id);
        //System.out.println(magazine);
        request.getSession().setAttribute("magazine", magazine);
        request.getRequestDispatcher("/editMagazine.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {

        Magazine magazine = (Magazine) request.getSession().getAttribute("magazine");
        LOG.debug(magazine);
        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String description = request.getParameter("description");
        int quan = Integer.parseInt(request.getParameter("quantityInMounth"));
        double price = Double.parseDouble(request.getParameter("price"));

        if (theme != null && !theme.isEmpty())
            magazine.setTheme(theme);
        if (name != null && !name.isEmpty())
            magazine.setName(name);
        if (description != null && !description.isEmpty())
            magazine.setDescription(description);
        if (quan != 0)
            magazine.setQuantityInMounth(quan);
        if (price != 0)
            magazine.setPrice(price);
        magazineService.update(magazine);
        response.sendRedirect("/MainPage");
    }
}
