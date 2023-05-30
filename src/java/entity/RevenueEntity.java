
package entity;

public class RevenueEntity {
    private String label;
    private double total;

    public RevenueEntity() {
    }

    public RevenueEntity(String label, double total) {
        this.label = label;
        this.total = total;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
