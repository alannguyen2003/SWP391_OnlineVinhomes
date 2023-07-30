/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.RoleEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.RoleRepository;

/**
 *
 * @author admin
 */
public class RoleService {
    RoleRepository rp = new RoleRepository();
    
    public ArrayList<RoleEntity> getAllRole() throws Exception {
        return rp.getAllRole();
    }
    
    public ArrayList<RoleEntity> get2Role() throws Exception {
        return rp.get2Role();
    }
    
    public RoleEntity getRoleByRoleId(int bid) throws SQLException {
        return rp.getRoleByRoleId(bid);
    }
}
