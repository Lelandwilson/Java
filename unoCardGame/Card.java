
package unoquattro;

/**
 * @author leland
 * Class to generate cards
 */


public abstract class Card {
    private int cardCode;

    public Card(int cardCode) {
        this.cardCode = cardCode;
    }

    // Getters
    public int getCardCode() {
        return cardCode;
    }

    public String getString(){
        // add function to convert cardCode to human readable string
        return Integer.toString(this.cardCode);
    }

    
}