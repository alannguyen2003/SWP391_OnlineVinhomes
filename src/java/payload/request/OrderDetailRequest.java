/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload.request;

/**
 *
 * @author acer
 */
public class OrderDetailRequest {
    private int id;
    private String service;
    private String supplier;
    private int price;
    private String note;

    public OrderDetailRequest() {
    }

    public OrderDetailRequest(int id, String service, String supplier, int price, String note) {
        this.id = id;
        this.service = service;
        this.supplier = supplier;
        this.price = price;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderDetailRequest{" + "id=" + id + ", service=" + service + ", supplier=" + supplier + ", price=" + price + ", note=" + note + '}';
    }
    
    
}
