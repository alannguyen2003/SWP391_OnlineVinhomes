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
    
    public static void main(String[] args) throws Exception {
        CategoryRepository categoryRepository = new CategoryRepository();
        for (CategoryEntity item : categoryRepository.getAllCategory()) {
            System.out.println(item);
        }
    }
}
