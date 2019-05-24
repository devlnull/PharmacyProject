package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.context.ICurrentContext;
import com.pharmacy.context.UserType;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.User;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.factories.CurrentContextFactory;
import com.pharmacy.factories.ScriptFactory;
import com.pharmacy.factories.UserFacadeFactory;
import com.pharmacy.models.ScriptRequestModel;
import com.pharmacy.services.IScriptService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PatientController implements Initializable {
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_submitRequest;
    @FXML
    private TextArea txt_request;
    @FXML
    private ComboBox<String> combo_doctors;
    private String selected_doc;

    private final IUserFacade _userFacade;
    private final ICurrentContext _currentContext;
    private final IScriptService _scriptService;

    public PatientController() {
        _userFacade = (new UserFacadeFactory()).create();
        _currentContext = (new CurrentContextFactory()).create();
        _scriptService = (new ScriptFactory()).create();
    }

    private void showMain(){
        WindowHelper winHelper = new WindowHelper();
        URL loginURL = getClass().getResource("main.fxml");
        winHelper.show(loginURL, "Pharmacy Project", 1024, 960);
    }

    public void exit(ActionEvent event) {
        try{
            showMain();
            close();
        } catch(Exception ex){
            AlertHelper.GetErrorAlert("Something is wrong!",
                    "Could not close the window!");
        }
    }

    public void submitRequest(ActionEvent event){
        try{
            User currentUser = _userFacade.GetUser(_currentContext.getCurrentUsername());
            Patient patient = (Patient) _userFacade.GetSubUser(currentUser);
            User tarUser = _userFacade.GetUser(selected_doc);
            Doctor doctor = (Doctor) _userFacade.GetSubUser(tarUser);
            if(doctor != null && patient != null){
                _scriptService.SubmitRequest(new ScriptRequestModel(
                        patient,
                        doctor,
                        txt_request.getText()
                ));
            }
            exit(null);
        } catch(Exception ex){
            AlertHelper.GetErrorAlert("Something is wrong!",
                    "Could not submit your request!");
        }
    }

    private void close() {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo_doctors.setItems(FXCollections.observableArrayList(
                _userFacade.GetGroupOfUsers(UserType.Doctor)
                        .stream()
                        .map(x->x.getUserName())
                        .collect(Collectors.toList())));
        combo_doctors.setOnAction(event -> selected_doc =  combo_doctors.getSelectionModel().getSelectedItem().toString());
    }
}
