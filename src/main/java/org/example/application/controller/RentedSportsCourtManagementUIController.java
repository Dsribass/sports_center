package org.example.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.application.Routes;
import org.example.application.view.Window;
import org.example.domain.entities.client.Client;
import org.example.domain.entities.sports_court_rental.SportsCourtRental;

import java.io.IOException;
import java.util.List;

import static org.example.application.main.Main.*;

public class RentedSportsCourtManagementUIController {
    @FXML
    private TableView<SportsCourtRental> tableView;
    @FXML
    private TableColumn<SportsCourtRental, String> cSportName;
    @FXML
    private TableColumn<SportsCourtRental, String> cTypeOfSport;
    @FXML
    private TableColumn<SportsCourtRental, String> cIDSportCourt;
    @FXML
    private TableColumn<SportsCourtRental, String> cCPFClient;
    @FXML
    private TableColumn<SportsCourtRental, String> cDate;
    @FXML
    private TextField CPFClient;
    @FXML
    private Label nameClient;

    ObservableList<SportsCourtRental> snapshot;
    Client client;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadList();
    }

    private void bindColumnsToValueSources() {
        cIDSportCourt.setCellValueFactory(new PropertyValueFactory<>("IdSportCourt"));
        cSportName.setCellValueFactory(new PropertyValueFactory<>("SportName"));
        cTypeOfSport.setCellValueFactory(new PropertyValueFactory<>("TypeOfSport"));
        cCPFClient.setCellValueFactory(new PropertyValueFactory<>("CPFClient"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void bindTableViewToItemsList() {
        snapshot = FXCollections.observableArrayList();
        tableView.setItems(snapshot);
    }

    private void loadList() {
        List<SportsCourtRental> sportsCourtRentals = findSportsCourtRentalUseCase.fetchAll();
        snapshot.clear();
        snapshot.addAll(sportsCourtRentals);
    }

    private void loadListByClient() {
        if (client != null) {
            List<SportsCourtRental> sportsCourtRentals = findSportsCourtRentalUseCase.fetchAllByClient(client);
            snapshot.clear();
            snapshot.addAll(sportsCourtRentals);
        }
    }

    public void returnPage(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.principalUI);
    }

    public void cancelRentSportCourt(ActionEvent actionEvent) {
        SportsCourtRental sportsCourtRental = tableView.getSelectionModel().getSelectedItem();
        if (sportsCourtRental != null) {
            if (cancelRentSportCourtUseCase.cancelRentSportCourt(sportsCourtRental)) {
                Alerts.show("Sucesso!", "Reserva Cancelada!", Alert.AlertType.INFORMATION);
                clearFilter();
                loadList();
            } else {
                Alerts.show("ERRO!", "Ocorreu algum erro!", Alert.AlertType.ERROR);
            }
            ;
        }
    }

    private void clearFilter() {
        nameClient.setText("");
        CPFClient.setText("");
    }

    public void filterByCPF(ActionEvent actionEvent) {
        String cpfClientText = CPFClient.getText();
        if (cpfClientText != null && !cpfClientText.equals("")) {
            verifyIfExistsAndSaveClient(cpfClientText);
            if(client != null){
                nameClient.setText(client.getName());
                loadListByClient();
            }else {
                clearFilter();
                Alerts.show("Usuário não encontrado", "Desculpe, nenhum usuário com esse cpf foi encontrado", Alert.AlertType.ERROR);
                loadList();
            }
        }else {
            clearFilter();
            loadList();
        }
    }

    private void verifyIfExistsAndSaveClient(String cpfClientText) {
        clientUseCase.findOne(cpfClientText).ifPresentOrElse(cli -> client = cli,() -> client = null);
    }

}
