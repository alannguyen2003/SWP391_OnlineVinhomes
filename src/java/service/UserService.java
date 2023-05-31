/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


/**
 *
 * @author vsngh
 */
import entity.UserEntity;
import java.sql.SQLException;
import repository.UserRepository;

public class UserService {
    
    UserRepository userRepo = new UserRepository();
    
    public UserEntity checkEmailExist(String email) throws SQLException {
        return userRepo.Check(email);
    }
    public void changePass(String email, String password) throws SQLException {
         userRepo.changePass(email, password);
    }

}

