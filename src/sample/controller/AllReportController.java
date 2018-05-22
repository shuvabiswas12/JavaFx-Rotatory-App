package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Doctor;


import sample.others.CustomDialogueBox;
import sample.others.ReportGenerator;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AllReportController implements Initializable {

    @FXML
    private StackPane generate_anchorPane;

    @FXML
    private TextField filePathTF;

    @FXML
    private JFXButton browseBtn;

    @FXML
    private TextField sessionTf;

    @FXML
    private JFXButton generateBtn;

    private DatabaseHandler databaseHandler;
    public Doctor doctor;
    private String path;
    private ResultSet doctorResultSet;
    private String session;
    private ReportGenerator reportGenerator;
    protected static List<String> sessionList;
    private CustomDialogueBox jfxDialogueBox;

    double x, y;


    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x); // for dragged in x axis
        stage.setY(event.getScreenY() - y); // for dragged in y axis

    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void close(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        databaseHandler = DatabaseHandler.getDBInstance();
        TextFields.bindAutoCompletion(sessionTf, sessionList);
        jfxDialogueBox = new CustomDialogueBox();
        reportGenerator = new ReportGenerator();

        browseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                Stage stage = (Stage) generate_anchorPane.getScene().getWindow();
                File file = directoryChooser.showDialog(stage);

                if (file != null) {
                    path = file.getAbsolutePath();
                    filePathTF.setText(path);
                }
            }
        });

        generateBtn.setOnAction(e -> {
            session = sessionTf.getText().toString().trim();

            if (path != null) {
                if (session.isEmpty()) {

                    // alert dialogue here
                    jfxDialogueBox.materialDialogue("Session Empty!", "Please, select a session...", generate_anchorPane);

                } else {

                    try {
                        generateOperations();
                        Notifications notifications = Notifications.create()
                                .title("Confirmation massage!")
                                .text("Generate Successful !")
                                .position(Pos.BOTTOM_RIGHT)
                                .hideAfter(Duration.seconds(5))
                                .graphic(null);

                        notifications.darkStyle();
                        notifications.showConfirm();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }

                }
            } else {
                // alert dialogue here
                jfxDialogueBox.materialDialogue("Path Empty!", "Please, select a path...", generate_anchorPane);

            }

        });


    }

    public void generateOperations() throws SQLException, IOException, ClassNotFoundException {

        try {
            doctorResultSet = databaseHandler.getDoctorBySession(session);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (doctorResultSet.next()) {
            doctor = new Doctor(doctorResultSet.getString(Const.DOCTOR_NAME), doctorResultSet.getString(Const.SESSION), doctorResultSet.getString(Const.FINAL_PROF), doctorResultSet.getInt(Const.DOCTOR_ID));
            System.out.println(doctorResultSet.getString(Const.DOCTOR_NAME) + " " + doctorResultSet.getString(Const.SESSION) + " " + doctorResultSet.getString(Const.FINAL_PROF) + " " + doctorResultSet.getInt(Const.DOCTOR_ID));
            reportGenerator.sheetProgram(doctor, databaseHandler, path);

        }
    }

    public void confirmationMessage() {
        CustomDialogueBox customDialogueBox = new CustomDialogueBox();
        customDialogueBox.materialDialogue("Successfully Generated!", "Report Generation has been completed! Check the folder.", generate_anchorPane);
    }

}
