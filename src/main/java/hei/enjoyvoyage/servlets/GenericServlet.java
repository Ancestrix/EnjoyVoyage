package hei.enjoyvoyage.servlets;

import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class GenericServlet extends HttpServlet {

    protected TemplateEngine createTemplateEngine(ServletContext servletContext) {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new Java8TimeDialect());

        return templateEngine;
    }
    protected String connectUser(HttpServletRequest req, WebContext context) {
        // is user connected ?
        String idUser = (String) req.getSession().getAttribute("connectedUser");
        boolean isUserConnected = !(idUser == null || idUser.equals(""));
        context.setVariable("isUserConnected", isUserConnected);

        // get current user information
        if (isUserConnected) {
            User userInfo = UserService.getInstance().getUserInfo(idUser);
            context.setVariable("userInfo", userInfo);
        }
        return idUser;
    }
}
