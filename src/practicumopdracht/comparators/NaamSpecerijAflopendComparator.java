package practicumopdracht.comparators;

import practicumopdracht.models.Specerij;

import java.util.Comparator;

/**
 * Sorteert namen van specerij in listview aflopend
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 19:35
 */
public class NaamSpecerijAflopendComparator implements Comparator<Specerij> {
    @Override
    public int compare(Specerij o1, Specerij o2) {

        if (o1.getNaam() == o2.getNaam()) {
            Double.compare(o1.getPrijsPerKiloGram(), o2.getPrijsPerKiloGram());
        }

        return CharSequence.compare(o2.getNaam(), o1.getNaam());
    }

}
