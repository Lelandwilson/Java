package com.mycompany.carrentalsystem;

/**
 *This class holds information related to individual cars that are 
 * available for hire. 
 * @author leland
 */
public class Car {
     //Class Variables:
    protected double carRate; //The base cost of hiring a car
    protected String carName; //The name of the type of car
    
    //Constructor           
    public Car(String carName, double carRate){
        this.carName = carName;
        this.carRate = carRate;
    }
    
    //Accessor method for ‘carRate’
    public double getCarRate(){
        return(this.carRate);
    }
    
    //Accessor method for ‘carName’
    public String getCarName(){
        return(this.carName);
    }
}

