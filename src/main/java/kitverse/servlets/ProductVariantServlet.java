/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package kitverse.servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import kitverse.dao.ProductVariantDAO;
import kitverse.models.ProductVariant;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ProductVariantServlet", urlPatterns = {"/variant"})
public class ProductVariantServlet extends HttpServlet {

    /**
     * Handles GET requests for product variant pages.
     *
     * Based on "action" parameter:
     *
     * new: Opens form to add a new variant.
     *
     * edit: Loads existing variant data for editing.
     *
     * product: Shows all variants of a specific product.
     *
     * If action is missing or invalid, redirects to product page.
     *
     * @param request contains request data from browser
     * @param response sends response back to browser
     * @throws ServletException : if servlet error occurs
     * @throws IOException : if input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // If no action → redirect safely
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/product");
            return;
        }

        ProductVariantDAO vdao = new ProductVariantDAO();

        switch (action) {

            case "new": {
                // keep productid for back button
                String productId = request.getParameter("productid");
                request.setAttribute("productid", productId);

                request.getRequestDispatcher("/WEB-INF/pages/productvariantadd.jsp")
                        .forward(request, response);
                break;
            }

            case "edit": {
                int variantId = Integer.parseInt(request.getParameter("variantid"));
                String productId = request.getParameter("productid");

                ProductVariant variant = vdao.getVariantById(variantId);

                request.setAttribute("variant", variant);
                request.setAttribute("productid", productId);

                request.getRequestDispatcher("/WEB-INF/pages/productvariantadd.jsp")
                        .forward(request, response);
                break;
            }

            case "product": {
                String productIdParam = request.getParameter("productid");

                // safety check
                if (productIdParam == null) {
                    response.sendRedirect(request.getContextPath() + "/product");
                    return;
                }

                int productId = Integer.parseInt(productIdParam);

                ArrayList<ProductVariant> variants = vdao.getVariantsByProductId(productId);

                request.setAttribute("variants", variants);
                request.setAttribute("productid", productId);

                request.getRequestDispatcher("/WEB-INF/pages/productvariantlist.jsp")
                        .forward(request, response);
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/product");
        }
    }

    /**
     * Handles POST requests for product variant operations.
     *
     * Based on "action" parameter:
     *
     * add: Adds a new product variant to database.
     *
     * update: Updates existing variant details.
     *
     * delete: Removes a variant from database.
     *
     * stock: Increases stock quantity of a variant.
     *
     * updateStock: Sets stock quantity directly.
     *
     * If operation fails, redirects or shows error message.
     *
     * @param request contains form form-data from user
     * @param response sends response back to browser
     * @throws ServletException : if servlet error occurs
     * @throws IOException : if input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");

        ProductVariantDAO vdao = new ProductVariantDAO();

        switch (action) {

            case "add": {
                int productId = Integer.parseInt(request.getParameter("productid"));
                String size = request.getParameter("size");
                double price = Double.parseDouble(request.getParameter("sellingPrice"));
                int stock = Integer.parseInt(request.getParameter("stock"));

                ProductVariant variant = new ProductVariant();
                variant.setProductId(productId);
                variant.setSize(size);
                variant.setSellingPrice(price);
                variant.setStock(stock);
                variant.setCreateAt(LocalDateTime.now());
                variant.setUpdatedAt(LocalDateTime.now());

                boolean isAdded = vdao.insertVariant(variant);

                if (isAdded) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productid=" + productId);
                } else {
                    request.setAttribute("error", "Failed to add variant!");
                    request.setAttribute("productid", productId);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productvariantadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "update": {
                int variantId = Integer.parseInt(request.getParameter("variantid"));
                int productId = Integer.parseInt(request.getParameter("productid"));

                String size = request.getParameter("size");
                double price = Double.parseDouble(request.getParameter("sellingPrice"));
                int stock = Integer.parseInt(request.getParameter("stock"));

                ProductVariant variant = new ProductVariant();
                variant.setVariantId(variantId);
                variant.setSize(size);
                variant.setSellingPrice(price);
                variant.setStock(stock);
                variant.setUpdatedAt(LocalDateTime.now());

                boolean isUpdated = vdao.updateVariant(variant);

                if (isUpdated) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productid=" + productId);
                } else {
                    request.setAttribute("error", "Failed to update variant!");
                    request.setAttribute("productid", productId);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productvariantadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "delete": {
                int variantId = Integer.parseInt(request.getParameter("variantid"));
                int productId = Integer.parseInt(request.getParameter("productid"));

                boolean isDeleted = vdao.deleteVariant(variantId);

                if (isDeleted) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productid=" + productId);
                } else {
                    System.out.println("Failed to delete variant!");
                }
                break;
            }

            case "stock": {
                int variantId = Integer.parseInt(request.getParameter("variantid"));
                String stockParam = request.getParameter("stock");

                if (stockParam == null || stockParam.isEmpty()) {
                    response.sendRedirect(request.getContextPath()
                            + "/variant?action=product&productid=" + request.getParameter("productid"));
                    return;
                }

                int addStock = Integer.parseInt(stockParam);
                int productId = Integer.parseInt(request.getParameter("productid"));

                boolean updated = vdao.increaseStock(variantId, addStock);

                if (updated) {
                    response.sendRedirect(request.getContextPath()
                            + "/variant?action=product&productid=" + productId);
                } else {
                    System.out.println("Failed to update stock!");
                }
                break;
            }

            case "updateStock": {
                int variantId = Integer.parseInt(request.getParameter("variantid"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                int productId = Integer.parseInt(request.getParameter("productid"));

                boolean updated = vdao.updateStock(variantId, stock);
                break;
            }
            default:
                response.sendRedirect(request.getContextPath() + "/product");
        }
    }

}
