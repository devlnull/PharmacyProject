package com.pharmacy.app.main;

import com.pharmacy.app.helpers.HyperLinkHelper;
import com.pharmacy.app.helpers.WindowHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.net.URI;
import java.net.URL;

public class MainController {
    private final URI aboutURI = java.net.URI.create("https://github.com/devlnull/PharmacyProject/");

    public void menu_about(ActionEvent event){
        HyperLinkHelper.openWebpage(aboutURI);
    }

    public void registerShow(ActionEvent event){
        WindowHelper winHelper = new WindowHelper();
        URL loginURL = getClass().getResource("register.fxml");
        winHelper.show(loginURL,"Register", 500, 500);
    }

    public void loginShow(ActionEvent event){
        WindowHelper winHelper = new WindowHelper();
        URL loginURL = getClass().getResource("login.fxml");
        winHelper.show(loginURL,"Log In", 500, 500);
    }

    public void exit(ActionEvent event){
        Platform.exit();
    }
}
