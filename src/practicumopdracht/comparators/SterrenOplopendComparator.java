package practicumopdracht.comparators;

import practicumopdracht.models.SpecerijWinkel;

import java.util.Comparator;

/**
 * Sorteert sterren in listview oplopend
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 16:43
 */

public class SterrenOplopendComparator implements Comparator<SpecerijWinkel> {
    @Override
    public int compare(SpecerijWinkel o1, SpecerijWinkel o2) {
        return Integer.compare(o1.getSterren(), o2.getSterren());

    }
}
