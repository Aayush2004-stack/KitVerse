/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.dao;

import java.sql.*;
import kitverse.models.Order;
import kitverse.utilities.DBConfig;

/**
 *
 * @author aayushbastola
 */
public class OrderDAO {

    private Connection conn;


    public OrderDAO() {
        try {
            conn = DBConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {

            System.out.println(ex.getLocalizedMessage());
        }
    }

    // INSERT ORDER
    public int insertOrder(Order order) {

        int orderId = 0;

        String query = "INSERT INTO orders (customer_id, total_amt, status, address) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getCustomerId());
            ps.setDouble(2, order.getTotalAmt());
            ps.setString(3, order.getStatus());
            ps.setString(4, order.getAddress());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (Exception e) {
           System.out.println(e.getLocalizedMessage());
        }

        return orderId;
    }


    // GET ORDER BY ID

    public Order getOrderById(int orderId) {

        Order order = null;

        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setTotalAmt(rs.getDouble("total_amt"));
                order.setStatus(rs.getString("status"));
                order.setAddress(rs.getString("address"));
                order.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return order;
    }
}
