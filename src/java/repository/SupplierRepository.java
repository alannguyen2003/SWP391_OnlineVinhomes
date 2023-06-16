/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.SupplierEntity;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierRepository {

    public ArrayList<SupplierEntity> getAllSupplier() throws Exception {
        ArrayList<SupplierEntity> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Supplier";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            SupplierEntity supplierEntity = new SupplierEntity();
            supplierEntity.setId(rs.getInt(1));
            supplierEntity.setName(rs.getString(2));
            supplierEntity.setPhone(rs.getString(3));
            supplierEntity.setEmail(rs.getString(4));
            supplierEntity.setAddress(rs.getString(5));
            list.add(supplierEntity);
        }
        return list;
    }

    public ArrayList<SupplierEntity> searchByName(String name) throws Exception {
        ArrayList<SupplierEntity> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Supplier \n"
                    + "where name like ?";
            pst = cn.prepareStatement(query);
            pst.setString(1, "%" + name + "%"); 
            rs = pst.executeQuery();
        }
        if (rs != null) {
            while (rs.next()) {
                SupplierEntity entity = new SupplierEntity();
                entity.setId(rs.getInt(1));
                entity.setName(rs.getString(2));
                entity.setPhone(rs.getString(3));
                entity.setEmail(rs.getString(4));
                entity.setAddress(rs.getString(5));
                list.add(entity);
            }
        }
        return list;
    }
    
    public String getSupplierEmail(int id) throws SQLException {
        String email = "";
        
        String query = "select email from Supplier where SID = ?";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            email += rs.getString("email");
        }
        return email;
    }
    
    public static void main(String[] args) throws Exception {
        SupplierRepository repository = new SupplierRepository();
        for (SupplierEntity entity : repository.searchByName("home")) System.out.println(entity);
    }

}
