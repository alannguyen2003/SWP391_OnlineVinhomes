/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import java.sql.*;
import entity.*;
import java.util.ArrayList;
/**
 *
 * @author acer
 */
public class CategoryRepository {
    
    // Get List of Full Category
    public ArrayList<CategoryEntity> getAllCategory() throws Exception {
        ArrayList<CategoryEntity> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Category";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while(rs.next()) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(rs.getInt(1));
            categoryEntity.setName(rs.getString(2));
            list.add(categoryEntity);
        }
        return list;
    }
    
    // Get List of Top 3 Category
    public ArrayList<CategoryEntity> getTopCategory() throws Exception {
        ArrayList<CategoryEntity> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select top 3 * from Category";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while(rs.next()) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(rs.getInt(1));
            categoryEntity.setName(rs.getString(2));
            list.add(categoryEntity);
        }
        return list;
    }
    
    public CategoryEntity getServiceByCategoryId(int id) throws SQLException {
        String query = "select * from Category where CID = ?";
        CategoryEntity cate = new CategoryEntity();
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
                cate.setId(rs.getInt("CID"));
                cate.setName(rs.getString("name"));
                
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
        return cate;
    }
    
    
    public static void main(String[] args) throws Exception {
        CategoryRepository categoryRepository = new CategoryRepository();
        for (CategoryEntity item : categoryRepository.getTopCategory()) {
            System.out.println(item);
        }
    }
}
