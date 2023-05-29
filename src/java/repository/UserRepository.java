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
        String query = "select * from Account where email=? and password=?";
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
                        rs.getInt(5)  );
            }
        } catch (SQLException ex) {
            System.err.println("Error at Login");
        }
        return null;
    }
    
    public ResidentEntity Login2(String email, String password) throws SQLException {
        //select * from tbl User where email = ? and password=?
        try {
            connect = new DBConfig().getConnection();
            ps = connect.prepareStatement(queryResident);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ResidentEntity(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9)
                        );
            }
        } catch (SQLException ex) {
            System.err.println("Error at Login Resident");
        }
        return null;
    }
    
    
    
    public UserEntity Check(String email) throws SQLException {
        //select * from tbl User where email = ? and password=?
        String query = "select * from Account where email=?";
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
                        rs.getInt(5)  );
            }
        } catch (SQLException ex) {
            System.err.println("Error at Check User");
        }
        return null;
    }
    
}
