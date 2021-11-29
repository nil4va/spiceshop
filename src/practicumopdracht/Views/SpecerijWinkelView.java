package practicumopdracht.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import practicumopdracht.models.SpecerijWinkel;

/**
 * View voor master class 'specerijWinkel'
 * @author Nilava Kazal [studentennummer: 500847707]
 */
public class SpecerijWinkelView extends View {
    private final MenuItem menuItem = new MenuItem("Laden");
    private final MenuItem menuItem1 = new MenuItem("Opslaan");
    private final MenuItem menuItem2 = new MenuItem("Afsluiten");
    private final MenuItem menuItem3 = new MenuItem("naam (A-Z)");
    private final MenuItem menuItem4 = new MenuItem("naam (Z-A)");
    private final MenuItem menuItem5 = new MenuItem("sterren (1-5)");
    private final MenuItem menuItem6 = new MenuItem("sterren (5-1)");
    private final Button opslaan = new Button("Opslaan");
    private final Button nieuw = new Button("Nieuw");
    private final Button verwijderen = new Button("Verwijderen");
    private final Button terug = new Button("Volgende");
    private final Label label = new Label("❅ Welkom in de specerijenwinkel ! ❄︎\n#1 in heel benelux 🏅\nbestel hier uw " +
            "specerijen 〠\n\n");
    private ListView<SpecerijWinkel> listView;
    private final MenuButton menuButton = new MenuButton("Bestand", null, menuItem, menuItem1, menuItem2);
    private final MenuButton menuButton1 = new MenuButton("sorteren", null, menuItem3, menuItem4, menuItem5, menuItem6);
    private final Label specerijNaam = new Label("Specerijwinkel ☞");
    private final TextField textField = new TextField();
    private final Label plaatsnaam = new Label("Plaatsnaam ☞");
    private final TextField tfPlaatsnaam = new TextField();
    private final Label sterren = new Label("aantal sterren (0 tm 5) ☞");
    private final TextField tfsterren = new TextField();
    private final ToggleGroup toggleGroup = new ToggleGroup();

    private Parent rootElement;

    public SpecerijWinkelView() {
        initLayout();
    }

    private void initLayout() {
        label.setScaleY(1.3);
        label.setLineSpacing(10);

        BorderPane borderPane = new BorderPane();
        HBox hBoxMenu = new HBox(menuButton, menuButton1);
        borderPane.setTop(hBoxMenu);

        listView = new ListView<>();

        GridPane gridPane = new GridPane();

        gridPane.setHgap(5);
        gridPane.setVgap(40);

        Region region = new Region();

        verwijderen.setVisible(false);
        terug.setVisible(false);
        nieuw.setVisible(false);

        HBox buttons = new HBox(opslaan, nieuw, verwijderen, terug);
        buttons.setSpacing(20);

        gridPane.addRow(1, specerijNaam, textField);
        gridPane.addRow(2, plaatsnaam, tfPlaatsnaam);
        gridPane.addRow(3, sterren, tfsterren);
        VBox vBox = new VBox(label, gridPane, buttons);
        HBox hBox = new HBox(listView, region, vBox);
        HBox.setHgrow(region, Priority.ALWAYS);
        HBox.setHgrow(region, Priority.ALWAYS);
        vBox.setAlignment(Pos.CENTER);
        HBox.setMargin(listView, new Insets(10, 50, 10, 10));
        VBox.setMargin(gridPane, new Insets(0, 0, 40, 50));
        VBox.setMargin(buttons, new Insets(40, 0, 0, 50));
        VBox.setVgrow(listView, Priority.ALWAYS);
        VBox vBox1 = new VBox(borderPane, hBox);
        HBox root = new HBox(vBox1);

        this.rootElement = root;
    }

    public TextField getTextField() {
        return textField;
    }

    public TextField getTfPlaatsnaam() {
        return tfPlaatsnaam;
    }

    public TextField getTfsterren() {
        return tfsterren;
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

    public ListView<SpecerijWinkel> getListView() {
        return listView;
    }


    public MenuItem getMenuItem() {
        return menuItem;
    }

    public MenuItem getMenuItem1() {
        return menuItem1;
    }

    public MenuItem getMenuItem2() {
        return menuItem2;
    }

    public Button getVerwijderen() {
        return verwijderen;
    }

    public MenuItem getMenuItem3() {
        return menuItem3;
    }

    public MenuItem getMenuItem4() {
        return menuItem4;
    }

    public MenuItem getMenuItem5() {
        return menuItem5;
    }

    public MenuItem getMenuItem6() {
        return menuItem6;
    }

    @Override
    public Parent getRoot() {
        return rootElement;
    }
}



