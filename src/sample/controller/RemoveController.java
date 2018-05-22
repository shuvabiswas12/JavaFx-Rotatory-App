package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import sample.database.Const;
import sample.database.DatabaseHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    protected TextField session;

    @FXML
    protected JFXButton removeBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    protected StackPane stackpane;

    protected List<String> sessionList;
    private DatabaseHandler databaseHandler = DatabaseHandler.getDBInstance();
    private ResultSet sessionRemove = null;

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
        sessionList = new ArrayList<>();

        try {
            sessionRemove = databaseHandler.getSession();
            while (sessionRemove.next()) {
                sessionList.add(sessionRemove.getString(Const.SESSION));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TextFields.bindAutoCompletion(session, sessionList);
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

    public void setSessionList(List<String> sessionList) {
        this.sessionList.clear();
        this.sessionList = sessionList;
    }
}
