/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author a2isaac
 */
public class Chequing extends Account implements java.io.Serializable {

    public Chequing(int a) {
    //REQUIRES: a integer that is greater than or equal to 20
    //MODIFIES: Changes the value in amount
    //EFFECTS: returns the new amount   
        if(a>=20)
          super.setAmount(a); 
    }   
    
        public int statusSavings(){
        //EFFECTS: returns amount
          return super.getAmount();
            }
    
}