/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload.request;

import entity.OrderDetailEntity;
import java.util.Date;
import java.util.HashMap;

public class AdminOrderListRequest {
    private int id;
    private int RID;
    private String residentName;
    private int CID;
    private String coordinatorName;
    private Date date;
    private Date delivery_time;
    private String status;
    private String note;
    private int BID;
    private String block;
    private String room;
    HashMap<OrderDetailEntity, String> od;
    
    public AdminOrderListRequest() {
    }

    public AdminOrderListRequest(int id, int RID, String residentName, int CID, String coordinatorName, Date date, Date delivery_time, String status, String note, int BID, String block, String room, HashMap<OrderDetailEntity, String> od) {
        this.id = id;
        this.RID = RID;
        this.residentName = residentName;
        this.CID = CID;
        this.coordinatorName = coordinatorName;
        this.date = date;
        this.delivery_time = delivery_time;
        this.status = status;
        this.note = note;
        this.BID = BID;
        this.block = block;
        this.room = room;
        this.od = od;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getBID() {
        return BID;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
     
    
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
    
}
