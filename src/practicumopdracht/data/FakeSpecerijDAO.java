package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Specerij;

import java.time.LocalDate;

/**
 * fake dao voor de detail class 'specerij'
 * @author Nilava Kazal [studentennummer: 500847707]
 */
public class FakeSpecerijDAO extends SpecerijDAO {

    /**
     * schrijft specerij winkels in de dao
     * @return true
     */

    @Override
    public boolean save() {
        return true;
    }

    /**
     * leest specerij winkels uit de dao
     * @return true
     */

    @Override
    public boolean load() {
        super.specerijen.add(new Specerij("s, ", "500 gram", true,
                LocalDate.of(2022, 12, 4), 6.6,
                MainApplication.getSpecerijWinkelDAO().specerijWinkels.get(0)));
        super.specerijen.add(new Specerij("s, ", "500 gram", true,
                LocalDate.of(2022, 12, 4), 6.6,
                MainApplication.getSpecerijWinkelDAO().specerijWinkels.get(1)));
        super.specerijen.add(new Specerij("s, ", "500 gram", true,
                LocalDate.of(2022, 12, 4), 6.6,
                MainApplication.getSpecerijWinkelDAO().specerijWinkels.get(2)));
        return true;
    }
}
