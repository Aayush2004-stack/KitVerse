/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package kitverse.servlets;

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
import kitverse.utilities.ValidationUtil;

/**
 *
 * @author aayushbastola
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/profile"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            response.sendRedirect(request.getContextPath());

            return;

        }

        switch (action) {

            case "updateProfile": {
                User user = (User) SessionUtil.getAttribute(request, "user");

                String nameRaw = request.getParameter("fullName");
                String passwordRaw = request.getParameter("password");
                String confirmPasswordRaw = request.getParameter("confirmPassword");

                String name = (nameRaw != null) ? nameRaw.trim() : "";
                String password = (passwordRaw != null) ? passwordRaw.trim() : "";
                String confirmPassword = (confirmPasswordRaw != null) ? confirmPasswordRaw.trim() : "";

                // NAME VALIDATION
                if (ValidationUtil.isNullOrEmpty(name)) {
                    request.setAttribute("error", "Name cannot be empty");
                    request.getRequestDispatcher("/WEB-INF/pages/profile.jsp")
                            .forward(request, response);

                    return;
                }

                // PASSWORD VALIDATION
                if (!password.isEmpty()) {

                    boolean isValidPass = ValidationUtil.isValidPassword(password);
                    boolean isMatch = ValidationUtil.doPasswordsMatch(
                            password,
                            confirmPassword
                    );
                    if (!isValidPass) {

                        request.setAttribute(
                                "error",
                                "Password must contain uppercase, lowercase, number and symbol"
                        );
                        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp")
                                .forward(request, response);

                        return;
                    }

                    if (!isMatch) {
                        request.setAttribute(
                                "error",
                                "Passwords do not match"
                        );

                        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp")
                                .forward(request, response);

                        return;
                    }

                    // HASH PASSWORD
                    String hashedPassword = PasswordUtil.getHashPassword(password);
                    user.setPassword(hashedPassword);
                }

                // UPDATE NAME
                user.setFullName(name);

                // UPDATE DB
                UserDAO userDAO = new UserDAO();
                boolean success = userDAO.updateProfile(user);

                if (success) {
                    SessionUtil.setAttribute(request, "user", user);

                    request.setAttribute(
                            "message",
                            "Profile updated successfully"
                    );

                } else {

                    request.setAttribute(
                            "error",
                            "Failed to update profile"
                    );
                }

                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp")
                        .forward(request, response);

                break;
            }

            default: {

                response.sendRedirect(request.getContextPath() + "/profile");

                break;

            }

        }

    }

}
