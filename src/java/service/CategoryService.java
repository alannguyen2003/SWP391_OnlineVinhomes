/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.CategoryEntity;
import java.util.ArrayList;
import repository.CategoryRepository;

/**
 *
 * @author acer
 */
public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();
    
    public ArrayList<CategoryEntity> getAllCategory() throws Exception {
        return categoryRepository.getAllCategory();
    }
    
    public static void main(String[] args) throws Exception {
        CategoryService categoryService = new CategoryService();
        for (CategoryEntity categoryEntity : categoryService.getAllCategory()) {
            System.out.println(categoryEntity);
        }
            
    }
}
