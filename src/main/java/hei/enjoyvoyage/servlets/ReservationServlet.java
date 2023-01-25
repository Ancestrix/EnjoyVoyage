package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.service.ReservationService;
import hei.enjoyvoyage.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/p/reservation")
    public class ReservationServlet extends GenericServlet{

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            WebContext context = new WebContext(req,resp,req.getServletContext());
            TemplateEngine templateEngine =  createTemplateEngine((req.getServletContext()));

            String id_user = (String) req.getSession().getAttribute("connectedUser");
            List<Hotel> listOfReservation = ReservationService.getInstance().listReservation(id_user);
            context.setVariable("reservationlist", listOfReservation);


            connectUser(req, context);

            templateEngine.process("reservation",context,resp.getWriter());
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_hotel = req.getParameter("id");
        String id_user = (String) req.getSession().getAttribute("connectedUser");

        ReservationService.getInstance().deleteReservation(id_user, id_hotel);

    }
}
