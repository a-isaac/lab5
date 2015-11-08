/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author a2isaac
 */
public class Manager extends UserProfile implements java.io.Serializable{
    
    public Manager() {
    //EFFECTS: returns new manager account
        super.setPass("admin");
        super.setUsername("admin");
        setRole();
    }

    @Override
    public void setRole() {
    super.role="Manager";
    }
}