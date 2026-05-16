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

    /**
     * Handles HTTP GET requests for product-related operations.
     *
     * This method processes the "action" parameter to determine the required
     * operation:
     *
     * <ul>
     * <li><b>new</b> - Forwards the request to the page for adding a new
     * product.</li>
     * <li><b>edit</b> - Retrieves product details by ID and forwards to the
     * edit page.</li>
     * <li><b>default</b> - Displays the list of all available products when no
     * valid action is provided.</li>
     * </ul>
     *
     * @param request the HttpServletRequest object containing client request
     * data
     * @param response the HttpServletResponse object used to send data back to
     * the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs during request
     * processing
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");

        ProductDAO pDao = new ProductDAO();

        switch (action) {

            case "new": {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productAdd.jsp");
                rd.forward(request, response);
                break;
            }

            case "edit": {
                int productId = Integer.parseInt(request.getParameter("productId"));

                Product product = pDao.getProductDetails(productId);

                request.setAttribute("product", product);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productAdd.jsp");
                rd.forward(request, response);
                break;
            }

            case "admin": {
                ArrayList<Product> products = pDao.fetchAllProducts();

                if (products == null) {
                    products = new ArrayList<>();
                }

                request.setAttribute("products", products);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productList.jsp");
                rd.forward(request, response);
                break;
            }
            default:{
                ArrayList<Product> products = pDao.fetchAllProducts();
                 if (products == null) {
                    products = new ArrayList<>();
                }
                 request.setAttribute("products", products);
                
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/product.jsp");
                rd.forward(request, response);
                break;
            }
        }
    }

    /**
     * Handles HTTP POST requests for product operations such as add, update,
     * and delete.
     *
     * This method processes the "action" parameter to determine the required
     * operation:
     *
     * <ul>
     * <li><b>add</b> - Creates a new product and stores it in the
     * database.</li>
     * <li><b>update</b> - Updates existing product details in the
     * database.</li>
     * <li><b>delete</b> - Removes a product from the database using its
     * ID.</li>
     * </ul>
     *
     * If the action is invalid or processing fails, an appropriate error
     * message is shown or the user is redirected back to the relevant page.
     *
     * @param request the HttpServletRequest object containing form data
     * submitted by the user
     * @param response the HttpServletResponse object used to send data back to
     * the client
     * @throws ServletException if a servlet-specific error occurs during
     * request processing
     * @throws IOException if an input or output error occurs during request
     * handling
     */
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
                    response.sendRedirect(request.getContextPath() + "/product?action=admin");
                } else {
                    request.setAttribute("error", "Failed to add product!");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "update": {
                int productId = Integer.parseInt(request.getParameter("productId"));

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
                    response.sendRedirect(request.getContextPath() + "/product?action=admin");
                } else {
                    request.setAttribute("error", "Failed to update product!");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "delete": {
                int productId = Integer.parseInt(request.getParameter("productId"));

                boolean isDeleted = pdao.deleteProduct(productId);

                if (isDeleted) {
                    response.sendRedirect(request.getContextPath() + "/product?action=admin");
                } else {
                    System.out.println("Failed to delete product!");
                }
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/product?action=admin");
        }
    }
}
