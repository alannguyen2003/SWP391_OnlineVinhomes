/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author vsngh
 */
public class SaleEntity {
    private int id;
    private String name;
    private Date date;
    private String servicename;
    private double price;
    private String status;
    private String catename;
    private int countcate;
    private int sold;
    private double revenue;

    public SaleEntity() {
    }

    public SaleEntity(String catename, int countcate) {
        this.catename = catename;
        this.countcate = countcate;
    }

    public SaleEntity(int id, Date date, String servicename, String status) {
        this.id = id;
        this.date = date;
        this.servicename = servicename;
        this.status = status;
    }

    public SaleEntity(int id, String servicename, double price,int sold, double revenue) {
        this.id = id;
        this.servicename = servicename;
        this.price = price;
        this.sold = sold;
        this.revenue = revenue;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    
    
    
}
