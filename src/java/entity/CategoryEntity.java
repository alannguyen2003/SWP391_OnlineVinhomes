/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author acer
 */
public class CategoryEntity {
    private int id;
    private String name;

    public CategoryEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
