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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");

        ProductVariantDAO vdao = new ProductVariantDAO();

        switch (action) {

            case "new": {
                // open add variant page
                RequestDispatcher rd = request.getRequestDispatcher("/pages/variantadd.jsp");
                rd.forward(request, response);
                break;
            }

            case "edit": {
                int variantId = Integer.parseInt(request.getParameter("variantid"));

                ProductVariant variant = vdao.getVariantById(variantId);

                request.setAttribute("variant", variant);

                RequestDispatcher rd = request.getRequestDispatcher("/pages/variantadd.jsp");
                rd.forward(request, response);
                break;
            }

            case "product": {
                // view variants of a product
                int productId = Integer.parseInt(request.getParameter("productid"));

                ArrayList<ProductVariant> variants = vdao.getVariantsByProductId(productId);

                request.setAttribute("variants", variants);
                request.setAttribute("productid", productId);

                RequestDispatcher rd = request.getRequestDispatcher("/pages/variantlist.jsp");
                rd.forward(request, response);
                break;
            }

            default: {
                response.sendRedirect(request.getContextPath() + "/product");
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
                    RequestDispatcher rd = request.getRequestDispatcher("/pages/variantadd.jsp");
                    rd.forward(request, response);
                }
                break;
            }

            case "update": {
                int variantId = Integer.parseInt(request.getParameter("variantid"));
                int productId = Integer.parseInt(request.getParameter("productid"));

                String size = request.getParameter("size");
                double price = Double.parseDouble(request.getParameter("sellingPrice"));

                ProductVariant variant = new ProductVariant();
                variant.setVariantId(variantId);
                variant.setSize(size);
                variant.setSellingPrice(price);
                variant.setUpdatedAt(LocalDateTime.now());

                boolean isUpdated = vdao.updateVariant(variant);

                if (isUpdated) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productid=" + productId);
                } else {
                    request.setAttribute("error", "Failed to update variant!");
                    RequestDispatcher rd = request.getRequestDispatcher("/pages/variantadd.jsp");
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
                int stock = Integer.parseInt(request.getParameter("stock"));
                int productId = Integer.parseInt(request.getParameter("productid"));

                boolean updated = vdao.updateStock(variantId, stock);

                if (updated) {
                    response.sendRedirect(request.getContextPath() + "/variant?action=product&productid=" + productId);
                } else {
                    System.out.println("Failed to update stock!");
                }
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/product");
        }
    }

}
