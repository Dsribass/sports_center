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
import org.example.domain.entities.client.Address;
import org.example.domain.entities.client.Client;

import java.io.IOException;
import java.util.List;

import static org.example.application.main.Main.clientUseCase;

public class ClientsManagementUIController {
    @FXML
    private TableView<Client> tableView;
    @FXML
    private TableColumn<Client, String> cCPF;
    @FXML
    private TableColumn<Client, String> cName;
    @FXML
    private TableColumn<Address, String> cCity;
    @FXML
    private TableColumn<Address, String> cRoad;
    @FXML
    private TableColumn<Address, Integer> cNumber;

    ObservableList<Client> snapshot;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadList();
    }

    private void bindColumnsToValueSources() {
        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        cRoad.setCellValueFactory(new PropertyValueFactory<>("road"));
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
    }

    private void bindTableViewToItemsList() {
        snapshot = FXCollections.observableArrayList();
        tableView.setItems(snapshot);
    }

    private void loadList() {
        List<Client> clients = clientUseCase.fetchAll();
        snapshot.clear();
        snapshot.addAll(clients);
    }

    public void returnPage(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.principalUI);
    }

    public void addClient(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.clientUI);
        setDataClient(null, UIMode.INSERT);
    }

    public void updateClient(ActionEvent actionEvent) throws IOException {
        Client client = tableView.getSelectionModel().getSelectedItem();
        if (client != null) {
            Window.setRoot(Routes.clientUI);
            setDataClient(client, UIMode.UPDATE);
        }
    }

    public void removeClient(ActionEvent actionEvent) {
        Client client = tableView.getSelectionModel().getSelectedItem();
        if (client != null) {
            clientUseCase.delete(client);
            loadList();
        }
    }

    public void detailClient(ActionEvent actionEvent) throws IOException {
        Client client = tableView.getSelectionModel().getSelectedItem();
        if (client != null) {
            Window.setRoot(Routes.clientUI);
            setDataClient(client, UIMode.DETAIL);
        }
    }

    private void setDataClient(Client client, UIMode uiMode) throws IOException {
        ClientUIController controller = (ClientUIController) Window.getController();
        controller.setData(client, uiMode);
    }

}


