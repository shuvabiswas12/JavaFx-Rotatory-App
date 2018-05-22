package sample.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.database.DatabaseHandler;
import sample.others.CustomDialogueBox;
import sample.others.FileSelector;
import sample.others.PathFinder;
import sample.others.RecordsBackup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class RecordController {

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField pathTFId;

    @FXML
    private JFXButton pathBtn;

    @FXML
    private JFXButton exportBtn;

    @FXML
    private Text totalInternsTF;

    @FXML
    private Text totalSessionTF;

    @FXML
    private TextField fileTf;

    @FXML
    private JFXButton chooseFilBtn;

    @FXML
    private JFXButton importBtn;

    private double x, y;
    private RecordsBackup recordsBackup;
    private DatabaseHandler databaseHandler;
    private PathFinder pathFinder;
    private CustomDialogueBox customDialogueBox;
    private FileSelector fileSelector;
    private String path;
    private File file;
    private static int value;
    private static int session;

    @FXML
    void clicked(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

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
    void initialize() throws SQLException, ClassNotFoundException {

        totalInternsTF.setText("Total Intern's: " + getValue());
        totalSessionTF.setText("Total Sessions: "+getSession());

        databaseHandler = DatabaseHandler.getDBInstance();
        recordsBackup = new RecordsBackup();
        pathFinder = new PathFinder();
        customDialogueBox = new CustomDialogueBox();

        // choose the dir and show this in text field
        pathBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                path = pathFinder.findPath(rootPane);
                pathTFId.setText(path);
            }
        });


        exportBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (path == null) {
                    customDialogueBox.materialDialogue("Path Empty!", "Please, select a path...", rootPane);
                } else {
                    recordsBackup.createDoctorSheet();
                    recordsBackup.createMedecineSheet();
                    recordsBackup.createSurgerySheet();
                    recordsBackup.createGynaeSheet();
                    try {
                        recordsBackup.createBackup(databaseHandler, path);
                        exportBtn.getScene().getWindow().hide();

                        Notifications notifications = Notifications.create()
                                .title("Confirmation massage!")
                                .text("Backup Successful !")
                                .position(Pos.BOTTOM_RIGHT)
                                .hideAfter(Duration.seconds(5))
                                .graphic(null);

                        notifications.darkStyle();
                        notifications.showConfirm();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        chooseFilBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileSelector = new FileSelector();
                file = fileSelector.chooseFile(rootPane);
                if (file != null)
                    file = file.getAbsoluteFile();
                    fileTf.setText(String.valueOf(file));
            }
        });

        importBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    try {
                        if (file == null) {
                            customDialogueBox.materialDialogue("Error!", "Please, select backup.xls file first ... ", rootPane);
                        } else {
                            recordsBackup.importToDB(databaseHandler, file);

                            importBtn.getScene().getWindow().hide();

                            Notifications notifications = Notifications.create()
                                    .title("Confirmation massage!")
                                    .text("Import Successful !")
                                    .position(Pos.BOTTOM_RIGHT)
                                    .hideAfter(Duration.seconds(5))
                                    .graphic(null);

                            notifications.darkStyle();
                            notifications.showConfirm();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void setValue(int value) {
        RecordController.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void setSession(int session) {
        RecordController.session = session;
    }

    public int getSession() {
        return session;
    }
}
