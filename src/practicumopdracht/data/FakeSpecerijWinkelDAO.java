package practicumopdracht.data;

import practicumopdracht.models.SpecerijWinkel;

/**
 *  fake dao voor de master clas 'specerij winkel'
 * @author Nilava Kazal [studentennummer: 500847707]
 */

public class FakeSpecerijWinkelDAO extends SpecerijWinkelDAO {

    /**
     * schrijft specerij winkels in de dao
     * @return true
     */

    @Override
    public boolean save(){
        return true;
    }

    /**
     * leest specerij winkels uit de dao
     * @return true
     */

    @Override
    public boolean load(){
        super.specerijWinkels.add(new SpecerijWinkel("spice king", "ams", 5));
        super.specerijWinkels.add(new SpecerijWinkel("Spice master", "rotterdam", 3));
        super.specerijWinkels.add(new SpecerijWinkel("garden spice", "hasselt", 4));
        return true;
    }
}
