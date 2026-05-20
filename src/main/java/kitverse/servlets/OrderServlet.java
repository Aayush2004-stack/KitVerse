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
import kitverse.dao.OrderDAO;
import kitverse.dao.OrderItemDAO;
import kitverse.dao.ProductDAO;
import kitverse.dao.ProductVariantDAO;
import kitverse.models.Order;
import kitverse.models.OrderItem;
import kitverse.models.Product;
import kitverse.models.ProductVariant;
import kitverse.models.User;
import kitverse.utilities.CookieUtil;
import kitverse.utilities.SessionUtil;

/**
 *
 * @author aayushbastola
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) SessionUtil.getAttribute(request, "user");

        int customerId = user.getId();

        OrderDAO orderDAO = new OrderDAO();

        List<Order> orders = orderDAO.getOrdersByCustomerId(customerId);

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/WEB-INF/pages/myOrder.jsp")
                .forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //if no action parameter Null Pointer exception so handle it
        final String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (action) {
            case "checkout": {

                User user = (User) SessionUtil.getAttribute(request, "user");
                ProductVariantDAO pvDAO = new ProductVariantDAO();
                int customerId = user.getId();
                int productId = Integer.parseInt(request.getParameter("productId"));
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                int availableStock = pvDAO.getStock(variantId);
                if (quantity > availableStock) {
                    response.sendRedirect(request.getContextPath()
                            + "/variant?action=view&productId=" + productId
                            + "&error=stock");

                    return;

                }

                String playerName = request.getParameter("playerName");
                if (playerName != null) {
                    playerName = playerName.trim();
                }

                String playerNoStr = request.getParameter("playerNo");
                Integer playerNo = null;
                if (playerNoStr != null && !playerNoStr.trim().isEmpty()) {
                    playerNo = Integer.parseInt(playerNoStr);
                }

                ProductVariant variant = pvDAO.getVariantById(variantId);

                ProductDAO pDAO = new ProductDAO();
                Product product = pDAO.getProductDetails(productId);

                double totalAmt = variant.getSellingPrice() * quantity;

                // PASS DATA TO CHECKOUT PAGE
                request.setAttribute("product", product);
                request.setAttribute("variant", variant);
                request.setAttribute("quantity", quantity);
                request.setAttribute("totalAmt", totalAmt);
                request.setAttribute("playerName", playerName);
                request.setAttribute("playerNo", playerNo);

                request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp")
                        .forward(request, response);

                break;
            }
            case "bulkCheckout": {

                User user = (User) SessionUtil.getAttribute(request, "user");

                String[] variantIds = request.getParameterValues("variantIds");

                if (variantIds == null || variantIds.length == 0) {
                    response.sendRedirect(request.getContextPath() + "/cart");
                    return;
                }

                ProductVariantDAO pvDAO = new ProductVariantDAO();
                ProductDAO pDAO = new ProductDAO();

                List<Map<String, Object>> items = new ArrayList<>();
                double totalAmt = 0;

                for (String vid : variantIds) {

                    int variantId = Integer.parseInt(vid);
                    int availableStock = pvDAO.getStock(variantId);

                    String qtyStr = request.getParameter("qty_" + variantId);
                    int quantity = (qtyStr != null && !qtyStr.isEmpty())
                            ? Integer.parseInt(qtyStr)
                            : 1;

                    if (quantity > availableStock) {
                        response.sendRedirect(request.getContextPath()
                                + "/cart?&error=stock");

                        return;

                    }
                    String playerName = request.getParameter("playerName_" + variantId);
                    String playerNoStr = request.getParameter("playerNo_" + variantId);

                    if (playerName != null) {
                        playerName = playerName.trim();
                    }

                    Integer playerNo = null;
                    if (playerNoStr != null && !playerNoStr.trim().isEmpty()) {
                        playerNo = Integer.parseInt(playerNoStr);
                    }
                    ProductVariant variant = pvDAO.getVariantById(variantId);
                    Product product = pDAO.getProductDetails(variant.getProductId());

                    Map<String, Object> item = new HashMap<>();
                    item.put("product", product);
                    item.put("variant", variant);
                    item.put("quantity", quantity);
                    item.put("productVariantId", variantId);
                    item.put("playerName", playerName);
                    item.put("playerNo", playerNo);

                    items.add(item);

                    totalAmt += variant.getSellingPrice() * quantity;
                }

                request.setAttribute("items", items);
                request.setAttribute("totalAmt", totalAmt);

                request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp")
                        .forward(request, response);

                break;
            }
            case "confirm": {

                ProductVariantDAO pvDAO = new ProductVariantDAO();
                User user = (User) SessionUtil.getAttribute(request, "user");
                int customerId = user.getId();

                String address = request.getParameter("address");
                double totalAmt = Double.parseDouble(request.getParameter("totalAmt"));

                Order order = new Order();
                order.setCustomerId(customerId);
                order.setTotalAmt(totalAmt);
                order.setStatus("PENDING");
                order.setAddress(address);

                OrderDAO oDao = new OrderDAO();
                int orderId = oDao.insertOrder(order);

                String[] variantIds = request.getParameterValues("variantIds");

                OrderItemDAO oiDAO = new OrderItemDAO();

                if (variantIds != null) {

                    // CART MODE
                    for (String vid : variantIds) {

                        int variantId = Integer.parseInt(vid);
                        int qty = Integer.parseInt(request.getParameter("qty_" + variantId));

                        String playerName = request.getParameter("playerName_" + variantId);

                        String playerNoStr = request.getParameter("playerNo_" + variantId);
                        Integer playerNo = null;

                        if (playerNoStr != null && !playerNoStr.trim().isEmpty()) {

                            playerNo = Integer.parseInt(playerNoStr.trim());

                        }

                        if (playerName != null) {

                            playerName = playerName.trim();

                        }

                        OrderItem item = new OrderItem();
                        item.setOrderId(orderId);
                        item.setProductVariantId(variantId);
                        item.setQuantity(qty);
                        item.setPlayerName(playerName);

                        item.setPlayerNo(playerNo);

                        oiDAO.insertOrderItem(item);
                        pvDAO.deductStock(variantId, qty);
                    }
                    Cookie cookie = CookieUtil.getCookie(request, "cart");

                    if (cookie != null && cookie.getValue() != null && !cookie.getValue().isEmpty()) {

                        List<String> ids = new ArrayList<>(
                                Arrays.asList(cookie.getValue().split("\\|"))
                        );

                        for (String vid : variantIds) {

                            ids.remove(vid);

                        }

                        // clean empty values
                        ids.removeIf(id -> id == null || id.trim().isEmpty());

                        if (ids.isEmpty()) {

                            CookieUtil.deleteCookie(response, "cart");

                        } else {

                            CookieUtil.addCookie(
                                    response,
                                    "cart",
                                    String.join("|", ids),
                                    7 * 24 * 60 * 60
                            );

                        }

                    }

                } else {

                    // BUY NOW MODE
                    int variantId = Integer.parseInt(request.getParameter("variantId"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));

                    String playerName = request.getParameter("playerName");
                    String playerNoStr = request.getParameter("playerNo");
                    if (playerName != null) {

                        playerName = playerName.trim();

                    }

                    Integer playerNo = null;

                    if (playerNoStr != null && !playerNoStr.trim().isEmpty()) {

                        playerNo = Integer.parseInt(playerNoStr.trim());

                    }

                    OrderItem item = new OrderItem();
                    item.setOrderId(orderId);
                    item.setProductVariantId(variantId);
                    item.setQuantity(quantity);
                    item.setPlayerName(playerName);

                    item.setPlayerNo(playerNo);

                    oiDAO.insertOrderItem(item);
                    pvDAO.deductStock(variantId, quantity);
                }

                response.sendRedirect(request.getContextPath()
                        + "/order");

                break;
            }
            default: {

            }

        }

    }

}
