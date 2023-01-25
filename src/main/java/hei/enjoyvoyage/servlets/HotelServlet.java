package hei.enjoyvoyage.servlets;


import hei.enjoyvoyage.dao.ReservationDao;
import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.service.HotelService;
import hei.enjoyvoyage.service.RecoHotelService;
import hei.enjoyvoyage.service.ReservationService;
import hei.enjoyvoyage.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/hotel")
public class HotelServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String hotelId = req.getParameter("id");
        Hotel hotel = HotelService.getInstance().getHotel(Integer.parseInt(hotelId));
        context.setVariable("hotel", hotel);

        connectUser(req, context);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("hotel", context, resp.getWriter());
    }

}


