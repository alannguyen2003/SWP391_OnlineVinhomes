package entity;

public class OrderDetailEntity {

    private int id;
    private int orderHeaderId;
    private int serviceId;
    private int categoryId;
    private int price;
    private int supplierId;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(int id, int orderHeaderId, int serviceId, int categoryId, int price, int supplierId) {
        this.id = id;
        this.orderHeaderId = orderHeaderId;
        this.serviceId = serviceId;
        this.categoryId = categoryId;
        this.price = price;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderHeaderId(int orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    
}
