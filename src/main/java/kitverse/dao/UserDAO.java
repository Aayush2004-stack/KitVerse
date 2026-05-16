/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import kitverse.daoInterfaces.UserDAOInterface;
import kitverse.models.User;
import kitverse.utilities.DBConfig;

/**
 *
 * @author aayushbastola
 */
public class UserDAO implements UserDAOInterface {

    private Connection conn;
    private boolean isConnectionError = false;

    public UserDAO() {
        try {
            conn = DBConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            isConnectionError = true;
            System.out.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Inserts a new user into the database after validating uniqueness
     * constraints.
     *
     * <p>
     * This method performs the following steps:
     * <ul>
     * <li>Checks if the email already exists (case-insensitive).</li>
     * <li>Checks if the phone number is already registered.</li>
     * <li>If both are unique, inserts the user into the database.</li>
     * </ul>
     *
     * <p>
     * <b>Return Codes:</b>
     * <ul>
     * <li>1 - User successfully inserted</li>
     * <li>0 - No rows affected</li>
     * <li>2 - Email already exists</li>
     * <li>3 - SQL error occurred</li>
     * <li>4 - Phone number already exists</li>
     * </ul>
     *
     * @param fullName the full name of the user
     * @param email the email address of the user
     * @param phnNo the phone number of the user
     * @param password the password of the user
     * @return an integer status code indicating the result of the operation
     */
    @Override
    public int insertUser(String fullName, String email, String phnNo, String password) {
        try {
            //check if email is already present
            final String CHECK_IF_USER = "select email from users where LOWER(email)=LOWER(?);";
            PreparedStatement pStm_ = conn.prepareStatement(CHECK_IF_USER);
            pStm_.setString(1, email);
            ResultSet rs = pStm_.executeQuery();
            if (rs.next()) {
                return 2;   // 2 for user or email already present
            }
            //check for already used phn no
            final String CHECK_PHN = "select phn_no from users where phn_no=?;";
            PreparedStatement pStm2 = conn.prepareStatement(CHECK_PHN);
            pStm2.setString(1, phnNo);
            ResultSet rs2 = pStm2.executeQuery();
            if (rs2.next()) {
                return 4;   // 4 for phn no already present
            }

            final String INSERT_USER = "insert into users (full_name, email, phn_no, password) values (?,?,?,?);";
            PreparedStatement pStm = conn.prepareStatement(INSERT_USER);
            pStm.setString(1, fullName);
            pStm.setString(2, email);
            pStm.setString(3, phnNo);
            pStm.setString(4, password);

            int result = pStm.executeUpdate();
            return result;  //0 or 1 
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return 3;  // if 3 fault in query
        }
    }

    /**
     * Retrieves a user from the database using their email address.
     *
     * <p>
     * This method queries the database for a user with the given email. If a
     * matching record is found, it is mapped to a {@link User} object.
     *
     * @param email the email address of the user to retrieve
     * @return a {@link User} object if found; otherwise {@code null}
     */
    @Override
    public User getUser(String email) {
        try {
            final String SELECT_USER = "select * from users where email=?;";

            PreparedStatement pStm_ = conn.prepareStatement(SELECT_USER);
            pStm_.setString(1, email);
            ResultSet rs = pStm_.executeQuery();
            if (rs.next()) {
                final User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setUserType(rs.getString("user_type"));
                user.setCreateAt(rs.getObject("created_at", LocalDateTime.class));
                user.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
                return user;

            }
            return null;

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }

    }
}
