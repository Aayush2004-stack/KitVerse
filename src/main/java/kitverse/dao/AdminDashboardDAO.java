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
}
