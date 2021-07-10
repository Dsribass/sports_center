package org.example.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.application.Routes;
import org.example.application.view.Window;
import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court.soccer_court.SoccerCourt;

import java.io.IOException;
import java.util.List;

import static org.example.application.main.Main.*;

public class PrincipalUIController {
    @FXML private TableView<SportsCourt> tableView;
    @FXML private TableColumn<SportsCourt,String> cSportName;
    @FXML private TableColumn<SoccerCourt,String> cTypeOfSport;
    @FXML private TableColumn<SportsCourt,Double> cCourtValue;
    @FXML private TableColumn<SportsCourt, CourtSize> cCourtSize;
    @FXML private TableColumn<SportsCourt,Integer> cCapacity;

    ObservableList<SportsCourt> snapshot;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadList();
    }

    private void bindColumnsToValueSources() {
        cSportName.setCellValueFactory(new PropertyValueFactory<>("SportName"));
        cTypeOfSport.setCellValueFactory(new PropertyValueFactory<>("TypeOfSport"));
        cCourtValue.setCellValueFactory(new PropertyValueFactory<>("CourtValue"));
        cCourtSize.setCellValueFactory(new PropertyValueFactory<>("CourtSize"));
        cCapacity.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
    }

    private void bindTableViewToItemsList() {
        snapshot = FXCollections.observableArrayList();
        tableView.setItems(snapshot);
    }

    private void loadList() {
        List<SportsCourt> sportsCourtsList = sportsCourtUseCase.fetchAll();
        snapshot.clear();
        snapshot.addAll(sportsCourtsList);
    }

    @FXML
    private void rentSportsCourt(ActionEvent actionEvent) throws IOException {
        SportsCourt sportsCourt = tableView.getSelectionModel().getSelectedItem();
        if(sportsCourt != null){
            Window.setRoot(Routes.rentSportCourtUI);
            RentSportsCourtUIController controller = (RentSportsCourtUIController) Window.getController();
            controller.setData(sportsCourt);
        }
    }
    @FXML
    private void clientsManagement(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.clientsManagementUI);
    }
    @FXML
    private void rentedSportsCourtManagement(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.rentedSportsCourtManagementUI);
    }
    @FXML
    private void addSportsCourt(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.sportsCourtUI);
        setDataSportsCourt(null,UIMode.INSERT);
    }

    @FXML
    private void updateSportsCourt(ActionEvent actionEvent) throws IOException {
        SportsCourt sportsCourt = tableView.getSelectionModel().getSelectedItem();
        if(sportsCourt != null){
            Window.setRoot(Routes.sportsCourtUI);
            setDataSportsCourt(sportsCourt,UIMode.UPDATE);
        }
    }
    @FXML
    private void removeSportsCourt(ActionEvent actionEvent) {
        SportsCourt sportsCourt = tableView.getSelectionModel().getSelectedItem();
        if(sportsCourt != null){
            sportsCourtUseCase.delete(sportsCourt);
            loadList();
        }
    }

    public void detailSportsCourt(ActionEvent actionEvent) throws IOException {
        SportsCourt sportsCourt = tableView.getSelectionModel().getSelectedItem();
        if(sportsCourt != null){
            Window.setRoot(Routes.sportsCourtUI);
            setDataSportsCourt(sportsCourt,UIMode.DETAIL);
        }
    }

    private void setDataSportsCourt(SportsCourt sportsCourt, UIMode uiMode) throws IOException {
        SportsCourtUIController controller = (SportsCourtUIController) Window.getController();
        controller.setData(sportsCourt, uiMode);
    }
}
