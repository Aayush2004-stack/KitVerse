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
import java.util.List;
import kitverse.dao.OrderDAO;
import kitverse.dao.OrderItemDAO;
import kitverse.dao.ProductDAO;
import kitverse.dao.ProductVariantDAO;
import kitverse.models.Order;
import kitverse.models.OrderItem;
import kitverse.models.Product;
import kitverse.models.ProductVariant;
import kitverse.models.User;
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
        String action = request.getParameter("action");

        switch (action) {
            case "checkout": {

                User user = (User) SessionUtil.getAttribute(request, "user");
                int customerId = user.getId();
                int productId = Integer.parseInt(request.getParameter("productId"));
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                String playerName = request.getParameter("playerName");
                if (playerName != null) {
                    playerName = playerName.trim();
                }

                String playerNoStr = request.getParameter("playerNo");
                Integer playerNo = null;
                if (playerNoStr != null && !playerNoStr.trim().isEmpty()) {
                    playerNo = Integer.parseInt(playerNoStr);
                }

                ProductVariantDAO pvDAO = new ProductVariantDAO();
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

                List<OrderItem> orderItems = new ArrayList<>();
                double totalAmt = 0;

                for (String vidStr : variantIds) {

                    int variantId = Integer.parseInt(vidStr);

                    // quantity from dynamic input
                    String qtyParam = request.getParameter("qty_" + variantId);
                    int quantity = (qtyParam != null && !qtyParam.isEmpty())
                            ? Integer.parseInt(qtyParam)
                            : 1;

                    ProductVariant variant = pvDAO.getVariantById(variantId);

                    // optional product fetch (if needed in checkout page)
                    Product product = pDAO.getProductDetails(variant.getProductId());

                    OrderItem item = new OrderItem();
                    item.setProductVariantId(variantId);
                    item.setQuantity(quantity);

                    orderItems.add(item);

                    totalAmt += variant.getSellingPrice() * quantity;
                }

                // pass to checkout page
                request.setAttribute("items", orderItems);
                request.setAttribute("totalAmt", totalAmt);

                request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp")
                        .forward(request, response);

                break;
            }
            case "confirm": {
                User user = (User) SessionUtil.getAttribute(request, "user");

                if (user == null) {

                    response.sendRedirect(request.getContextPath() + "/login");

                    return;

                }

                int customerId = user.getId();

                // 2. REQUIRED FIELDS
                int variantId = Integer.parseInt(request.getParameter("variantId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double totalAmt = Double.parseDouble(request.getParameter("totalAmt"));
                String address = request.getParameter("address");

                // 3. OPTIONAL FIELDS            
                String playerName = request.getParameter("playerName");

                if (playerName != null && playerName.trim().isEmpty()) {
                    playerName = null;
                }

                String playerNoStr = request.getParameter("playerNo");
                Integer playerNo = null;
                if (playerNoStr != null && !playerNoStr.trim().isEmpty()) {

                    try {
                        playerNo = Integer.parseInt(playerNoStr);

                    } catch (NumberFormatException e) {
                        playerNo = 0;
                    }
                } else {
                    playerNo = 0;
                }

                // 4. CREATE ORDER (PARENT)
                Order order = new Order();

                order.setCustomerId(customerId);
                order.setTotalAmt(totalAmt);
                order.setStatus("PENDING");
                order.setAddress(address);

                OrderDAO oDao = new OrderDAO();
                int orderId = oDao.insertOrder(order);

                // 5. CREATE ORDER ITEM (CHILD)
                OrderItem item = new OrderItem();

                item.setOrderId(orderId);
                item.setProductVariantId(variantId);
                item.setQuantity(quantity);
                //just for testing

                item.setPlayerName(playerName);
                item.setPlayerNo(playerNo);

                OrderItemDAO oiDAO = new OrderItemDAO();

                oiDAO.insertOrderItem(item);

                // 6. REDIRECT TO INVOICE
                response.sendRedirect(
                        request.getContextPath() + "/order?action=invoice&orderId=" + orderId
                );
            }

        }

    }

}
