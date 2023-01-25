package hei.enjoyvoyage.filters;

import hei.enjoyvoyage.service.UserService;
import hei.enjoyvoyage.utils.Verification;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/p/admin/*")
public class AdminFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String idUser = (String) httpRequest.getSession().getAttribute("connectedUser");
        boolean isUserConnected = !Verification.isEmpty(idUser);

        if (!isUserConnected) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/accueil");
            return;
        }

        boolean isAdmin = UserService.getInstance().checkIfAdmin(idUser);;
        if (!isAdmin) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/accueil");
            return;
        }
        chain.doFilter(request, response);
    }
}
