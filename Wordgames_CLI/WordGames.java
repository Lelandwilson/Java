import java.util.Scanner; // for keyboard input
import java.io.File; // for importing dictionary text file
import java.io.FileNotFoundException; //for handelling file not found exceptions
import java.util.List; //for words list
import java.util.ArrayList; //for words list

public class WordGames {
    
    private static String DICTIONARY = "dictionary.txt"; // dictionary text file
    private static List<String> WORDS_LIST;
    /*
    main() method that will be responsible for processing menu selections 
    and initiating the corresponding problem that matches the selection
    */
    public static void main(String[] args) throws FileNotFoundException{
                
        int menueSelection = -1;
        boolean exitFlag = false, setup = false;
       
        if(setup == false){
            /*
            This is the first time the program has been run, 
            therfore, generate an array to store each word and load the 
            dictionary file words into the array.
            */
            WORDS_LIST = new ArrayList<String>(); 
            loadDictionary();
            setup = true; // do not run the setup method again, only once needed.
        }

        // commence loop to keep th egame running until exit flag raised
        while(exitFlag == false){
           
            menueSelection = getSelection();
            
            switch(menueSelection) { 
                case 1: //Substring problems menu option
                    substringProblem();
                    break;

                case 2: //Points problems menu option
                    pointsProblem();
                    break;

                case 3: // Exit, do not break, allow to cascade into default scinario


                default: //default is to exit
                    exitFlag = true;
            }
        }
        
        System.out.println("\nGoodbye!");
        System.exit(0);

        
        

    }
    public static int getSelection(){
        int selection = -1;
        boolean flag = false;
        
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        
        // Display wlecome message and menu to player
        System.out.println("\nWelcome to the Word Games program menu.");
        System.out.println("Select from one of the following options.");
       
        // Menu
        System.out.println("\n|------------------------|");
        System.out.println("|-1. Substring problem. -|");
        System.out.println("|-2. Points problem.-----|");
        System.out.println("|-3. Exit.          -----|");
        System.out.println("|------------------------|");
        System.out.println("\nEnter your selection: ");

        flag = false; //Reset while loop exit flag to false

                // While loop to wait for an integer input and not a char or string
        while(flag == false){
            if (input.hasNextInt()){
                selection = input.nextInt();
                // check for valid input (1-3)
                if(selection >=1 && selection <=3){
                    flag = true; //Set exit flag to true to exit loop
                }
                else{
                    // input was an int, but not in the correct range
                    System.out.println("Invalid option. Try again.");
                    continue;
                }
                
            }
            else {
                // input was not an int
                System.out.println("Invalid option. Try again.");
                input.next();
                continue;
            }
        }

        
        return(selection);
    }
    
    public static void substringProblem() {
        String subString = "";
        int firstInd = -1, middleInd, lastInd = -1;
        
        
        System.out.println("\nSubstring problem. ");
        // Declare 'input' as scanner input stream
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter a substring: ");
        subString = input.next();
        
        subString = subString.toLowerCase(); // ensure lower case
        
        //Sterilize input, strip spaces and numbers
        subString = subString.replaceAll(" \\d",""); 
        
        for(int i = 0; i< WORDS_LIST.size(); i++){ //loop through the list
            String word = WORDS_LIST.get(i);

            firstInd = word.indexOf(subString);
            middleInd = word.indexOf(subString,1);
            lastInd = word.lastIndexOf(subString); 
            
            System.out.print(word); 

            if(word.contains(subString)){ 
                //if substring at index 0 must be prefix
                if(firstInd == 0){ 
                    System.out.print(" - prefix"); 

                }
                /*
                if word has more than 1 substring and it is not the same index
                as word length-1
                */
                if(middleInd != -1 && middleInd!= (word.length()-1)){
                    System.out.print(" - infix"); 
                 }

                 // if substring index = word length-1, subtring is suffix
                 if(lastInd == (word.length()-1)){
                     System.out.print(" - suffix"); 
                 }
            }
            else{
                System.out.print(" - not found "); 
            }

            System.out.print("\n");
        }
    }
    
    public static void pointsProblem() {
        System.out.println("\nPoints problem. ");
        
        for(int i = 0; i< WORDS_LIST.size(); i++){
            String word = WORDS_LIST.get(i);
            int points = 0;

            for(int l = 0; l < word.length(); l++){
                char c=word.charAt(l);
                if(c == 'a' || c == 'e' || c == 'i' || c == 'l' || c == 'n'
                         || c == 'o' || c == 'r' || c == 's' || c == 't'
                         || c == 'u'){
                    points += 1;
                }
                if(c == 'd' || c == 'g' ){
                    points += 2;
                }
                if(c == 'b' || c == 'c'  || c == 'm'  || c == 'p'){
                    points += 3;
                }
                if(c == 'f' || c == 'h'  || c == 'v'  || c == 'w' || c == 'y' ){
                    points += 4;
                }
                if(c == 'k' ){
                    points += 5;
                }
                if(c == 'j' || c == 'x' ){
                    points += 8;
                }
                if(c == 'q' || c == 'z' ){
                    points += 10;
                }
            }
            System.out.printf("%s is worth %d points\n", word, points);
        
        
        }
        
    
    }
    public static void loadDictionary() throws FileNotFoundException{
        File f1 = new File(DICTIONARY);
        if (f1.isFile() == false){
            System.out.println("\nError, 'dictionary.txt' does not exist in parent folder. Exiting");
            System.out.println("Goodbye!");
            System.exit(0);
        }
        
        Scanner fileScanner = new Scanner(f1);
        
        while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
             WORDS_LIST.add(line);
        }
        if(WORDS_LIST.size() <=0){
            System.out.println("\nError, no words in 'dictionary.txt'. Exiting");
            System.out.println("Goodbye!");
            System.exit(0);
        }
        
        
    }
    
}
