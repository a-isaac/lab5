/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author a2isaac
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

public final class UserInterface {

    UserProfile temp;
    private static int commandInt;
    private static String commands;

    public static void write(UserProfile a) throws FileNotFoundException, IOException {
        BufferedWriter out;
        if (!find(a.getUsername())) {
            try {
                //replace helloworld.txt with the name of the file
                out = new BufferedWriter(new FileWriter("accounts.txt", true));
                //Write out the specified string to the file
                out.write(a.getUsername() + " ");
                out.write(a.getPass() + " ");
                out.write(a.getRole() + " ");
                if (a.getRole().equals("Customer")) {
                    Customer b = (Customer) a;
                    out.write(b.getFirst().getAmount() + " ");
                    if (null != b.getSecond()) {
                        out.write(b.getSecond().getAmount() + " ");
                    }
                }

                out.newLine();
                out.close();//flushes and closes the stream

            } catch (IOException e) {
                System.out.println("There was a problem writing to file:" + e);
            }
        }
        else  System.out.println("Username already in use.\n");
    }

    public static Customer read(String name, String pass) {
        Customer c1 = null;
        int a, b = -100;
        Chequing first;
        Savings second;
        BufferedReader in;
        String read = null;
        try {     //open a bufferedReader to file helloworld.txt
            in = new BufferedReader(new FileReader("accounts.txt"));
            //read a line from accounts.txt and save into a string
            while ((read = in.readLine()) != null) {
                //Split the line and save the variables
                String[] arr = read.split(" ");
                //Extracting all the user information
                if (arr[0].equals(name)) {
                    a = parseInt(arr[3]);
                    first = new Chequing(a);
                    if (arr.length > 4) {
                        b = parseInt(arr[4]);
                        second = new Savings(b);
                        c1 = new Customer(name, pass, first, first.getAmount(), second, second.getAmount());
                    } else {
                        c1 = new Customer(name, pass, first, first.getAmount());
                    }
                }

            }
          
            //safely close the BufferedReader after use
            in.close();
        } catch (IOException e) {
            System.out.println("There was a problem:" + e);
        }
        return c1;

    }

