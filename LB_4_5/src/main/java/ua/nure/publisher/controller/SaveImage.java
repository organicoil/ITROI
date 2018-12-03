package ua.nure.publisher.controller;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.service.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/saveImage")
public class SaveImage extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CreateMagazine.class);

    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {
        super.init();
        magazineService = (MagazineService) getServletContext().getAttribute("magazineService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Magazine magazine =(Magazine) request.getSession().getAttribute("magazine");
        LOG.debug(magazine);
        Part filePart = request.getPart("file");
        InputStream filecontent = null;
        try {
            filecontent = filePart.getInputStream();
            byte[] bytes = new byte[filecontent.available()];
            filecontent.read(bytes);
            magazine.setImg(bytes);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);

        } finally {
            if (filecontent != null) {
                filecontent.close();
            }

        }
        magazineService.addMagazine(magazine);



        response.sendRedirect("/MainPage");



    }
}


