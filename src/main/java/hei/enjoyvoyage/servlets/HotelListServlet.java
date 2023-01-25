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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/listhotel")
public class HotelListServlet  extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String destination = req.getParameter("destination");
        List<Hotel> listOfHotels = new ArrayList<>();
        if(destination==null){
            listOfHotels = HotelService.getInstance().listHotels();
        }else{
            listOfHotels = HotelService.getInstance().listRechercheHotels(destination);
        }
        connectUser(req, context);
        context.setVariable("hotelsList", listOfHotels);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("listhotel", context, resp.getWriter());
    }
}



