/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoquattro;
/**
 * @author leland
 * Class to generate colored cards, extends ColourCards
 */
public class StandardColourCard extends ColourCard{
    private int cardNumber;

    public StandardColourCard(int cardCode, String cardColour,int cardNumber) {
        super(cardCode, cardColour);
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }
    
    @Override
    public String getString() {
        return "Card Code: " + getCardCode()+" - " + getCardColour()+ " " 
                + Integer.toString(this.cardNumber) ;
               
    }


    
}
