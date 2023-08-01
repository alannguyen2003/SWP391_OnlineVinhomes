/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload.request;

public class AdminResidentListRequest {

    private int AID;
    private String phone;
    private String email;
    private String name;
    private String gender;
    private String role;
    private boolean status;
    private String block;
    private String room;

    public AdminResidentListRequest() {
    }

    public AdminResidentListRequest(int AID, String phone, String email, String name, String gender, String role, boolean status, String block, String room) {
        this.AID = AID;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.block = block;
        this.room = room;
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
    
    

}
