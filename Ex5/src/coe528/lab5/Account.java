/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author a2isaac
 */
public abstract class Account {
   private int amount;
   
    protected void setAmount(int t1) {
    //REQUIRES: A non negative integer
    //MODIFIES: Changes the value in amount
    //EFFECTS: returns the new amount
        if(t1>=0)
        this.amount = t1;
    }

    public int getAmount() {
    //EFFECTS: returns amount
        return amount;
    }
   
    
}
