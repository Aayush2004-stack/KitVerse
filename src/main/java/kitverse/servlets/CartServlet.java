/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package kitverse.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kitverse.dao.ProductDAO;
import kitverse.dao.ProductVariantDAO;
import kitverse.models.Product;
import kitverse.models.ProductVariant;
import kitverse.utilities.CookieUtil;

/**
 *
 * @author aayushbastola
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    private ProductVariantDAO pvDAO = new ProductVariantDAO();
    private ProductDAO pDAO = new ProductDAO();

    // ADD TO CART
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {

            case "add": {

                int variantId = Integer.parseInt(request.getParameter("variantId"));

                Cookie cookie = CookieUtil.getCookie(request, "cart");

                List<String> ids = new ArrayList<>();

                if (cookie != null && cookie.getValue() != null && !cookie.getValue().isEmpty()) {

                    ids.addAll(Arrays.asList(cookie.getValue().split("\\|")));

                }

                String idStr = String.valueOf(variantId);

                if (!ids.contains(idStr)) {

                    ids.add(idStr);

                }

                String updatedValue = String.join("|", ids);

                CookieUtil.addCookie(
                        response,
                        "cart",
                        updatedValue,
                        7 * 24 * 60 * 60
                );

                response.sendRedirect(request.getContextPath() + "/cart");

                break;

            }
            case "remove": {

                int variantId = Integer.parseInt(request.getParameter("variantId"));

                Cookie cookie = CookieUtil.getCookie(request, "cart");

                if (cookie != null && cookie.getValue() != null) {

                    List<String> ids = new ArrayList<>(
                            Arrays.asList(cookie.getValue().split("\\|"))
                    );

                    ids.remove(String.valueOf(variantId));

                    CookieUtil.addCookie(
                            response,
                            "cart",
                            String.join("|", ids),
                            7 * 24 * 60 * 60
                    );

                }

                response.sendRedirect(request.getContextPath() + "/cart");

                break;

            }

            default:

                response.sendRedirect(request.getContextPath() + "/product");

        }

    }

    // SHOW CART
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie cookie = CookieUtil.getCookie(request, "cart");
        List<Map<String, Object>> cartItems = new ArrayList<>();
        if (cookie != null && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
            String[] ids = cookie.getValue().split("\\|");
            for (String idStr : ids) {
                int variantId = Integer.parseInt(idStr);
                // GET VARIANT
                ProductVariant variant = pvDAO.getVariantById(variantId);
                if (variant != null) {

                    // GET PRODUCT USING productId FROM VARIANT
                    Product product = pDAO.getProductDetails(variant.getProductId());
                    Map<String, Object> item = new HashMap<>();

                    item.put("variant", variant);

                    item.put("product", product);

                    cartItems.add(item);

                }

            }

        }
        String error = request.getParameter("error");
        if (error != null) {
            request.setAttribute("error", "Insufficient stock.");
        }

        request.setAttribute("cartItems", cartItems);

        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);

    }

}
