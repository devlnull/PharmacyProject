package com.pharmacy.app.main;

import com.pharmacy.app.helpers.AlertHelper;
import com.pharmacy.app.helpers.HyperLinkHelper;
import com.pharmacy.app.helpers.WindowHelper;
import com.pharmacy.context.ICurrentContext;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.factories.CurrentContextFactory;
import com.pharmacy.factories.UserFacadeFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final URI aboutURI = java.net.URI.create("https://github.com/devlnull/PharmacyProject/");
    private final IUserFacade _userFacade;
    private final ICurrentContext _currentContext;
    @FXML
    private Label lbl_username;
    @FXML
    private Button btn_auth;
    @FXML
    private Button btn_register;

    public MainController(){
        this._currentContext = (new CurrentContextFactory()).create();
        _userFacade = (new UserFacadeFactory()).create();
    }

    public void menu_about(ActionEvent event){
        HyperLinkHelper.openWebpage(aboutURI);
    }

    public void registerShow(ActionEvent event){
        WindowHelper winHelper = new WindowHelper();
        URL loginURL = getClass().getResource("register.fxml");
        winHelper.show(loginURL,"Register", 500, 500);
        close();
    }

    public void loginShow(ActionEvent event){
        WindowHelper winHelper = new WindowHelper();
        URL loginURL = getClass().getResource("login.fxml");
        winHelper.show(loginURL,"Log In", 500, 500);
        close();
    }

    public void exit(ActionEvent event){
        Platform.exit();
    }

    public void logout(ActionEvent event) {
        try {
            _userFacade.LogOut(_currentContext.getCurrentUsername());
            loginShow(null);
            _currentContext.flush();
        }
        catch(Exception ex) {
            AlertHelper.GetErrorAlert("Log Out Failed!",
                    "Something is wrong, could not log out of the system.");
        }
    }

    private void close() {
        Stage stage = (Stage) btn_auth.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(_currentContext.isLoggedIn()){
            lbl_username.setText(_currentContext.getCurrentUserInfo());
            btn_auth.setText("Logout");
            btn_auth.setOnAction(this::logout);
            btn_register.setVisible(false);
        } else {
            btn_register.setVisible(true);
            lbl_username.setText("Guest");
            btn_auth.setText("Log In");
            btn_auth.setOnAction(this::loginShow);
            btn_register.setOnAction(this::registerShow);
        }
    }
}
