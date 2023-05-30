
package entity;

import java.util.Date;


public class OrderHeaderEntity {
    private int id;
    private Date date;
    private String status;
    private int residentId;
    private int employeeId;
    private String note;

    public OrderHeaderEntity() {
    }

    public OrderHeaderEntity(int id, Date date, String status, int residentId, int employeeId, String note) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.residentId = residentId;
        this.employeeId = employeeId;
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
