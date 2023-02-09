package unoquattro;

import java.util.ArrayList; //For storing player and card arrays
import java.util.Scanner; // For user keyboard input
import java.util.*; // shuffeling the arraylists

/**
 * @author Lel
 */

public class QuattroGame{
    public ArrayList<Card> cardListStack = new ArrayList<Card>();
    public ArrayList<Card> cardListDiscard = new ArrayList<Card>();
    public ArrayList<Player> playerList = new ArrayList<Player>();
    final int NUMBER_OF_PLAYERS = 4;
    final int STARTING_CARD_NUMBER = 3;
    final int MAX_NO_CARDS = 40;
    int cardCodeSeed = 0;

    
    public void playGame(){
        createCards();//create cards for play
        createPlayers();//create players in teh game
        dealCards();//deal cards to players
        playFirstCard();//turn over top card
        playByTurn();//play cards until a winner is determined
    }

    
    //Method to generate a fresh deck of cards and then shuffle the deck
    void createCards(){
        //for each card colour; 0=Blue, 1=Green, 2=Red, 3=Yellow
        for (int i = 0; i <= 3; i++){
            
            //for each card 0 to 9
            for(int x = 0; x <= 9; x++){  
                int colour;
                switch(i){
                    case 0:
                        colour = 10; //Blue card range 0-9
                        cardListStack.add(new StandardColourCard(colour + x, "Blue", x));
                        //System.out.println(Integer.toString(colour + x)+ " Blue");
                        break;
                        
                    case 1:
                        colour = 20; //Green  card range 10-19
                        cardListStack.add(new StandardColourCard(colour + x, "Green", x));
                        //System.out.println(Integer.toString(colour + x)+ " Green");
                        break;
                        
                    case 2:
                        colour = 30; //Red card range 20-29
                        cardListStack.add(new StandardColourCard(colour + x, "Red", x));
                        //System.out.println(Integer.toString(colour + x)+ " Red");
                        break;
                        
                    default:
                        colour = 40; //Yellow card range 30-39
                        cardListStack.add(new StandardColourCard(colour + x, "Yellow", x));
                        //System.out.println(Integer.toString(colour + x)+ " Yellow");
                        break;         
                }
                
            }
        }
        
        //Shuffle the deck
        Collections.shuffle(cardListStack);
        
    }
    
    // Method to create players
    void createPlayers(){ // create 3 Bot players and 1 human player
        for (int i =0; i < NUMBER_OF_PLAYERS; i++){
            if(i != 0){ //Bot Player
                playerList.add(new Player("Bot_Player "+ Integer.toString(i), new ArrayList<Card>(), false));
            }
            else{
                //Human Player
                
                String playerName = "";
                System.out.println("**********************************************");
                System.out.println("**   Welcome to a new game of Uno Quattro   **");
                System.out.println("**********************************************");
                System.out.println("Please enter a name with one or more, non-spaced characters");
                
                Scanner input = new Scanner(System.in);  // Create a Scanner object

                while(true) {
                    
                    String line = input.nextLine();

                    try{
                        if (line.isEmpty() || line.contains(" ")) {
                            throw new InputMismatchException();
                            
                        }
                        else{
                            playerName = line;
                            break;
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Incorrect input\n"
                                + "Please enter a name with one or more, non-spaced characters");
                    }

                }
           
                
                playerList.add(new Player(playerName, new ArrayList<Card>(), true));
                
            }  
        }

    }
    
    // Method to deal cards to players
    void dealCards(){
        //for each player
        for(int i =0; i < NUMBER_OF_PLAYERS; i++){
            // for max starting number of cards (ie 3)
            for(int x =0; x< STARTING_CARD_NUMBER; x++){
                Card currentCard = cardListStack.get(0); //Card at top of draw pile
                playerList.get(i).addCardToHand(currentCard);//Assign card to player
                cardListStack.remove(0); // remove card from the arrayList as it has bveen delt

            }
        }
    }
    
    // Method to play the first card for the game
    void playFirstCard(){
        //Turn over top card of deck and add to discard pile 
        Card topCard = cardListStack.get(0);    
        cardListStack.remove(0);
        cardListDiscard.add(topCard);

        // Print out card
        System.out.println("*****************************");
        System.out.println("   The top card is:");
        System.out.println("   "+ topCard.getString());
        System.out.println("*****************************\n");

    }
    
