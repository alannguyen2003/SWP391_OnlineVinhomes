/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.HashMap;

public class SupplierServiceEntity {
    
    private ServiceEntity se;
    private HashMap<SupplierEntity, String> sle;

    public SupplierServiceEntity() {
    }

    public SupplierServiceEntity(ServiceEntity se, HashMap<SupplierEntity, String> sle) {
        this.se = se;
        this.sle = sle;
    }

    public ServiceEntity getSe() {
        return se;
    }

    public void setSe(ServiceEntity se) {
        this.se = se;
    }

    public HashMap<SupplierEntity, String> getSle() {
        return sle;
    }

    public void setSle(HashMap<SupplierEntity, String> sle) {
        this.sle = sle;
    }
    
    
}
