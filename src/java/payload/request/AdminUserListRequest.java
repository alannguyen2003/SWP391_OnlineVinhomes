package payload.request;

public class AdminUserListRequest {
    
    private int AID;
    private String phone;
    private String email;
    private String name;
    private String gender;
    private String role;
    private boolean status;

    public AdminUserListRequest() {
    }

    public AdminUserListRequest(int AID, String phone, String email, String name, String gender, String role, boolean status) {
        this.AID = AID;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.status = status;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
