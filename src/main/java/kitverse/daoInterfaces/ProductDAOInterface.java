/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.daoInterfaces;

/**
 *
 * @author ACER
 */
import kitverse.models.Product;
import java.util.ArrayList;

public interface ProductDAOInterface {

    // Fetch all products
    ArrayList<Product> fetchAllProducts();

    // Insert new product
    boolean insertProduct(Product product);

    // Get product details by ID
    Product getProductDetails(int productId);

    // Update existing product
    boolean updateProduct(Product product);

    // Delete product
    boolean deleteProduct(int productId);

    // Get products by category (useful for jersey filtering)
    ArrayList<Product> getProductsByCategory(String category);

    // Get products by team (e.g., Real Madrid, Barcelona)
    ArrayList<Product> getProductsByTeam(String teamName);

    // Search product by name
    ArrayList<Product> searchProductByName(String keyword);

    // Get total number of products
    int getTotalProducts();
    
}
