package hei.enjoyvoyage.servlets;


import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.service.HotelService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/p/admin/modifhotel")
public class ModifierHotelServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String hotelId = req.getParameter("id");
        Hotel hotel = HotelService.getInstance().getHotel(Integer.parseInt(hotelId));
        context.setVariable("hotel", hotel);

        connectUser(req, context);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("modifhotel", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String ville = req.getParameter("ville");
        String pays = req.getParameter("pays");
        String description = req.getParameter("description");
        Double prix = Double.parseDouble(req.getParameter("prix"));
        String photo = req.getParameter("photo");
        Integer id = Integer.valueOf(req.getParameter("id"));

        try {
            Hotel modifHotel = new Hotel(id, nom, ville, pays, description, prix, photo);
            Hotel createdHotel = HotelService.getInstance().modifHotel(modifHotel,id);
            resp.sendRedirect(String.format("listhotel", createdHotel.getId()));
        } catch (IllegalArgumentException iae) {
            req.getSession().setAttribute("errorMessage", iae.getMessage());
            resp.sendRedirect("modifhotel");
        }
    }
}


