/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.CoordinatorEntity;
import entity.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Quoc
 */
public class CoordinatorRepository {

    // This method to get All Coordinator which is available for assign to Order
    public ArrayList<CoordinatorEntity> getAvailableCoordinator() throws Exception {
        ArrayList<CoordinatorEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = ("select * from Coordinator c \n"
                    + "JOIN dbo.Account a \n"
                    + "ON a.AID = c.ID\n"
                    + "WHERE c.enabled = 1");
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            CoordinatorEntity entity = new CoordinatorEntity();
            entity.setCID(rs.getInt(1));
            entity.setAvailable(rs.getBoolean(2));
            entity.setPhone(rs.getString(4));
            entity.setEmail(rs.getString(5));
            entity.setName(rs.getString(7));
            entity.setGender(rs.getString(8));
            list.add(entity);
        }
        return list;
    }
    
        // This method to get All Coordinator for Admin Manage Coordinator
    public ArrayList<CoordinatorEntity> getAllCoordinator() throws Exception {
        ArrayList<CoordinatorEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = ("select * from Coordinator c \n"
                    + "JOIN dbo.Account a \n"
                    + "ON a.AID = c.ID");
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            CoordinatorEntity entity = new CoordinatorEntity();
            entity.setCID(rs.getInt(1));
            entity.setAvailable(rs.getBoolean(2));
            entity.setPhone(rs.getString(4));
            entity.setEmail(rs.getString(5));
            entity.setName(rs.getString(7));
            entity.setGender(rs.getString(8));
            list.add(entity);
        }
        return list;
    }
    
    public static void main(String[] args) throws Exception {
        CoordinatorRepository cdr = new CoordinatorRepository();
        cdr.getAvailableCoordinator();
    }
}
