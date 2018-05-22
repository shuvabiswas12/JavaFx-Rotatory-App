package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Doctor;
import sample.model.GynaeObs;
import sample.model.Medicine;
import sample.model.Surgery;

public class ShowPlacementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text nameId;

    @FXML
    private Text idId;

    @FXML
    private Button editBtn;

    @FXML
    private Text internal_medicine_date1;

    @FXML
    private Text internal_medicine_date2;

    @FXML
    private Text paediatrics_date;

    @FXML
    private Text skin_vd_date;

    @FXML
    private Text psychiatry_date;

    @FXML
    private Text cardiology_date;

    @FXML
    private Text gastroenterology_date;

    @FXML
    private Text nephrology_date;

    @FXML
    private Text neuromedicine_date;

    @FXML
    private Text general_surgery_date1;

    @FXML
    private Text general_surgery_date2;

    @FXML
    private Text ortho_casualty_date;

    @FXML
    private Text anaesthetia_date;

    @FXML
    private Text opthalmology_date;

    @FXML
    private Text otolaryngo_rhinology_date;

    @FXML
    private Text paediatric_surgery_date;

    @FXML
    private Text urology_date;

    @FXML
    private Text radiology_date;

    @FXML
    private Text indoor_date1;

    @FXML
    private Text indoor_date2;

    @FXML
    private Text family_planning_date;

    @FXML
    private Text obstetrics_emergency_date;

    @FXML
    private Text community_date;


    // others variable
    public static Doctor doctor;
    private Medicine medicine;
    private GynaeObs gynaeObs;
    private Surgery surgery;

    private ResultSet resultSet1, resultSet2, resultSet3;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {

        databaseHandler = DatabaseHandler.getDBInstance();

        nameId.setText("" + doctor.getName());
        idId.setText("Id: " + doctor.getId());
        showPlacement();

        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/editPlacement.fxml"));
                EditPlacementController.doctor = doctor;
                EditPlacementController.gynae_Obs = gynaeObs;
                EditPlacementController.medicinePlacement = medicine;
                EditPlacementController.surgeryPlacement = surgery;
                try {
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit Placement!");
                    stage.getIcons().add(new Image("/sample/assests/pill.png"));
                    stage.show();

                    editBtn.getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void showPlacement() throws SQLException {
        try {
            resultSet1 = databaseHandler.getMedicinePlacement(doctor.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        while (resultSet1.next()) {
            medicine = new Medicine(resultSet1.getString(Const.INTERNAL_MEDICINE_START_1), resultSet1.getString(Const.INTERNAL_MEDICINE_START_2), resultSet1.getString(Const.PAEDIATRICS_START), resultSet1.getString(Const.SKIN_VD_START), resultSet1.getString(Const.PSYCHIATRY_START), resultSet1.getString(Const.CARDIOLOGY_START), resultSet1.getString(Const.GASTROENTEROLOGY_START), resultSet1.getString(Const.NEPHROLOGY_START), resultSet1.getString(Const.NEUROMEDICINE_START),
                    resultSet1.getString(Const.INTERNAL_MEDICINE_END_1), resultSet1.getString(Const.INTERNAL_MEDICINE_END_2), resultSet1.getString(Const.PAEDIATRICS_END), resultSet1.getString(Const.SKIN_VD_END), resultSet1.getString(Const.PSYCHIATRY_END),
                    resultSet1.getString(Const.CARDIOLOGY_END), resultSet1.getString(Const.GASTROENTEROLOGY_END), resultSet1.getString(Const.NEPHROLOGY_END), resultSet1.getString(Const.NEUROMEDICINE_END),
                    doctor.getId());

        }

        internal_medicine_date1.setText(medicine.getInternalMedicine_Start1() + "\tTo\t" + medicine.getInternalMedicine_End1());

        String val = medicine.getInternalMedicine_Start2();
        String val2 = medicine.getInternalMedicine_End2();
        if ((val == null && val2 == null) || (val.equals("-") && val2.equals("-"))) {
            //internal_medicine_date2.setDisable(true);
            internal_medicine_date2.setOpacity(0.0);
        } else {
            internal_medicine_date2.setText(medicine.getInternalMedicine_Start2() + "\tTo\t" + medicine.getInternalMedicine_End2());
        }

        paediatrics_date.setText(medicine.getPaediatrics_Start() + "\tTo\t" + medicine.getPaediatrics_End());
        skin_vd_date.setText(medicine.getSkin_VD_Start() + "\tTo\t" + medicine.getSkin_VD_End());
        psychiatry_date.setText(medicine.getPsychiatry_Start() + "\tTo\t" + medicine.getPsychiatry_End());
        cardiology_date.setText(medicine.getCardiology_Start() + "\tTo\t" + medicine.getCardiology_End());
        gastroenterology_date.setText(medicine.getGastroenterology_Start() + "\tTo\t" + medicine.getGastroenterology_End());
        nephrology_date.setText(medicine.getNephrology_Start() + "\tTo\t" + medicine.getNephrology_End());
        neuromedicine_date.setText(medicine.getNeuromedicine_Start() + "\tTo\t" + medicine.getNeuromedicine_End());


        try {
            resultSet2 = databaseHandler.getSurgeryPlacement(doctor.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (resultSet2.next()) {
            surgery = new Surgery(doctor.getId(), resultSet2.getString(Const.GENERAL_SURGERY_START_1), resultSet2.getString(Const.GENERAL_SURGERY_END_1), resultSet2.getString(Const.ORTHOCASILTY_START), resultSet2.getString(Const.ORTHOCASILTY_END),
                    resultSet2.getString(Const.anaesthetia_start), resultSet2.getString(Const.anaesthetia_end), resultSet2.getString(Const.opthalmalogy_start), resultSet2.getString(Const.opthalmalogy_end), resultSet2.getString(Const.otolaryngoRhinology_start),
                    resultSet2.getString(Const.otolaryngoRhinology_end), resultSet2.getString(Const.paediatricSurgery_start), resultSet2.getString(Const.paediatricSurgery_end), resultSet2.getString(Const.urology_start), resultSet2.getString(Const.urology_end),
                    resultSet2.getString(Const.radiology_start), resultSet2.getString(Const.radiology_end), resultSet2.getString(Const.GENERAL_SURGERY_START_2), resultSet2.getString(Const.GENERAL_SURGERY_END_2));
        }

        general_surgery_date1.setText(surgery.getGeneralSurgery_start_1() + "\tTo\t" + surgery.getGeneralSurgery_end_1());

        String val3 = surgery.getGeneralSurgery_start_2();
        String val4 = surgery.getGeneralSurgery_end_2();

        if ((val3 == null && val4 == null) || (val3.equals("-") && val4.equals("-"))) {
            general_surgery_date2.setOpacity(0.0);
        } else {
            general_surgery_date2.setText(surgery.getGeneralSurgery_start_2() + "\tTo\t" + surgery.getGeneralSurgery_end_2());
        }

        ortho_casualty_date.setText(surgery.getOrthoCasialty_start() + "\tTo\t" + surgery.getOrthoCasialty_end());
        anaesthetia_date.setText(surgery.getAnaesthetia_start() + "\tTo\t" + surgery.getAnaesthetia_end());
        opthalmology_date.setText(surgery.getOpthalmalogy_start() + "\tTo\t" + surgery.getOpthalmalogy_end());
        otolaryngo_rhinology_date.setText(surgery.getOtolaryngoRhinology_start() + "\tTo\t" + surgery.getOtolaryngoRhinology_end());
        paediatric_surgery_date.setText(surgery.getPaediatricSurgery_start() + "\tTo\t" + surgery.getPaediatricSurgery_end());
        urology_date.setText(surgery.getUrology_start() + "\tTo\t" + surgery.getUrology_end());
        radiology_date.setText(surgery.getRadiology_start() + "\tTo\t" + surgery.getRadiology_end());


        try {
            resultSet3 = databaseHandler.getGynaePlacement(doctor.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (resultSet3.next()) {
            gynaeObs = new GynaeObs(resultSet3.getString(Const.INDOOR_START_1), resultSet3.getString(Const.INDOOR_END_1), resultSet3.getString(Const.INDOOR_START_2),
                    resultSet3.getString(Const.INDOOR_END_2), resultSet3.getString(Const.FAMILY_PLANNING_START), resultSet3.getString(Const.FAMILY_PLANNING_END),
                    resultSet3.getString(Const.OBSTERICS_EMERGENCY_START), resultSet3.getString(Const.OBSTERICS_EMERGENCY_END), resultSet3.getString(Const.COMMUNITY_START),
                    resultSet3.getString(Const.COMMUNITY_END), doctor.getId());
        }


        indoor_date1.setText(gynaeObs.getIndoorStart1() + "\tTo\t" + gynaeObs.getIndoorEnd1());

        String val5 = gynaeObs.getIndoorStart2();
        String val6 = gynaeObs.getIndoorEnd2();

        if ((val5 == null && val6 == null) || (val5.equals("-") && val6.equals("-"))) {
            indoor_date2.setOpacity(0.0);
        } else {
            indoor_date2.setText(gynaeObs.getIndoorStart2() + "\tTo\t" + gynaeObs.getIndoorEnd2());
        }

        family_planning_date.setText(gynaeObs.getFamilyPlanningStart() + "\tTo\t" + gynaeObs.getFamilyPlanningEnd());
        obstetrics_emergency_date.setText(gynaeObs.getObstericsEmergencyStart() + "\tTo\t" + gynaeObs.getObstericsEmergencyEnd());
        community_date.setText(gynaeObs.getCommunityStart() + "\tTo\t" + gynaeObs.getCommunityEnd());
    }

}
