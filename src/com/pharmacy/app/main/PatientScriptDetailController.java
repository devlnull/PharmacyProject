package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Script;
import com.pharmacy.entities.ScriptDetail;
import com.pharmacy.factories.MedicineFactory;
import com.pharmacy.factories.ScriptFactory;
import com.pharmacy.models.PatientScriptModel;
import com.pharmacy.services.IMedicineService;
import com.pharmacy.services.IScriptService;
import com.pharmacy.services.MedicineService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatientScriptDetailController implements Initializable {
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_purchase;
    @FXML
    private TextArea txt_respond;
    @FXML
    private ListView<String> lst_detail;
    private final IScriptService _scriptService;
    private final IMedicineService _medicineService;
    private PatientScriptModel selected_script;

    public PatientScriptDetailController(){
        _scriptService = (new ScriptFactory()).create();
        _medicineService = (new MedicineFactory()).create();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Script script = _scriptService.GetScriptById(getTargetScript().getScriptId());
            txt_respond.setText(script.getResponse());
            List<ScriptDetail> scriptDetails = _scriptService.GetScriptDetails(script.getId());
            List<Medicine> medicines = new ArrayList<>();
            for(ScriptDetail detail : scriptDetails){
                Medicine medicine = _medicineService.GetMedicine(detail.getMedicineId());
                lst_detail.getItems().add(medicine.toString());
            }
        });
    }

    public void purchase(ActionEvent event){
        AlertHelper.GetInfoAlert("Not implemented!", "Purchasing system is not implemented yet.");
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

    public PatientScriptModel getTargetScript() {
        return selected_script;
    }

    public void setTargetScript(PatientScriptModel selected_script) {
        this.selected_script = selected_script;
    }
}
