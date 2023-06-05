/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.ServiceEntity;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author acer
 */
public class ServiceRepository {

    public ArrayList<ServiceEntity> getAllService() throws Exception {
        ArrayList<ServiceEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Service";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            ServiceEntity entity = new ServiceEntity();
            entity.setServiceID(rs.getInt(1));
            entity.setName(rs.getString(2));
            entity.setDescription(rs.getString(3));
            entity.setLowerPrice(rs.getDouble(4));
            entity.setUpperPrice(rs.getDouble(5));
            entity.setSupplierID(rs.getInt(6));
            entity.setCategoryID(rs.getInt(7));
            list.add(entity);
        }
        return list;
    }

    public ArrayList<ServiceEntity> getServiceByCategory(int categoryId) throws Exception {
        ArrayList<ServiceEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Service \n"
                    + "where category_id = ?";
            pst = cn.prepareStatement(query);
            pst.setInt(1, categoryId);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            ServiceEntity entity = new ServiceEntity();
            entity.setServiceID(rs.getInt(1));
            entity.setName(rs.getString(2));
            entity.setDescription(rs.getString(3));
            entity.setLowerPrice(rs.getDouble(4));
            entity.setUpperPrice(rs.getDouble(5));
            entity.setSupplierID(rs.getInt(6));
            entity.setCategoryID(rs.getInt(7));
            list.add(entity);
        }
        return list;
    }

    public ServiceEntity getServiceById(int id) throws SQLException {
        String query = "select * from service where service_id = ?";
        ServiceEntity service = new ServiceEntity();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBConfig.getConnection();
            pre = con.prepareStatement(query);
            // code go la phai nam duoi pre = con.pre
            pre.setInt(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                // output tu database
                service.setServiceID(rs.getInt("service_id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setLowerPrice(rs.getDouble("lower_price"));
                service.setUpperPrice(rs.getDouble("upper_price"));
                service.setSupplierID(rs.getInt("supplier_id"));
                service.setCategoryID(rs.getInt("category_id"));
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (rs != null) {
                rs.close();
            }

        }
        return service;
    }
    
    
    
    
    
    public static void main(String[] args) throws Exception {
        ServiceRepository repository = new ServiceRepository();
        System.out.println(repository.getServiceById(1));
    }

}
