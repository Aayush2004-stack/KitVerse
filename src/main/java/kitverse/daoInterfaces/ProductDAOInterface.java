/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.daoInterfaces;

/**
 *
 * @author ACER
 */
import java.util.ArrayList;
import java.util.List;
import kitverse.models.Product;

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


    // Search product by name
    ArrayList<Product> searchProductByName(String keyword);

    // Get total number of products
    int getTotalProducts();
    
    List<Product> getPaginatedProducts(int offset, int limit);
    
}
