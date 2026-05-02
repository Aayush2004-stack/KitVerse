/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.utilities;

/**
 *
 * @author aayushbastola
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database configuration utility class.
 *
 * This class is responsible for creating and providing
 * a connection to the MySQL database.
 *
 * It uses JDBC driver to connect with the database.
 *
 * Database details used:
 * - Database name: kitVerse
 * - Username: root
 * - Password: (empty)
 * - URL: jdbc:mysql://localhost:3306/kitVerse
 */
public class DBConfig {

    private static final String DB_NAME = "kitVerse";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;

    /**
     * Creates and returns a connection to the database.
     *
     * This method: 
     * - Loads MySQL JDBC driver 
     * - Connects to the database using URL, username, and password
     *
     * @return Connection object for database access
     * @throws SQLException : if database connection fails
     * @throws ClassNotFoundException : if JDBC driver is not found
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
