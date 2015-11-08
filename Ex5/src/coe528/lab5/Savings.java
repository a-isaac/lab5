/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author a2isaac
 */
public class Savings extends Account implements java.io.Serializable{

    public Savings(int a) {    
    //REQUIRES: a integer that is greater than or equal to 20
    //MODIFIES: Changes the value in amount
    //EFFECTS: returns the new amount  
       if(a>=20)
          super.setAmount(a); 
       else System.out.println("Deposit amounts smaller than 20 dollars not allowed!\n");
    }
    public int statusSavings(){
    //EFFECTS: returns amount
       return super.getAmount();
    }    
}