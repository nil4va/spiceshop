package practicumopdracht.data;

import practicumopdracht.models.SpecerijWinkel;

import java.io.*;

/**
 * @author Nilava Kazal [studentennummer: 500847707] 20/03/2021 08:16
 */
public class BinarySpecerijWinkelDAO extends SpecerijWinkelDAO {
    private final String FILENAME = "specerijWinkel.dat";

    /**
     * schrijft specerij winkels in de dao
     * @return true
     */

    @Override
    public boolean save() {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(FILENAME)))){

            dataOutputStream.writeInt(specerijWinkels.size());

            for (SpecerijWinkel specerijWinkel : specerijWinkels) {
                dataOutputStream.writeUTF(specerijWinkel.getNaam());
                dataOutputStream.writeUTF(specerijWinkel.getPlaatsnaam());
                dataOutputStream.writeInt(specerijWinkel.getSterren());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * leest specerij winkels uit de dao
     * @return true
     */

    @Override
    public boolean load() {

        specerijWinkels.clear();

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(new File(FILENAME)))){
            int numberOfWinkels = dataInputStream.readInt();

            for (int i = 0; i < numberOfWinkels; i++) {
                String naam = dataInputStream.readUTF();
                String plaatsnaam = dataInputStream.readUTF();
                int sterren = dataInputStream.readInt();

                specerijWinkels.add(new SpecerijWinkel(naam, plaatsnaam, sterren));
            }

        } catch (Exception e) {
            System.out.println("ging fout");
            e.getStackTrace();
        }
        return false;
    }
}
