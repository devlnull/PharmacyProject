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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    @FXML
    private HBox hbox_header;
    @FXML
    private TilePane tiles_content;

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
            initializeUser();
            initializeHeader();
            initializeContent();
        } else {
            initializeGuest();
        }
    }

    private Node[] getDoctorContent(){
        return new Node[] {
                createBox("Scripts", new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        WindowHelper winHelper = new WindowHelper();
                        URL loginURL = getClass().getResource("doctorScripts.fxml");
                        winHelper.show(loginURL,"Scripts", 960, 800);
                        close();
                    }
                }),
                createBox("Licenses", null),
        };
    }

    private Button createBox(String text,
                           EventHandler<ActionEvent> event){
        Button btn = new Button(text);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.prefHeight(256);
        btn.prefWidth(256);
        btn.setFont(new Font(36));
        btn.setOnAction(event);
        btn.setTextFill(Color.web("#77067d"));
        btn.setPadding(new Insets(50,50,50,50));
        btn.setStyle("-fx-background-radius:50;");

        btn.setOnMouseEntered(new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent t) {
                btn.setStyle("-fx-background-color:#77067d;");
                btn.setTextFill(Color.web("white"));
            }
        });

        btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                btn.setStyle("-fx-background-radius:50;");
                btn.setStyle("-fx-background-color:transparent;");
                btn.setTextFill(Color.web("#77067d"));
            }
        });
        return btn;
    }

    private Node[] getEmployeeContent(){
        return new Node[] {
                createBox("Medicines", null),
                createBox("Medicine Categories", null),
                createBox("Insurances", null),
                createBox("Products", null),
                createBox("Orders", null),
                createBox("Scripts", null),
                createBox("Doctor Licenses", null),
                createBox("Companies", null),
        };
    }

    private Node[] getPatientContent(){
        return new Node[] {
                createBox("Scripts", new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        WindowHelper winHelper = new WindowHelper();
                        URL loginURL = getClass().getResource("patientScripts.fxml");
                        winHelper.show(loginURL,"Scripts", 960, 800);
                        close();
                    }
                }),
                createBox("Orders", null),
                createBox("Basket", null),
        };
    }

    private void initializeContent() {
        switch(_currentContext.getCurrentUserType()){
            case Doctor:
                tiles_content.getChildren().addAll(getDoctorContent());
                break;
            case Employee:
                tiles_content.getChildren().addAll(getEmployeeContent());
                break;
            case Patient:
                tiles_content.getChildren().addAll(getPatientContent());
                break;
        }
    }

    private void initializeUser() {
        lbl_username.setText(_currentContext.getCurrentUserInfo());
        btn_auth.setText("Logout");
        btn_auth.setOnAction(this::logout);
        btn_register.setVisible(false);
    }

    private void initializeGuest() {
        btn_register.setVisible(true);
        lbl_username.setText("Guest");
        btn_auth.setText("Log In");
        btn_auth.setOnAction(this::loginShow);
        btn_register.setOnAction(this::registerShow);
    }

    private Button createHeaderButton(String text, EventHandler<ActionEvent> event){
        Button btn =  new Button(text);
        btn.setPrefHeight(80);
        btn.setOnAction(event);
        return btn;
    }

    private Node[] getDoctorHeader(){
        return new Node[] {
                createHeaderButton( "New license", null),
        };
    }

    private Node[] getEmployeeHeader(){
        return new Node[] {
                createHeaderButton( "Add new medicine", null),
                createHeaderButton( "Add new medicine category", null),
                createHeaderButton( "Add new insurance", null),
                createHeaderButton( "Add new company", null),
                createHeaderButton( "Reports", null),
        };
    }

    private Node[] getPatientHeader(){
        return new Node[] {
                createHeaderButton("Request new script",
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                WindowHelper winHelper = new WindowHelper();
                                URL loginURL = getClass().getResource("patientRequest.fxml");
                                winHelper.show(loginURL,"Request script", 600, 350);
                                close();
                            }
                        }),
                createHeaderButton( "Manage profile", null),
                createHeaderButton( "Checkout!", null),
        };
    }

    private void initializeHeader() {
        switch(_currentContext.getCurrentUserType()){
            case Doctor:
                hbox_header.getChildren().addAll(getDoctorHeader());
                break;
            case Employee:
                hbox_header.getChildren().addAll(getEmployeeHeader());
                break;
            case Patient:
                hbox_header.getChildren().addAll(getPatientHeader());
                break;
        }
    }
}
