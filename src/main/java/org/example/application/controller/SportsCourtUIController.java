package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.application.Routes;
import org.example.application.view.Window;
import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court.soccer_court.SoccerCourt;
import org.example.domain.entities.sports_court.soccer_court.TypeOfSoccer;
import org.example.domain.entities.sports_court.volleyball_court.TypeOfVolleyball;
import org.example.domain.entities.sports_court.volleyball_court.VolleyballCourt;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.example.application.main.Main.sportsCourtUseCase;

public class SportsCourtUIController {
    @FXML private RadioButton choiceIndoorCourt;
    @FXML private ComboBox<String> cbSelectSportName;
    @FXML private ComboBox<String> cbSelectTypeOfSportCourt;
    @FXML private TextField txtValue;
    @FXML private RadioButton checkBig;
    @FXML private RadioButton checkMedium;
    @FXML private RadioButton checkSmall;
    @FXML private Button btnInsertOrUpdate;

    SportsCourt sportsCourt;
    UIMode uiMode;

    @FXML
    public void initialize() {
        cbSelectSportName.getItems().setAll(Arrays.stream(Sports.values()).map(Sports::toString).collect(Collectors.toList()));
    }

    public void setData(SportsCourt sportsCourt,UIMode uiMode) {
        this.sportsCourt = sportsCourt;
        this.uiMode = uiMode;
        setEntityToViewIfNotNull();
        configViewMode();
    }

    private void configViewMode() {
        if(UIMode.UPDATE == uiMode){
            cbSelectSportName.setDisable(true);
            btnInsertOrUpdate.setText("Atualizar");
        }else if(UIMode.DETAIL == uiMode){
            cbSelectSportName.setDisable(true);
            cbSelectTypeOfSportCourt.setDisable(true);
            choiceIndoorCourt.setDisable(true);
            checkBig.setDisable(true);
            checkMedium.setDisable(true);
            checkSmall.setDisable(true);
            txtValue.setDisable(true);
            btnInsertOrUpdate.setVisible(false);
        }
    }

    private void setEntityToViewIfNotNull() {
        if(sportsCourt != null){
            cbSelectSportName.setValue(sportsCourt.getSportName());
            setValuesOfTypeSportsCourt(sportsCourt.getSportName());
            cbSelectTypeOfSportCourt.setValue(sportsCourt.getTypeOfSport());
            choiceIndoorCourt.setSelected(sportsCourt.getIndoorCourt());
            switch (sportsCourt.getCourtSize()){
                case BIG: {
                    checkBig.setSelected(true);
                    break;
                }
                case MEDIUM: {
                    checkMedium.setSelected(true);
                    break;
                }
                case SMALL: {
                    checkSmall.setSelected(true);
                    break;
                }
            }
            txtValue.setText(sportsCourt.getCourtValue().toString());
        }
    }

    private void getEntityFromView() {
        String sportName = cbSelectSportName.getValue();
        instantiateSportsCourtProperKind(sportName);
        sportsCourt.setIndoorCourt(choiceIndoorCourt.isSelected());
        sportsCourt.setCourtSize(getCourtSizeFromView());
        sportsCourt.setCourtValue(Double.valueOf(txtValue.getText()));
    }

    private CourtSize getCourtSizeFromView() {
        if(checkBig.isSelected())
            return CourtSize.BIG;
        else if(checkMedium.isSelected())
            return CourtSize.MEDIUM;
        else
            return CourtSize.SMALL;
    }

    private void instantiateSportsCourtProperKind(String sportName) {
        if(sportName.equals(Sports.SOCCER.toString())){
            if(sportsCourt == null){
                sportsCourt = new SoccerCourt();
            }
            ((SoccerCourt)sportsCourt).setTypeOfSoccer(TypeOfSoccer.convertToEnum(cbSelectTypeOfSportCourt.getValue()));
        }
        else{
            if(sportsCourt == null){
                sportsCourt = new VolleyballCourt();
            }
            ((VolleyballCourt)sportsCourt).setTypeOfVolleyball(TypeOfVolleyball.convertToEnum(cbSelectTypeOfSportCourt.getValue()));
        }
    }

    public void returnPage(ActionEvent actionEvent) throws IOException {
        Window.setRoot(Routes.principalUI);
    }

    public void insertOrUpdateEntity(ActionEvent actionEvent) throws IOException {
        if(isFilledInputs()){
            getEntityFromView();
            if(uiMode == UIMode.INSERT)
                sportsCourtUseCase.insert(sportsCourt);
            else
                sportsCourtUseCase.update(sportsCourt.getId(),sportsCourt);

            Window.setRoot(Routes.principalUI);
        }
    }

    public void selectSportName(ActionEvent actionEvent) {
        String sportName = cbSelectSportName.getValue();
        if(sportName != null){
            setValuesOfTypeSportsCourt(sportName);
        }
    }

    private void setValuesOfTypeSportsCourt(String sportName) {
        if(sportName.equals(Sports.SOCCER.toString())){
            cbSelectTypeOfSportCourt.setDisable(false);
            cbSelectTypeOfSportCourt.getItems().setAll(Arrays.stream(TypeOfSoccer.values()).map(TypeOfSoccer::toString).collect(Collectors.toList()));
        }else{
            cbSelectTypeOfSportCourt.setDisable(false);
            cbSelectTypeOfSportCourt.getItems().setAll(Arrays.stream(TypeOfVolleyball.values()).map(TypeOfVolleyball::toString).collect(Collectors.toList()));
        }
    }

    private boolean isFilledInputs(){
        return cbSelectSportName.getValue() != null && cbSelectTypeOfSportCourt.getValue() != null &&
                choiceIndoorCourt.getText() != null && txtValue.getText() != null &&
                (checkBig.getText() != null || checkMedium.getText() != null || checkSmall.getText() != null);
    }
}
