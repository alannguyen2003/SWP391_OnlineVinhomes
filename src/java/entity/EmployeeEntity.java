/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class EmployeeEntity {
    
    private int AID;
    private double salary;
    private int manager_id;

    public EmployeeEntity() {
    }

    public EmployeeEntity(int AID, double salary, int manager_id) {
        this.AID = AID;
        this.salary = salary;
        this.manager_id = manager_id;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
    
    
    
}
