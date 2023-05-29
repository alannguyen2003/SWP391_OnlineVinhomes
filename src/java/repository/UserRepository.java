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

/**
 *
 * @author vsngh
 */
public class UserRepository {
    
    Connection connect = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
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
            System.err.println("Error at Login UserFacade");
        }
        return null;
    }
    
    public UserEntity Signup(String username, String address, String phone, String email, String password) throws SQLException {
        //String query = "insert into account values(?,?,?,?,?,?,?)";
        String query = "insert into Account values (?, ?, ?, ?, ?, 'ROLE_CUSTOMER')";

        try {
            connect = new DBConfig().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error at Signup UserFacade");
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
            System.err.println("Error at Check User Facade");
        }
        return null;
    }
    
}
