/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package kitverse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kitverse.dao.AdminDashboardDAO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "AdminDashboardServlet", urlPatterns = {"/admin/dashboard"})
public class AdminDashboardServlet extends HttpServlet {

    /**
     * Handles GET request for admin dashboard.
     *
     * This method: 
     * - Calls DAO methods to get summary statistics 
     * - Sets data as request attributes 
     * - Forwards request to adminDashboard.jsp
     *
     * @param request contains request data
     * @param response sends response back to browser
     * @throws ServletException : if servlet error occurs
     * @throws IOException : if input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminDashboardDAO dao = new AdminDashboardDAO();

        request.setAttribute("totalProducts", dao.getTotalProducts());
        request.setAttribute("totalVariants", dao.getTotalVariants());
        request.setAttribute("totalStock", dao.getTotalStock());
        request.setAttribute("lowStock", dao.getLowStockCount());
        request.setAttribute("outOfStock", dao.getOutOfStockCount());

        request.getRequestDispatcher("/WEB-INF/pages/adminDashboard.jsp")
                .forward(request, response);
    }

}
