package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.context.ICurrentContext;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.Script;
import com.pharmacy.entities.User;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.factories.CurrentContextFactory;
import com.pharmacy.factories.ScriptFactory;
import com.pharmacy.factories.UserFacadeFactory;
import com.pharmacy.models.DoctorScriptModel;
import com.pharmacy.services.IScriptService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

public class DoctorScriptController implements Initializable {
    private final IUserFacade _userFacade;
    private final ICurrentContext _currentContext;
    private final IScriptService _scriptService;

    @FXML
    private TableView<DoctorScriptModel> tbl_scripts;
    @FXML
    private Button btn_exit;
    private DoctorScriptModel _scriptModel = null;

    public DoctorScriptController() {
        _userFacade = (new UserFacadeFactory()).create();
        _currentContext = (new CurrentContextFactory()).create();
        _scriptService = (new ScriptFactory()).create();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        User currentUser = _userFacade.GetUser(_currentContext.getCurrentUsername());
        Doctor doc = (Doctor) _userFacade.GetSubUser(currentUser);
        List<DoctorScriptModel> scripts = mapToScriptModel(_scriptService.GetDoctorScripts(doc.getId()));
        fillTable(scripts);
    }

    private List<DoctorScriptModel> mapToScriptModel(List<Script> getScripts) {
        List<DoctorScriptModel> scripts = new ArrayList<>();
        for(Script script : getScripts){
            scripts.add(new DoctorScriptModel(script.getId(),
                    getPatientName(script.getPatientId()),
                    script.getStatus().toString()));
        }
        return scripts;
    }

    private String getPatientName(String patientId) {
        Patient patient = _userFacade.GetPatientById(patientId);
        return patient.getName();
    }

    private void fillTable(List<DoctorScriptModel> scripts) {
        tbl_scripts.setItems(FXCollections.observableArrayList(scripts));
    }

    private void initializeTable() {
        TableColumn scriptIdCol = new TableColumn("ScriptId");
        scriptIdCol.setMinWidth(350);
        scriptIdCol.setCellValueFactory(new PropertyValueFactory<>("scriptId"));

        TableColumn patientCol = new TableColumn("Patient");
        patientCol.setMinWidth(350);
        patientCol.setCellValueFactory(new PropertyValueFactory<>("patient"));

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setMinWidth(200);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        tbl_scripts.getColumns().addAll(scriptIdCol, patientCol, statusCol);
        tbl_scripts.setRowFactory( tv -> {
            TableRow<DoctorScriptModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    DoctorScriptModel rowData = row.getItem();
                    getDetails(rowData);
                    close();
                }
            });
            return row ;
        });
    }

    public void getDetails(DoctorScriptModel scriptModel){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL location = getClass().getResource("doctorScriptDetail.fxml");
            fxmlLoader.setLocation(location);
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage = new Stage();
            DoctorScriptDetailController controller = fxmlLoader.<DoctorScriptDetailController>getController();
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
