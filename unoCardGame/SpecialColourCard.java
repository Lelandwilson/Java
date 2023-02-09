
package unoquattro;

/**
 * @author leland
 * Class extends ColourCard for special actions
 */
public class SpecialColourCard extends ColourCard{
    private String specialAbiltiy;

    //Constructor
    public SpecialColourCard(int cardCode, String cardColour,String specialAbiltiy) {
        super(cardCode, cardColour);
        this.specialAbiltiy = specialAbiltiy;
    }

    //Setters
    public void setSpecialAbiltiy(String specialAbiltiy) {
        this.specialAbiltiy = specialAbiltiy;
    }
    
    
}
