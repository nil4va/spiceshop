package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import practicumopdracht.MainApplication;
import practicumopdracht.Views.SpecerijWinkelView;
import practicumopdracht.Views.View;
import practicumopdracht.comparators.NaamAflopendComparator;
import practicumopdracht.comparators.NaamOplopendComparator;
import practicumopdracht.comparators.SterrenAflopendComparator;
import practicumopdracht.comparators.SterrenOplopendComparator;
import practicumopdracht.data.SpecerijDAO;
import practicumopdracht.data.SpecerijWinkelDAO;
import practicumopdracht.models.Specerij;
import practicumopdracht.models.SpecerijWinkel;

import java.util.List;

/**
 * controller voor de master class 'specerijWinkel'
 *
 * @author Nilava Kazal [studentennummer: 500847707]
 */

public class SpecerijWinkelController extends Controller {
    private SpecerijWinkelView specerijWinkelView = new SpecerijWinkelView();
    private SpecerijWinkelDAO specerijWinkelDAO = MainApplication.getSpecerijWinkelDAO();
    private SpecerijDAO specerijDAO = MainApplication.getSpecerijDAO();


    public SpecerijWinkelController() {
        specerijWinkelView.getNieuw().setOnAction(actionEvent -> nieuw());
        specerijWinkelView.getOpslaan().setOnAction(actionEvent -> opslaan());
        specerijWinkelView.getTerug().setOnAction(actionEvent -> terug());
        specerijWinkelView.getVerwijderen().setOnAction(actionEvent -> verwijderen());
        specerijWinkelView.getMenuItem().setOnAction(actionEvent -> ladenMenu());
        specerijWinkelView.getMenuItem1().setOnAction(actionEvent -> opslaanMenu());
        specerijWinkelView.getMenuItem2().setOnAction(actionEvent -> afsluitenMenu());
        specerijWinkelView.getMenuItem3().setOnAction(actionEvent -> sortNaamOplopend());
        specerijWinkelView.getMenuItem4().setOnAction(actionEvent -> sortNaamAflopend());
        specerijWinkelView.getMenuItem5().setOnAction(actionEvent -> sortSterrenOplopend());
        specerijWinkelView.getMenuItem6().setOnAction(actionEvent -> sortSterrenAflopend());
        specerijWinkelView.getListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                specerijWinkelView.getVerwijderen().setVisible(true);
                specerijWinkelView.getNieuw().setVisible(true);
                specerijWinkelView.getTerug().setVisible(true);
                int selectedItem = specerijWinkelView.getListView().getSelectionModel().getSelectedIndex();
                if (specerijWinkelView.getListView().getSelectionModel().getSelectedIndex() == selectedItem) {
                    specerijWinkelView.getTextField().setText(specerijWinkelView.getListView().getSelectionModel().getSelectedItem().getNaam());
                    specerijWinkelView.getTfsterren().setText(String.valueOf(specerijWinkelView.getListView().getSelectionModel().getSelectedItem().getSterren()));
                    specerijWinkelView.getTfPlaatsnaam().setText(specerijWinkelView.getListView().getSelectionModel().getSelectedItem().getPlaatsnaam());
                }
            }
        });
        refreshData();
    }

    /**
     * sorteert sterren oplopend mbv menubar item
     */

    private void sortSterrenOplopend() {
        specerijWinkelView.getListView().getItems().sort(new SterrenOplopendComparator());

    }

    /**
     * sorteert sterren aflopend mbv menubar item
     */

    private void sortSterrenAflopend() {
        specerijWinkelView.getListView().getItems().sort(new SterrenAflopendComparator());
    }

    /**
     * sorteert naam oplopend mbv menubar item
     */

    private void sortNaamOplopend() {
        specerijWinkelView.getListView().getItems().sort(new NaamOplopendComparator());
    }

    /**
     * sorteert naam aflopend mbv menubar item
     */
    private void sortNaamAflopend() {
        specerijWinkelView.getListView().getItems().sort(new NaamAflopendComparator());
    }

    /**
     * verwijdert het geselcteerde veld in de listview van de dao & refresht direct.
     */

    private void verwijderen() {
        if (specerijWinkelView.getListView().getSelectionModel().getSelectedItem() == specerijWinkelView.getListView().getSelectionModel().getSelectedItem()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "weet u zeker dat u het geselecteerde item wilt " +
                    "verwijderen ?", ButtonType.YES);

            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (ButtonType.YES.equals(result)) {
                MainApplication.getSpecerijWinkelDAO().remove(specerijWinkelView.getListView().getSelectionModel().getSelectedItem());
                List<Specerij> verwijder =
                        specerijDAO.getAllFor(specerijWinkelView.getListView().getSelectionModel().getSelectedItem());
                for (int i = 0; i < verwijder.size(); i++) {
                    specerijDAO.remove(verwijder.get(i));
                }
                specerijWinkelDAO.save();
                specerijWinkelDAO.load();
                specerijDAO.save();
                specerijDAO.load();
                refreshData();
                resetFields();
            }
        }
    }

    /**
     * vraagt of je applicatie wilt opslaan voor het afluisten, waarna applicatie sluit
     */

    private void afsluitenMenu() {
        opslaanMenu();
        MainApplication.getStage().close();
    }

    /**
     * vraagt of je data wilt opslaan
     */

    private void opslaanMenu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wilt u de data opslaan ?", ButtonType.YES,
                ButtonType.NO);

        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

        if (ButtonType.YES.equals(result)) {
            MainApplication.getSpecerijWinkelDAO().save();
            MainApplication.getSpecerijDAO().save();
            refreshData();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "opslaan gelukt");
            alert1.show();
        }
    }

    /**
     * laadt beide listviews
     */

    private void ladenMenu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "klik 'YES' om te laden", ButtonType.YES,
                ButtonType.NO);

        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

        if (ButtonType.YES.equals(result)) {
            MainApplication.getSpecerijWinkelDAO().load();
            MainApplication.getSpecerijDAO().load();
            refreshData();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "laden gelukt");
            alert1.show();
        }
    }

    /**
     * gaat naar detail view na je een specerij winkel hebt geselecteerd
     */

    private void terug() {
        if (specerijWinkelDAO.getAll().contains(specerijWinkelView.getListView().getSelectionModel().getSelectedItem())) {
            MainApplication.switchController(new SpecerijController(specerijWinkelView.getListView().getSelectionModel().getSelectedItem()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("kies eerst een winkel");
            alert.show();
        }
    }

    /**
     * kijkt of alle inputvelden gedlig zijn, en slaat het op
     */

    private void opslaan() {
        if (specerijWinkelView.getTextField().getText().length() == 0 || specerijWinkelView.getTextField().getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("er is geen winkel ingevuld");
            alert.show();
        } else if (specerijWinkelView.getTfPlaatsnaam().getText().length() == 0 || specerijWinkelView.getTfPlaatsnaam().getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("er is geen plaatsnaam ingevuld");
            alert.show();
        } else if (specerijWinkelView.getTfsterren().getText().isBlank() || specerijWinkelView.getTfsterren().getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("aantal sterren niet ingevuld");
            alert.show();
        } else if (!specerijWinkelView.getTfsterren().getText().matches("^[0-5]*$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("aantal sterren moet tussen 0 en 5 zijn");
            alert.show();
        } else {
            if (specerijWinkelView.getListView().getSelectionModel().getSelectedItem() == specerijWinkelView.getListView().getSelectionModel().getSelectedItem()) {
                specerijWinkelDAO.remove(specerijWinkelView.getListView().getSelectionModel().getSelectedItem());
            }
            String naam = specerijWinkelView.getTextField().getText();
            String plaatsnaam = specerijWinkelView.getTfPlaatsnaam().getText();
            int sterren = Integer.parseInt(specerijWinkelView.getTfsterren().getText());
            SpecerijWinkel specerijWinkel = new SpecerijWinkel(naam, plaatsnaam, sterren);
            specerijWinkelDAO.addOrUpdate(specerijWinkel);
            specerijWinkelDAO.save();
            refreshData();
            resetFields();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(specerijWinkel.toString());
            alert.show();
        }
    }

    /**
     * cleart input velden
     */

    private void resetFields() {
        specerijWinkelView.getTextField().clear();
        specerijWinkelView.getTfPlaatsnaam().clear();
        specerijWinkelView.getTfsterren().clear();
    }

    /**
     * maakt nieuw model aan door alles te deselecteren & input velden te legen
     */

    private void nieuw() {
        specerijWinkelView.getListView().getSelectionModel().clearSelection();
        resetFields();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("er is een nieuw aangemaakt");
        alert.show();
    }

    /**
     * bewerkt selecteerde model
     */

    public void bewerken() {
        specerijWinkelDAO.addOrUpdate(specerijWinkelView.getListView().getSelectionModel().getSelectedItem());
        specerijWinkelDAO.save();
        specerijWinkelDAO.load();
        refreshData();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("alles is bewerkt");
        alert.show();
    }

    /**
     * refresht listview
     */

    private void refreshData() {
        ObservableList<SpecerijWinkel> winkels =
                FXCollections.observableArrayList(specerijWinkelDAO.getAll());
        specerijWinkelView.getListView().setItems(winkels);
        specerijWinkelView.getListView().getItems().sort(new NaamAflopendComparator());
    }

    @Override
    public View getView() {
        return specerijWinkelView;
    }
}
