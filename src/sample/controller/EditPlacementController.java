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
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.Doctor;
import sample.model.GynaeObs;
import sample.model.Medicine;
import sample.model.Surgery;
import sample.others.CustomDateFormat;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPlacementController extends CustomDateFormat {

    @FXML
    private HBox id;

    @FXML
    private Button showBtn;

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
    private Button medicineUpdateBtn;

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
    private Button surgeryUpdateBtn;

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
    private Button gynaeUpdateBtn;

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


    private PlacementController placementController;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/LL/yyyy");

    // others variable
    public static Doctor doctor;
    protected static Medicine medicinePlacement;
    protected static GynaeObs gynae_Obs;
    protected static Surgery surgeryPlacement;

    private DatabaseHandler databaseHandler = DatabaseHandler.getDBInstance();


    @FXML
    void initialize() {

        nameId.setText(doctor.getName());
        dr_Id.setText(String.valueOf(doctor.getId()));
        session.setText(doctor.getSession());


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
        ;
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

        setDate_in_DatePicker();

        showBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ShowPlacementController.doctor = doctor;

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/showPlacement.fxml"));
                try {
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("/sample/assests/pill.png"));
                    stage.show();
                    showBtn.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        medicineUpdateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    medicineSaveButtonAction(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        gynaeUpdateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gynaeObsSaveButtonAction(true);
            }
        });

        surgeryUpdateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                surgerySaveButtonAction(true);
            }
        });


    }

    public void setDate_in_DatePicker() {


        // medicine part ---- >>
        // --------------------------------------------------------------
        internal_medicine_start1_Id.setValue(LocalDate.parse(medicinePlacement.getInternalMedicine_Start1(), dateTimeFormatter));
        internal_medicine_end1_Id.setValue(LocalDate.parse(medicinePlacement.getInternalMedicine_End1(), dateTimeFormatter));

        String internal_medicine_start_2 = medicinePlacement.getInternalMedicine_Start2();
        String internal_medicine_end_2 = medicinePlacement.getInternalMedicine_Start2();
        if (internal_medicine_start_2.equals("-")) {
            internal_medicine_start2_Id.setValue(null);
        } else {
            internal_medicine_start2_Id.setValue(LocalDate.parse(internal_medicine_start_2, dateTimeFormatter));
        }
        if (internal_medicine_end_2.equals("-")) {
            internal_medicine_end2_Id.setValue(null);
        } else {
            internal_medicine_end2_Id.setValue(LocalDate.parse(internal_medicine_start_2, dateTimeFormatter));
        }

        paediatrics_start_Id.setValue(LocalDate.parse(medicinePlacement.getPaediatrics_Start(), dateTimeFormatter));
        paediatrics_end_Id.setValue(LocalDate.parse(medicinePlacement.getPaediatrics_End(), dateTimeFormatter));

        String skin_vd_1 = medicinePlacement.getSkin_VD_Start();
        String skin_vd_2 = medicinePlacement.getSkin_VD_End();
        if (skin_vd_1.equals("-")) {
            skin_vd_start_Id.setValue(null);
        } else {
            skin_vd_start_Id.setValue(LocalDate.parse(skin_vd_1, dateTimeFormatter));
        }

        if (skin_vd_2.equals("-")) {
            skin_vd_end_Id.setValue(null);
        } else {
            skin_vd_end_Id.setValue(LocalDate.parse(skin_vd_2, dateTimeFormatter));
        }

        String cardiology_1 = medicinePlacement.getCardiology_Start();
        String cardiology_2 = medicinePlacement.getCardiology_End();
        if (cardiology_1.equals("-")) {
            cardiology_start_Id.setValue(null);
        } else {
            cardiology_start_Id.setValue(LocalDate.parse(cardiology_1, dateTimeFormatter));
        }

        if (cardiology_2.equals("-")) {
            cardiology_end_Id.setValue(null);
        } else {
            cardiology_end_Id.setValue(LocalDate.parse(cardiology_2, dateTimeFormatter));
        }

        String nephrology_1 = medicinePlacement.getNephrology_Start();
        String nephrology_2 = medicinePlacement.getNephrology_End();
        if (nephrology_1.equals("-")) {
            nephrology_start_Id.setValue(null);
        } else {
            nephrology_start_Id.setValue(LocalDate.parse(nephrology_1, dateTimeFormatter));
        }

        if (nephrology_2.equals("-")) {
            nephrology_end_Id.setValue(null);
        } else {
            nephrology_end_Id.setValue(LocalDate.parse(nephrology_2, dateTimeFormatter));
        }

        String psychiatry_1 = medicinePlacement.getPsychiatry_Start();
        String psychiatry_2 = medicinePlacement.getPsychiatry_End();
        if (psychiatry_1.equals("-")) {
            psychiatry_start_Id.setValue(null);
        } else {
            psychiatry_start_Id.setValue(LocalDate.parse(psychiatry_1, dateTimeFormatter));
        }

        if (psychiatry_2.equals("-")) {
            psychiatry_end_Id.setValue(null);
        } else {
            psychiatry_end_Id.setValue(LocalDate.parse(psychiatry_2, dateTimeFormatter));
        }


        String gasteroenterology_1 = medicinePlacement.getGastroenterology_Start();
        String gasteroenterology_2 = medicinePlacement.getGastroenterology_End();
        if (gasteroenterology_1.equals("-")) {
            gastroenterology_start_Id.setValue(null);
        } else {
            gastroenterology_start_Id.setValue(LocalDate.parse(gasteroenterology_1, dateTimeFormatter));
        }

        if (gasteroenterology_2.equals("-")) {
            gastroenterology_end_Id.setValue(null);
        } else {
            gastroenterology_end_Id.setValue(LocalDate.parse(gasteroenterology_2, dateTimeFormatter));
        }

        String neuromedicine_1 = medicinePlacement.getNeuromedicine_Start();
        String neuromedicine_2 = medicinePlacement.getNeuromedicine_End();
        if (neuromedicine_1.equals("-")) {
            neuromedicine_start_Id.setValue(null);
        } else {
            neuromedicine_start_Id.setValue(LocalDate.parse(neuromedicine_1, dateTimeFormatter));
        }

        if (neuromedicine_2.equals("-")) {
            neuromedicine_end_Id.setValue(null);
        } else {
            neuromedicine_end_Id.setValue(LocalDate.parse(neuromedicine_2, dateTimeFormatter));
        }


        // surgery part ---- >>
        // --------------------------------------------------------------

        general_surgery_start1_id.setValue(LocalDate.parse(surgeryPlacement.getGeneralSurgery_start_1(), dateTimeFormatter));
        general_surgery_end1_id.setValue(LocalDate.parse(surgeryPlacement.getGeneralSurgery_end_1(), dateTimeFormatter));

        ortho_Casualty_start_id.setValue(LocalDate.parse(surgeryPlacement.getOrthoCasialty_start(), dateTimeFormatter));
        ortho_Casualty_end_id.setValue(LocalDate.parse(surgeryPlacement.getOrthoCasialty_end(), dateTimeFormatter));

        anaesthetia_start_id.setValue(LocalDate.parse(surgeryPlacement.getAnaesthetia_start(), dateTimeFormatter));
        anaesthetia_end_id.setValue(LocalDate.parse(surgeryPlacement.getAnaesthetia_end(), dateTimeFormatter));

        opthalmology_start1_id.setValue(LocalDate.parse(surgeryPlacement.getOpthalmalogy_start(), dateTimeFormatter));
        opthalmology_end_id.setValue(LocalDate.parse(surgeryPlacement.getOpthalmalogy_end(), dateTimeFormatter));

        Otolaryngo_rhinology_start_id.setValue(LocalDate.parse(surgeryPlacement.getOtolaryngoRhinology_start(), dateTimeFormatter));
        Otolaryngo_rhinology_end_id.setValue(LocalDate.parse(surgeryPlacement.getOtolaryngoRhinology_end(), dateTimeFormatter));

        String general_Start_2 = surgeryPlacement.getGeneralSurgery_start_2();
        String general_end_2 = surgeryPlacement.getGeneralSurgery_end_2();
        if (general_Start_2.equals("-")) {
            general_surgery_start2_id.setValue(null);
        } else {
            general_surgery_start2_id.setValue(LocalDate.parse(general_Start_2, dateTimeFormatter));
        }

        if (general_end_2.equals("-")) {
            general_surgery_end2_id.setValue(null);
        } else {
            general_surgery_end2_id.setValue(LocalDate.parse(general_end_2, dateTimeFormatter));
        }

        String paediatrics_surgery_1 = surgeryPlacement.getPaediatricSurgery_start();
        String paediatrics_surgery_2 = surgeryPlacement.getPaediatricSurgery_end();
        if (paediatrics_surgery_1.equals("-")) {
            paediatric_Surgery_start_id.setValue(null);
        } else {
            paediatric_Surgery_start_id.setValue(LocalDate.parse(paediatrics_surgery_1, dateTimeFormatter));
        }

        if (paediatrics_surgery_2.equals("-")) {
            paediatric_Surgery_end_id.setValue(null);
        } else {
            paediatric_Surgery_end_id.setValue(LocalDate.parse(paediatrics_surgery_2, dateTimeFormatter));
        }

        String urology_1 = surgeryPlacement.getUrology_start();
        String urology_2 = surgeryPlacement.getUrology_end();
        if (urology_1.equals("-")) {
            urology_start_id.setValue(null);
        } else {
            urology_start_id.setValue(LocalDate.parse(urology_1, dateTimeFormatter));
        }

        if (urology_2.equals("-")) {
            urology_end_id.setValue(null);
        } else {
            urology_end_id.setValue(LocalDate.parse(urology_2, dateTimeFormatter));
        }

        String radiology_1 = surgeryPlacement.getRadiology_start();
        String radiology_2 = surgeryPlacement.getRadiology_end();
        if (radiology_1.equals("-")) {
            radiology_start_id.setValue(null);
        } else {
            radiology_start_id.setValue(LocalDate.parse(radiology_1, dateTimeFormatter));
        }

        if (radiology_2.equals("-")) {
            radiology_end_id.setValue(null);
        } else {
            radiology_end_id.setValue(LocalDate.parse(radiology_2, dateTimeFormatter));
        }


        // gynae part ---- >>
        // --------------------------------------------------------------

        indoor_start1.setValue(LocalDate.parse(gynae_Obs.getIndoorStart1(), dateTimeFormatter));
        indoor_end1.setValue(LocalDate.parse(gynae_Obs.getIndoorEnd1(), dateTimeFormatter));

        family_planning_start.setValue(LocalDate.parse(gynae_Obs.getFamilyPlanningStart(), dateTimeFormatter));
        family_planning_end.setValue(LocalDate.parse(gynae_Obs.getFamilyPlanningEnd(), dateTimeFormatter));

        obstetrics_start.setValue(LocalDate.parse(gynae_Obs.getObstericsEmergencyStart(), dateTimeFormatter));
        obstetrics_end.setValue(LocalDate.parse(gynae_Obs.getObstericsEmergencyEnd(), dateTimeFormatter));

        community_start.setValue(LocalDate.parse(gynae_Obs.getCommunityStart(), dateTimeFormatter));
        community_end.setValue(LocalDate.parse(gynae_Obs.getCommunityEnd(), dateTimeFormatter));

        String indoor_Start_2 = gynae_Obs.getIndoorStart2();
        String indoor_end_2 = gynae_Obs.getIndoorEnd2();

        if (indoor_Start_2.equals("-")) {
            indoor_start2.setValue(null);
        } else {
            indoor_start2.setValue(LocalDate.parse(indoor_Start_2, dateTimeFormatter));
        }

        if (indoor_end_2.equals("-")) {
            indoor_end2.setValue(null);
        } else {
            indoor_end2.setValue(LocalDate.parse(indoor_end_2, dateTimeFormatter));
        }


    }

    protected void surgerySaveButtonAction(Boolean value) {

        if (general_surgery_start1_id.getValue() != null && general_surgery_end1_id.getValue() != null &&
                ortho_Casualty_start_id.getValue() != null && ortho_Casualty_end_id.getValue() != null &&
                anaesthetia_start_id.getValue() != null && anaesthetia_end_id.getValue() != null &&
                opthalmology_start1_id.getValue() != null && opthalmology_end_id.getValue() != null &&
                Otolaryngo_rhinology_start_id.getValue() != null && Otolaryngo_rhinology_end_id.getValue() != null) {


            generalSurgery_start_1 = String.valueOf(general_surgery_start1_id.getValue().format(dateTimeFormatter));
            generalSurgery_end_1 = String.valueOf(general_surgery_end1_id.getValue().format(dateTimeFormatter));
            orthoCasialty_start = String.valueOf(ortho_Casualty_start_id.getValue().format(dateTimeFormatter));
            orthoCasialty_end = String.valueOf(ortho_Casualty_end_id.getValue().format(dateTimeFormatter));
            anaesthetia_start = String.valueOf(anaesthetia_start_id.getValue().format(dateTimeFormatter));
            anaesthetia_end = String.valueOf(anaesthetia_end_id.getValue().format(dateTimeFormatter));
            opthalmalogy_start = String.valueOf(opthalmology_start1_id.getValue().format(dateTimeFormatter));
            opthalmalogy_end = String.valueOf(opthalmology_end_id.getValue().format(dateTimeFormatter));
            otolaryngoRhinology_start = String.valueOf(Otolaryngo_rhinology_start_id.getValue().format(dateTimeFormatter));
            otolaryngoRhinology_end = String.valueOf(Otolaryngo_rhinology_end_id.getValue().format(dateTimeFormatter));

            if (general_surgery_start2_id.getValue() == null || general_surgery_end2_id.getValue() == null) {
                generalSurgery_start_2 = "-";
                generalSurgery_end_2 = "-";
            } else {
                generalSurgery_start_2 = String.valueOf(general_surgery_start2_id.getValue().format(dateTimeFormatter));
                generalSurgery_end_2 = String.valueOf(general_surgery_end2_id.getValue().format(dateTimeFormatter));
            }

            if (paediatric_Surgery_start_id.getValue() == null || paediatric_Surgery_end_id.getValue() == null) {
                paediatricSurgery_start = "-";
                paediatricSurgery_end = "-";
            } else {
                paediatricSurgery_start = String.valueOf(paediatric_Surgery_start_id.getValue().format(dateTimeFormatter));
                paediatricSurgery_end = String.valueOf(paediatric_Surgery_end_id.getValue().format(dateTimeFormatter));
            }

            if (urology_start_id.getValue() == null || urology_end_id.getValue() == null) {
                urology_start = "-";
                urology_end = "-";
            } else {
                urology_start = String.valueOf(urology_start_id.getValue().format(dateTimeFormatter));
                urology_end = String.valueOf(urology_end_id.getValue().format(dateTimeFormatter));
            }

            if (radiology_start_id.getValue() == null || radiology_end_id.getValue() == null) {
                radiology_start = "-";
                radiology_end = "-";
            } else {
                radiology_start = String.valueOf(radiology_start_id.getValue().format(dateTimeFormatter));
                radiology_end = String.valueOf(radiology_end_id.getValue().format(dateTimeFormatter));
            }


            surgeryPlacement = new Surgery(Integer.parseInt(dr_Id.getText().toString()), generalSurgery_start_1, generalSurgery_end_1, orthoCasialty_start, orthoCasialty_end,
                    anaesthetia_start, anaesthetia_end, opthalmalogy_start, opthalmalogy_end, otolaryngoRhinology_start, otolaryngoRhinology_end, paediatricSurgery_start, paediatricSurgery_end, urology_start, urology_end, radiology_start, radiology_end, generalSurgery_start_2, generalSurgery_end_2);

            try {
                databaseHandler.surgeryToDb(surgeryPlacement, surgeryPlacement.getSurgery_id());

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

    protected void gynaeObsSaveButtonAction(Boolean value) {

        if (indoor_start1.getValue() != null && indoor_end1.getValue() != null && family_planning_start.getValue() != null && family_planning_end.getValue() != null && community_start.getValue() != null && community_end.getValue() != null) {

            indoorStart1 = String.valueOf(indoor_start1.getValue().format(dateTimeFormatter));
            indoorEnd1 = String.valueOf(indoor_end1.getValue().format(dateTimeFormatter));
            familyPlanningStart = String.valueOf(family_planning_start.getValue().format(dateTimeFormatter));
            familyPlanningEnd = String.valueOf(family_planning_end.getValue().format(dateTimeFormatter));
            obstericsEmergencyStart = String.valueOf(obstetrics_start.getValue().format(dateTimeFormatter));
            obstericsEmergencyEnd = String.valueOf(obstetrics_end.getValue().format(dateTimeFormatter));
            communityStart = String.valueOf(community_start.getValue().format(dateTimeFormatter));
            communityEnd = String.valueOf(community_end.getValue().format(dateTimeFormatter));

            if (indoor_start2.getValue() == null || indoor_end2.getValue() == null) {
                indoorStart2 = "-";
                indoorEnd2 = "-";
            } else {
                indoorStart2 = String.valueOf(indoor_start2.getValue().format(dateTimeFormatter));
                indoorEnd2 = String.valueOf(indoor_end2.getValue().format(dateTimeFormatter));
            }

            gynae_Obs = new GynaeObs(indoorStart1, indoorEnd1, indoorStart2, indoorEnd2, familyPlanningStart, familyPlanningEnd, obstericsEmergencyStart, obstericsEmergencyEnd, communityStart, communityEnd, Integer.valueOf(dr_Id.getText().toString()));

            try {
                databaseHandler.gynaeObsToDb(gynae_Obs, gynae_Obs.getGynaeObs_id());

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

    protected void medicineSaveButtonAction(Boolean value) throws SQLException, ClassNotFoundException {

        if (internal_medicine_start1_Id.getValue() != null && internal_medicine_end1_Id.getValue() != null && paediatrics_start_Id.getValue() != null && paediatrics_end_Id.getValue() != null) {

            internalMedicine_Start1 = String.valueOf(internal_medicine_start1_Id.getValue().format(dateTimeFormatter));
            internalMedicine_End1 = String.valueOf(internal_medicine_end1_Id.getValue().format(dateTimeFormatter));
            paediatrics_Start = String.valueOf(paediatrics_start_Id.getValue().format(dateTimeFormatter));
            paediatrics_End = String.valueOf(paediatrics_end_Id.getValue().format(dateTimeFormatter));


            if (skin_vd_start_Id.getValue() == null || skin_vd_end_Id.getValue() == null) {
                skin_VD_Start = "-";
                skin_VD_End = "-";
            } else {
                skin_VD_Start = String.valueOf(skin_vd_start_Id.getValue().format(dateTimeFormatter));
                skin_VD_End = String.valueOf(skin_vd_end_Id.getValue().format(dateTimeFormatter));
            }

            if (psychiatry_start_Id.getValue() == null || psychiatry_end_Id.getValue() == null) {
                psychiatry_Start = "-";
                psychiatry_End = "-";
            } else {
                psychiatry_Start = String.valueOf(psychiatry_start_Id.getValue().format(dateTimeFormatter));
                psychiatry_End = String.valueOf(psychiatry_end_Id.getValue().format(dateTimeFormatter));
            }

            if (cardiology_start_Id.getValue() == null || cardiology_end_Id.getValue() == null) {
                cardiology_Start = "-";
                cardiology_End = "-";
            } else {
                cardiology_Start = String.valueOf(cardiology_start_Id.getValue().format(dateTimeFormatter));
                cardiology_End = String.valueOf(cardiology_end_Id.getValue().format(dateTimeFormatter));
            }

            if (gastroenterology_start_Id.getValue() == null || gastroenterology_end_Id.getValue() == null) {
                gastroenterology_Start = "-";
                gastroenterology_End = "-";
            } else {
                gastroenterology_Start = String.valueOf(gastroenterology_start_Id.getValue().format(dateTimeFormatter));
                gastroenterology_End = String.valueOf(gastroenterology_end_Id.getValue().format(dateTimeFormatter));
            }

            if (nephrology_start_Id.getValue() == null || nephrology_end_Id.getValue() == null) {
                nephrology_Start = "-";
                nephrology_End = "-";
            } else {
                nephrology_Start = String.valueOf(nephrology_start_Id.getValue().format(dateTimeFormatter));
                nephrology_End = String.valueOf(nephrology_end_Id.getValue().format(dateTimeFormatter));
            }

            if (neuromedicine_start_Id.getValue() == null || neuromedicine_end_Id.getValue() == null) {
                neuromedicine_Start = "-";
                neuromedicine_End = "-";
            } else {
                neuromedicine_Start = String.valueOf(neuromedicine_start_Id.getValue().format(dateTimeFormatter));
                neuromedicine_End = String.valueOf(neuromedicine_end_Id.getValue().format(dateTimeFormatter));
            }

            /** internal medicine shift part */

            if (internal_medicine_start2_Id.getValue() == null || internal_medicine_end2_Id.getValue() == null) {
                internalMedicine_Start2 = "-";
                internalMedicine_End2 = "-";
            } else {
                internalMedicine_Start2 = String.valueOf(internal_medicine_start2_Id.getValue().format(dateTimeFormatter));
                internalMedicine_End2 = String.valueOf(internal_medicine_end2_Id.getValue().format(dateTimeFormatter));
            }

            medicinePlacement = new Medicine(internalMedicine_Start1, internalMedicine_Start2, paediatrics_Start, skin_VD_Start, psychiatry_Start, cardiology_Start,
                    gastroenterology_Start, nephrology_Start, neuromedicine_Start, internalMedicine_End1, internalMedicine_End2, paediatrics_End,
                    skin_VD_End, psychiatry_End, cardiology_End, gastroenterology_End, nephrology_End, neuromedicine_End, Integer.valueOf(dr_Id.getText()));

            databaseHandler.medicineToDB(medicinePlacement, medicinePlacement.getDoctorId());

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
}
