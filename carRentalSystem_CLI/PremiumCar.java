
package com.mycompany.carrentalsystem;

/**
 *This class is an extension of the Car class. As such, all existing 
 * functionality is automatically inherited. This documentation lists 
 * additions and differences only
 * @author leland
 */
public class PremiumCar extends Car{
    //Class Variables:
    private double INSURANCERATE = 0.05;
    
    // Constructor
    public PremiumCar(String carName, double carRate){
        super(carName, carRate);
        this.carName = carName;
        this.carRate = carRate + (carRate * INSURANCERATE);
    }
    
    //Override method of Car class
    public double getCarRate(){
        return(this.carRate);
    }
}

