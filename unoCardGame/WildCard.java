
package unoquattro;

/**
 * @author leland
 *  * Class to generate wild cards, extends Cards, implements SpecialAbility
 */

public class WildCard extends Card implements SpecialAbility{
    private String specialAbiltiy;
    
    //Constructor
    public WildCard(int cardCode, String specialAbiltiy) {
        super(cardCode);
        this.specialAbiltiy = specialAbiltiy;
    }


    //Getters
    @Override
    public String getString() {
        return this.specialAbiltiy;
               
    }
    
    public void activeAbility(){
    }
    
    
}