package practicumopdracht.data;

import practicumopdracht.controllers.SpecerijController;
import practicumopdracht.models.Specerij;
import practicumopdracht.models.SpecerijWinkel;

import java.util.ArrayList;
import java.util.List;

/**
 * dao voor de detail class 'specerij'
 *
 * @author Nilava Kazal [studentennummer: 500847707]
 */
public abstract class SpecerijDAO implements DAO<Specerij> {
    protected List<Specerij> specerijen;

    public SpecerijDAO() {
        this.specerijen = new ArrayList<>();
    }

    /**
     * @param object van de master 'specerij winkel'
     * @return lijst met alle specerijen van de specerijenwinkel
     */

    public List<Specerij> getAllFor(SpecerijWinkel object) {
        ArrayList<Specerij> specerijenForWinkel = new ArrayList<>();

        for (Specerij specerij : specerijen) {
            if (specerij.getHoortBij() == object) {
                specerijenForWinkel.add(specerij);
            }
        }
        return specerijenForWinkel;
    }

    /**
     * @return alle specerijen uit de dao
     */
    @Override
    public List<Specerij> getAll() {
        return specerijen;
    }

    /**
     * voegt een specerij toe, of wordt een bestaande specerij bewerkt
     * @param object van een specerij
     */

    @Override
    public void addOrUpdate(Specerij object) {
        if (specerijen.contains(object)) {
            for (int i = 0; i < specerijen.size(); i++) {
                if (specerijen.get(i) == object) {
                    specerijen.add(i, object);
                }
            }
        } else {
            specerijen.add(object);
        }
    }

    /**
     * verwijdert een specerij uit de dao
     * @param object een specerij
     */

    @Override
    public void remove(Specerij object) {
        specerijen.remove(object);
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
