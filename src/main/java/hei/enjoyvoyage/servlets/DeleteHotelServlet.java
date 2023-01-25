package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.service.HotelService;
import hei.enjoyvoyage.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/p/admin/deletehotel")
public class DeleteHotelServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String hotelId = req.getParameter("id");
        Hotel hotel = HotelService.getInstance().getHotel(Integer.parseInt(hotelId));
        context.setVariable("hotel", hotel);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("listhotel", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_hotel = req.getParameter("id");
        HotelService.getInstance().deleteHotel(id_hotel);
    }
}



