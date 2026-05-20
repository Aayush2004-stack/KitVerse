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
import kitverse.daoInterfaces.ProductVariantDAOInterface;
import kitverse.models.ProductVariant;
import kitverse.utilities.DBConfig;

public class ProductVariantDAO implements ProductVariantDAOInterface {

    private Connection conn;
    private boolean isConnectionError = false;

    public ProductVariantDAO() {
        try {
            conn = DBConfig.getConnection();

        } catch (Exception ex) {
            isConnectionError = true;
            System.out.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Inserts a new product variant into the database.
     *
     * @param variant the ProductVariant object containing variant details
     * @return true if insertion is successful, false otherwise
     */
    @Override
    public boolean insertVariant(ProductVariant variant) {

        String query = "INSERT INTO product_variants (product_id, size, selling_price, stock) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, variant.getProductId());
            ps.setString(2, variant.getSize());
            ps.setDouble(3, variant.getSellingPrice());
            ps.setInt(4, variant.getStock());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL ERROR: " + e.getMessage());
        }

        return false;
    }

    /**
     * Retrieves all product variants belonging to a specific product.
     *
     * @param productId the ID of the product
     * @return ArrayList of ProductVariant objects for the given product
     */
    @Override
    public ArrayList<ProductVariant> getVariantsByProductId(int productId) {

        ArrayList<ProductVariant> list = new ArrayList<>();
        String query = "SELECT * FROM product_variants WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Retrieves a product variant using its variant ID.
     *
     * @param variantId the ID of the variant
     * @return ProductVariant object if found, otherwise null
     */
    @Override
    public ProductVariant getVariantById(int variantId) {

        String query = "SELECT * FROM product_variants WHERE variant_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, variantId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Updates an existing product variant in the database.
     *
     * @param variant the ProductVariant object containing updated details
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updateVariant(ProductVariant variant) {
        if (isConnectionError) {
            return false;
        }

        String query
                = "UPDATE product_variants SET size=?, selling_price=?, stock=? WHERE variant_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, variant.getSize());
            ps.setDouble(2, variant.getSellingPrice());
            ps.setInt(3, variant.getStock());
            ps.setInt(4, variant.getVariantId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return false;
    }

    /**
     * Deletes a product variant from the database using variant ID.
     *
     * @param variantId the ID of the variant to delete
     * @return true if deletion is successful, false otherwise
     */
    @Override
    public boolean deleteVariant(int variantId) {

        String query = "DELETE FROM product_variants WHERE variant_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, variantId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Updates the stock value of a specific product variant.
     *
     * @param variantId the ID of the variant
     * @param stock the new stock value to set
     * @return true if stock update is successful, false otherwise
     */
    @Override
    public boolean updateStock(int variantId, int stock) {

        String query = "UPDATE product_variants SET stock=? WHERE variant_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, stock);
            ps.setInt(2, variantId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Deducts the specified quantity from a product variant's stock only if
     * sufficient stock is available.
     *
     * <p>
     * This method ensures that stock never becomes negative by updating the
     * stock only when the current stock is greater than or equal to the
     * requested quantity.</p>
     *
     * @param variantId the ID of the product variant
     * @param quantity the quantity to deduct from stock
     * @return {@code true} if the stock was successfully deducted;
     * {@code false} if insufficient stock exists or if an error occurs
     */
    @Override
    public boolean deductStock(int variantId, int quantity) {

        String query = "UPDATE product_variants SET stock = stock - ? WHERE variant_id=? AND stock >= ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, quantity);
            ps.setInt(2, variantId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Retrieves the current stock of a product variant.
     *
     * @param variantId the ID of the variant
     * @return current stock value, or 0 if not found
     */
    @Override
    public int getStock(int variantId) {

        String query = "SELECT stock FROM product_variants WHERE variant_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, variantId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("stock");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Increases the stock of a product variant.
     *
     * @param variantId the ID of the variant
     * @param addStock the quantity to add to current stock
     * @return true if stock update is successful, false otherwise
     */
    @Override
    public boolean increaseStock(int variantId, int addStock) {

        String query = "UPDATE product_variants SET stock = stock + ? WHERE variant_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, addStock);
            ps.setInt(2, variantId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Converts the current row of the given {@code ResultSet} into a
     * {@code ProductVariant} object.
     *
     * @param rs the {@code ResultSet} positioned at the current row containing
     * product variant data
     * @return a fully populated {@code ProductVariant} object
     * @throws SQLException if an error occurs while reading data from the
     * {@code ResultSet}
     */
    private ProductVariant map(ResultSet rs) throws SQLException {
        ProductVariant v = new ProductVariant();

        v.setVariantId(rs.getInt("variant_id"));
        v.setProductId(rs.getInt("product_id"));
        v.setSize(rs.getString("size"));
        v.setSellingPrice(rs.getDouble("selling_price"));
        v.setStock(rs.getInt("stock"));
        v.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
        v.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

        return v;
    }
}
