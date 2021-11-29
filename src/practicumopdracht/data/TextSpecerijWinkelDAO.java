package practicumopdracht.data;

import practicumopdracht.models.SpecerijWinkel;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Text DAO voor de master class 'specerijWinkel'
 * @author Nilava Kazal [studentennummer: 500847707] 13/03/2021 20:28
 */

public class TextSpecerijWinkelDAO extends SpecerijWinkelDAO {
    private final String FILENAME = "specerijWinkel.txt";

    /**
     * schrijft speceri winkels in de dao
     * @return true
     */

    @Override
    public boolean save() {

        try {
            PrintWriter printWriter = new PrintWriter(new File(FILENAME));

            for (int i = 0; i < specerijWinkels.size(); i++) {
                printWriter.println(specerijWinkels.get(i).specerijwinkelsGescheidenMetKomma());
            } printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * leest speceri winkels uit de dao
     * @return true
     */

    @Override
    public boolean load() {
        specerijWinkels.clear();

        try (Scanner scanner = new Scanner(new File(FILENAME))) {

            while (scanner.hasNext()) {
                String name = scanner.nextLine();

                String [] specerijWinkelParts = name.split(",");

                if(specerijWinkelParts.length == 3){
                    super.specerijWinkels.add(new SpecerijWinkel(specerijWinkelParts[0], specerijWinkelParts[1],
                            Integer.parseInt(specerijWinkelParts[2])));
                }

                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
