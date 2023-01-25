package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.service.HotelService;
import hei.enjoyvoyage.service.RecoHotelService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/p/admin/ParametreAdmin")
public class ParametreAdminServlet extends GenericServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        connectUser(req, context);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("admin", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            RecoHotelService.getInstance().addReco(id);
            resp.sendRedirect("/p/admin/ParametreAdmin");
        } catch (IllegalArgumentException iae) {
            req.getSession().setAttribute("errorMessage", iae.getMessage());
            resp.sendRedirect("/p/admin/ParametreAdmin");
        }
    }
}