    // Method to play the game, in sequence player by player
    void playByTurn(){
        boolean noWinner = false;
        //while there is not yet a winner
        while(!noWinner){

            Player humanPlayer = playerList.get(0);
            //print out cards in human players hand. Human is player 0
            System.out.println(humanPlayer.getName());
            
            //Let the human player enter option and process the game arcodingly.
            if(playerList.get(0).getIsHuman()){
                System.out.println("Enter the card code of the card you"
                    + " would like to play\n"
                    + "If you don't have a valid card enter 0\n");
                
                
                
                System.out.println("Cards " + humanPlayer.getName() + " has are:");
            
                Scanner input = new Scanner(System.in);  // Create a Scanner object for keyboard input

                //Print out cards in players hand
                for(int x = 0; x < humanPlayer.getCardList().size(); x++){
                    System.out.println("|--" + humanPlayer.getCardList().get(x).getString());
                }

                // ---------Human player actions ---------------//
                boolean turnflag = false;
                boolean inputflag = false;
                while(turnflag == false){
                    String playerName = humanPlayer.getName();
                    int response=-1;
                    //catch user input, we only want int
                    
                    
                    // While loop to wait for an integer input and not a char or string
                    while(true){
                        if (input.hasNextInt()){
                            response = input.nextInt(); //repsonse = useres input for card selection
                            break;
                        }
                        else {
                            //check only integer as input is userd
                            System.out.println("Error: Please insert an integer");
                            input.next();
                        }
                    }
                    
                    // Card turned over on deck, in play
                    Card topCard = cardListDiscard.get(cardListDiscard.size()-1);
                    int cardInplay_cardCode = topCard.getCardCode();

                    // ------- Pick up card ---------//
                    //If 0 entered, pick up top card and move on to next player
                    if(response == 0){
                        // add new card from deck to human player's hand
                        Card drawCard = cardListStack.get(0); //Card at top of draw pile
                        playerList.get(0).addCardToHand(drawCard);//Assign card to player
                        cardListStack.remove(0); // remove card from the arrayList as it has been delt
                        
                        //Display the action that has been done by the human player.
                        System.out.println(playerName + " has picked up " + drawCard.getString()+"\n");
                        
                        //playFirstCard(); //turn over new card
                        break; // end fo turn

                    }
                    
                    // ------- Put down card ---------//
                    else{ 
                        //check if number is in players hand of cards;
                        //check each card in their hand to see if it matches their input
                        boolean cardCheck = false;
                        for(int i =0; i < humanPlayer.getCardList().size(); i++){
                            //current card in focus
                            Card playerSelectedCard = humanPlayer.getCardList().get(i);

                            //check if card no = the user input
                            if(playerSelectedCard.getCardCode() != response){
                                
                                //if this is the last card in their hand and it does not match their input
                                //display incorrect input statement
                                if(i+1 == humanPlayer.getCardList().size()){
                                   // System.out.println(humanPlayer.getCardList().size());
                                    System.out.println("Incorrect selection. You do not hold that card. Please try again.");
                                }
                            }

                            else{
                                //Selection matches a card in hand, is correct input. 
                                //Check if it matches the top card on the deck

                                String responseString = Integer.toString(response);
                                String topcard = Integer.toString(cardInplay_cardCode);
                                Character topCardColour =  topcard.charAt(0); // assign colour code for comparison 
                                Character topCardNo = topcard.charAt(1); // assign number code for comparison 


                                //check for number or colour match
                                if(topCardColour.equals(responseString.charAt(0)) || topCardNo.equals(responseString.charAt(1))){
                                    String Message = "";
                                    if(topCardColour.equals(responseString.charAt(0))){
                                        Message = "colour ";

                                    }
                                    else{
                                        Message = "number ";

                                    }
                                    System.out.println("** Well done, you got a "+ Message +" match **");
                                    System.out.println(playerName + " played "
                                    + playerSelectedCard.getString());

                                    //Remove card from players hand
                                    humanPlayer.removeCardFromHand(playerSelectedCard);
                                    //Add card to discarded card pile
                                    cardListDiscard.add(playerSelectedCard);
                                    

                                    int humanCardsLeft = humanPlayer.getCardList().size();
                                    System.out.println(playerName + " has "
                                    + Integer.toString(humanCardsLeft) +" cards left \n");
                                    
                                    // Print out top card
                                    topofDiscardpile();


                                    if(humanCardsLeft <= 0){
                                        System.out.println("!!!! " + playerName + " has won the game!!!!!");
                                        noWinner = true;
                                        return;
                                        // -------- End of game, human won ----------- //
                                    }

                                    
                                    turnflag = true;
                                    break;
                                }
                                // card suggested is not a match to what is on the top of the deck
                                else{
                                    System.out.println("Sorry, card " + responseString +" is not a suitable option. Please try again, or input 0 to pick up card\n");
                                    break;

                                }

                            }
                        }

                    } 

                }
                //Check if draw pile is empty
                pile_empty();
                
            }
            
            
            // ---------- Bot player actions ---------------//
            
            //Cycle thorugh bot players
            for(int i =1; i < NUMBER_OF_PLAYERS; i++){
                Card topCard = cardListDiscard.get(cardListDiscard.size()-1);
                boolean pickUpCard = true;
                int botCardsLeft;
                Player currentBot = playerList.get(i);
                String botName = playerList.get(i).getName();
                System.out.println(botName + ":");

                //cycle through the cards in bots hand
                for(int a =0; a < currentBot.getCardList().size(); a++){
                    Card botSelectedCard = currentBot.getCardList().get(a);

                    String cardNo = Integer.toString(currentBot.getCardList().get(a).getCardCode());
                    Character botCardColour =  cardNo.charAt(0);
                    Character botCardNo = cardNo.charAt(1);
                    
                    int cardCode = topCard.getCardCode();
                    String topcard = Integer.toString(cardCode);
                    Character topCardColour =  topcard.charAt(0);
                    Character topCardNo = topcard.charAt(1);

                    // --------- Put down card ----------//
                    
                    //check for number or colour match
                    if(topCardColour.equals(botCardColour) || topCardNo.equals(botCardNo)){

                        String Message;
                        if(topCardColour.equals(botCardColour)){
                            Message = "colour ";
                        }
                        else{
                            Message = "number ";
                        }
                                
                        System.out.println(botName + " got a " + Message + "match");

                        //Add card to discarded card pile
                        cardListDiscard.add(botSelectedCard);
                        //Remove card from players hand
                        currentBot.removeCardFromHand(botSelectedCard);
                        
                        System.out.println(botName + " played "
                             + botSelectedCard.getString());
                        
                        botCardsLeft = currentBot.getCardList().size();
                        
                        System.out.println(botName + " has "
                                + Integer.toString(botCardsLeft) +" cards left \n");
                        
                        if(botCardsLeft <= 0){
                            System.out.println("!!!! " + botName + " has won the game!!!!!");
                            noWinner = true;
                            return;
                            // -------- End of game, bot won ----------- //
                        }
                        
                        pickUpCard = false;  
                        topofDiscardpile();
                        break;

                    }


                }
                // ------- Pick up a card ----------//
                if(pickUpCard){
                    
                    Card drawCard = cardListStack.get(0); //Card at top of draw pile
                    currentBot.addCardToHand(drawCard);//Assign card to player
                    cardListStack.remove(0); // remove card from the arrayList as it has been delt

                    //Display the action that has been done by the bot player.
                    System.out.println(botName + " has picked up " + drawCard.getString());
                    System.out.println(botName + " has "
                                + Integer.toString(currentBot.getCardList().size()) +" cards left \n");                    

                    //playFirstCard(); //turn over new card
                    topofDiscardpile();


                }    
                //End of individuals turn
                //Check if draw pile is empty
                pile_empty();
            }
        }
    }
    
    
    //method to check if we have used up all the cards
    void pile_empty(){

        //if all cards are in play then we have used up all the cards.
        if(cardListStack.size() <= 0){
            System.out.println("Draw pile is now empty, time to reset the deck\n");

            //cycle through Discard pile: for each card (other than the card in play), 
            //remove it from discard pile and put it onto the new deck
            for(int i=1; i< cardListDiscard.size()-1; i++){
                Card curentCard = cardListDiscard.get(i); //Card at top of discard pile
                cardListStack.add(curentCard);
                cardListDiscard.remove(i); 
                
                //Shuffle the new draw pile
                 Collections.shuffle(cardListStack);
            }
        }
        else{
            //do nothing as deck is not yet depleted
        }
    }
    
    //method print out card on top of pile (card in play)
    void topofDiscardpile(){
         // Print out top card
        Card top_Card = cardListDiscard.get(cardListDiscard.size()-1);
        System.out.println("*****************************");
        System.out.println("   The top card is:");
        System.out.println("   "+ top_Card.getString());
        System.out.println("*****************************\n");
    }
} 

