package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.service.HotelService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/p/admin/addhotel")
public class AddHotelServlet  extends GenericServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        connectUser(req, context);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("addhotel", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String ville = req.getParameter("ville");
        String pays = req.getParameter("pays");
        String description = req.getParameter("description");
        Double prix = Double.parseDouble(req.getParameter("prix"));
        String photo = req.getParameter("photo");



        try {
                Hotel newHotel = new Hotel(null, nom, ville, pays, description, prix, photo);
                Hotel createdHotel = HotelService.getInstance().addHotel(newHotel);
                resp.sendRedirect(String.format("hotel?id=%d", createdHotel.getId()));
            } catch (IllegalArgumentException iae) {
                req.getSession().setAttribute("errorMessage", iae.getMessage());
                resp.sendRedirect("addhotel");
            }
        }
    }



