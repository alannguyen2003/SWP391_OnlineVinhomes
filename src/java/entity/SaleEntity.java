/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author vsngh
 */
public class SaleEntity {
    private int id;
    private String name;
    private String servicename;
    private double price;
    private String status;
    private String catename;
    private int countcate;

    public SaleEntity() {
    }

    public SaleEntity(String catename, int countcate) {
        this.catename = catename;
        this.countcate = countcate;
    }
    
    

    public SaleEntity(int id, String name, String servicename, double price, String status) {
        this.id = id;
        this.name = name;
        this.servicename = servicename;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public int getCountcate() {
        return countcate;
    }

    public void setCountcate(int countcate) {
        this.countcate = countcate;
    }
    
    
    
}
