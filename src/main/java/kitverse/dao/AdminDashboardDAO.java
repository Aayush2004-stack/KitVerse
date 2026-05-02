/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.dao;

/**
 *
 * @author ACER
 */
import kitverse.utilities.DBConfig;
import java.sql.*;

public class AdminDashboardDAO {

    private Connection conn;
    private boolean isConnectionError = false;

    public AdminDashboardDAO() {
        try {
            conn = DBConfig.getConnection();

            if (conn == null) {
                throw new SQLException("DB connection is NULL");
            }

            System.out.println("DB CONNECTED SUCCESSFULLY");

        } catch (Exception ex) {
            ex.printStackTrace();
            conn = null;
            isConnectionError = true;
        }
    }

    // Total products
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

    // Total variants
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

    // Total stock in system
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

    // Low stock variants (stock <= 5)
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

    // Out of stock variants
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
}
