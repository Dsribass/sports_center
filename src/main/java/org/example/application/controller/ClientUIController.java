package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.application.Routes;
import org.example.application.view.Window;
import org.example.domain.entities.client.Address;
import org.example.domain.entities.client.Client;

import java.io.IOException;

import static org.example.application.main.Main.clientUseCase;

public class ClientUIController {
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtRoad;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtComplement;
    @FXML
    private Button btnAddClient;

    Client client;
    UIMode uiMode;

    public void setData(Client client, UIMode uiMode) {
        this.client = client;
        this.uiMode = uiMode;
        setEntityToViewIfNotNull();
        configViewMode();
    }

    private void configViewMode() {
        if (uiMode == UIMode.DETAIL) {
            txtCPF.setDisable(true);
            txtName.setDisable(true);
            txtCity.setDisable(true);
            txtState.setDisable(true);
            txtRoad.setDisable(true);
            txtNumber.setDisable(true);
            txtComplement.setDisable(true);
            btnAddClient.setVisible(false);
        } else if (uiMode == UIMode.UPDATE) {
            txtCPF.setDisable(true);
            btnAddClient.setText("Atualizar");
        }
    }

    private void setEntityToViewIfNotNull() {
        if (client != null) {
            txtCPF.setText(client.getCpf());
            txtName.setText(client.getName());
            txtCity.setText(client.getCity());
            txtState.setText(client.getState());
            txtRoad.setText(client.getRoad());
            txtNumber.setText(client.getNumber().toString());
            txtComplement.setText(client.getComplement());
        }
    }

    private void getEntityFromView() {
        if (client == null)
            client = new Client();
        if (client.getAddress() == null)
            client.setAddress(new Address(client.getCpf()));

        client.setCpf(txtCPF.getText());
        client.setName(txtName.getText());
        client.getAddress().setCity(txtCity.getText());
        client.getAddress().setState(txtState.getText());
        client.getAddress().setRoad(txtRoad.getText());
        client.getAddress().setNumber(Integer.valueOf(txtNumber.getText()));
        client.getAddress().setComplement(txtComplement.getText());
    }

    public void returnPage(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.clientsManagementUI);
    }

    public void insertOrUpdateClient(ActionEvent actionEvent) throws IOException {
        if (isFilledInputs()) {
            getEntityFromView();
            if (UIMode.INSERT == uiMode)
                clientUseCase.insert(client);
            else
                clientUseCase.update(client.getCpf(), client);
            Window.setRoot(Routes.clientsManagementUI);
        }
    }

    private boolean isFilledInputs() {
        return txtCPF.getText() != null && txtName.getText() != null &&
                txtCity.getText() != null && txtState.getText() != null &&
                txtRoad.getText() != null && txtNumber.getText() != null;
    }
}
