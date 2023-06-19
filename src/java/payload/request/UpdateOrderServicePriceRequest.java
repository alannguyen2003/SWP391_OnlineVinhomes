/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload.request;

/**
 *
 * @author Quoc
 */
public class UpdateOrderServicePriceRequest {
    private int id;
    private int serviceID;
    private String name;
    private int minPrice;
    private int maxPrice;

    public UpdateOrderServicePriceRequest() {
    }

    public UpdateOrderServicePriceRequest(int id, int serviceID, String name, int minPrice, int maxPrice) {
        this.id = id;
        this.serviceID = serviceID;
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "UpdateOrderServicePriceRequest{" + "id=" + id + ", serviceID=" + serviceID + ", name=" + name + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + '}';
    }
}
