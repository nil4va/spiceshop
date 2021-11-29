package practicumopdracht.models;

import practicumopdracht.MainApplication;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Model voor detail class 'specerij'
 * @author Nilava Kazal [studentennummer: 500847707]
 */
public class Specerij implements Serializable {
    private String naam;
    private String gram;
    private boolean strooibus;
    private LocalDate leverdatum;
    private double prijsPerKiloGram;
    private transient SpecerijWinkel hoortBij;
    private transient int indexHoortBij;

    public Specerij(String naam, String gram, boolean strooibus, LocalDate leverdatum, double prijsPerKiloGram, SpecerijWinkel hoortBij) {
        this.naam = naam;
        this.gram = gram;
        this.strooibus = strooibus;
        this.leverdatum = leverdatum;
        this.prijsPerKiloGram = prijsPerKiloGram;
        this.hoortBij = hoortBij;
        this.indexHoortBij = MainApplication.getSpecerijWinkelDAO().getIdFor(hoortBij);
    }

    public SpecerijWinkel getHoortBij() {
        return hoortBij;
    }

    /**
     * @return en string waar alle attributen gescheiden zijn dmv een komma
     */

    public String specerijGescheidenMetKomma() {
        return String.format("%s,%s,%b,%s,%2.2f,%d", this.naam, this.gram, this.strooibus, this.leverdatum,
                this.prijsPerKiloGram, this.indexHoortBij);
    }

    /**
     * @return een string met alle waardes van de attributen
     */

    @Override
    public String toString() {
        return "naam: " + naam +
                "\ngram: " + gram +
                "\nstrooibus: " + strooibus +
                "\nleverdatum: " + leverdatum +
                "\nprijsPerKiloGram: " + prijsPerKiloGram;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijsPerKiloGram() {
        return prijsPerKiloGram;
    }

    public String getGram() {
        return gram;
    }

    public boolean isStrooibus() {
        return strooibus;
    }

    public LocalDate getLeverdatum() {
        return leverdatum;
    }

    public void setHoortBij(SpecerijWinkel hoortBij) {
        this.hoortBij = hoortBij;
    }
}
