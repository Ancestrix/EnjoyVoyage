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
import java.util.List;

@WebServlet("/p/admin/list")
public class UsersListServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<User> listOfUsers = UserService.getInstance().listUsers();
        context.setVariable("usersList", listOfUsers);

        connectUser(req, context);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("list", context, resp.getWriter());
    }
}



