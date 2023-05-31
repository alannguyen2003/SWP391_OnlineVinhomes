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
 * @author admin
 */
public class HomeService {
    private CategoryRepository categoryRepository = new CategoryRepository();
    
    public ArrayList<CategoryEntity> getTopCategory() throws Exception {
        return categoryRepository.getTopCategory();
    }
}
