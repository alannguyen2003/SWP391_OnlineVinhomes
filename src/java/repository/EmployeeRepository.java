/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.EmployeeEntity;
import entity.ResidentEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {
    
    public EmployeeEntity read(String aid) throws SQLException {
        EmployeeEntity employee = new EmployeeEntity();

        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("select * from employee where  AID = ?");
        pstm.setString(1, aid);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            employee.setAID(rs.getInt("AID"));
            employee.setManager_id(rs.getInt("manager_id"));
            employee.setSalary(rs.getDouble("salary"));
        }
        con.close();
        return employee;
    }
    
}
