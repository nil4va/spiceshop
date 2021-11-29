package practicumopdracht.models;

/**
 * Model voor master class 'specerijWinkel'
 * @author Nilava Kazal [studentennummer: 500847707]
 */
public class SpecerijWinkel {
    private String naam;
    private String plaatsnaam;
    private int sterren;

    public SpecerijWinkel(String naam, String plaatsnaam, int sterren) {
        this.naam = naam;
        this.plaatsnaam = plaatsnaam;
        this.sterren = sterren;
    }

    /**
     * @return een string met alle waardes van de attributen
     */

    @Override
    public String toString() {
        return "naam: " + naam +
                "\nplaatsnaam: " + plaatsnaam +
                "\nsterren: " + sterren + " sterren";
    }

    /**
     * @return en string waar alle attributen gescheiden zijn dmv een komma
     */

    public String specerijwinkelsGescheidenMetKomma(){
        return String.format("%s,%s,%d", this.naam, this.plaatsnaam, this.sterren);
    }

    public String getNaam() {
        return naam;
    }

    public String getPlaatsnaam() {
        return plaatsnaam;
    }

    public int getSterren() {
        return sterren;
    }
}
