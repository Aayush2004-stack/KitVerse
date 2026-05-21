/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kitverse.models.Order;
import kitverse.utilities.DBConfig;

/**
 *
 * @author aayushbastola
 */
public class OrderDAO {

    private Connection conn;

    public OrderDAO() {
        try {
            conn = DBConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {

            System.out.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Inserts a new order into the database.
     *
     * @param order the {@code Order} object containing the customer ID, total
     * amount, status, and delivery address
     * @return the generated order ID if the insertion is successful; {@code 0}
     * if the insertion fails
     */
    public int insertOrder(Order order) {

        int orderId = 0;

        final String query = "INSERT INTO orders (customer_id, total_amt, status, address) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getCustomerId());
            ps.setDouble(2, order.getTotalAmt());
            ps.setString(3, order.getStatus());
            ps.setString(4, order.getAddress());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return orderId;
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return the matching {@code Order} object if found; {@code null}
     * otherwise
     */
    public Order getOrderById(int orderId) {

        Order order = null;

        final String query = "SELECT * FROM orders WHERE order_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setTotalAmt(rs.getDouble("total_amt"));
                order.setStatus(rs.getString("status"));
                order.setAddress(rs.getString("address"));
                order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return order;
    }

    /**
     * Retrieves all orders from the database, ordered by creation date in
     * descending order.
     *
     * @return a {@code List<Order>} containing all orders
     */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        final String query = "SELECT * FROM orders ORDER BY created_at DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setTotalAmt(rs.getDouble("total_amt"));
                order.setStatus(rs.getString("status"));
                order.setAddress(rs.getString("address"));

                Timestamp created = rs.getTimestamp("created_at");
                if (created != null) {
                    order.setCreatedAt(created.toLocalDateTime());
                }

                Timestamp updated = rs.getTimestamp("updated_at");
                if (updated != null) {
                    order.setUpdatedAt(updated.toLocalDateTime());
                }

                orders.add(order);
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return orders;
    }

    /**
     * Updates the status of an existing order.
     *
     * @param orderId the ID of the order to update
     * @param status the new order status
     * @return {@code true} if the order status is updated successfully;
     * {@code false} otherwise
     */
    public boolean updateOrderStatus(int orderId, String status) {
        final String query = "UPDATE orders SET status = ? WHERE order_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return false;
    }

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a {@code List<Order>} containing the customer's orders, ordered
     * by order ID in descending order
     */
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();

        final String query = "SELECT * FROM orders WHERE customer_id = ? ORDER BY order_id DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setTotalAmt(rs.getDouble("total_amt"));
                order.setStatus(rs.getString("status"));
                order.setAddress(rs.getString("address"));

                Timestamp created = rs.getTimestamp("created_at");
                if (created != null) {
                    order.setCreatedAt(created.toLocalDateTime());
                }

                Timestamp updated = rs.getTimestamp("updated_at");
                if (updated != null) {
                    order.setUpdatedAt(updated.toLocalDateTime());
                }
                orders.add(order);
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

        }

        return orders;
    }

}
