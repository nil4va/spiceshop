package practicumopdracht.comparators;

import practicumopdracht.models.SpecerijWinkel;

import java.util.Comparator;

/**
 * Sorteert sterren in listview aflopend
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 16:43
 */

public class SterrenAflopendComparator implements Comparator<SpecerijWinkel> {
    @Override
    public int compare(SpecerijWinkel o1, SpecerijWinkel o2) {
        return Integer.compare(o2.getSterren(), o1.getSterren());
    }
}
