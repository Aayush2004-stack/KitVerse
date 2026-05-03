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
            response.sendRedirect(request.getContextPath() + "/product?action=admin");
            return;
        }

        ProductVariantDAO vDao = new ProductVariantDAO();

        switch (action) {

            case "new": {
                // keep productId for back button
                String productId = request.getParameter("productId");
                request.setAttribute("productId", productId);

                request.getRequestDispatcher("/WEB-INF/pages/productVariantAdd.jsp")
                        .forward(request, response);
                break;
            }

            case "edit": {
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                String productId = request.getParameter("productId");

                ProductVariant variant = vDao.getVariantById(variantId);

                request.setAttribute("variant", variant);
                request.setAttribute("productId", productId);

                request.getRequestDispatcher("/WEB-INF/pages/productVariantAdd.jsp")
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

                request.getRequestDispatcher("/WEB-INF/pages/productVariantList.jsp")
                        .forward(request, response);
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/product?action=admin");
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

        ProductVariantDAO Vdao = new ProductVariantDAO();

        switch (action) {

            case "add": {
                int productId = Integer.parseInt(request.getParameter("productId"));
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

                boolean isAdded = Vdao.insertVariant(variant);

                if (isAdded) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productId=" + productId);
                } else {
                    request.setAttribute("error", "Failed to add variant!");
                    request.setAttribute("productId", productId);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productvariantadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "edit": {
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                String size = request.getParameter("size");
                double price = Double.parseDouble(request.getParameter("sellingPrice"));
                int stock = Integer.parseInt(request.getParameter("stock"));

                ProductVariant variant = new ProductVariant();
                variant.setVariantId(variantId);
                variant.setSize(size);
                variant.setSellingPrice(price);
                variant.setStock(stock);
                variant.setUpdatedAt(LocalDateTime.now());

                boolean isUpdated = Vdao.updateVariant(variant);

                if (isUpdated) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productId=" + productId);
                } else {
                    request.setAttribute("error", "Failed to update variant!");
                    request.setAttribute("productId", productId);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/productVariantAdd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "delete": {
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                boolean isDeleted = Vdao.deleteVariant(variantId);

                if (isDeleted) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productId=" + productId);
                } else {
                    System.out.println("Failed to delete variant!");
                }
                break;
            }

            case "stock": {
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                String stockParam = request.getParameter("stock");

                if (stockParam == null || stockParam.isEmpty()) {
                    response.sendRedirect(request.getContextPath()
                            + "/variant?action=product&productId=" + request.getParameter("productId"));
                    return;
                }

                int addStock = Integer.parseInt(stockParam);
                int productId = Integer.parseInt(request.getParameter("productId"));

                boolean updated = Vdao.increaseStock(variantId, addStock);

                if (updated) {
                    response.sendRedirect(request.getContextPath()
                            + "/variant?action=product&productId=" + productId);
                } else {
                    System.out.println("Failed to update stock!");
                }
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
                response.sendRedirect(request.getContextPath() + "/product?action=admin");
        }
    }

}
