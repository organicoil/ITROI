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
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/AddImage")
public class AddImage extends HttpServlet{


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


        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException{
           Magazine magazine = (Magazine) request.getSession().getAttribute("magazine");

                Part filePart = request.getPart("file");
                InputStream fileContent = null;
                try {
                    fileContent = filePart.getInputStream();
                    byte[] bytes = new byte[fileContent.available()];
                    fileContent.read(bytes);
                    magazine.setImg(bytes);
                } catch (IOException e) {
                    LOG.error(e);
                } finally {
                    if (fileContent != null) {
                        fileContent.close();
                    }
                }
                magazineService.update(magazine);
            response.sendRedirect("/MainPage");
        }
}
