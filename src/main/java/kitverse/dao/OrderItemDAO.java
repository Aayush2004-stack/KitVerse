/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.dao;
import java.sql.*;
import kitverse.models.OrderItem;
import kitverse.utilities.DBConfig;
/**
 *
 * @author aayushbastola
 */
public class OrderItemDAO {

    private Connection conn;

    public OrderItemDAO() {
         try {
            conn = DBConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {

            System.out.println(ex.getLocalizedMessage());
        }
    }

    // INSERT ORDER ITEM

    public void insertOrderItem(OrderItem item) {

        String query = "INSERT INTO order_items "
                   + "(order_id, product_variant_id, quantity, player_name, player_no) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductVariantId());
            ps.setInt(3, item.getQuantity());
            ps.setString(4, item.getPlayerName());
            ps.setInt(5, item.getPlayerNo());
            
           
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }



}
