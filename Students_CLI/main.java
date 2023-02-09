
package com.mycompany.StudentsCLI;

/* @author leland*/

import java.util.Scanner; // for keyboard input
import java.util.List;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        int selection = -1;
        boolean exit = false, flag = false, enrolLoop = false;

        // Listr Arrays for enrolled students, per grade
        List<String> gPrep = new ArrayList<String>();
        List<String> gOne = new ArrayList<String>();
        List<String> gTwo = new ArrayList<String>();
        List<String> gThree = new ArrayList<String>();
        List<String> gFour = new ArrayList<String>();
        List<String> gFive = new ArrayList<String>();
        List<String> gSix = new ArrayList<String>();
       
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nWelcome to Delway Primary School");
            
        // Start while loop to contain main program functions
        while(exit == false){
            // Display main menu to user
            System.out.println("\n|--------MENU-----------|");
            System.out.println("|-1: Enrol a Student----|");
            System.out.println("|-2: Print Enrolments---|");
            System.out.println("|-3: Quit the program---|");
            System.out.println("|-----------------------|");
            System.out.println("\nPlease choose option required:");
            flag = false; //Reset while loop exit flag to false
            enrolLoop = false;

                    // While loop to wait for an integer input and not a char or string
            while(flag == false){
                if (input.hasNextInt()){
                    selection = input.nextInt();
                    flag = true; //Set exit flag to true to exit loop
                }
                else {
                    System.out.println("Error: Please insert an integer (1-3)");
                    input.next();
                    continue;
                }
            }

            // Switch case for user seletcion frommain menu.
            // Select 1 to enrol student
            // Select 2 to print Student enrollment per grade
            // Select 3 to quit program
            switch(selection) {
                case 1: //select 1 to enrol student
                    while(enrolLoop == false){
                        enrolNewStudent(input, gPrep, gOne, gTwo, gThree, gFour,
                                gFive, gSix);  

                        System.out.println("Do you want to enrol another student? Y/N");
                        String choice = "";
                        choice = input.next();
                        if(choice.equals("N") || choice.equals("n")){
                           enrolLoop = true;
                           break;
                        }

                    }
                    break;
                  
                case 2: // Select 2 to print Student enrollment per grade
                    printEnrolments( gPrep, gOne, gTwo, gThree, gFour,
                          gFive, gSix);
                  break;
                  
                case 3: // Select 3 to quit program
                    System.out.println("Are you sure you wish to quit? Y/N");
                        String choice = "";
                        choice = input.next();
                        if(choice.equals("Y") || choice.equals("y")){
                           System.out.println("\n\nExiting. Thankyou and goodbye.");
                            exit = true;
                            break;
                        }
                        else{
                            exit = false;
                            break;
                        }

            }
        }
    System.exit(0);
    }
    

    // Seperate method for enrolling students
    static void enrolNewStudent(Scanner input, List<String> gPrep, List<String> gOne,
            List<String> gTwo, List<String> gThree, List<String> gFour, List<String> gFive, List<String> gSix) {
        
        String fName = "", lName = "";
        int age = 0;
        //Input while loop exit flag, initilize as false
        boolean flag = false;
    
        // Prompt for student details
        System.out.println("Step 1/3: Enter child's first name:");
        fName = input.next(); 
        System.out.println("Step 2/3: Enter child's surname:");
        lName = input.next(); 
        System.out.println("Step 3/3: Enter child's age:");
        // While loop to wait for an integer input and not a char or string
        while(flag == false){
            if (input.hasNextInt()){
                age = input.nextInt();
                if(age <5 || age>11){
                    System.out.println("Error: Please check age of prospective student (5-11) and try again");
                    System.out.println("Step 3/3: Enter child's age:");
                    continue;
                }
                else{
                    flag = true; //Set exit flag to true to exit loop
                }
            }
            else {
                System.out.println("Error: Please insert an integer (5-11)");
                input.next();
                continue;
            }
        }

        //Assign student to correct grade as per age and check if grade is full yet or not
        switch(age) {
            case 5:
                if(gPrep.size()<10){
                    gPrep.add(fName + "_" + lName);
                }
                else{
                    System.out.printf("Warning to many students in Grade Prep [%d]\n", gPrep.size());
                }
              break;
              
            case 6:
                if(gOne.size() < 10){
                    gOne.add(fName + "_" + lName);
                }
                else{
                    System.out.printf("Warning to many students in Grade One [%d]\n", gOne.size());    
                    }
              break;
              
            case 7:
                if(gTwo.size() < 10){
                    gTwo.add(fName + "_" + lName);
                }
                else{
                    System.out.printf("Warning to many students in Grade Two [%d]\n", gTwo.size());    
                    }
              break;
              
            case 8:
                if(gThree.size() < 10){
                    gThree.add(fName + "_" + lName);
                }
                else{
                    System.out.printf("Warning to many students in Grade Three [%d]\n", gThree.size());    
                    }
              break;
              
            case 9:
                if(gFour.size() < 10){
                    gFour.add(fName + "_" + lName);
                }
                else{
                    System.out.printf("Warning to many students in Grade Four [%d]\n", gFour.size());    
                    }
              break;
              
            case 10:
                if(gFive.size() < 10){
                    gFive.add(fName + "_" + lName);
                }
                else{
                    System.out.printf("Warning to many students in Grade Five [%d\n", gFive.size());    
                    }
              break;
              
            case 11:
                if(gSix.size() < 10){
                    gSix.add(fName + "_" + lName);
                }
                else{
                    System.out.printf("Warning to many students in Grade Six [%d]\n", gSix.size());    
                    }
              break;
        }


        //Upon sucesfull enrolment, infrom user
        System.out.printf("\n* The child has been successfully enrolled:  %s_%s * \n",fName,lName);
    }
    
    
    // Seperate method for printing enrolment lists, per grade
    static void printEnrolments(List<String> gPrep, List<String> gOne,
            List<String> gTwo, List<String> gThree, List<String> gFour, List<String> gFive, List<String> gSix) {
        System.out.println("\nCurrent enrolments are:");
        
        System.out.print("Grade_Prep: ");
        System.out.println(gPrep);
        System.out.print("Grade_1: ");
        System.out.println(gOne);
        System.out.print("Grade_2: ");
        System.out.println(gTwo);
        System.out.print("Grade_3: ");
        System.out.println(gThree);
        System.out.print("Grade_4: ");
        System.out.println(gFour);
        System.out.print("Grade_5: ");
        System.out.println(gFive);
        System.out.print("Grade_6: ");
        System.out.println(gSix);
        
    }
    
     
    
}
