/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.RoleEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;


/**
 *
 * @author admin
 */
public class RoleRepository {
    public ArrayList<RoleEntity> getAllRole() throws SQLException {
        ArrayList<RoleEntity> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Role";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while(rs.next()) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(rs.getInt(1));
            roleEntity.setName(rs.getString(2));
            list.add(roleEntity);
        }
        return list;
    }
    
    public static void main(String[] args) throws SQLException {
        RoleRepository rp = new RoleRepository();
        for(RoleEntity r : rp.getAllRole()){
            System.out.println(r);
        }
    }
}
