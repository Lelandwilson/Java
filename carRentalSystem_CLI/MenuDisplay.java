package com.mycompany.carrentalsystem;

import java.util.Scanner; // for keyboard input
import java.io.File; // for importing dictionary text file
import java.io.FileNotFoundException; //for handelling file not found exceptions
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the retrieval of available cars from a 
 * CSV file and displays them to the user. 
 * @author leland
 */
public class MenuDisplay {
    
    //Class variables
    private static String CAR_LIST_FILE = "CarList.csv"; // Car list CSV file
    private ArrayList<ArrayList<String>> carsListArray;
    private int noOfCars = 0;
    
    
    //-- loadData class for loading data fromo CSV file into List Array --//
    private void loadData() throws FileNotFoundException{
        carsListArray = new ArrayList(); 
        File f1 = new File(CAR_LIST_FILE);
        Scanner fileScanner = new Scanner(f1);
        
        if (f1.isFile() == false){
            System.out.printf("\n\nError, '%s' does not exist in parent folder\n",f1);
            
        }
        else{  
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
                    carsListArray.add(rowData);             
                }
            }
        }
    }

    
    // -- getSelection Class for maninging user input for menu -- //
    public int getSelection(){
        
        boolean menuLoop = false, flag = false;
        int selection = -1;
        
         // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        
        // Start while loop to contain main program functions
        while(menuLoop == false){
            // Display main menu to user
            System.out.println("\nSelect from one of the following options:\n\n");
            System.out.println("\t 1. To make a booking");
            System.out.println("\t 2. To exit the system\n");
            
            System.out.println("Please enter your selection: ");

            flag = false; //Reset while loop exit flag to false

                    // While loop to wait for an integer input and not a char or string
            while(flag == false){
                if (input.hasNextInt()){
                    selection = input.nextInt();
                    if(selection == 1){ // Carry on to make a booking
                        flag = true; // set flag to true to Int while loop
                        menuLoop = true;// set flag to true to menu while loop
                        break;
                    }   
                    else if(selection == 2){
                        System.exit(0);
                    }
                    else{
                        System.out.println("Please enter either '1' or '2' to continue: ");
                    }
                }
                else {
                    System.out.println("Error: Please insert an integer (1-2)");
                    input.next();
                    continue;
                }
            }            
        }
        return(noOfCars);
    }
    
    //Method for producing correct amount of spaces per column of data for menu
    public static String geneSpaces(int Sreq, int refSize){
        String ouput="";
        for(int i = 0; i< (Sreq - refSize); i++){
            ouput+= " ";                    
        }
        return(ouput);
    }
    
    // -- displayCarList Class for displaying initial list of cars int system -- //
    public int displayCarList(){
        int MENU_WIDTH = 74;
        
        for(int i = 0; i < 149; i++){
            if(i < MENU_WIDTH || i > MENU_WIDTH){
                System.out.print("*");
            }
            else{
                System.out.print("\n\t\tWelcome to the Carrington Car Rental\n");
            }
        }    
        try{
            loadData(); //Load Data from file for displaying in menu
        }
        catch (FileNotFoundException e) {
            System.out.printf("\n\nError, '%s' does not exist in parent folder\n",CAR_LIST_FILE);
            System.out.printf("\nError: %s",e);
            System.exit(0);
        }
        //Print Menu //
        System.out.print("\nCars Available for booking:\n");
        
        for(int i = 0; i < MENU_WIDTH; i++){
            System.out.print("_");
           
        }
        

        System.out.print("\nCar No.  ");
        System.out.print("Car Name        ");
        System.out.print("Seats    ");
        System.out.print("Transmission  ");
        System.out.print("Car Type  ");
        System.out.print("Rate/Day($)\n");
        
        System.out.print("-------  -------         ");
        System.out.print("-----    ------------  --------");
        System.out.print("  --------\n");
        

        
        // for list entry
        for(int i = 0; i< carsListArray.size(); i++){
            if(carsListArray.get(i) != null){ 
                ArrayList<String> carData = carsListArray.get(i);
                //for car entry
                //System.out.println(carData);
                for(int column = 0; column < carData.size(); column++){
                    //System.out.println(carData.get(column));
                    if(carData.get(column) != null){
                        String cellData = carData.get(column);
                        //First column
                        if(column == 0){
                            //print cell data
                            System.out.print(cellData);
                            //Generate correct no of spaces per column
                            System.out.print(geneSpaces(9,cellData.length()));
                        }
                        //Second column
                        else if(column == 1){
                            //print cell data
                            System.out.print(cellData);
                            //Generate correct no of spaces per column
                            System.out.print(geneSpaces(16,cellData.length()));                              
                        }
                        //Thrid Column
                        else if(column == 2){
                            //print cell data
                            System.out.print(cellData);
                            //Generate correct no of spaces per column
                            System.out.print(geneSpaces(9,cellData.length()));                            
                        }
                        //Forth column
                        else if(column == 3){
                            //print cell data
                            System.out.print(cellData);
                            //Generate correct no of spaces per column
                            System.out.print(geneSpaces(14,cellData.length())); 
                        }
                        //Fifth column
                        else if(column == 4){
                            //print cell data
                            System.out.print(cellData);
                            //Generate correct no of spaces per column
                            System.out.print(geneSpaces(10,cellData.length()));                             
                        }
                        //Last column
                        else if(column == 5){
                            System.out.print(cellData);
                                                   
                        }
                    }
                    
                }
                
                noOfCars++;
                System.out.print("\n");
            }
            
            
        }
        //System.out.println(carsListArray);

        for(int i = 0; i < MENU_WIDTH; i++){
            System.out.print("_"); 
        }
        System.out.printf("\nTotal of %d cars available\n\n", noOfCars);
        System.out.println("**Note for premium cars, there's additional 5% "
                + "insurance access applied\nto the rate.");
        System.out.println("*****************************************");
        return(noOfCars);
    }
    
    
}


