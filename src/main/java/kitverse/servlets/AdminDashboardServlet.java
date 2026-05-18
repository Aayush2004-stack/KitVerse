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
     * Handles HTTP GET requests for the admin dashboard.
     *
     * This method performs the following operations:
     * <ul>
     * <li>Retrieves summary statistics from the DAO layer.</li>
     * <li>Sets the retrieved data as request attributes.</li>
     * <li>Forwards the request to the admin dashboard view
     * (adminDashboard.jsp).</li>
     * </ul>
     *
     * @param request the HttpServletRequest object containing client request
     * data
     * @param response the HttpServletResponse object used to send data back to
     * the client
     * @throws ServletException if a servlet-specific error occurs during
     * request processing
     * @throws IOException if an input or output error occurs during request
     * handling
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
        request.setAttribute("totalOrders", dao.getTotalOrders());
        request.setAttribute("totalRevenue", dao.getTotalRevenue());
        request.setAttribute("pendingOrders", dao.getOrdersByStatus("PENDING"));
        request.setAttribute("deliveredOrders", dao.getOrdersByStatus("DELIVERED"));
        request.setAttribute("cancelledOrders", dao.getOrdersByStatus("CANCELLED"));
        request.setAttribute("todayOrders", dao.getTodayOrders());
        request.setAttribute("todayRevenue", dao.getTodayRevenue());
        request.setAttribute("weeklyRevenue", dao.getWeeklyRevenue());

        request.getRequestDispatcher("/WEB-INF/pages/adminDashboard.jsp")
                .forward(request, response);
    }

}
