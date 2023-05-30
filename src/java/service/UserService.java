/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

<<<<<<< HEAD
/**
 *
 * @author vsngh
 */
public class UserService {
    
=======
import entity.UserEntity;
import java.sql.SQLException;
import repository.UserRepository;

/**
 *
 * @author ASUS
 */
public class UserService {
    
    UserRepository userRepo = new UserRepository();
    
    public UserEntity checkEmailExist(String email) throws SQLException {
        return userRepo.Check(email);
    }
    public void changePass(String email, String password) throws SQLException {
         userRepo.changePass(email, password);
    }

>>>>>>> 4efd67ce30baee61f92e7273013e6062bd00e574
}
