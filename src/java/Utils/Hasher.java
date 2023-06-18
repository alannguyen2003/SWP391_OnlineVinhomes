/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Hasher {
    public static String doHashing(String password, String salt) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.reset();
            messageDigest.update(salt.getBytes());
            byte[] result = messageDigest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : result) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
       
        return "";
    }
    public static String createSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes.toString();
    }
    public static void main(String[] args) {
//        byte[] 
        System.out.println(Hasher.doHashing("123456", "[B@4b85612c"));
//System.out.println(Hasher.createSalt().toString());
    }
}
