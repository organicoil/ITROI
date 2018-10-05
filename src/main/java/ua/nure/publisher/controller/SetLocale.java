package ua.nure.publisher.controller;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class SetLocale extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (isValidRequest(req)) {
            req.getSession().setAttribute("locale", req.getParameter("locale"));
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    private boolean isValidRequest(HttpServletRequest req) {
        String locale = req.getParameter("locale");
        return locale.equals("ru") || locale.equals("en") || locale.equals("uk");
    }
}