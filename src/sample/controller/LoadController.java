package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoadController implements Initializable {

    @FXML
    protected TextField session;

    @FXML
    public JFXButton loadBtn;

    @FXML
    protected JFXButton cancelBtn;

    @FXML
    private AnchorPane root;

    @FXML
    protected StackPane stackpane;

    protected static List<String> sessionList;

    double x, y;


    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x); // for dragged in x axis
        stage.setY(event.getScreenY() - y); // for dragged in y axis
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(session, sessionList); // this is auto complete text field code

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancelBtn.getScene().getWindow().hide();
            }
        });


    }

    public void setSession(String session) {
        this.session.setText(session);
    }

    public String getSession() {
        return session.getText().toString().trim();
    }


}
