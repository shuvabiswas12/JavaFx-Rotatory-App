package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.Notifications;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Doctor;
import sample.model.GynaeObs;
import sample.model.Medicine;
import sample.model.Surgery;
import sample.others.PathFinder;
import sample.others.ReportGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private TextField filePathTF;

    @FXML
    private AnchorPane generate_anchorPane;


    @FXML
    private Button browseBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button generateBtn;


    private double x, y;
    private DatabaseHandler databaseHandler;
    public static Doctor doctor;
    private PathFinder pathFinder;
    private String path;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        databaseHandler = DatabaseHandler.getDBInstance();
        pathFinder = new PathFinder();

        // choose the dir and show this in text field
        browseBtn.setOnAction(e -> {
            path = pathFinder.findPath(generate_anchorPane);
            filePathTF.setText(path);
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancelBtn.getScene().getWindow().hide();
            }
        });

        generateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (path == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Empty Path");
                    alert.setHeaderText(null);
                    alert.setContentText("Please, Select the Path first !!");
                    alert.showAndWait();
                } else {
                    try {

                        ReportGenerator reportGenerator = new ReportGenerator();
                        reportGenerator.sheetProgram(doctor, databaseHandler, path);

                        generateBtn.getScene().getWindow().hide();

                        Notifications notifications = Notifications.create()
                                .title("Confirmation massage!")
                                .text("Report is Successfully Generated!")
                                .position(Pos.BOTTOM_RIGHT)
                                .hideAfter(Duration.seconds(5))
                                .graphic(null);

                        notifications.darkStyle();
                        notifications.showConfirm();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }

}
