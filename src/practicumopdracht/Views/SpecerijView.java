package practicumopdracht.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import practicumopdracht.models.Specerij;
import practicumopdracht.models.SpecerijWinkel;

/**
 * View voor detail class 'specerij'
 * @author Nilava Kazal [studentennummer: 500847707]
 */
public class SpecerijView extends View {
    private final RadioButton naamOplopendRadioBtn = new RadioButton("naam (A-Z)");
    private final RadioButton naamAflopendRadioBtn = new RadioButton("naam (Z-A)");
    private final RadioButton sterrenOplopendRadioBtn = new RadioButton("prijs per kg (laag-hoog))");
    private final RadioButton sterrenAflopendRadioBtn = new RadioButton("prijs per kg (hoog-laag))");
    private final ToggleGroup toggleGroup = new ToggleGroup();

    private final Button opslaan = new Button("Opslaan");
    private final Button nieuw = new Button("Nieuw");
    private final Button verwijderen = new Button("verwijderen");
    private Parent rootElement;
    GridPane gridPane = new GridPane();
    Label label = new Label("❅ Welkom in de specerijenwinkel ! ❄︎\n#1 in heel benelux 🏅\nbestel hier uw " +
            "specerijen 〠\n\n");
    private CheckBox checkBoxStrooibus = new CheckBox("lever lege strooibus per specerij");
    Label inHuisOp = new Label("Lever thuis op deze datum ☞");
    DatePicker datePicker = new DatePicker();
    TextArea opmerkingen = new TextArea("opmerking voor bestelling: ");
    Region region = new Region();
    Label gramLabel = new Label("Hoeveel gram ? ☞");
    ComboBox<String> gram = new ComboBox<>();
    Label prijsPerKG = new Label("Prijs per KG ☞");
    TextField invoerPrijsPerKG = new TextField();
    Label naam = new Label("Specerij ☞");
    TextField naamSpecerij = new TextField();
    ComboBox<SpecerijWinkel> cbWinkel = new ComboBox<>();
    private ListView<Specerij> listView = new ListView<>();

    private void initLayout() {
        label.setTextAlignment(TextAlignment.CENTER);
        label.setScaleY(1.3);
        opmerkingen.setMaxSize(370, 100);
        gridPane.addRow(1, naam, naamSpecerij);
        gridPane.addRow(2, gramLabel, gram);
        gridPane.addRow(3, checkBoxStrooibus);
        gridPane.addRow(4, inHuisOp, datePicker);
        gridPane.addRow(5, prijsPerKG, invoerPrijsPerKG);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        naamOplopendRadioBtn.setToggleGroup(toggleGroup);
        naamAflopendRadioBtn.setToggleGroup(toggleGroup);
        sterrenOplopendRadioBtn.setToggleGroup(toggleGroup);
        sterrenAflopendRadioBtn.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(naamOplopendRadioBtn);
        VBox radioButtons = new VBox(naamOplopendRadioBtn, naamAflopendRadioBtn, sterrenOplopendRadioBtn,
                sterrenAflopendRadioBtn);

        naamOplopendRadioBtn.setPadding(new Insets(10, 0, 0, 0));
        naamAflopendRadioBtn.setPadding(new Insets(5, 0, 0, 0));
        sterrenOplopendRadioBtn.setPadding(new Insets(5, 0, 0, 0));
        sterrenAflopendRadioBtn.setPadding(new Insets(5, 0, 0, 0));


        gram.getItems().addAll("100 gr", "200 gr", "500 gr");
        gram.setValue("0 gram");

        verwijderen.setVisible(false);
        nieuw.setVisible(false);

        HBox buttons = new HBox(opslaan, nieuw, verwijderen, terug);
        buttons.setSpacing(20);
        HBox.setHgrow(region, Priority.ALWAYS);
        HBox hBox = new HBox(label, cbWinkel);
        hBox.setSpacing(10);
        VBox vBox = new VBox(hBox, gridPane, opmerkingen, buttons);
        vBox.setSpacing(25);
        VBox vBox1 = new VBox(listView, radioButtons);
        vBox1.setPadding(new Insets(40, 40, 40, 0));
        HBox hBox1 = new HBox(vBox, region, vBox1);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        HBox.setMargin(vBox, new Insets(20, 10, 20, 50));
        HBox.setMargin(listView, new Insets(10, 50, 10, 0));
        VBox.setMargin(gridPane, new Insets(0, 0, 15, 0));
        HBox.setHgrow(listView, Priority.ALWAYS);
        this.rootElement = hBox1;
    }

    @Override
    public Parent getRoot() {
        return this.rootElement;
    }

    public Button getOpslaan() {
        return opslaan;
    }

    public Button getNieuw() {
        return nieuw;
    }

    public Button getTerug() {
        return terug;
    }

    private Button terug = new Button("Terug");

    public SpecerijView() {
        initLayout();
    }

    public TextField getTextField() {
        return invoerPrijsPerKG;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public CheckBox getCheckBoxStrooibus() {
        return checkBoxStrooibus;
    }

    public TextArea getOpmerkingen() {
        return opmerkingen;
    }

    public ComboBox<String> getGram() {
        return gram;
    }

    public TextField getNaamSpecerij() {
        return naamSpecerij;
    }

    public ComboBox<SpecerijWinkel> getCbWinkel() {
        return cbWinkel;
    }

    public ListView<Specerij> getListView() {
        return listView;
    }

    public RadioButton getNaamOplopendRadioBtn() {
        return naamOplopendRadioBtn;
    }

    public RadioButton getNaamAflopendRadioBtn() {
        return naamAflopendRadioBtn;
    }

    public RadioButton getPrijsOplopendRadioBtn() {
        return sterrenOplopendRadioBtn;
    }

    public RadioButton getPrijsAflopendRadioBtn() {
        return sterrenAflopendRadioBtn;
    }

    public Button getVerwijderen() {
        return verwijderen;
    }

    public TextField getInvoerPrijsPerKG() {
        return invoerPrijsPerKG;
    }
}
