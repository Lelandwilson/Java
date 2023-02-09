
package unoquattro;

import java.util.ArrayList; // import the ArrayList class

/**
 * @author leland
 * Class to generate players
 */


public class Player {
    private String name;
    private ArrayList<Card> cardList;
    private Boolean isHuman; // true = human player, false = bot

    // Constructor
    public Player(String name, ArrayList<Card> cardList, Boolean isHuman) {
        this.name = name;
        this.cardList = cardList;
        this.isHuman = isHuman;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public Boolean getIsHuman() {
        return isHuman;
    }
         
    //Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void addCardToHand(Card card){
        this.cardList.add(card);
    }
    
    public void removeCardFromHand(Card card){
        this.cardList.remove(card);
    }
}
