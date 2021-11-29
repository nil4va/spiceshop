package practicumopdracht.data;

import practicumopdracht.models.SpecerijWinkel;

import java.util.ArrayList;
import java.util.List;

/**
 * dao voor de master class 'specerij winkel'
 *
 * @author Nilava Kazal [studentennummer: 500847707]
 */

public abstract class SpecerijWinkelDAO implements DAO<SpecerijWinkel> {
    protected List<SpecerijWinkel> specerijWinkels;

    public SpecerijWinkelDAO() {
        this.specerijWinkels = new ArrayList<>();
    }

    /**
     * @param id van een specerijwinkel
     * @return een specerijwinkel met de gegeven id
     */

    public SpecerijWinkel getById(int id) {
        if (specerijWinkels.get(id) != null) {
            return specerijWinkels.get(id);
        } else return null;
    }

    /**
     * @param object een specerijwinkel
     * @return id voor de specerijwinkel
     */

    public int getIdFor(SpecerijWinkel object) {
        if (specerijWinkels.contains(object)) {
            for (int i = 0; i < specerijWinkels.size(); i++) {
                if (specerijWinkels.get(i).equals(object)) return i;
            }
        }
        return -1;
    }

    /**
     * @return alle speceirjwinkel uit de dao
     */

    @Override
    public List getAll() {
        return specerijWinkels;
    }

    /**
     * voegt een specerijwinkel toe, en anders wordt een bestaande specerijwinkel bewerkt
     *
     * @param object een specerijwinkel
     */
    @Override
    public void addOrUpdate(SpecerijWinkel object) {
        if (specerijWinkels.contains(object)) {
            for (int i = 0; i < specerijWinkels.size(); i++) {
                if (specerijWinkels.get(i).equals(object)) {
                    specerijWinkels.remove(object);
                    specerijWinkels.add(i, object);
                }
            }
        } else {
            specerijWinkels.add(object);
        }
    }

    /**
     * verwijdert een specerij winkel uit de dao
     *
     * @param object een specerijwinkel
     */

    @Override
    public void remove(SpecerijWinkel object) {
        specerijWinkels.remove(object);
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
