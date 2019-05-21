package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.context.ICurrentContext;
import com.pharmacy.context.UserType;
import com.pharmacy.entities.User;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.factories.CurrentContextFactory;
import com.pharmacy.factories.UserFacadeFactory;
import com.pharmacy.models.MemberRegisterModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

public class AccountController {
    @FXML
    private TextField txt_firstname;
    @FXML
    private TextField txt_lastname;
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private RadioButton rdb_doctor;
    @FXML
    private RadioButton rdb_patient;
    @FXML
    private RadioButton rdb_employee;
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_register;

    private final IUserFacade _userFacade;
    private final ICurrentContext _currentContext;

    public AccountController() {
        _userFacade = (new UserFacadeFactory()).create();
        _currentContext = (new CurrentContextFactory()).create();
    }

    private MemberRegisterModel getRegisterModel() {
        UserType userType = UserType.Patient;
        if (rdb_doctor.isSelected())
            userType = UserType.Doctor;
        else if (rdb_employee.isSelected())
            userType = UserType.Employee;
        return new MemberRegisterModel(txt_username.getText(),
                txt_password.getText(),
                userType,
                txt_firstname.getText(),
                txt_lastname.getText());
    }

    public void register(ActionEvent event) {
        try {
            if (_userFacade.CreateMember(getRegisterModel())) {
                WindowHelper winHelper = new WindowHelper();
                URL loginURL = getClass().getResource("login.fxml");
                winHelper.show(loginURL, "Log In", 500, 500);
                close();
            }
        }
        catch(Exception ex) {
            AlertHelper.GetInfoAlert("Something is wrong!",
                    "Could not register this account, check the information and try again!");
        }
    }

    public void login(ActionEvent event) {
        try {
            if(_userFacade.LogIn(txt_username.getText(), txt_password.getText())){
                User currentUser = _userFacade.GetUser(txt_username.getText());
                _currentContext.setCurrentUser(currentUser);
                showMain();
                close();
            } else{
                throw new Exception();
            }
        }
        catch(Exception ex) {
            AlertHelper.GetInfoAlert("Log In Failed!",
                    "Username or password is invalid!");
        }
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

    private void close() {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
}
