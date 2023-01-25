package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;





@WebServlet("/p/admin/user")
public class ModifierUtilisateurServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String userId = req.getParameter("id");
        User user = UserService.getInstance().getUser(Integer.parseInt(userId));
        context.setVariable("user", user);

        connectUser(req, context);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("user", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String id = req.getParameter("id");

        try {
            UserService.getInstance().modifUser(nom,prenom,email,id);
            resp.sendRedirect("/p/admin/list");
        } catch (IllegalArgumentException iae) {
            req.getSession().setAttribute("errorMessage", iae.getMessage());
            resp.sendRedirect("/p/admin/user");
        }
    }
}
