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
import kitverse.dao.ProductVariantDAO;
import kitverse.models.ProductVariant;

/**
 *
 * @author ACER
 */
@WebServlet(name = "AdminProductVariantServlet", urlPatterns = {"/admin/variant"})
public class AdminProductVariantServlet extends HttpServlet {

    private Integer parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    private Double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Handles HTTP GET requests for product variant pages.
     *
     * This method processes the "action" parameter to determine the required
     * operation:
     *
     * <ul>
     * <li><b>new</b> - Opens the form to add a new product variant.</li>
     * <li><b>edit</b> - Loads existing variant data for editing.</li>
     * <li><b>product</b> - Displays all variants associated with a specific
     * product.</li>
     * </ul>
     *
     * If the action is missing or invalid, the request is redirected to the
     * product page.
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

        final String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");

        ProductVariantDAO vDao = new ProductVariantDAO();

        switch (action) {

            case "new": {
                // keep productId for back button
                String productId = request.getParameter("productId");
                request.setAttribute("productId", productId);

                request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantAdd.jsp")
                        .forward(request, response);
                break;
            }

            case "edit": {
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                String productId = request.getParameter("productId");

                ProductVariant variant = vDao.getVariantById(variantId);

                request.setAttribute("variant", variant);
                request.setAttribute("productId", productId);

                request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantAdd.jsp")
                        .forward(request, response);
                break;
            }

            case "product": {
                String productIdParam = request.getParameter("productId");

                // safety check
                if (productIdParam == null) {
                    response.sendRedirect(request.getContextPath() + "/product");
                    return;
                }

                int productId = Integer.parseInt(productIdParam);

                ArrayList<ProductVariant> variants = vDao.getVariantsByProductId(productId);

                request.setAttribute("variants", variants);
                request.setAttribute("productId", productId);

                request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantList.jsp")
                        .forward(request, response);
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "admin/product");
        }
    }

    /**
     * Handles HTTP POST requests for product variant operations.
     *
     * This method processes the "action" parameter to determine the required
     * operation:
     *
     * <ul>
     * <li><b>add</b> - Adds a new product variant to the database.</li>
     * <li><b>update</b> - Updates existing variant details.</li>
     * <li><b>delete</b> - Removes a variant from the database.</li>
     * <li><b>stock</b> - Increases the stock quantity of a variant.</li>
     * <li><b>updateStock</b> - Sets the stock quantity to a specific
     * value.</li>
     * </ul>
     *
     * If the operation fails or an invalid action is provided, the method
     * redirects the user or displays an appropriate error message.
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

        ProductVariantDAO Vdao = new ProductVariantDAO();

        switch (action) {

            case "add": {

                int productId = Integer.parseInt(request.getParameter("productId"));

                String size = request.getParameter("size");
                String priceStr = request.getParameter("sellingPrice");
                String stockStr = request.getParameter("stock");

                Double price = parseDoubleSafe(priceStr);
                Integer stock = parseIntSafe(stockStr);

                boolean hasError = false;
                String error = null;

                if (size == null || size.trim().isEmpty()) {
                    error = "Size is required.";
                    hasError = true;
                } else if (price == null) {
                    error = "Price must be a valid number.";
                    hasError = true;
                } else if (price <= 0) {
                    error = "Price must be greater than 0.";
                    hasError = true;
                } else if (stock == null) {
                    error = "Stock must be a valid number.";
                    hasError = true;
                } else if (stock < 0) {
                    error = "Stock cannot be negative.";
                    hasError = true;
                }

                if (hasError) {
                    request.setAttribute("error", error);
                    request.setAttribute("productId", productId);

                    request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantAdd.jsp")
                            .forward(request, response);
                    return;
                }

                ProductVariant variant = new ProductVariant();
                variant.setProductId(productId);
                variant.setSize(size);
                variant.setSellingPrice(price);
                variant.setStock(stock);
                variant.setCreateAt(LocalDateTime.now());
                variant.setUpdatedAt(LocalDateTime.now());

                boolean isAdded = Vdao.insertVariant(variant);

                if (isAdded) {
                    response.sendRedirect(request.getContextPath()
                            + "/admin/variant?action=product&productId=" + productId);
                } else {
                    request.setAttribute("error", "Failed to add variant!");
                    request.setAttribute("productId", productId);

                    request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantAdd.jsp")
                            .forward(request, response);
                }

                break;
            }
            case "edit": {

                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                String size = request.getParameter("size");
                String priceStr = request.getParameter("sellingPrice");
                String stockStr = request.getParameter("stock");

                Double price = parseDoubleSafe(priceStr);
                Integer stock = parseIntSafe(stockStr);

                boolean hasError = false;
                String error = null;

                if (size == null || size.trim().isEmpty()) {
                    error = "Size is required.";
                    hasError = true;
                } else if (price == null || price <= 0) {
                    error = "Price must be greater than 0.";
                    hasError = true;
                } else if (stock == null || stock < 0) {
                    error = "Stock must be 0 or more.";
                    hasError = true;
                }

                if (hasError) {
                    request.setAttribute("error", error);
                    request.setAttribute("productId", productId);

                    ProductVariant variant = Vdao.getVariantById(variantId);
                    request.setAttribute("variant", variant);

                    request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantAdd.jsp")
                            .forward(request, response);
                    return;
                }

                ProductVariant variant = new ProductVariant();
                variant.setVariantId(variantId);
                variant.setSize(size);
                variant.setSellingPrice(price);
                variant.setStock(stock);
                variant.setUpdatedAt(LocalDateTime.now());

                boolean isUpdated = Vdao.updateVariant(variant);

                if (isUpdated) {
                    response.sendRedirect(request.getContextPath()
                            + "/admin/variant?action=product&productId=" + productId);
                } else {
                    request.setAttribute("error", "Failed to update variant!");
                    request.setAttribute("productId", productId);
                    request.setAttribute("variant", Vdao.getVariantById(variantId));

                    request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantAdd.jsp")
                            .forward(request, response);
                }

                break;
            }

            case "delete": {
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                boolean isDeleted = Vdao.deleteVariant(variantId);

                if (isDeleted) {
                    response.sendRedirect(request.getContextPath() + "/admin/variant?action=product&productId=" + productId);
                    return;
                } else {
                    // Error message
                    request.setAttribute("error", "Failed to delete variant!");

                    // Reload variants
                    ArrayList<ProductVariant> variants = Vdao.getVariantsByProductId(productId);

                    request.setAttribute("variants", variants);
                    request.setAttribute("productId", productId);

                    // Forward back to variant list page
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantList.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "stock": {

                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                String stockStr = request.getParameter("stock");

                Integer addStock = parseIntSafe(stockStr);

                if (addStock == null || addStock <= 0) {
                    request.setAttribute("error", "Stock must be a positive number.");

                    ArrayList<ProductVariant> variants = Vdao.getVariantsByProductId(productId);
                    request.setAttribute("variants", variants);
                    request.setAttribute("productId", productId);

                    request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantList.jsp")
                            .forward(request, response);
                    return;
                }

                boolean updated = Vdao.increaseStock(variantId, addStock);

                if (!updated) {
                    request.setAttribute("error", "Failed to update stock.");

                    ArrayList<ProductVariant> variants = Vdao.getVariantsByProductId(productId);
                    request.setAttribute("variants", variants);
                    request.setAttribute("productId", productId);

                    request.getRequestDispatcher("/WEB-INF/pages/adminPages/productVariantList.jsp")
                            .forward(request, response);
                    return;
                }

                response.sendRedirect(request.getContextPath()
                        + "/admin/variant?action=product&productId=" + productId);

                break;
            }

            case "updateStock": {
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                boolean updated = Vdao.updateStock(variantId, stock);
                break;
            }
            default:
                response.sendRedirect(request.getContextPath() + "/admin/product");
        }
    }

}
