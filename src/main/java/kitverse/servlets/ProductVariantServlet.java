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
import java.util.ArrayList;
import kitverse.dao.ProductDAO;
import kitverse.dao.ProductVariantDAO;
import kitverse.models.Product;
import kitverse.models.ProductVariant;

/**
 *
 * @author aayushbastola
 */
@WebServlet(name = "ProductVariantServlet", urlPatterns = {"/variant"})
public class ProductVariantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductVariantDAO vDao = new ProductVariantDAO();
        String productIdParam = request.getParameter("productId");

        if (productIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/product");
            return;

        }
        int productId = Integer.parseInt(productIdParam);
        ArrayList<ProductVariant> variants
                = vDao.getVariantsByProductId(productId);
        ProductDAO pDao = new ProductDAO();
        Product product = pDao.getProductDetails(productId);
        ProductVariant defaultVariant = null;

        for (ProductVariant v : variants) {
            if (v.getStock() > 0) {
                if (defaultVariant == null
                        || v.getSellingPrice() < defaultVariant.getSellingPrice()) {
                    defaultVariant = v;

                }

            }

        }

        request.setAttribute("product", product);

        request.setAttribute("variants", variants);
        request.setAttribute("selectedVariant", defaultVariant);
        String error = request.getParameter("error");

        if (error != null) {

            request.setAttribute("error", "Insufficient stock.");

        }

        // CUSTOMER PAGE
        request.getRequestDispatcher("/WEB-INF/pages/productVariant.jsp")
                .forward(request, response);

    }

}
