/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobBoard;
import org.mindrot.jbcrypt.BCrypt;
// loc: http://www.mindrot.org/projects/jBCrypt/#download
/**
 *
 * @author nahom
 */
public class PasswordOprations {
 
    public static String hashPassword(String password) {
        // Generate a salt
        String salt = BCrypt.gensalt();

        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, salt);

        return hashedPassword;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        // Compare the entered password with the hashed password
        return BCrypt.checkpw(password, hashedPassword);
    }
}
