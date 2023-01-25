package hei.enjoyvoyage.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/p/*")
public class AuthenticationFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String userConnected = (String) httpRequest.getSession().getAttribute("connectedUser");
        boolean isUserConnected = !(userConnected == null || userConnected.equals(""));

        if (!isUserConnected) {
            System.out.println("Please login to access this page.");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("../connexion");
            return;
        }
        chain.doFilter(request, response);
    }
}
