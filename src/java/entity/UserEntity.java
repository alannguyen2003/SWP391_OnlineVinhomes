/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author vsngh
 */
public class UserEntity {

    private int AID;
    private String phone;
    private String email;
    private String password;
    private String name;
    private int BID;
    private int roleID;
    private String room;
    private String managerId;

    public UserEntity() {
    }

    public UserEntity(int AID, String phone, String email, String password, String name, int BID, int roleID) {
        this.AID = AID;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.BID = BID;
        this.roleID = roleID;
    }

    
    
    public UserEntity(int AID, String phone, String email, String password, String name, int BID, int roleID, String room, String managerId) {
        this.AID = AID;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.BID = BID;
        this.roleID = roleID;
        this.room = room;
        this.managerId = managerId;
    }

    

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBID() {
        return BID;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "AID=" + AID + ", phone=" + phone + ", email=" + email + ", password=" + password + ", name=" + name + ", BID=" + BID + ", roleID=" + roleID + ", room=" + room + ", managerId=" + managerId + '}';
    }
    
    

}
