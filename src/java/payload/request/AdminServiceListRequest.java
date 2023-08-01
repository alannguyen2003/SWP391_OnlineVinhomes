/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload.request;

/**
 *
 * @author Quoc
 */
public class AdminServiceListRequest {
    
    private int serviceID;
    private String name;
    private String description;
    private double lowerPrice;
    private double upperPrice;
    private int categoryID;
    private String categoryName;
    private double rated;

    public AdminServiceListRequest() {
    }

    public AdminServiceListRequest(int serviceID, String name, String description, double lowerPrice, double upperPrice, int categoryID, String categoryName, double rated) {
        this.serviceID = serviceID;
        this.name = name;
        this.description = description;
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.rated = rated;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(double lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public double getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(double upperPrice) {
        this.upperPrice = upperPrice;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getRated() {
        return rated;
    }

    public void setRated(double rated) {
        this.rated = rated;
    }
    
    
    
}
