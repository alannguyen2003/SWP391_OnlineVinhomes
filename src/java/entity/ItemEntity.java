/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class ItemEntity {
    private ServiceEntity service;
    private double lowerPrice;
    private double upperPrice;
    
    public ItemEntity(ServiceEntity service, double lowerPrice, double upperPrice){
        this.service = service;
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public double getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(double lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public double getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(double upperPrice) {
        this.upperPrice = upperPrice;
    }

    
}
