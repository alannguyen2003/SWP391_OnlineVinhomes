/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.SupplierEntity;
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
}
