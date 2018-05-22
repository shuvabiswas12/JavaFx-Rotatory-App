package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.others.CustomDateFormat;
import sample.database.DatabaseHandler;
import sample.model.Doctor;
import sample.others.CustomDialogueBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class EditController extends CustomDateFormat {

    @FXML
    private HBox newDoctorRootPane;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField doctorId;

    @FXML
    private DatePicker sessionDP;

    @FXML
    private TextField finalProf;

    @FXML
    protected Button updateBt;

    @FXML
    private StackPane stackpane;

    private double x, y;
    private static ObservableList<Doctor> doctorObservableList;
    private Doctor updateDoctor;
    private DatabaseHandler databaseHandler;
    private final List<String> patterns = Arrays.asList("dd/MM/yyyy", "dd.MM.yyyy");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy");
    private CustomDialogueBox jfxDialogueBox;
    private String temp_Id;

    @FXML
    void close(MouseEvent event) {
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
    void keyShortcut(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            updateDoctorInfo();
        }
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();

    }

    @FXML
    void initialize() {

        databaseHandler = DatabaseHandler.getDBInstance();
        jfxDialogueBox = new CustomDialogueBox();

        formatingDate(sessionDP);

        for (Doctor doctor : doctorObservableList) {
            nameTF.setText(doctor.getName());
            temp_Id = String.valueOf(doctor.getId());
            doctorId.setText(temp_Id);

            String date = doctor.getSession();
            LocalDate localDate = LocalDate.parse(date, formatter);
            sessionDP.setValue(localDate);

            finalProf.setText(doctor.getFinalProf());
        }

        /*updateBt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });*/


    }

    public void updateDoctorInfo() {

        String name = nameTF.getText().toString().trim();
        String id = doctorId.getText().toString().trim();
        String finalprof = finalProf.getText().toString().trim();
        int prev_id = Integer.parseInt(temp_Id);

        try {
            int dr_id = Integer.parseInt(id);

            if (!name.equals("") && !id.equals("") && !finalprof.equals("") && sessionDP.getValue() != null) {
                updateDoctor = new Doctor(name, String.valueOf(sessionDP.getValue().format(formatter)), finalprof, Integer.valueOf(doctorId.getText().toString().trim()));

                if (prev_id == dr_id) {
                    update();
                } else {
                    ResultSet findResult = databaseHandler.checkDoctorById(dr_id);
                    int val = 0;

                    while (findResult.next()) {
                        val++;
                    }
                    System.out.println(val);


                    if (val == 0) {
                        update();
                    } else {
                        jfxDialogueBox.materialDialogue("Error!", "Duplicate id found.\nPlease, sure id must be an unique!", stackpane);
                    }
                }

            } else {
                jfxDialogueBox.materialDialogue("Empty Fields!", "Please, fill up all fields first.", stackpane);
            }

        } catch (Exception e) {
            jfxDialogueBox.materialDialogue("Value Parsing Error!", "Please, sure ID is must an numeric value", stackpane);
            //e.printStackTrace();
        }

    }

    protected static void setDoctorObservableList(ObservableList<Doctor> selecteddoctorObservableList) {
        doctorObservableList = selecteddoctorObservableList;
    }

    protected void update() throws SQLException, ClassNotFoundException {
        databaseHandler.updateDoctor(updateDoctor, Integer.parseInt(temp_Id));
        Notifications notifications = Notifications.create()
                .title("Confirmation massage!")
                .text("Successfully Updated!")
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(Duration.seconds(5))
                .graphic(null);
        notifications.showConfirm();
    }

}
