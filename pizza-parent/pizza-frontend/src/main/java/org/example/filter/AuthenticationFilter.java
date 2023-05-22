package org.example.filter;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/xhtml/*"})
public class AuthenticationFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String loginURL = request.getContextPath() + "/login.xhtml";
        String userURL = request.getContextPath() + "/xhtml/user/";
        String adminURL = request.getContextPath() + "/xhtml/admin/";

        boolean loggedIn = (session != null) && (session.getAttribute("user") != null || session.getAttribute("admin") != null);
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        boolean userRequest = request.getRequestURI().startsWith(userURL);
        boolean adminRequest = request.getRequestURI().startsWith(adminURL);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

        if (loggedIn || loginRequest || userRequest || adminRequest || resourceRequest) {
            if (!resourceRequest) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
            }

            if (userRequest && (session == null || session.getAttribute("user") == null)) {
                response.sendRedirect(loginURL);
                return;
            }

            if (adminRequest && (session == null || session.getAttribute("admin") == null)) {
                response.sendRedirect(loginURL);
                return;
            }

            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURL);
        }
    }
}
