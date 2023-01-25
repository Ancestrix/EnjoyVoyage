package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.service.HotelService;
import hei.enjoyvoyage.service.RecoHotelService;
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
import java.util.List;

@WebServlet("/accueil")
public class  AccueilServlet extends GenericServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        List<Hotel> listOfHotels = HotelService.getInstance().DernierAjoutHotels();
        context.setVariable("hotelsList", listOfHotels);
        List<Hotel> listOfReco = RecoHotelService.getInstance().listReco();
        context.setVariable("RecoList", listOfReco);

        connectUser(req, context);

        TemplateEngine templateEngine =  createTemplateEngine((req.getServletContext()));
        templateEngine.process("accueil",context,resp.getWriter());
    }
}