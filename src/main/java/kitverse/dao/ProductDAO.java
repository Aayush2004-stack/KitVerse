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

    /**
     * Retrieves all products from the database.
     *
     * @return ArrayList containing all Product objects
     */
    @Override
    public ArrayList<Product> fetchAllProducts() {

        ArrayList<Product> products = new ArrayList<>();
        final String query = "SELECT * FROM products";

        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(map(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return products;
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product the Product object containing the product name, team name,
     * category, description, and image path
     * @return {@code true} if the product is inserted successfully;
     * {@code false} otherwise
     */
    @Override
    public boolean insertProduct(Product product) {

        final String query = "INSERT INTO products (product_name, team_name, category, description, image_path) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getTeamName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImagePath());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Retrieves product details by product ID.
     *
     * @param productId the ID of the product
     * @return Product object if found, otherwise null
     */
    @Override
    public Product getProductDetails(int productId) {

        final String query = "SELECT * FROM products WHERE product_id=?";

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

    /**
     * Updates an existing product in the database.
     *
     * @param product the Product object containing the updated product name,
     * team name, category, description, image path, and product ID
     * @return {@code true} if the product is updated successfully;
     * {@code false} otherwise
     */
    @Override
    public boolean updateProduct(Product product) {

        final String query = "UPDATE products SET product_name=?, team_name=?, category=?, description=?, image_path=? WHERE product_id=?;";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getTeamName());
            ps.setString(3, product.getCategory());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImagePath());
            ps.setInt(6, product.getProductId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return false;
    }

    /**
     * Deletes a product from the database using product ID.
     *
     * @param productId the ID of the product to delete
     * @return true if deletion is successful, false otherwise
     */
    @Override
    public boolean deleteProduct(int productId) {

        final String query = "DELETE FROM products WHERE product_id=?;";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return false;
    }

    /**
     * Retrieves products filtered by category.
     *
     * @param category the category name
     * @return ArrayList of products in the given category
     */
    @Override
    public ArrayList<Product> getProductsByCategory(String category) {

        ArrayList<Product> list = new ArrayList<>();
        final String query = "SELECT * FROM products WHERE category=?;";

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

//    /**
//     * Retrieves products filtered by team name.
//     *
//     * @param teamName the team name
//     * @return ArrayList of products belonging to the team
//     */
//    @Override
//    public ArrayList<Product> getProductsByTeam(String teamName) {
//
//        ArrayList<Product> list = new ArrayList<>();
//        final String query = "SELECT * FROM products WHERE team_name=?";
//
//        try (PreparedStatement ps = conn.prepareStatement(query)) {
//
//            ps.setString(1, teamName);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                list.add(map(rs));
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//
//        return list;
//    }
    /**
     * Searches products by keyword in product name.
     *
     * @param keyword search keyword
     * @return ArrayList of matching products
     */
    @Override
    public ArrayList<Product> searchProductByName(String keyword) {

        ArrayList<Product> list = new ArrayList<>();
        final String query = "SELECT * FROM products WHERE product_name LIKE ?;";

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

    /**
     * Retrieves the total number of products in the database.
     *
     * @return total product count
     */
    @Override
    public int getTotalProducts() {

        final String query = "SELECT COUNT(*) as count FROM products;";

        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return 0;
    }

    /**
     * Retrieves a paginated list of products from the database.
     *
     * @param offset the starting position of the first record
     * @param limit the maximum number of products to retrieve
     * @return a {@code List<Product>} containing the products for the requested
     * page
     */
    @Override
    public List<Product> getPaginatedProducts(int offset, int limit) {

        List<Product> products = new ArrayList<>();

        final String query = "SELECT * FROM products ORDER BY product_id DESC LIMIT ? OFFSET ?;";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(map(rs));
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return products;
    }

    /**
     * Maps a ResultSet row into a Product object.
     *
     * @param rs ResultSet containing product data
     * @return mapped Product object
     * @throws SQLException if database access error occurs
     */
    private Product map(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setTeamName(rs.getString("team_name"));
        product.setCategory(rs.getString("category"));
        product.setDescription(rs.getString("description"));
        product.setImagePath(rs.getString("image_path"));
        product.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
        product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

        return product;
    }
}
