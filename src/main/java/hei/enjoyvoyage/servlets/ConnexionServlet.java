package hei.enjoyvoyage.servlets;


import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext context = new WebContext(request,response,request.getServletContext());
        TemplateEngine templateEngine =  createTemplateEngine((request.getServletContext()));
        templateEngine.process("connexion",context,response.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("mail");
        String mdp = req.getParameter("mdp");

        if(UserService.getInstance().checkPassword(email, mdp)){
            String userId = UserService.getInstance().getUserIdFromEmail(email);
            req.getSession().setAttribute("connectedUser", userId);
            resp.sendRedirect("accueil");


        } else {
            System.out.println("Email ou mdp incorrect ");
            resp.sendRedirect("connexion");
        }
        this.doGet(req, resp);
    }
}
