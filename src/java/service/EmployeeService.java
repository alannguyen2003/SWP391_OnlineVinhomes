/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.EmployeeEntity;
import java.sql.SQLException;
import repository.EmployeeRepository;

public class EmployeeService {
    
    EmployeeRepository employeeRepo = new EmployeeRepository();
    
    public EmployeeEntity read(String aid) throws SQLException {
        return employeeRepo.read(aid);
    }
    
}
