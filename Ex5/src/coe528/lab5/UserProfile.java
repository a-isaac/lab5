/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author a2isaac
 */
public abstract class UserProfile {
    
    private String pass;
    private String username;
    protected String role;
    
    public void setPass(String pass) {
    //MODIFIES: creates the account password
    //EFFECTS: returns the new account password
        this.pass = pass;
    }

    public String getPass() {
    //EFFECTS: returns the account password    
        return pass;
    }

    public String getRole() {
    //EFFECTS: returns the type of account    
        return role;
    }

    public String getUsername() {
   //EFFECTS: returns the account username   
        return username;
    }

    public void setUsername(String username) {
    //MODIFIES: creates the account username
    //EFFECTS: returns the new account username
        this.username = username;
    }

    public abstract void setRole();

    
}
