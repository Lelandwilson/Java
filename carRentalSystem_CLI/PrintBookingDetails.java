package com.mycompany.carrentalsystem;

/**
 * This class displays details of a finalized booking to the user. 
 * @author leland
 */

//Displays the details of booking to customer or user
public class PrintBookingDetails {
    static void printBookingDetails(CarBooking carBooking, Customer customer){
        
        System.out.printf("\n\tThanks for your booking %s\n",customer.getCustomerName());
        System.out.printf("\tEmail:   \t\t\t%s\n",customer.getCustomerEmail());
        System.out.printf("\tAddress: \t\t\t%s\n",customer.getCustomerAddress());

        System.out.println("\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        System.out.println("\t\t******** Booking confirmed ********\n"); 
        
        
        System.out.println("\tHere is your booking summary:");
        System.out.println("\t- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        System.out.printf("\tCar selected         \t\t\t%s\n",carBooking.getCarName());
        System.out.printf("\tStart date           \t\t\t%s\n",carBooking.getStartDate());
        System.out.printf("\tEnd date             \t\t\t%s\n",carBooking.getEndDate());
        System.out.printf("\tNumber of days booked\t\t\t%s\n",carBooking.getTotalDays());
        System.out.printf("\tCar rate per day     \t\t\t%s\n",carBooking.getNewRate());
        System.out.println("\t_____________________________________________________________");
        System.out.printf("\tTotal cost\t\t\t%s\n",carBooking.getCost());
        System.out.println("\n\n");
        
    }
    
}
