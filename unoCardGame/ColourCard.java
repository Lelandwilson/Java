
package unoquattro;

/**
 * @author leland
 * Class to generate colored cards, extends Cards
 */


public class ColourCard extends Card {
    private String cardColour;

    //Constructor
    public ColourCard(int cardCode, String cardColour) {
        super(cardCode);
        this.cardColour = cardColour;
    }

    //Getters
    public String getCardColour() {
        return cardColour;
    }

    @Override
    public String getString() {
        // reverse cardcode and extract card details
        return this.cardColour;
    }
    

    
    
}