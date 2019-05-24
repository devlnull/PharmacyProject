package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.context.ICurrentContext;
import com.pharmacy.context.UserType;
import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Script;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.factories.CurrentContextFactory;
import com.pharmacy.factories.MedicineFactory;
import com.pharmacy.factories.ScriptFactory;
import com.pharmacy.factories.UserFacadeFactory;
import com.pharmacy.models.DoctorScriptModel;
import com.pharmacy.models.ScriptResponseModel;
import com.pharmacy.services.IMedicineService;
import com.pharmacy.services.IScriptService;
import com.pharmacy.services.MedicineService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DoctorRespondController implements Initializable {
    private final IUserFacade _userFacade;
    private final ICurrentContext _currentContext;
    private final IScriptService _scriptService;
    private final IMedicineService _medicineService;
    private DoctorScriptModel _scriptModel;

    @FXML
    private TextArea txt_respond;
    @FXML
    private Button btn_clear;
    @FXML
    private Button btn_close;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_submit;
    @FXML
    private ListView<Medicine> lst_med;
    @FXML
    private ComboBox<Medicine> combo_med;
    private Medicine selected_med;

    public DoctorRespondController() {
        _userFacade = (new UserFacadeFactory()).create();
        _currentContext = (new CurrentContextFactory()).create();
        _scriptService = (new ScriptFactory()).create();
        _medicineService = (new MedicineFactory()).create();
    }

    public void clearList(ActionEvent event){
        this.lst_med.getItems().clear();
    }

    public void addMedicine(ActionEvent event){
        this.lst_med.getItems().add(selected_med);
    }

    public void submit(ActionEvent event){
        try{
            Script script = _scriptService.GetScriptById(getTargetScript().getScriptId());

            ScriptResponseModel model = new ScriptResponseModel(script,
                    txt_respond.getText(),
                    new ArrayList<>(lst_med.getItems()));
            _scriptService.ResponseToScript(model);
            close(null);
        } catch (Exception ex){
            AlertHelper.GetErrorAlert("Error!","Could not submit your respond.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()-> {
            List<Medicine> medicines = _medicineService.GetMedicines();
            combo_med.setItems(FXCollections.observableArrayList(medicines));
            StringConverter<Medicine> converter = new StringConverter<Medicine>() {
                @Override
                public String toString(Medicine object) {
                    return object.toString();
                }

                @Override
                public Medicine fromString(String string) {
                    String[] terms = string.split(Pattern.quote("|"));
                    List<Medicine> medicines = _medicineService.GetMedicineByName(terms[1]);
                    medicines = medicines.stream()
                            .filter(x -> x.getDosage().equals(terms[2]))
                            .collect(Collectors.toList());
                    return medicines.get(0);
                }
            };
            combo_med.setConverter(converter);
            combo_med.setOnAction(event ->
                    selected_med =  combo_med.getSelectionModel().getSelectedItem());
        });
    }

    public void setTargetScript(DoctorScriptModel targetScript) {
        this._scriptModel = targetScript;
    }

    public DoctorScriptModel getTargetScript() {
        return _scriptModel;
    }

    private void showMain(){
        WindowHelper winHelper = new WindowHelper();
        URL loginURL = getClass().getResource("doctorScripts.fxml");
        winHelper.show(loginURL, "Pharmacy Project", 1024, 960);
    }

    private void closeHere() {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.close();
    }

    public void close(ActionEvent event) {
        try{
            showMain();
            closeHere();
        } catch(Exception ex){
            AlertHelper.GetErrorAlert("Something is wrong!",
                    "Could not close the window!");
        }
    }

}
