/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload.request;

import entity.OrderDetailEntity;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class OrderHeaderRequest {
    private int id;
    private int uid;
    private String residentName;
    private Date date;
    private Date delivery_time;
    private String status;
    private int eid;
    private String employeeName;
    private String note;
    HashMap<OrderDetailEntity, String> od;
    
    public OrderHeaderRequest() {
    }

    public OrderHeaderRequest(int id, int uid, String residentName, Date date, Date delivery_time, String status, int eid, String employeeName, String note, HashMap<OrderDetailEntity, String> od) {
        this.id = id;
        this.uid = uid;
        this.residentName = residentName;
        this.date = date;
        this.delivery_time = delivery_time;
        this.status = status;
        this.eid = eid;
        this.employeeName = employeeName;
        this.note = note;
        this.od = od;

    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getUid(){
        return uid;
    }
    
    public void setUid(int uid){
        this.uid = uid;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public HashMap<OrderDetailEntity, String> getOd() {
        return od;
    }

    public void setOd(HashMap<OrderDetailEntity, String> od) {
        this.od = od;
    }
    
    public double getTotal() {
        int total = 0;

        for (OrderDetailEntity od : this.od.keySet()) {
            total += od.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "OrderHeaderRequest{" + "id=" + id + ", residentName=" + residentName + ", date=" + date + ", status=" + status + ", employeeName=" + employeeName + ", note=" + note + '}';
    }
}
