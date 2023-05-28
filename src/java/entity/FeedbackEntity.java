/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class FeedbackEntity {
    private int UID;
    private int DID;
    private String message;
    private String name;
    private String contactNumber;
    private String email;
    private int FID;

    public FeedbackEntity() {
    }

    public FeedbackEntity(int UID, int DID, String message, String name, String contactNumber, String email, int FID) {
        this.UID = UID;
        this.DID = DID;
        this.message = message;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.FID = FID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getDID() {
        return DID;
    }

    public void setDID(int DID) {
        this.DID = DID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    @Override
    public String toString() {
        return "FeedbackEntity{" + "UID=" + UID + ", DID=" + DID + ", message=" + message + ", name=" + name + ", contactNumber=" + contactNumber + ", email=" + email + ", FID=" + FID + '}';
    }

}
