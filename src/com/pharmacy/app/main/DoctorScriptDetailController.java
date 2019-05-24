package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.context.ICurrentContext;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.Script;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.factories.CurrentContextFactory;
import com.pharmacy.factories.ScriptFactory;
import com.pharmacy.factories.UserFacadeFactory;
import com.pharmacy.models.DoctorScriptModel;
import com.pharmacy.services.IScriptService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorScriptDetailController implements Initializable {
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_respond;
    @FXML
    private Label lbl_patient;
    @FXML
    private TextArea txt_request;
    private DoctorScriptModel _scriptModel;

    private final IUserFacade _userFacade;
    private final ICurrentContext _currentContext;
    private final IScriptService _scriptService;

    public DoctorScriptDetailController() {
        _userFacade = (new UserFacadeFactory()).create();
        _currentContext = (new CurrentContextFactory()).create();
        _scriptService = (new ScriptFactory()).create();
    }

    public void setTargetScript(DoctorScriptModel scriptModel) {
        this._scriptModel = scriptModel;
    }

    public DoctorScriptModel getTargetScript() {
        return _scriptModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Script script = _scriptService.GetScriptById(getTargetScript().getScriptId());
            Patient patient = _userFacade.GetPatientById(script.getPatientId());
            lbl_patient.setText(patient.getName());
            txt_request.setText(script.getRequest());
        });
    }

    public void respond(ActionEvent event) {
        try{
            showRespondWindow();
            close();
        } catch(Exception ex){
            AlertHelper.GetErrorAlert("Something is wrong!",
                    "Could not submit your respond");
        }
    }

    public void showRespondWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL location = getClass().getResource("doctorResponse.fxml");
            fxmlLoader.setLocation(location);
            Scene scene = new Scene(fxmlLoader.load(), 600, 800);
            Stage stage = new Stage();
            DoctorRespondController controller = fxmlLoader.<DoctorRespondController>getController();
            controller.setTargetScript(getTargetScript());
            stage.setResizable(false);
            stage.setTitle("Script request details");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    private void showMain(){
        WindowHelper winHelper = new WindowHelper();
        URL loginURL = getClass().getResource("doctorScripts.fxml");
        winHelper.show(loginURL, "Scripts", 960, 800);
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

    private void close() {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
}
