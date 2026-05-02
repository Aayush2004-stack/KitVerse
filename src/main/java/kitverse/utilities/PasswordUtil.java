/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kitverse.utilities;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aayushbastola
 */

public class PasswordUtil {
    //best value is between 10 and 12 for time complexity and robustness
    // 2^COST times iteration for hashing, COST 10 and 11 means double iteration
    private final static int COST = 10;  
    
    public static String getHashPassword(String inputPassword){
        // Generate a salt with a default work factor (10 is the default, 
        //12 is a reasonable modern default)
        String salt = BCrypt.gensalt(COST);
        // Hash the password with the generated salt
        return BCrypt.hashpw(inputPassword, salt);
    }
    
    public static boolean checkPassword(String passwordTyped, String hashedPassword){
        return BCrypt.checkpw(passwordTyped, hashedPassword);
    }
}
