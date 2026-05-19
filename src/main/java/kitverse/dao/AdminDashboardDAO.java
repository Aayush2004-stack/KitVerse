/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.dao;

/**
 *
 * @author ACER
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kitverse.utilities.DBConfig;

public class AdminDashboardDAO {

    private Connection conn;
    private boolean isConnectionError = false;

    public AdminDashboardDAO() {
        try {
            conn = DBConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            isConnectionError = true;
            System.out.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Retrieves the total number of products in the system.
     *
     * @return total count of products
     */
    public int getTotalProducts() {
        String sql = "SELECT COUNT(*) FROM products";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves the total number of product variants in the system.
     *
     * @return total count of product variants
     */
    public int getTotalVariants() {
        String sql = "SELECT COUNT(*) FROM product_variants";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Calculates the total stock available across all product variants.
     *
     * @return sum of all stock quantities
     */
    public int getTotalStock() {
        String sql = "SELECT SUM(stock) FROM product_variants";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves the number of product variants with low stock.
     *
     * Low stock is defined as stock less than or equal to 5 and greater than 0.
     *
     * @return count of low stock variants
     */
    public int getLowStockCount() {
        String sql = "SELECT COUNT(*) FROM product_variants WHERE stock <= 5 AND stock > 0";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves the number of product variants that are out of stock.
     *
     * @return count of variants with zero stock
     */
    public int getOutOfStockCount() {
        String sql = "SELECT COUNT(*) FROM product_variants WHERE stock = 0";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves the total number of orders placed in the system.
     *
     * @return total count of orders
     */
    public int getTotalOrders() {
        String sql = "SELECT COUNT(*) FROM orders";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Calculates the total revenue generated from all orders.
     *
     * @return total revenue amount (sum of total_amt from orders)
     */
    public double getTotalRevenue() {
        String sql = "SELECT COALESCE(SUM(total_amt), 0) FROM orders";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves the number of orders filtered by a specific status.
     *
     * @param status the order status to filter by (e.g., "PENDING",
     * "COMPLETED", "CANCELLED")
     * @return count of orders matching the given status
     */
    public int getOrdersByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM orders WHERE status = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves the number of orders placed on the current date.
     *
     * @return count of today's orders
     */
    public int getTodayOrders() {
        String sql = "SELECT COUNT(*) FROM orders WHERE DATE(created_at) = CURDATE()";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Calculates the total revenue generated from orders placed today.
     *
     * @return total revenue for the current day
     */
    public double getTodayRevenue() {
        String sql = "SELECT COALESCE(SUM(total_amt), 0) "
                + "FROM orders WHERE DATE(created_at) = CURDATE()";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retrieves revenue for the last 7 days (including today).
     *
     * <p>
     * The method calculates daily revenue from 6 days ago up to today using a
     * loop and returns a list of values in chronological order.</p>
     *
     * @return list of revenue values for each of the last 7 days
     */
    public List<Double> getWeeklyRevenue() {
        List<Double> weeklyRevenue = new ArrayList<>();
        String sql = """
        SELECT COALESCE(SUM(total_amt), 0) AS revenue
        FROM orders
        WHERE DATE(created_at) = CURDATE() - INTERVAL ? DAY
        """;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            // 6 days ago to today
            for (int i = 6; i >= 0; i--) {
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    weeklyRevenue.add(rs.getDouble("revenue"));
                } else {
                    weeklyRevenue.add(0.0);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return weeklyRevenue;
    }
}
