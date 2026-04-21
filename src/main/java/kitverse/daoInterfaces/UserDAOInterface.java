/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package kitverse.daoInterfaces;

import kitverse.models.User;

/**
 *
 * @author aayushbastola
 */
public interface UserDAOInterface {
     //create new user
    int insertUser(String fullName,  String email, String phnNo, String password);
    
    //get password for the user
    User getUser(String email);
    
}
