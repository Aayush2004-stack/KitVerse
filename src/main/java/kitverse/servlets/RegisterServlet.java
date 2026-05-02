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
import kitverse.utilities.ValidationUtil;

/**
 *
 * @author aayushbastola
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    //Capitalize words
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/register.jsp");
        rd.forward(request, response);
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // get from form and trim
    String firstName = request.getParameter("firstName").trim();
    String lastName = request.getParameter("lastName").trim();
    String email = request.getParameter("email").trim();
    String phone = request.getParameter("phone").trim();
    String password = request.getParameter("password").trim();
    String confirmPassword = request.getParameter("confirmPassword").trim();

    // check valid data
    boolean isValidFirst = !ValidationUtil.isNullOrEmpty(firstName)
            && ValidationUtil.isAlphabetic(firstName);

    boolean isValidLast = !ValidationUtil.isNullOrEmpty(lastName)
            && ValidationUtil.isAlphabetic(lastName);

    boolean isValidEmail = ValidationUtil.isValidEmail(email);

    boolean isValidPhone = ValidationUtil.isValidPhoneNumber(phone);

    boolean isValidPass = ValidationUtil.isValidPassword(password);

    boolean isValidConfirm = ValidationUtil.doPasswordsMatch(password, confirmPassword);

    // Error messages 
    String erFirst = isValidFirst ? "" : "Invalid first name";
    String erLast = isValidLast ? "" : "Invalid last name";
    String erEmail = isValidEmail ? "" : "Invalid email";
    String erPhone = isValidPhone ? "" : "Invalid phone";
    String erPass = isValidPass ? "" : "Weak password";
    String erConfirm = isValidConfirm ? "" : "Passwords do not match";

    // Send errors to JSP
    request.setAttribute("erFirst", erFirst);
    request.setAttribute("erLast", erLast);
    request.setAttribute("erEmail", erEmail);
    request.setAttribute("erPhone", erPhone);
    request.setAttribute("erPass", erPass);
    request.setAttribute("erConfirm", erConfirm);

    // Preserve old values (EXCEPT password)
    request.setAttribute("firstName", firstName);
    request.setAttribute("lastName", lastName);
    request.setAttribute("email", email);
    request.setAttribute("phone", phone);

    // 6. If ANY error → return
    if (!erFirst.isEmpty() || !erLast.isEmpty() || !erEmail.isEmpty()
            || !erPhone.isEmpty() || !erPass.isEmpty() || !erConfirm.isEmpty()) {

        request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
                .forward(request, response);
        return;
    }

    // 7. Process data
    firstName = capitalize(firstName);
    lastName = capitalize(lastName);
    String fullName = firstName + " " + lastName;

    UserDAO userDAO = new UserDAO();
    int check = userDAO.insertUser(fullName, email, phone, password);

    switch (check) {
        case 1:
            response.sendRedirect(request.getContextPath() + "/login");
            break;

        case 2:
            request.setAttribute("error", "User/Email already present!");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
                    .forward(request, response);
            break;

        default:
            request.setAttribute("error", "Server error!");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
                    .forward(request, response);
    }
}
}
