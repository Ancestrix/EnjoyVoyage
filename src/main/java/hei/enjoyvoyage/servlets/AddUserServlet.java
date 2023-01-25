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
import java.io.IOException;

@WebServlet("/inscription")
public class AddUserServlet extends GenericServlet {

    @Override


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        connectUser(req, context);

        TemplateEngine engine = createTemplateEngine(req.getServletContext());


        String userConnected = (String) req.getSession().getAttribute("connectedUser");
        boolean isUserConnected = !(userConnected == null || userConnected.equals(""));

        if(!isUserConnected) {
            engine.process("inscription",context,resp.getWriter());
        } else {
            resp.sendRedirect("accueil");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String mail = req.getParameter("mail");
        String mdp = req.getParameter("mdp");

        try {
            User newUser = new User(null, nom, prenom, mail, mdp, false);
            User createdUser = UserService.getInstance().addUser(newUser);
            resp.sendRedirect(String.format("connexion", createdUser.getId()));
        } catch(IllegalArgumentException iae) {
            req.getSession().setAttribute("errorMessage", iae.getMessage());
            resp.sendRedirect("inscription");
        }
    }

}

