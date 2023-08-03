
package entity;

import java.util.Date;


public class OrderHeaderEntity {
    private int id;
    private Date date;
    private Date delivery_time;
    private String status;
    private int residentId;
    private int coordinatorID;
    private int blockId;
    private String note;

    public OrderHeaderEntity() {
    }

    public OrderHeaderEntity(int id, Date date, Date delivery_time, String status, int residentId, int coordinatorID, int blockId, String note) {
        this.id = id;
        this.date = date;
        this.delivery_time = delivery_time;
        this.status = status;
        this.residentId = residentId;
        this.coordinatorID = coordinatorID;
        this.blockId = blockId;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public int getCoordinatorID() {
        return coordinatorID;
    }

    public void setCoordinatorID(int coordinatorID) {
        this.coordinatorID = coordinatorID;
    }

    public int getBlockid() {
        return blockId;
    }

    public void setBlockid(int blockId) {
        this.blockId = blockId;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderHeaderEntity{" + "id=" + id + ", date=" + date + ", delivery_time=" + delivery_time + ", status=" + status + ", residentId=" + residentId + ", coordinatorID=" + coordinatorID + ", blockId=" + blockId + ", note=" + note + '}';
    }
    
}
