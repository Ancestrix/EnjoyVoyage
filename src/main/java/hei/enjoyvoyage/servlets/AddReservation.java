package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.service.ReservationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/addreservation")
public class AddReservation  extends GenericServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_hotel = req.getParameter("id");
        String id_user = (String) req.getSession().getAttribute("connectedUser");

        ReservationService.getInstance().addReservation(id_user, id_hotel);
    }
}
