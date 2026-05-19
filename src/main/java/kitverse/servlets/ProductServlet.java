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
import java.util.List;
import kitverse.dao.ProductDAO;
import kitverse.models.Product;

/**
 *
 * @author aayushbastola
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    private static final int LIMIT = 8; //max 8 product in one page

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO pDAO = new ProductDAO();

        String search = request.getParameter("search");
        String category = request.getParameter("category");
        boolean isSearchMode = search != null && !search.trim().isEmpty();
        boolean isCategoryMode = category != null && !category.trim().isEmpty() && !category.equalsIgnoreCase("All");

        List<Product> products;
        int totalProducts;

        if (isSearchMode) {
            search = search.trim();
            products = pDAO.searchProductByName(search);
            totalProducts = products.size();
            request.setAttribute("mode", "search");
        } else if (isCategoryMode) {
            category = category.trim();
            products = pDAO.getProductsByCategory(category);
            totalProducts = products.size();
            request.setAttribute("mode", "category");

        } else {

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

            products = pDAO.getPaginatedProducts(offset, LIMIT);

            if (products == null) {
                products = new ArrayList<>();
            }

            totalProducts = pDAO.getTotalProducts();
            int totalPages = (int) Math.ceil((double) totalProducts / LIMIT);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
        }
        request.setAttribute("products", products);
        request.setAttribute("search", search);
        request.setAttribute("category", category);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/product.jsp");
        rd.forward(request, response);

    }

}
