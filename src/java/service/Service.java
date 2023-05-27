/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import entity.FeedbackEntity;
import java.sql.SQLException;
import repository.FeedbackRepository;
/**
 *
 * @author admin
 */
public class Service {
    private FeedbackRepository feedbackRepository = new FeedbackRepository();
    
    public void addFeedback(int UID, int DID, String message, String name, String contact, String email) throws SQLException{  
        feedbackRepository.addFeedback(UID, DID, message, name, contact, email);
    }
}
