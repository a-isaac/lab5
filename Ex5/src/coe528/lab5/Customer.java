/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author a2isaac
 */
public class Customer extends UserProfile implements java.io.Serializable {

    private Account first, second = null;

    public Customer(String name, String passw, Chequing a, int b) {
    //EFFECTS: returns new cusomer with only chequing account
        super.setPass(passw);
        setRole();
        super.setUsername(name);
        a = new Chequing(b);
        first = a;
    }

    public Customer(String name, String passw, Chequing a, int b, Savings c, int d) {
    //EFFECTS: returns new cusomer with both chequing and savings accounts
        super.setPass(passw);
        setRole();
        super.setUsername(name);
        a = new Chequing(b);
        first = a;
        c = new Savings(d);
        second = c;
    }

    @Override
    public void setRole() {
        super.role = "Customer";
    }

    public Account getFirst() {
    //EFFECTS: returns chequing account
        return first;
    }

    public Account getSecond() {
    //EFFECTS: returns savings account    
        return second;
    }
    

    public void transferToSavings(int q) {
    //REQUIRES: an integer that is less or equal to amount in chequing
    //MODIFIES: Changes the value in chequing and savings accounts
    //EFFECTS: returns the new amount in chequing and savings accounts    
        int cheqAmount = this.first.getAmount() - q;
        int savAmount = this.second.getAmount() + q;
        if(cheqAmount<0)
            System.out.println("Insufficient funds in the chequing account\n");
        else{
            this.first.setAmount(cheqAmount);
            this.second.setAmount(savAmount);
        }
    }

}
