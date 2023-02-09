package com.mycompany.carrentalsystem;

import java.util.Scanner;

/**
 * This class manages the details of the customer looking to hire a car.
 * @author leland
 */
public class Customer {
    private String customerName;     //Name of the customer
    private String customerEmail;    //Email address of the customer
    private String customerAddress;  //Address of the customer
   
    public Customer(){
        this.customerName = "";
        this.customerEmail = "";
        this.customerAddress = "";
    }
        
    public Customer(String customerName, String customerEmail, String customerAddress){
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
    }
    
    
    public void makeNewCustomer(){
        
        boolean flag = false;
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        
        // Request customer Name, Email and address
        System.out.println("\tPlease enter your name");    

        // Name
        if (input.hasNextLine()){
            this.customerName = input.nextLine();
        }

        // Email, check for valid email
        while(flag == false){
            System.out.println("\tPlease enter your email");
            if (input.hasNextLine()){
                String email = input.nextLine();
                if(email.indexOf('@') != -1){   
                    this.customerEmail = email;
                    flag = true;
                    break;
                }
                else{
                    continue;
                }
            }
        }
        // Address
        System.out.println("\tPlease enter your Address");
        if (input.hasNextLine()){
            this.customerAddress = input.nextLine();
        }         
        for(int i =0; i<73; i++){
            System.out.print("-");
        }
    }
    
    //Accessor method for ‘customerName’
    public String getCustomerName(){
        return(this.customerName);
    }
    
    //Accessor method for ‘customerEmail’
    public String getCustomerEmail(){
        return(this.customerEmail);
    }
    
    //Accessor method for ‘customerAddress’
    public String getCustomerAddress(){
        return(this.customerAddress);
    }
    
}
