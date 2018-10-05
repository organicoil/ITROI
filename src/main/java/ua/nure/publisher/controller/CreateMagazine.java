package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.service.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createMagazine")
public class CreateMagazine extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CreateMagazine.class);

    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.debug(request.getParameter("name"));
        LOG.debug(request.getParameter("alldesc"));
        LOG.debug(request.getParameter("theme"));
        LOG.debug(request.getParameter("quan"));
        LOG.debug(request.getParameter("price"));

        String name = request.getParameter("name");
        String description = request.getParameter("alldesc");
        String theme = request.getParameter("theme");
        System.out.println(request.getParameter("quan"));
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("theme"));
        System.out.println(request.getParameter("alldesc"));
        int quan = Integer.parseInt(request.getParameter("quan"));
        double price = Double.parseDouble(request.getParameter("price"));

        Magazine magazine = new Magazine();
        magazine.setPrice(price);
        magazine.setTheme(theme);
        magazine.setQuantityInMounth(quan);
        magazine.setDescription(description);
        magazine.setName(name);


        int id = magazineService.addMagazine(magazine);

        request.getSession().setAttribute("id",id);
      //  request.getRequestDispatcher("/MainPage").forward(request, response);
        response.sendRedirect("/createMagazine.jsp");
    }
}

