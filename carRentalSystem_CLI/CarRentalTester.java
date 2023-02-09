package com.mycompany.carrentalsystem;

import java.io.FileNotFoundException;
import java.time.LocalDate; 
/**
 *
 * @author leland
 */
public class CarRentalTester {
    public static void main(String[] args) {
        int carSelection = 0, carID = 0;
        
        while(true){
       
            //Instance of MenuDisplay
            MenuDisplay menu = new MenuDisplay();

            menu.displayCarList(); // Generate menu
            carSelection = menu.getSelection(); //Take user selection input

            //Instance of CarAndBookingDates
            CarAndBookingDates order = new CarAndBookingDates();
            carID = order.carSelection(carSelection); //User selects car
            System.out.println("\tPlease enter booking start date:");

            //Instance for start date
            LocalDate startDate = order.getCarBookingDateFull(); //User selects dates

            //Instance for end date
            LocalDate endDate;
            //Create a loop to ensure end date is after start date
            do{
                System.out.println("\n\tPlease enter booking end date:");
                endDate = order.getCarBookingDateFull(); //User selects dates
            }
            while(endDate.compareTo(startDate) <0);

            // new instance of CarBooking
            CarBooking carBooking = new CarBooking(startDate,endDate,carID); 
            carBooking.makeBooking();

            Customer customer = new Customer();
            customer.makeNewCustomer();

            PrintBookingDetails print = new PrintBookingDetails();
            print.printBookingDetails(carBooking, customer);
            }
       
    }
    
}
