/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Source;
import entity.UserEntity;
import entity.ResidentEntity;

/**
 *
 * @author vsngh
 */
public class UserRepository {
    
    Connection connect = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String queryResident = "select Account.AID, Resident.name,Account.email, Account.password, "
            + "Account.phone ,Resident.room, Resident.UID, Resident.BID, Account.roleId "
            + "from Account join Resident on Resident.AID = Account.AID where email =? and password =?";
    
    public UserEntity Login(String email, String password) throws SQLException {
        //select * from tbl User where email = ? and password=?
        String query = """
                       select Account.AID, Account.phone, Account.email, Account.password, Account.name, Account.BID, Account.roleId, Resident.room, Employee.manager_id 
                         from Account left join Resident on Account.AID = Resident.AID left join Employee on Account.AID = Employee.AID where email=? and password=?""";
        try {
            connect = new DBConfig().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new UserEntity(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)
                );
            }
        } catch (SQLException ex) {
            System.err.println("Error at Login");
        }
        return null;
    }
     
    
    
    
    public UserEntity Check(String email) throws SQLException {
        //select * from tbl User where email = ? and password=?
        String query = """
                       select Account.AID, Account.phone, Account.email, Account.password, Account.name, Account.BID, Account.roleId, Resident.room, Employee.manager_id 
                         from Account left join Resident on Account.AID = Resident.AID left join Employee on Account.AID = Employee.AID where email=? """;
        try {
            connect = new DBConfig().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new UserEntity(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            System.err.println("Error at Check User");
        }
        return null;
    }
    
    public void changePass(String email, String password) throws SQLException {
        String query = "update Account set password = ? where email = ?";
        connect = DBConfig.getConnection();
        ps = connect.prepareStatement(query);
        ps.setString(2, email);
        ps.setString(1, password);
        ps.executeUpdate();
        connect.close();
        
    }
    
    public void createAccount(String phone, String email, String password, String name, int blockId, int roleId) throws SQLException{
        String query = "insert into Account values(?,?,?,?,?,?)";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, phone);
        stm.setString(2, email);
        stm.setString(3, password);
        stm.setString(4, name);
        stm.setInt(5, blockId);
        stm.setInt(6, roleId);
        stm.executeUpdate();
        con.close();
    }
    
    
    public static void main(String[] args) throws SQLException {
       UserRepository rep = new UserRepository();
       rep.createAccount("1234567890", "a@gmail.com", "123456", "Manager", 3, 3);
    }
    
}
