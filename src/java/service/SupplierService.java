/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.SupplierEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.SupplierRepository;

/**
 *
 * @author Quoc
 */
public class SupplierService {
    
    private SupplierRepository supplierRepo = new SupplierRepository();
    
    public ArrayList<SupplierEntity> getAllSupplier() throws Exception {
        return supplierRepo.getAllSupplier();
    }
    
    public ArrayList<SupplierEntity> searchByName(String name) throws Exception {
        return name.isBlank()? supplierRepo.getAllSupplier() : supplierRepo.searchByName(name);
    }
    
    public String getSupplierEmail(int id) throws SQLException {
        return supplierRepo.getSupplierEmail(id);
    }
    
    public static void main(String[] args) throws Exception {
        SupplierService service = new SupplierService();
        for (SupplierEntity entity : service.searchByName("home")) {
            System.out.println(entity);
        }
        System.out.println(service.getSupplierEmail(1));
    }
}
