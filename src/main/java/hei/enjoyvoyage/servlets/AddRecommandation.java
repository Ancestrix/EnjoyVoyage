package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.service.RecoHotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addrecommandation")
public class AddRecommandation extends GenericServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_hotel = req.getParameter("id");

        RecoHotelService.getInstance().addReco(id_hotel);
    }
}