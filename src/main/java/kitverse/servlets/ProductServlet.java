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
import java.time.LocalDateTime;
import java.util.ArrayList;
import kitverse.dao.ProductDAO;
import kitverse.models.Product;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");

        ProductDAO pDao = new ProductDAO();

        switch (action) {

            case "new": {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productadd.jsp");
                rd.forward(request, response);
                break;
            }

            case "edit": {
                int productId = Integer.parseInt(request.getParameter("productid"));

                Product product = pDao.getProductDetails(productId);

                request.setAttribute("product", product);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productadd.jsp");
                rd.forward(request, response);
                break;
            }

            case "admin": {
                ArrayList<Product> products = pDao.fetchAllProducts();

                if (products == null) {
                    products = new ArrayList<>();
                }

                request.setAttribute("products", products);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productlist.jsp");
                rd.forward(request, response);
                break;
            }
            default:{
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Product.jsp");
                rd.forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");

        ProductDAO pdao = new ProductDAO();

        switch (action) {

            case "add": {
                String name = request.getParameter("productName");
                String team = request.getParameter("teamName");
                String category = request.getParameter("category");
                String description = request.getParameter("description");
                String image = request.getParameter("imagePath");

                if (image == null || image.trim().isEmpty()) {
                    image = "resources/images/background.jpeg";
                }

                Product product = new Product();
                product.setProductName(name);
                product.setTeamName(team);
                product.setCategory(category);
                product.setDescription(description);
                product.setImagePath(image);
                product.setCreateAt(LocalDateTime.now());
                product.setUpdatedAt(LocalDateTime.now());

                boolean isAdded = pdao.insertProduct(product);

                if (isAdded) {
                    response.sendRedirect(request.getContextPath() + "/product");
                } else {
                    request.setAttribute("error", "Failed to add product!");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "update": {
                int productId = Integer.parseInt(request.getParameter("productid"));

                String name = request.getParameter("productName");
                String team = request.getParameter("teamName");
                String category = request.getParameter("category");
                String description = request.getParameter("description");
                String image = request.getParameter("imagePath");

                Product product = new Product();
                product.setProductId(productId);
                product.setProductName(name);
                product.setTeamName(team);
                product.setCategory(category);
                product.setDescription(description);
                product.setImagePath(image);
                product.setUpdatedAt(LocalDateTime.now());

                boolean isUpdated = pdao.updateProduct(product);

                if (isUpdated) {
                    response.sendRedirect(request.getContextPath() + "/product");
                } else {
                    request.setAttribute("error", "Failed to update product!");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "delete": {
                int productId = Integer.parseInt(request.getParameter("productid"));

                boolean isDeleted = pdao.deleteProduct(productId);

                if (isDeleted) {
                    response.sendRedirect(request.getContextPath() + "/product");
                } else {
                    System.out.println("Failed to delete product!");
                }
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/product");
        }
    }
}
