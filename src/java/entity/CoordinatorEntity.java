
package entity;

public class CoordinatorEntity {
    private int CID;
    private boolean available;
    private String phone;
    private String email;
    private String name;
    private String gender;

    public CoordinatorEntity() {
    }

    public CoordinatorEntity(int CID, boolean available, String phone, String email, String name, String gender) {
        this.CID = CID;
        this.available = available;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.gender = gender;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}
