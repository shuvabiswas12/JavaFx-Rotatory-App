package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {

    @FXML
    public Label loadingId;
    public static Label label;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = loadingId;
    }
}
