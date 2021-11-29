package practicumopdracht.comparators;
import practicumopdracht.models.SpecerijWinkel;

import java.util.Comparator;

/**
 * Sorteert namen in listview aflopend
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 16:42
 */

public class NaamAflopendComparator implements Comparator<SpecerijWinkel> {
    @Override
    public int compare(SpecerijWinkel o1, SpecerijWinkel o2) {
        return CharSequence.compare(o2.getNaam(), o1.getNaam());
    }
}
