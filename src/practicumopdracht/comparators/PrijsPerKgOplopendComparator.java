package practicumopdracht.comparators;

import practicumopdracht.models.Specerij;

import java.util.Comparator;

/**
 * Sorteert prijs in listview oplopend
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 19:35
 */
public class PrijsPerKgOplopendComparator implements Comparator<Specerij> {
    @Override
    public int compare(Specerij o1, Specerij o2) {
        return Double.compare(o1.getPrijsPerKiloGram(), o2.getPrijsPerKiloGram());
    }
}

