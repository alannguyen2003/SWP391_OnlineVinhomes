
package payload.request;

public class ResidentProfileRequest {
    
    private int AID;
    private String phone;
    private String email;
    private String name;
    private String gender;
    private String block;
    private String room;
    private int status;

    public ResidentProfileRequest() {
    }

    public ResidentProfileRequest(int AID, String email, String phone, String name, String gender, String block, String room) {
        this.AID = AID;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.gender = gender;
        this.block = block;
        this.room = room;
    }

    public ResidentProfileRequest(int AID, String phone, String email, String name, String gender, String block, String room, int status) {
        this.AID = AID;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.block = block;
        this.room = room;
        this.status = status;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
