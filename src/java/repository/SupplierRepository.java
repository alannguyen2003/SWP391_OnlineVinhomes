/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.SupplierEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        while(rs.next()) {
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
    
}
