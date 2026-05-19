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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO pDao = new ProductDAO();

        ArrayList<Product> products = pDao.fetchAllProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        request.setAttribute("products", products);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/product.jsp");
        rd.forward(request, response);

    }



}
