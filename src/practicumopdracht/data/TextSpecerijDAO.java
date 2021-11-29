package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Specerij;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Text DAO voor de detail class 'specerij'
 * @author Nilava Kazal [studentennummer: 500847707] 13/03/2021 20:28
 */
public class TextSpecerijDAO extends SpecerijDAO {
    private final String FILENAME = "specerij.txt";

    /**
     * @return saved alle inputvelden dmv een printwriter
     */

    @Override
    public boolean save() {

        try {
            PrintWriter printWriter = new PrintWriter(new File(FILENAME));

            for (int i = 0; i < specerijen.size(); i++) {
                printWriter.println(specerijen.get(i).specerijGescheidenMetKomma());
            }
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @return load dao dmv een scanner
     */

    @Override
    public boolean load() {
        specerijen.clear();

        try (Scanner scanner = new Scanner(new File(FILENAME))) {

            while (scanner.hasNext()) {
                String name = scanner.nextLine();

                String[] specerijParts = name.split(",");

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate bestelDatum = LocalDate.parse(specerijParts[3], dateTimeFormatter);

                super.specerijen.add(new Specerij(specerijParts[0], specerijParts[1],
                        Boolean.parseBoolean(specerijParts[2]), bestelDatum
                        , Double.parseDouble(specerijParts[4]),
                        MainApplication.getSpecerijWinkelDAO().getById(Integer.parseInt(specerijParts[5]))));


                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
