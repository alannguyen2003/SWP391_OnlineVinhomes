/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.RoleEntity;
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
}
