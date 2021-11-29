package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import practicumopdracht.MainApplication;
import practicumopdracht.Views.SpecerijView;
import practicumopdracht.Views.View;
import practicumopdracht.comparators.*;
import practicumopdracht.data.SpecerijDAO;
import practicumopdracht.models.Specerij;
import practicumopdracht.models.SpecerijWinkel;

import java.time.LocalDate;

/**
 * controller voor detail 'specerij'
 * @author Nilava Kazal [studentennummer: 500847707]
 */

public class SpecerijController extends Controller {
    SpecerijView specerijView;
    private SpecerijDAO specerijDAO = MainApplication.getSpecerijDAO();
    private SpecerijWinkel specerijWinkel;
    private ObservableList<Specerij> specerijen;

    public SpecerijController(SpecerijWinkel object) {
        specerijWinkel = object;
        specerijView = new SpecerijView();

        specerijView.getNieuw().setOnAction(actionEvent -> nieuw());
        specerijView.getOpslaan().setOnAction(actionEvent -> opslaan());
        specerijView.getTerug().setOnAction(actionEvent -> terug());
        specerijView.getVerwijderen().setOnAction(actionEvent -> verwijderen());

        specerijView.getNaamOplopendRadioBtn().setOnAction(actionEvent -> sort());
        specerijView.getNaamAflopendRadioBtn().setOnAction(actionEvent -> sort());
        specerijView.getPrijsOplopendRadioBtn().setOnAction(actionEvent -> sort());
        specerijView.getPrijsAflopendRadioBtn().setOnAction(actionEvent -> sort());
        specerijView.getCbWinkel().setOnAction(actionEvent -> comboBoxWinkelSwitch());

        specerijView.getCbWinkel().getItems().addAll(MainApplication.getSpecerijWinkelDAO().getAll());

        specerijView.getListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                specerijView.getVerwijderen().setVisible(true);
                specerijView.getNieuw().setVisible(true);
                int selectedItem = specerijView.getListView().getSelectionModel().getSelectedIndex();
                if (specerijView.getListView().getSelectionModel().getSelectedIndex() == selectedItem) {
                    specerijView.getNaamSpecerij().setText(specerijView.getListView().getSelectionModel().getSelectedItem().getNaam());
                    specerijView.getGram().setValue(specerijView.getListView().getSelectionModel().getSelectedItem().getGram());
                    specerijView.getCheckBoxStrooibus().setSelected(specerijView.getListView().getSelectionModel().getSelectedItem().isStrooibus());
                    specerijView.getDatePicker().setValue(specerijView.getListView().getSelectionModel().getSelectedItem().getLeverdatum());
                    specerijView.getInvoerPrijsPerKG().setText(String.valueOf(specerijView.getListView().getSelectionModel().getSelectedItem().getPrijsPerKiloGram()));
                }
            }
        });

        refreshData();
    }

    /**
     * combobox om van specerijWinkel (master) te switchen
     */

    private void comboBoxWinkelSwitch() {
        SpecerijWinkel selectedWinkel = specerijView.getCbWinkel().getSelectionModel().getSelectedItem();
        ObservableList<Specerij> winkelSwitch =
                FXCollections.observableArrayList(specerijDAO.getAllFor(selectedWinkel));
        specerijView.getListView().setItems(winkelSwitch);
    }

    /**
     * action event voor verwijderen
     */

    private void verwijderen() {
        if (specerijView.getListView().getSelectionModel().getSelectedItem() == specerijView.getListView().getSelectionModel().getSelectedItem()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "weet u zeker dat u het geselecteerde item wilt " +
                    "verwijderen ?", ButtonType.YES);

            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (ButtonType.YES.equals(result)) {
                MainApplication.getSpecerijDAO().remove(specerijView.getListView().getSelectionModel().getSelectedItem());
                specerijDAO.save();
                specerijDAO.load();
                refreshData();
                clearFields();
            }

        }
    }

    /**
     * action even om naar de master view 'specerij winkel' te gaan
     */

    private void terug() {
        MainApplication.switchController(new SpecerijWinkelController());
    }

    /**
     * action event om detail model 'specerij' op te slaan na validatie
     */

    private void opslaan() {
        LocalDate today = LocalDate.now();

        if (specerijView.getGram().getValue() == "0 gram") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("hoeveelheid gram is niet gekozen");
            alert.show();
        } else if (specerijView.getNaamSpecerij().getText().isBlank() || specerijView.getNaamSpecerij().getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("er is geen specerij ingevuld");
            alert.show();
        } else if (specerijView.getDatePicker().getValue() == null || !today.isBefore(specerijView.getDatePicker().getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("datum is fout");
            alert.show();
        } else if (!specerijView.getTextField().getText().matches("^\\d+\\.[0-9]{2}") ||
                specerijView.getTextField().getText().length() == 0 ||
                specerijView.getTextField().getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("geen geldig prijs per kg");
            alert.show();
        } else {
            if (specerijView.getListView().getSelectionModel().getSelectedItem() == specerijView.getListView().getSelectionModel().getSelectedItem()) {
                specerijDAO.remove(specerijView.getListView().getSelectionModel().getSelectedItem());
            }
            String naam = specerijView.getNaamSpecerij().getText();
            boolean strooibus = specerijView.getCheckBoxStrooibus().isSelected();
            String gram = String.valueOf(specerijView.getGram().getSelectionModel().getSelectedItem());
            LocalDate leverdatum = specerijView.getDatePicker().getValue();
            double prijsPerKG = Double.parseDouble(specerijView.getTextField().getText());
            SpecerijWinkel hoortBij = specerijView.getCbWinkel().getSelectionModel().getSelectedItem();
            Specerij specerij = new Specerij(naam, gram, strooibus, leverdatum, prijsPerKG, hoortBij);
            specerijDAO.addOrUpdate(specerij);
            refreshData();
            clearFields();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(specerij.toString());
            alert.show();
        }
    }

    /**
     * cleart input velden
     */

    private void clearFields() {
        specerijView.getNaamSpecerij().clear();
        specerijView.getGram().setValue("0 gram");
        specerijView.getTextField().clear();
        specerijView.getDatePicker().setValue(null);
        specerijView.getCheckBoxStrooibus().setSelected(false);
        specerijView.getOpmerkingen().setText("opmerking voor bestelling: ");
    }

    /**
     * refresht listview en combobox
     */

    private void refreshData() {
        specerijen =
                FXCollections.observableArrayList(specerijDAO.getAllFor(specerijWinkel));
        specerijView.getListView().setItems(specerijen);
        specerijView.getCbWinkel().setValue(specerijWinkel);
    }

    /**
     * maakt een nieuw model aan als er iets is geselecteerd in de listview
     */

    private void nieuw() {
        specerijView.getListView().getSelectionModel().clearSelection();
        clearFields();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("er is een nieuw aangemaakt");
        alert.show();
    }

    /**
     * action event om de listview te sorteren mbv radiobuttons
     */

    private void sort() {
        if (specerijView.getNaamAflopendRadioBtn().isSelected()) {
            specerijView.getListView().getItems().sort(new NaamSpecerijAflopendComparator());
        } else if (specerijView.getPrijsOplopendRadioBtn().isSelected()) {
            specerijView.getListView().getItems().sort(new PrijsPerKgOplopendComparator());
        } else if (specerijView.getPrijsAflopendRadioBtn().isSelected()) {
            specerijView.getListView().getItems().sort(new PrijsPerKgAflopendComparator());
        } else if (specerijView.getNaamOplopendRadioBtn().isSelected()) {
            specerijView.getListView().getItems().sort(new NaamSpecerijOplopendComparator());

        }
    }

    @Override
    public View getView() {
        return specerijView;
    }
}
