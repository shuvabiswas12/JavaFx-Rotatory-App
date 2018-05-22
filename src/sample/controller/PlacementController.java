package sample.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.database.Const;
import sample.model.Doctor;
import sample.others.CustomDateFormat;
import sample.database.DatabaseHandler;
import sample.model.GynaeObs;
import sample.model.Medicine;
import sample.model.Surgery;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class PlacementController extends CustomDateFormat {
    @FXML
    private HBox id;

    @FXML
    private Text nameId;

    @FXML
    private Text dr_Id;

    @FXML
    private Text session;

    @FXML
    private DatePicker internal_medicine_start1_Id;

    @FXML
    private DatePicker internal_medicine_end1_Id;

    @FXML
    private DatePicker internal_medicine_start2_Id;

    @FXML
    private DatePicker internal_medicine_end2_Id;

    @FXML
    private DatePicker paediatrics_start_Id;

    @FXML
    private DatePicker paediatrics_end_Id;

    @FXML
    private DatePicker skin_vd_start_Id;

    @FXML
    private DatePicker skin_vd_end_Id;

    @FXML
    private DatePicker psychiatry_start_Id;

    @FXML
    private DatePicker psychiatry_end_Id;

    @FXML
    private DatePicker cardiology_start_Id;

    @FXML
    private DatePicker cardiology_end_Id;

    @FXML
    private DatePicker gastroenterology_start_Id;

    @FXML
    private DatePicker gastroenterology_end_Id;

    @FXML
    private DatePicker nephrology_start_Id;

    @FXML
    private DatePicker nephrology_end_Id;

    @FXML
    private DatePicker neuromedicine_start_Id;

    @FXML
    private DatePicker neuromedicine_end_Id;

    @FXML
    private Button medicineSaveBtn;

    @FXML
    private DatePicker general_surgery_start1_id;

    @FXML
    private DatePicker general_surgery_end1_id;

    @FXML
    private DatePicker general_surgery_start2_id;

    @FXML
    private DatePicker general_surgery_end2_id;

    @FXML
    private DatePicker ortho_Casualty_start_id;

    @FXML
    private DatePicker ortho_Casualty_end_id;

    @FXML
    private DatePicker anaesthetia_start_id;

    @FXML
    private DatePicker anaesthetia_end_id;

    @FXML
    private DatePicker opthalmology_start1_id;

    @FXML
    private DatePicker opthalmology_end_id;

    @FXML
    private DatePicker Otolaryngo_rhinology_start_id;

    @FXML
    private DatePicker Otolaryngo_rhinology_end_id;

    @FXML
    private DatePicker paediatric_Surgery_start_id;

    @FXML
    private DatePicker paediatric_Surgery_end_id;

    @FXML
    private DatePicker urology_start_id;

    @FXML
    private DatePicker urology_end_id;

    @FXML
    private DatePicker radiology_start_id;

    @FXML
    private DatePicker radiology_end_id;

    @FXML
    private Button surgerySaveBtn;

    @FXML
    private DatePicker indoor_start1;

    @FXML
    private DatePicker indoor_end1;

    @FXML
    private DatePicker indoor_start2;

    @FXML
    private DatePicker indoor_end2;

    @FXML
    private DatePicker family_planning_start;

    @FXML
    private DatePicker family_planning_end;

    @FXML
    private DatePicker obstetrics_start;

    @FXML
    private DatePicker obstetrics_end;

    @FXML
    private DatePicker community_start;

    @FXML
    private DatePicker community_end;

    @FXML
    private Button gynaeSaveBtn;


    private DatabaseHandler databaseHandler;
    private Medicine medicine;
    private GynaeObs gynaeObs;
    private Surgery surgery;
    protected static String doctorId, doctorName, doctorSession;

    /**
     * medicine part
     */
    private String internalMedicine_Start1;
    private String internalMedicine_Start2;
    private String paediatrics_Start;
    private String skin_VD_Start;
    private String psychiatry_Start;
    private String cardiology_Start;
    private String gastroenterology_Start;
    private String nephrology_Start;
    private String neuromedicine_Start;

    private String internalMedicine_End1;
    private String internalMedicine_End2;
    private String paediatrics_End;
    private String skin_VD_End;
    private String psychiatry_End;
    private String cardiology_End;
    private String gastroenterology_End;
    private String nephrology_End;
    private String neuromedicine_End;


    /**
     * Gynae & Obs Part
     */
    private String indoorStart1;
    private String indoorEnd1;
    private String indoorStart2;
    private String indoorEnd2;
    private String familyPlanningStart;
    private String familyPlanningEnd;
    private String obstericsEmergencyStart;
    private String obstericsEmergencyEnd;
    private String communityStart;
    private String communityEnd;


    /**
     * Surgery
     */
    private String generalSurgery_start_1;
    private String generalSurgery_start_2;
    private String generalSurgery_end_1;
    private String generalSurgery_end_2;
    private String orthoCasialty_start;
    private String orthoCasialty_end;
    private String anaesthetia_start;
    private String anaesthetia_end;
    private String opthalmalogy_start;
    private String opthalmalogy_end;
    private String otolaryngoRhinology_start;
    private String otolaryngoRhinology_end;
    private String paediatricSurgery_start;
    private String paediatricSurgery_end;
    private String urology_start;
    private String urology_end;
    private String radiology_start;
    private String radiology_end;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy");


    @FXML
    void initialize() {

        databaseHandler = DatabaseHandler.getDBInstance();
        dr_Id.setText(doctorId);
        nameId.setText(doctorName);
        session.setText(doctorSession);

        formatingDate(internal_medicine_start1_Id);
        formatingDate(internal_medicine_start2_Id);
        formatingDate(internal_medicine_end1_Id);
        formatingDate(internal_medicine_end2_Id);
        formatingDate(paediatrics_start_Id);
        formatingDate(paediatrics_start_Id);
        formatingDate(paediatrics_end_Id);
        formatingDate(skin_vd_start_Id);
        formatingDate(skin_vd_end_Id);
        formatingDate(psychiatry_start_Id);
        formatingDate(psychiatry_end_Id);
        formatingDate(cardiology_start_Id);
        formatingDate(cardiology_end_Id);
        formatingDate(gastroenterology_start_Id);
        formatingDate(gastroenterology_end_Id);
        formatingDate(nephrology_start_Id);
        formatingDate(nephrology_end_Id);
        formatingDate(neuromedicine_start_Id);
        formatingDate(neuromedicine_end_Id);

        formatingDate(general_surgery_start1_id);
        formatingDate(general_surgery_start2_id);
        formatingDate(general_surgery_end1_id);
        formatingDate(general_surgery_end2_id);
        formatingDate(ortho_Casualty_start_id);
        formatingDate(ortho_Casualty_end_id);
        formatingDate(anaesthetia_end_id);
        formatingDate(anaesthetia_start_id);
        formatingDate(opthalmology_start1_id);
        formatingDate(opthalmology_end_id);
        formatingDate(Otolaryngo_rhinology_start_id);
        formatingDate(Otolaryngo_rhinology_end_id);
        formatingDate(paediatric_Surgery_start_id);
        formatingDate(paediatric_Surgery_end_id);
        formatingDate(urology_start_id);
        formatingDate(urology_end_id);
        formatingDate(radiology_start_id);
        formatingDate(radiology_end_id);

        formatingDate(indoor_start1);
        formatingDate(indoor_start2);
        formatingDate(indoor_end1);
        formatingDate(indoor_end2);
        formatingDate(family_planning_start);
        formatingDate(family_planning_end);
        formatingDate(obstetrics_start);
        formatingDate(obstetrics_end);
        formatingDate(community_start);
        formatingDate(community_end);

        medicineSaveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int val = databaseHandler.getMedicineById(Integer.parseInt(doctorId));

                    if (val == 0) {
                        medicineSaveButtonAction();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("Duplicate Id!");
                        alert.setContentText("There is another placement as this doctor");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        gynaeSaveBtn.setOnAction(e -> {
            try {
                int val = databaseHandler.getGynaeById(Integer.parseInt(doctorId));

                if (val == 0) {
                    gynaeObsSaveButtonAction();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Duplicate Found!");
                    alert.setContentText("There is another placement as this doctor");
                    alert.showAndWait();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });


        surgerySaveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int val = databaseHandler.getSurgeryById(Integer.parseInt(doctorId));

                    if (val == 1) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("Duplicate Id Found!");
                        alert.setContentText("There is another placement as this doctor");
                        alert.showAndWait();
                    } else {
                        surgerySaveButtonAction();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    protected void medicineSaveButtonAction() throws SQLException, ClassNotFoundException {

        if (internal_medicine_start1_Id.getValue() != null && internal_medicine_end1_Id.getValue() != null && paediatrics_start_Id.getValue() != null && paediatrics_end_Id.getValue() != null) {

            internalMedicine_Start1 = String.valueOf(internal_medicine_start1_Id.getValue().format(formatter));
            internalMedicine_End1 = String.valueOf(internal_medicine_end1_Id.getValue().format(formatter));
            paediatrics_Start = String.valueOf(paediatrics_start_Id.getValue().format(formatter));
            paediatrics_End = String.valueOf(paediatrics_end_Id.getValue().format(formatter));


            if (skin_vd_start_Id.getValue() == null || skin_vd_end_Id.getValue() == null) {
                skin_VD_Start = "-";
                skin_VD_End = "-";
            } else {
                skin_VD_Start = String.valueOf(skin_vd_start_Id.getValue().format(formatter));
                skin_VD_End = String.valueOf(skin_vd_end_Id.getValue().format(formatter));
            }

            if (psychiatry_start_Id.getValue() == null || psychiatry_end_Id.getValue() == null) {
                psychiatry_Start = "-";
                psychiatry_End = "-";
            } else {
                psychiatry_Start = String.valueOf(psychiatry_start_Id.getValue().format(formatter));
                psychiatry_End = String.valueOf(psychiatry_end_Id.getValue().format(formatter));
            }

            if (cardiology_start_Id.getValue() == null || cardiology_end_Id.getValue() == null) {
                cardiology_Start = "-";
                cardiology_End = "-";
            } else {
                cardiology_Start = String.valueOf(cardiology_start_Id.getValue().format(formatter));
                cardiology_End = String.valueOf(cardiology_end_Id.getValue().format(formatter));
            }

            if (gastroenterology_start_Id.getValue() == null || gastroenterology_end_Id.getValue() == null) {
                gastroenterology_Start = "-";
                gastroenterology_End = "-";
            } else {
                gastroenterology_Start = String.valueOf(gastroenterology_start_Id.getValue().format(formatter));
                gastroenterology_End = String.valueOf(gastroenterology_end_Id.getValue().format(formatter));
            }

            if (nephrology_start_Id.getValue() == null || nephrology_end_Id.getValue() == null) {
                nephrology_Start = "-";
                nephrology_End = "-";
            } else {
                nephrology_Start = String.valueOf(nephrology_start_Id.getValue().format(formatter));
                nephrology_End = String.valueOf(nephrology_end_Id.getValue().format(formatter));
            }

            if (neuromedicine_start_Id.getValue() == null || neuromedicine_end_Id.getValue() == null) {
                neuromedicine_Start = "-";
                neuromedicine_End = "-";
            } else {
                neuromedicine_Start = String.valueOf(neuromedicine_start_Id.getValue().format(formatter));
                neuromedicine_End = String.valueOf(neuromedicine_end_Id.getValue().format(formatter));
            }

            /** internal medicine shift part */

            if (internal_medicine_start2_Id.getValue() == null && internal_medicine_end2_Id.getValue() == null) {
                internalMedicine_Start2 = "-";
                internalMedicine_End2 = "-";
            } else {
                internalMedicine_Start2 = String.valueOf(internal_medicine_start2_Id.getValue().format(formatter));
                internalMedicine_End2 = String.valueOf(internal_medicine_end2_Id.getValue().format(formatter));
            }

            medicine = new Medicine(internalMedicine_Start1, internalMedicine_Start2, paediatrics_Start, skin_VD_Start, psychiatry_Start, cardiology_Start,
                    gastroenterology_Start, nephrology_Start, neuromedicine_Start, internalMedicine_End1, internalMedicine_End2, paediatrics_End,
                    skin_VD_End, psychiatry_End, cardiology_End, gastroenterology_End, nephrology_End, neuromedicine_End, Integer.valueOf(dr_Id.getText()));

            databaseHandler.medicineToDB(medicine);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Saving Info");
            alert.setContentText("Saving Successfully");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Empty Field");
            alert.setContentText("Fill up the all mandatory field");
            alert.showAndWait();
        }

    }

    protected void gynaeObsSaveButtonAction() {

        if (indoor_start1.getValue() != null && indoor_end1.getValue() != null && family_planning_start.getValue() != null && family_planning_end.getValue() != null && community_start.getValue() != null && community_end.getValue() != null) {

            indoorStart1 = String.valueOf(indoor_start1.getValue().format(formatter));
            indoorEnd1 = String.valueOf(indoor_end1.getValue().format(formatter));
            familyPlanningStart = String.valueOf(family_planning_start.getValue().format(formatter));
            familyPlanningEnd = String.valueOf(family_planning_end.getValue().format(formatter));
            obstericsEmergencyStart = String.valueOf(obstetrics_start.getValue().format(formatter));
            obstericsEmergencyEnd = String.valueOf(obstetrics_end.getValue().format(formatter));
            communityStart = String.valueOf(community_start.getValue().format(formatter));
            communityEnd = String.valueOf(community_end.getValue().format(formatter));

            if (indoor_start2.getValue() == null || indoor_end2.getValue() == null) {
                indoorStart2 = "-";
                indoorEnd2 = "-";
            } else {
                indoorStart2 = String.valueOf(indoor_start2.getValue().format(formatter));
                indoorEnd2 = String.valueOf(indoor_end2.getValue().format(formatter));
            }

            gynaeObs = new GynaeObs(indoorStart1, indoorEnd1, indoorStart2, indoorEnd2, familyPlanningStart, familyPlanningEnd, obstericsEmergencyStart, obstericsEmergencyEnd, communityStart, communityEnd, Integer.valueOf(dr_Id.getText().toString()));

            try {
                databaseHandler.gynaeObsToDb(gynaeObs);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Saving Info");
                alert.setContentText("Successfully Saved");
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Empty Field");
            alert.setContentText("Please, fill up all the fields");
            alert.showAndWait();
        }
    }

    protected void surgerySaveButtonAction() {

        if (general_surgery_start1_id.getValue() != null && general_surgery_end1_id.getValue() != null &&
                ortho_Casualty_start_id.getValue() != null && ortho_Casualty_end_id.getValue() != null &&
                anaesthetia_start_id.getValue() != null && anaesthetia_end_id.getValue() != null &&
                opthalmology_start1_id.getValue() != null && opthalmology_end_id.getValue() != null &&
                Otolaryngo_rhinology_start_id.getValue() != null && Otolaryngo_rhinology_end_id.getValue() != null) {


            generalSurgery_start_1 = String.valueOf(general_surgery_start1_id.getValue().format(formatter));
            generalSurgery_end_1 = String.valueOf(general_surgery_end1_id.getValue().format(formatter));
            orthoCasialty_start = String.valueOf(ortho_Casualty_start_id.getValue().format(formatter));
            orthoCasialty_end = String.valueOf(ortho_Casualty_end_id.getValue().format(formatter));
            anaesthetia_start = String.valueOf(anaesthetia_start_id.getValue().format(formatter));
            anaesthetia_end = String.valueOf(anaesthetia_end_id.getValue().format(formatter));
            opthalmalogy_start = String.valueOf(opthalmology_start1_id.getValue().format(formatter));
            opthalmalogy_end = String.valueOf(opthalmology_end_id.getValue().format(formatter));
            otolaryngoRhinology_start = String.valueOf(Otolaryngo_rhinology_start_id.getValue().format(formatter));
            otolaryngoRhinology_end = String.valueOf(Otolaryngo_rhinology_end_id.getValue().format(formatter));

            if (general_surgery_start2_id.getValue() == null || general_surgery_end2_id.getValue() == null) {
                generalSurgery_start_2 = "-";
                generalSurgery_end_2 = "-";
            } else {
                generalSurgery_start_2 = String.valueOf(general_surgery_start2_id.getValue().format(formatter));
                generalSurgery_end_2 = String.valueOf(general_surgery_end2_id.getValue().format(formatter));
            }

            if (paediatric_Surgery_start_id.getValue() == null || paediatric_Surgery_end_id.getValue() == null) {
                paediatricSurgery_start = "-";
                paediatricSurgery_end = "-";
            } else {
                paediatricSurgery_start = String.valueOf(paediatric_Surgery_start_id.getValue().format(formatter));
                paediatricSurgery_end = String.valueOf(paediatric_Surgery_end_id.getValue().format(formatter));
            }

            if (urology_start_id.getValue() == null || urology_end_id.getValue() == null) {
                urology_start = "-";
                urology_end = "-";
            } else {
                urology_start = String.valueOf(urology_start_id.getValue().format(formatter));
                urology_end = String.valueOf(urology_end_id.getValue().format(formatter));
            }

            if (radiology_start_id.getValue() == null || radiology_end_id.getValue() == null) {
                radiology_start = "-";
                radiology_end = "-";
            } else {
                radiology_start = String.valueOf(radiology_start_id.getValue().format(formatter));
                radiology_end = String.valueOf(radiology_end_id.getValue().format(formatter));
            }


            surgery = new Surgery(Integer.parseInt(dr_Id.getText().toString()), generalSurgery_start_1, generalSurgery_end_1, orthoCasialty_start, orthoCasialty_end,
                    anaesthetia_start, anaesthetia_end, opthalmalogy_start, opthalmalogy_end, otolaryngoRhinology_start, otolaryngoRhinology_end, paediatricSurgery_start, paediatricSurgery_end, urology_start, urology_end, radiology_start, radiology_end, generalSurgery_start_2, generalSurgery_end_2);

            try {
                databaseHandler.surgeryToDb(surgery);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Saving info");
                alert.setContentText("Successfully Saved");
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Empty Field");
            alert.setContentText("Please, Fill up the all mandatory field...");
            alert.showAndWait();


        }

    }

}