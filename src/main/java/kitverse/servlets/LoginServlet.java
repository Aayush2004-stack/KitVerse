/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package kitverse.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kitverse.dao.UserDAO;
import kitverse.models.User;
import kitverse.utilities.PasswordUtil;
import kitverse.utilities.SessionUtil;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
        rd.forward(request, response);
    }
 
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO: handle validation for empty fields  
        String email = request.getParameter("email").trim();
        String typedPassword = request.getParameter("password").trim();
        
        UserDAO userDao = new UserDAO();
        User user = userDao.getUser(email);
        //if no user found in database send error message
        if (user == null) {
            request.setAttribute("error", "Invalid Email or Password");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
            rd.forward(request, response);
        } else {
            String hashedPassword = user.getPassword();
            boolean matched = PasswordUtil.checkPassword(typedPassword, hashedPassword);
            //if user and password matched, redirect to topiclist

            if (matched) {
                   SessionUtil.setAttribute(request, "user", user);
                   if(user.getUserType().equalsIgnoreCase("admin")){
                       response.sendRedirect(request.getContextPath() +"/admin/dashboard");
                   }
                   else{
                       response.sendRedirect(request.getContextPath() +"/product");
                   }
                
                
            } else {
                //if password is mismatched, send error message to login page
                request.setAttribute("error", "user or password mismatch!");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        }

    }
    

}
