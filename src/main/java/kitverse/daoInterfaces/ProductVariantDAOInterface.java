/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.daoInterfaces;

/**
 *
 * @author ACER
 */
import kitverse.models.ProductVariant;
import java.util.ArrayList;

public interface ProductVariantDAOInterface {

    // Insert new variant
    boolean insertVariant(ProductVariant variant);

    // Get all variants of a product
    ArrayList<ProductVariant> getVariantsByProductId(int productId);

    // Get single variant
    ProductVariant getVariantById(int variantId);

    // Update variant (price, size)
    boolean updateVariant(ProductVariant variant);

    // Delete variant
    boolean deleteVariant(int variantId);

    // Update stock (admin adds stock)
    boolean updateStock(int variantId, int newStock);

    // Deduct stock (when user buys)
    boolean deductStock(int variantId, int quantity);

    // Check stock availability
    int getStock(int variantId);
}
