package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.application.Routes;
import org.example.application.view.Window;
import org.example.domain.entities.client.Client;
import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court_rental.SportsCourtRental;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.example.application.main.Main.*;

public class RentSportsCourtUIController {
    @FXML private Button btnConfirmRent;
    @FXML private Label nameClient;
    @FXML private TextField CPFClient;
    @FXML private Label lbDate;
    @FXML private DatePicker datePicker;
    @FXML private Button btnCheckAvailability;

    SportsCourt sportsCourt;
    Client client;

    private SportsCourtRental getEntityFromView() {
        return new SportsCourtRental(
                sportsCourt,
                client,
                datePicker.getValue()
        );
    }

    public void searchClient(ActionEvent actionEvent) {
        String cpfClientText = CPFClient.getText();
        if(cpfClientText != null){
            final Optional<Client> optionalClient = clientUseCase.findOne(cpfClientText);
            if(optionalClient.isPresent()) {
                client = optionalClient.get();
                nameClient.setText(client.getName());
                setDisableStateInputsDate(false);
            }else{
                client = null;
                nameClient.setText("");
                setDisableStateInputsDate(true);
                Alerts.show(
                        "Usuário não encontrado",
                        "Desculpe, nenhum usuário com esse cpf foi encontrado",
                        Alert.AlertType.ERROR
                );
            }
        }
    }

    private void setDisableStateInputsDate(boolean state) {
        lbDate.setDisable(state);
        btnCheckAvailability.setDisable(state);
        datePicker.setDisable(state);
    }

    public void checkAvailability(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        if(date != null){
            if (rentSportCourtUseCase.isRentedSportsCourt(sportsCourt,date)){
                Alerts.show("Indisponível","Quadra já está reservada para uso neste dia!", Alert.AlertType.ERROR);
                btnConfirmRent.setDisable(true);
            }else{
                Alerts.show("Disponível","Quadra disponível para ser alugada!!!", Alert.AlertType.INFORMATION);
                btnConfirmRent.setDisable(false);
            }
        }
    }

    public void confirmRent(ActionEvent actionEvent) throws IOException {
        if(isFilledInputs()){
            SportsCourtRental sportsCourtRental = getEntityFromView();
            rentSportCourtUseCase.rentSportCourt(sportsCourtRental);
            Window.setRoot(Routes.principalUI);
        }
    }

    public void returnPage(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.principalUI);
    }

    public void setData(SportsCourt sportsCourt) {
        this.sportsCourt = sportsCourt;
    }

    private boolean isFilledInputs(){
        return CPFClient.getText() != null && datePicker.getValue() != null;
    }
}
