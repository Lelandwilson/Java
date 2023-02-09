
package com.mycompany.carrentalsystem;

import java.util.Scanner;
import java.time.LocalDate;  
import java.time.Month;
import java.time.YearMonth;
/**
 * This class manages user selections of the car be hired, 
 * and the dates they would like to hire.
 * @author leland
 */
public class CarAndBookingDates {
     //Class Variables:
    private int year, month, day;
    
    //Constructor
    public CarAndBookingDates(){
        year = 0;
        month = 0;
        day = 0;
        
    }
    
    public static int carSelection(int carsAvailable){
        int selection =0;
        boolean flag = false;
        //Prompt for car selection in menu
        System.out.println("\tPlesse enter desired Car ID based on "
                + "the records available");
        
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        flag = false; //Reset while loop exit flag to false

                // While loop to wait for an integer input and not a char or string
        while(!flag){
            if (input.hasNextInt()){
                selection = input.nextInt();
                // check for valid input (1-3)
                if(selection >=1 && selection <=carsAvailable){
                    flag = true; //Set exit flag to true to exit loop
                    break;
                }
                else{
                    // input was an int, but not in the correct range
                    System.out.println("\tSelection invalid. Please try again.");
                    continue;
                }  
            }
            else {
                // input was not an int
                System.out.println("\tSelection invalid. Please use a number.");
                input.next();
                continue;
            }
        }
        return(selection);           
    }
    
    public LocalDate getCarBookingDateFull(){
        String Date = "";
        int monthInt, dayInt;
        //Get Year
        Date = Integer.toString(promptForYear())+ "-";
        
        //Get Month and ensure formatting
        monthInt = promptForMonth();
        if(monthInt <10){
            Date += "0"+ Integer.toString(monthInt);
        }
        else{
            Date += Integer.toString(monthInt);
        }
        
        //Get Day and ensure formatting
        dayInt = promptForDay();
        if(dayInt <10){
            Date += "-0"+ Integer.toString(dayInt);
        }
        else{
            Date += "-" + Integer.toString(dayInt);
        }
        
        
        LocalDate d1 = LocalDate.parse(Date);  

        return(d1);
    }
    
    private boolean validateYearString(String year){
        int yearInt = 0;
        // check input is (Integer) String
        try{
            yearInt = Integer.parseInt(year);  
        }
        catch (NumberFormatException e){
            System.out.print("\tPlease only input a number\n");
            return(false);
        }
        LocalDate date = LocalDate.now(); 
        // allow max of 10 year into the future ??
        if(yearInt < date.getYear()+10){
             // get the current year and ensure the input is this as a minimum
            if(yearInt >= date.getYear()){
                return(true);
            }
            else{
                return(false);
            }
        }
        else{
            return(false);
        }    
    }
    
    private boolean validateMonthString(String month){
        int monthInt = 0;  
        // check input is (Integer) String
        try{
            monthInt = Integer.parseInt(month); 
        }
        catch (NumberFormatException e){
            System.out.print("\tPlease only input a number\n");
            return(false);
        }
        
        //ensure the input is in range
        if(monthInt >= 1 && monthInt <= 12){
            // get the current month and ensure the input is this as a minimum
            LocalDate date = LocalDate.now();          
            if(this.year == date.getYear()){ // If it is this month, of this year
                if(monthInt >= date.getMonthValue()){
                    return(true);
                }
            }
            else if(this.year > date.getYear()){ // if it is a later month
                return(true);
            }
            return(false); // Month is earlier than this month
        }
        else{
            return(false); // incorrect input not 1-12
        }    
    }
    
    private boolean validateDayString(String day){
        int dayInt = 0;
        // check input is (Integer) String
        try{
            dayInt = Integer.parseInt(day); 
        }
        catch (NumberFormatException e){
            System.out.print("\tPlease only input a number\n");
            return(false);
        }

        if(dayInt >= 1 && dayInt <= 31){
            LocalDate date = LocalDate.now(); 
            
            YearMonth yearMonth = YearMonth.of(this.year, Month.of(this.month));

            // Day is within length of month
            if(dayInt <= yearMonth.lengthOfMonth()){
                // if day is not earlier than today, of this year and month
                if((this.year == date.getYear()) && this.month == date.getMonthValue()){
                    if(date.getDayOfMonth() <= dayInt){
                        return(true);
                    }
                    else{ // day is earlier than current day
                        return(false);
                    }
                }
                else{ // it is not this year or month, so proceed
                    return(true);
                }                   
            }
            else{ // Not a valid day for this month length
                return(false);
            }
        }
        else{
            return(false);  
        }

    }
    
    public int promptForYear(){
        String yearStirng;
        boolean flag = false;
        
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        while(!flag){
            System.out.println("\tPlease enter the year - for example '2022':");
            yearStirng= input.nextLine();
            if(!yearStirng.equals("")){
                if(validateYearString(yearStirng)){
                    
                    
                    this.year = Integer.valueOf(yearStirng);
                    flag = true;
                }  
                else{
                    continue;
                }
            }
              
            else{
                continue;
            }          
        }
        
        return(this.year);
    }
    
    public int promptForMonth(){
        boolean flag = false;
        String mothStirng;
        
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        while(!flag){
            System.out.println("\tPlease enter the month number - for example '5':");
            mothStirng= input.nextLine();
            if(!mothStirng.equals("")){
                if(validateMonthString(mothStirng)){
                    this.month = Integer.valueOf(mothStirng);
                    flag = true;
                }
                else{
                    continue;
                }
            }
                
            else{
                continue;
            }          
        }
        return(this.month);
    }
    
    public int promptForDay(){
        boolean flag = false;
        String dayStirng;
        
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        while(!flag){
            System.out.println("\tPlease enter the day number - for example '21':");
            dayStirng= input.nextLine();
            if(validateDayString(dayStirng)){
                this.day = Integer.valueOf(dayStirng);
                flag = true;
            }    
            else{
                continue;
            }          
        }
        return(this.day);
    }
   
}

