package practicumopdracht.comparators;

import practicumopdracht.models.Specerij;

import java.util.Comparator;

/**
 * Sorteert namen van specerijen in listview oplopend
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 19:35
 */
public class NaamSpecerijOplopendComparator implements Comparator<Specerij> {


    @Override
    public int compare(Specerij o1, Specerij o2) {

        if (o1.getNaam() == o2.getNaam()){
            Double.compare(o2.getPrijsPerKiloGram(), o1.getPrijsPerKiloGram());
        }

        return CharSequence.compare(o1.getNaam(), o2.getNaam());
    }
}

