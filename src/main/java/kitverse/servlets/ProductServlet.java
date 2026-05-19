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
import java.util.ArrayList;
import kitverse.dao.ProductDAO;
import kitverse.models.Product;

/**
 *
 * @author aayushbastola
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private static final int LIMIT = 9; //max 9 product in one page

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO pDao = new ProductDAO();
        
        int page = 1;//page parameter
        
        String pageParam = request.getParameter("page");
        
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        
        int offset = (page - 1) * LIMIT; //calculating offset
        

        ArrayList<Product> products = (ArrayList<Product>) pDao.getPaginatedProducts(offset, LIMIT); //casting list to arraylist
        
        if (products == null) {
            products = new ArrayList<>();
        }
        
        int totalProducts = pDao.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / LIMIT);
        
        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/product.jsp");
        rd.forward(request, response);

    }



}