    public static void delete(String name) throws FileNotFoundException, IOException {
        File inputFile = new File("accounts.txt");   // Your file  
        File tempFile = new File("Temp.txt");// temp file

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {

            if (currentLine.contains(name)) {
                continue;
            }
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();
       
        reader=new BufferedReader(new FileReader(tempFile));
        writer = new BufferedWriter(new FileWriter(inputFile));
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine);
            writer.newLine();
        }
        writer.close();
        reader.close();
    }

    public UserInterface() throws IOException {
        this.temp = new Manager();//Initializing the bank by creating Manager account
        File f = new File("accounts.txt");
        if (!f.exists()) {
            write(temp);
        }
    }

    private static String userInput() {
        Scanner user_input = new Scanner(System.in);
        commands = user_input.next();
//      System.out.print("This is what Test1 is :" + commands);
        return commands;
    }

    private static int userInputInt() {
        Scanner user_input = new Scanner(System.in);
        commandInt = user_input.nextInt();
    //    System.out.print("This is what Test1 is :" + commandInt);
        return commandInt;
    }

    public boolean authenticate(String name, String pass, String type) {
        BufferedReader in;
        String read = null;
        try {     //open a bufferedReader to file accounts.txt
            in = new BufferedReader(new FileReader("accounts.txt"));
            //read a line from accounts.txt and save into a string
            while ((read = in.readLine()) != null) {
                //Split the line and save the variables
                String[] arr = read.split(" ");
                //Extracting all the user information
                if (arr[0].equals(name)) {
                    if (arr[1].equals(pass) && arr[2].equals(type)) {
                        if (type.equals("Customer")) {
                            System.out.println("To view balance of Chequing account input 'vc'\n"
                                    + "To view balance of Savings account input 'vs'\n"
                                    + "To transfer money from chequeing to savings unput 'transfer'\n");
                        } else {
                            System.out.println("To add user with Chequing account input 'add1'\n"
                                    + "To add user with both accounnts input 'add2'\n"
                                    + "To delete user input del Username/\n");
                        }
                        return true;
                    }
                }
            }
            System.out.println("file output: " + read);
            //safely close the BufferedReader after use
            in.close();
        } catch (IOException e) {
            System.out.println("There was a problem while reading:" + e);
        }

        System.out.println(
                "User not found\n");
        return false;
    }

    public static boolean find(String name) {
        BufferedReader in;
        String read = null;
        try {     //open a bufferedReader to file helloworld.txt
            in = new BufferedReader(new FileReader("accounts.txt"));
            //read a line from accounts.txt and save into a string
            while ((read = in.readLine()) != null) {
                //Split the line and save the variables
                String[] arr = read.split(" ");
                //Extracting all the user information
                if (arr[0].equals(name)) {
                    return true;
                }
            }
            in.close();
            return false;
        } catch (IOException e) {
            System.out.println("There was a problem while reading:" + e);
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String name, password;
        String input = null;
        UserInterface ABCbank = new UserInterface();

        String tmp1, tmp2;
        int tmp10, tmp20;

        while (true) {
            System.out.println("Enter m for Manager or c for Customer\n");
            input = userInput();
            
            if (!input.equals("m") && !input.equals("c"))
                System.out.println("Please input account type (m or c)\n");
            
            else if (input.equals("m")) {
                System.out.println("Please enter username \n");
                name = userInput();
                System.out.println("Please enter password....\n");
                password = userInput();
                
                //Manager Component-----------------------------
                if (ABCbank.authenticate(name, password, "Manager")) {

                    input = userInput();
                    while (!input.equals("logout")) {
                        if (input.equals("add1")) {
                            tmp1 = null;
                            tmp2 = null;
                            tmp10 = -1;
                            System.out.println("Enter New Customer Name: ");
                            tmp1 = userInput();
                            System.out.println("Enter New Password: ");
                            tmp2 = userInput();
                            System.out.println("Enter amount to Cheq: ");
                            tmp10 = userInputInt();
                            Chequing temp1 = null;
                            Customer custTemp1 = new Customer(tmp1, tmp2, temp1, tmp10);
                            write(custTemp1);
                        } 
                        
                        else if (input.equals("add2")) {
                            tmp1 = null;
                            tmp2 = null;
                            tmp10 = -1;
                            System.out.println("Enter New Customer Name: ");
                            tmp1 = userInput();
                            System.out.println("Enter New Password: ");
                            tmp2 = userInput();
                            System.out.println("Enter amount in Cheq: ");
                            tmp10 = userInputInt();
                            System.out.println("Enter amount in Saving: ");
                            tmp20 = userInputInt();
                            Chequing temp1 = null;
                            Savings sav1 = null;
                            Customer custTemp1 = new Customer(tmp1, tmp2, temp1, tmp10, sav1, tmp20);
                            write(custTemp1);
                        } 
                        
                        else if (input.equals("del")) {
                            System.out.println("Enter Customer Name that will be deleted:\n");
                            tmp1 = userInput();
                            delete(tmp1);
                        } 
                        
                        else
                            System.out.println("Enter correct command!....\n");
                       
                        System.out.println("Input next command....\n");
                        input = userInput();
                    }
                }
            } else {

                //Customer Component----------------------------------
                System.out.println("Please enter username \n");
                name = userInput();
               if(!find(name)) {
                    System.out.println("Login Denied....\n");
                    continue;
               }
                    System.out.println("Please enter password....\n");
                    password = userInput();
                    if (ABCbank.authenticate(name, password, "Customer")) {
                    Customer c2 = read(name, password);
                    input = userInput();
                    
                    while (!input.equals("logout")) {
                        if (input.equals("vc")) {
                            System.out.println("Chequeing account balance is :" + c2.getFirst().getAmount() + "\n");
                        } else if (input.equals("vs")) {
                            if (null == c2.getSecond()) {
                                System.out.println("This operation is not possible\n");
                            } else {
                                System.out.println("Chequeing account balance is :" + c2.getSecond().getAmount() + "\n");
                            }
                        } else if (input.equals("transfer")) {
                            System.out.println("Enter Transfer amount....\n");
                            tmp20 = userInputInt();
                            if (null == c2.getSecond()) {
                                System.out.println("This operation is not possible\n");
                            } else {
                                c2.transferToSavings(tmp20);
                                delete(c2.getUsername());
                                write(c2);
                            }
                        } else {
                            System.out.println("Enter correct command!....\n");
                        }
                        System.out.println("Input next command....\n");
                        input = userInput();
                    }
                }
            }

            if (input.equals("end")) {
                break;
            }

        }

    }

}