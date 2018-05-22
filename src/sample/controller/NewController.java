package sample.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.others.CustomDateFormat;
import sample.animation.FadeEffect;
import sample.database.DatabaseHandler;
import sample.model.Doctor;
import sample.others.CustomDialogueBox;

public class NewController extends CustomDateFormat {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox newDoctorRootPane;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField doctorId;

    @FXML
    private DatePicker sessionDP;

    @FXML
    private TextField finalProf;

    @FXML
    protected Button saveBt;

    @FXML
    private Text confermationId;

    @FXML
    private StackPane stackpane;

    private DatabaseHandler databaseHandler;
    private double x, y;
    private DateTimeFormatter formatter;
    private int drId;
    private CustomDialogueBox jfxDialogueBox;

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

    @FXML
    void close(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void backBtn(MouseEvent event) {

    }

    @FXML
    void keyShortcut(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            saveDoctorInfo();
        }
    }

    @FXML
    void initialize() {

        databaseHandler = DatabaseHandler.getDBInstance();
        jfxDialogueBox = new CustomDialogueBox();

        formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy");

        formatingDate(sessionDP);

        /*saveBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                saveDoctorInfo();
            }
        });*/

    }

    public void saveDoctorInfo() {

        String name = nameTF.getText().toString().trim();
        String id = doctorId.getText().toString().trim();

        String profFinal = finalProf.getText().toString().trim();

        if (!name.equals("") && !id.equals("") && !profFinal.equals("") && sessionDP.getValue() != null) {

            try {
                drId = Integer.parseInt(id);

                try {
                    int value = databaseHandler.getDoctorById(drId);
                    if (value == 0) {
                        String sessionDate = String.valueOf(sessionDP.getValue().format(formatter));
                        databaseHandler.saveDoctor(new Doctor(name, sessionDate, profFinal, drId));

                        FadeEffect fadeEffect = new FadeEffect(confermationId);
                        confermationId.setText(name + " successfully saved!");
                        fadeEffect.setEffect();

                        nameTF.clear();
                        doctorId.clear();
                    } else {

                        jfxDialogueBox.materialDialogue("Duplicate Id Found!", "Please, sure id must be an unique!", stackpane);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {

                jfxDialogueBox.materialDialogue("Number Input Problem", "Please, sure id must be a number", stackpane);

            }

        } else {

            jfxDialogueBox.materialDialogue("Form Incomplete", "Fill Up All the Stuff", stackpane);

        }

    }

}
