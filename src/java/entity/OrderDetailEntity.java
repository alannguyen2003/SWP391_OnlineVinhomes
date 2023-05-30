package entity;
public class OrderDetailEntity {
    
    private int id;
    private int orderHeaderId;
    private int serviceId;
    private int categoryId;
    private double minPrice;
    private double maxPrice;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(int id, int orderHeaderId, int serviceId, int categoryId, double minPrice, double maxPrice) {
        this.id = id;
        this.orderHeaderId = orderHeaderId;
        this.serviceId = serviceId;
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
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

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
    
}
