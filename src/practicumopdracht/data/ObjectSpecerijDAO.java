package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Specerij;

import java.io.*;

/**
 * Object DAO voor de detail class 'specerij'
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 08:13
 */
public class ObjectSpecerijDAO extends SpecerijDAO {
    private final String FILENAME = "specerij.dat";

    /**
     * schrijft specerij winkels in de dao
     * @return true
     */

    @Override
    public boolean save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(FILENAME)))) {

            oos.writeInt(specerijen.size());

            for (Specerij specerij : specerijen) {
                oos.writeObject(specerij);
                oos.writeInt(MainApplication.getSpecerijWinkelDAO().getIdFor(specerij.getHoortBij()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * leest specerij winkels uit de dao
     * @return true
     */

    @Override
    public boolean load() {
        specerijen.clear();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FILENAME)))) {
            int numberOfSpecerijen = ois.readInt();

            for (int i = 0; i < numberOfSpecerijen; i++) {
                Specerij specerij = (Specerij) ois.readObject();
                int specerijWinkel = ois.readInt();
                specerij.setHoortBij(MainApplication.getSpecerijWinkelDAO().getById(specerijWinkel));
                specerijen.add(specerij);
            }
            return true;
        } catch (Exception e) {
            System.out.println("het ging niet goed");
            e.printStackTrace();
        }
        return true;
    }
}
