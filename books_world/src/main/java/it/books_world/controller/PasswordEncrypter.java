package it.books_world.controller;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypter {
    
    private static PasswordEncrypter instance = new PasswordEncrypter();
    private PasswordEncrypter(){}
    public static PasswordEncrypter getInstance() { return instance; }

    public String encryptPassword(String password){
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return encryptedPassword;
    }
}
