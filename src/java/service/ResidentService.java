/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ResidentEntity;
import java.sql.SQLException;
import repository.ResidentRepository;

public class ResidentService {
    
    ResidentRepository residentRepo = new ResidentRepository();
    
    public ResidentEntity read(String aid) throws SQLException {
        return residentRepo.read(aid);
    }
}
