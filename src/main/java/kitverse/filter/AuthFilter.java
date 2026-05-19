/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package kitverse.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kitverse.models.User;
import kitverse.utilities.SessionUtil;

/**
 *
 * @author aayushbastola
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // Cast the request and response to HttpServletRequest and HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Get the requested URI
        String uri = req.getRequestURI();

        String context = req.getContextPath();

        //Allow static files to pass in pages; else file may not load
        if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }
        boolean isPublic = uri.endsWith("/login")
                || uri.endsWith("/register")
                || uri.endsWith("/")
                || uri.endsWith("/aboutUs")
                || uri.endsWith("/contactUs");
                
        User user = (User) SessionUtil.getAttribute(req, "user");

        boolean isLoggedIn = user != null;
        if (!isLoggedIn) {

            if (isPublic) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(context + "/login");
            }
            return;

        }
        String role = user.getUserType(); // admin / normal
        boolean adminOnly
                = uri.contains("/admin") || uri.contains("/upload");

        
        //restrict normal users from admin sites
        if (adminOnly && !role.equalsIgnoreCase("admin")) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;

        }


        chain.doFilter(request, response);
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

}
