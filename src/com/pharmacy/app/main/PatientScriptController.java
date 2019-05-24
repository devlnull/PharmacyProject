package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.context.ICurrentContext;
import com.pharmacy.entities.*;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.factories.CurrentContextFactory;
import com.pharmacy.factories.ScriptFactory;
import com.pharmacy.factories.UserFacadeFactory;
import com.pharmacy.models.DoctorScriptModel;
import com.pharmacy.models.PatientScriptModel;
import com.pharmacy.services.IScriptService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientScriptController implements Initializable {
    private final IUserFacade _userFacade;
    private final ICurrentContext _currentContext;
    private final IScriptService _scriptService;

    @FXML
    private TableView<PatientScriptModel> tbl_scripts;
    @FXML
    private Button btn_exit;

    public PatientScriptController() {
        _userFacade = (new UserFacadeFactory()).create();
        _currentContext = (new CurrentContextFactory()).create();
        _scriptService = (new ScriptFactory()).create();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        User currentUser = _userFacade.GetUser(_currentContext.getCurrentUsername());
        Patient patient = (Patient) _userFacade.GetSubUser(currentUser);
        List<PatientScriptModel> scripts = mapToScriptModel(_scriptService.GetPatientScripts(patient.getId()));
        fillTable(scripts);
    }

    private List<PatientScriptModel> mapToScriptModel(List<Script> getScripts) {
        List<PatientScriptModel> scripts = new ArrayList<>();
        for(Script script : getScripts){
            scripts.add(new PatientScriptModel(script.getId(),
                    getDoctorName(script.getDoctorId()),
                    script.getStatus().toString()));
        }
        return scripts;
    }

    private String getDoctorName(String doctorId) {
        Doctor doc = _userFacade.GetDoctorById(doctorId);
        return doc.getName();
    }

    private void fillTable(List<PatientScriptModel> scripts) {
        tbl_scripts.setItems(FXCollections.observableArrayList(scripts));
    }

    private void initializeTable() {
        TableColumn scriptIdCol = new TableColumn("ScriptId");
        scriptIdCol.setMinWidth(350);
        scriptIdCol.setCellValueFactory(new PropertyValueFactory<>("scriptId"));

        TableColumn doctorCol = new TableColumn("Doctor");
        doctorCol.setMinWidth(350);
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setMinWidth(200);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        tbl_scripts.getColumns().addAll(scriptIdCol, doctorCol, statusCol);
        tbl_scripts.setRowFactory( tv -> {
            TableRow<PatientScriptModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    PatientScriptModel rowData = row.getItem();
                    if(rowData.getStatus().equals(ScriptStatus.Responded.toString())){
                        getDetails(rowData);
                        close();
                    } else{
                        AlertHelper.GetInfoAlert("Not Responded",
                                "The script you want to see details, is not responded yet!");
                    }
                }
            });
            return row ;
        });
    }

    public void getDetails(PatientScriptModel scriptModel){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL location = getClass().getResource("patientResult.fxml");
            fxmlLoader.setLocation(location);
            Scene scene = new Scene(fxmlLoader.load(), 450, 555);
            Stage stage = new Stage();
            PatientScriptDetailController controller = fxmlLoader.<PatientScriptDetailController>getController();
            controller.setTargetScript(scriptModel);
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
        URL loginURL = getClass().getResource("main.fxml");
        winHelper.show(loginURL, "Pharmacy Project", 1024, 960);
    }

    private void close() {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
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
}
