
package com.mycompany.carrentalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.Scanner;

import java.time.temporal.ChronoUnit;


         
/**
 * This class does all the work of calculating duration and 
 * cost of the hire of a car. 
 * @author leland
 */
public class CarBooking {
     //Class Variables:
    private LocalDate startDate; //Start date
    private LocalDate endDate;   //End date
    private int carNumber;       //ID no. of car
    private long totalDays;      //Duration (days)
    private double newRate;      //Calc rate of booking
    private double cost;         //Total booking cost
    private Car car;             //Car in booking
    
    private static String CAR_LIST_FILE = "CarList.csv"; // Car list CSV file
    private ArrayList<ArrayList<String>> carsListArray;
        
    //Constructor
    CarBooking(LocalDate startDate, LocalDate endDate,
            int carNumber){
        this.startDate = startDate;
        this.endDate = endDate;
        this.carNumber = carNumber-1;
    }
    
   //-- loadData class for loading data fromo CSV file into List Array --//
    private void loadData() throws FileNotFoundException{
        
        this.carsListArray = new ArrayList(); 
        File f1 = new File(CAR_LIST_FILE);
        Scanner fileScanner = new Scanner(f1);
        
        //Load data from CSV file
        while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();

            //Make an Array list and populate it with data from each row of file
            //to hold each car's infomation
            if(!line.isEmpty()){ 
                ArrayList<String> rowData = new ArrayList();
                int startIndex = 0;
                for(int i = 0; i < line.length(); i++){
                    String cellData;
                    if(line.charAt(i) == ',') {
                        cellData = line.substring(startIndex,i);
                        rowData.add(cellData); 
                        startIndex = i+1;
                    }
                    else if(i ==line.length()-1){
                        cellData = line.substring(startIndex,i+1);
                        rowData.add(cellData); 
                    }
                }
                // Add row of data into Cars List
                this.carsListArray.add(rowData); 
            }
        }
        

    }

    
    // Load CVS data,check if car is premium, create car instance, adjust car rate.
    public void makeBooking(){
        String carName;
        double rate;
        
                
        try{
            loadData(); // Load data from CSV file
        }
        catch(FileNotFoundException e){
            System.out.println("\n\n"+e+"\n\n");
            System.out.printf("\n\nError, '%s' does not exist in parent folder\n",CAR_LIST_FILE);
            System.exit(0);
        }
        
        carName = this.carsListArray.get(carNumber).get(1);
        rate = Double.parseDouble(this.carsListArray.get(carNumber).get(5));
        
        //Check if selected car is premium and adjust newRate accordingly
        if(this.carsListArray.get(carNumber).get(4).equals("Premium")){      
            Car hireCar = new PremiumCar(carName,rate);
            this.car = hireCar;
        }
        else{
            Car hireCar = new Car(carName,rate);
            this.car = hireCar;
        }
        this.newRate = this.car.carRate;
        
        
        calculateTotalDays(); //Calc total days method
        calculateCost(this.car.carRate, this.totalDays);//Calc cost method
        
    }
        
    //Calc the diff in days b/w start date and end date of the booking 
    public void calculateTotalDays(){   
        this.totalDays = ChronoUnit.DAYS.between(this.startDate, this.endDate);

    }
    
    //Sets the cost of a booking based on the rate by the number of days 
    public void calculateCost(double newRate, long totalDays){
        this.cost = newRate * totalDays;
    }
    
    //Accessor method for ‘cost’
    public double getCost(){
        return(this.cost);
    }
    
    //Accessor method for ‘startDate’ 
    public LocalDate getStartDate (){        
        return(this.startDate);
    }
    
    //Accessor method for ‘endDate’
    public LocalDate getEndDate (){
        return(this.endDate);
    }
    
    //Accessor method for ‘totalDays’
    public long getTotalDays(){
        return(this.totalDays);
    }
    
    //Accessor method for ‘carNumber’
    public int getCarNumber(){
        return(this.carNumber);
    }
    
    //Accessor method for ‘newRate’
    public double getNewRate(){
        return(this.newRate);
    }    
    
    //Accessor method for ‘carName’
    public String getCarName(){
        return(String.valueOf(this.car.carName));
         
    }    
}
