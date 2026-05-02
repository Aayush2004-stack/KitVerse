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
import kitverse.daoInterfaces.ProductDAOInterface;
import kitverse.models.Product;
import kitverse.utilities.DBConfig;

public class ProductDAO implements ProductDAOInterface {

    private Connection conn;
    private boolean isConnectionError = false;

    public ProductDAO() {
        try {
            conn = DBConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            isConnectionError = true;
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @Override
    public ArrayList<Product> fetchAllProducts() {

        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(map(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return products;
    }

    @Override
    public boolean insertProduct(Product product) {

        String query = "INSERT INTO products (product_name, team_name, category, description, image_path, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getTeamName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImagePath());
            ps.setTimestamp(6, Timestamp.valueOf(product.getCreateAt()));
            ps.setTimestamp(7, Timestamp.valueOf(product.getUpdatedAt()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Product getProductDetails(int productId) {

        String query = "SELECT * FROM products WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return null;
    }

    @Override
    public boolean updateProduct(Product product) {

        String query = "UPDATE products SET product_name=?, team_name=?, category=?, description=?, image_path=?, updated_at=? WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getTeamName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImagePath());
            ps.setObject(6, product.getUpdatedAt());
            ps.setInt(7, product.getProductId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {

        String query = "DELETE FROM products WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return false;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(String category) {

        ArrayList<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return list;
    }

    @Override
    public ArrayList<Product> getProductsByTeam(String teamName) {

        ArrayList<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE team_name=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, teamName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return list;
    }

    @Override
    public ArrayList<Product> searchProductByName(String keyword) {

        ArrayList<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_name LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return list;
    }

    @Override
    public int getTotalProducts() {

        String query = "SELECT COUNT(*) as count FROM products";

        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return 0;
    }

    // Helper method
    private Product map(ResultSet rs) throws SQLException {
        Product p = new Product();

        p.setProductId(rs.getInt("product_id"));
        p.setProductName(rs.getString("product_name"));
        p.setTeamName(rs.getString("team_name"));
        p.setCategory(rs.getString("category"));
        p.setDescription(rs.getString("description"));
        p.setImagePath(rs.getString("image_path"));
        p.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
        p.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

        return p;
    }
}
