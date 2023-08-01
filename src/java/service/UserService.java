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
import java.util.ArrayList;
import java.util.List;
import payload.request.AdminResidentListRequest;
import payload.request.AdminUserListRequest;
import payload.request.ResidentProfileRequest;
import repository.UserRepository;

public class UserService {
    
    UserRepository userRepo = new UserRepository();
    
    public UserEntity Login(String email, String password) throws SQLException {
        return userRepo.Login(email, password);
    }
    
    public UserEntity checkEmailExist(String email) throws SQLException {
        return userRepo.Check(email);
    }
    public void resetPass(String email, String password, String salt) throws SQLException {
        userRepo.resetPass(email, password, salt);
    }
    public void changePass(String aid, String password) throws SQLException {
         userRepo.changePass(aid, password);
    }
    public void createAccount(UserEntity userEntity) throws SQLException{
        userRepo.createAccount(userEntity);
    }
    
    public ArrayList<AdminUserListRequest> getAllUser() throws Exception{
        return userRepo.getAllUser();
    }
    
    public ArrayList<UserEntity> getAllUserByName(String name) throws Exception{
        return userRepo.getAllUserByName(name);
    }
    
    public String getUserForChart() throws SQLException {
        return userRepo.getTopUserJsArray();
    }
    public String getUserMoneyForChart() throws SQLException {
        return userRepo.getTopUserTotalMoneyJsArray();
    }
    
    public int getCountResident() throws Exception {
        return userRepo.getCountResident();
    }
    
    public boolean addNewResident(UserEntity entity) throws Exception {
        if (userRepo.Check(entity.getEmail()) != null ) {
            return false;
        }
        userRepo.addNewResident(entity);
        return true;
    }
    
    // This method get User Information but often use to get information for Admin or Manager because 
    // Resident Information and Coordinator Information have different table so they have getResident and get Coordinator instead
    public UserEntity getUser(int aid) throws SQLException {
        return userRepo.getUser(aid);
    }
    
    // This method to get List of all Resident for Admin Pages
    public ArrayList<AdminResidentListRequest> getAllResident() throws SQLException {
        return userRepo.getAllResident();
    }
    
    // Get Resident Information to see in the Profile Page
    public ResidentProfileRequest getResident(int aid) throws SQLException {
        return userRepo.getResident(aid);
    }
    
    public UserEntity getManagerOfBlock(int blockId) throws SQLException {
        return userRepo.getManagerOfBlock(blockId);
    }
    
    public void updateInfo(String username, String gender, int bid, String phone, int aid) throws SQLException{
        userRepo.updateProfile(username, gender, bid, phone, aid);
    }
    
    public String getUserSalt(String email) throws SQLException {
        return userRepo.getUserSalt(email);
    }
    
    public static void main(String[] args) throws Exception {
        UserService service = new UserService();
        System.out.println(service.getCountResident());
    }
}

