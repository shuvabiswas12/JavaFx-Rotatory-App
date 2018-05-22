package sample.controller;

import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import javafx.util.Callback;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Doctor;
import sample.others.CustomDialogueBox;


public class HomeController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private JFXComboBox<String> filter;

    @FXML
    private JFXTreeTableView<Doctor> tableView;

    @FXML
    private TextField searchTextId;

    @FXML
    private VBox aboutScene;

    @FXML
    private VBox addScene;

    @FXML
    private Button copyBtn;

    @FXML
    private HBox newDoctorBtn;

    @FXML
    private HBox reportsBox;


    // others variable
    private DatabaseHandler databaseHandler;
    private ObservableList<Doctor> doctorObservableList;
    private ObservableList<Doctor> selectedDoctor;
    private ResultSet resultSet;
    private ObservableList<String> filterComboBoxList;
    private String sessionLoad = null;
    private CustomDialogueBox jfxDialogueBox;

    // tree table view column name variable
    TreeTableColumn placement;
    TreeTableColumn<Doctor, String> name;
    TreeTableColumn<Doctor, Integer> id;
    TreeTableColumn<Doctor, String> session;
    TreeTableColumn<Doctor, String> finalProf;

    @FXML
    void aboutBtn(MouseEvent event) {
        aboutScene.toFront();
    }

    @FXML
    void addBtn(MouseEvent event) {
        addScene.toFront();
    }

    @FXML
    void databaseBox(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
        RecordController.setValue(databaseHandler.getNumberOfDoctor());
        RecordController.setSession(databaseHandler.getNumberOfSession());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/records.fxml"));
        Parent root = loader.load();
        Stage recordsStage = new Stage(StageStyle.UNDECORATED);
        recordsStage.setScene(new Scene(root));
        recordsStage.getIcons().add(new Image("/sample/assests/pill.png"));
        recordsStage.showAndWait();

    }

    @FXML
    void load(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {

        resultSet = databaseHandler.getSession();
        List<String> sessionList = new ArrayList<>();

        while (resultSet.next()) {
            sessionList.add(resultSet.getString(Const.SESSION));
        }

        LoadController.sessionList = sessionList;


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/load.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("/sample/assests/pill.png"));
        stage.show();

        LoadController loadController = loader.getController();

        loadController.loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sessionLoad = loadController.getSession();
                if (sessionLoad.isEmpty()) {
                    jfxDialogueBox.materialDialogue("Empty Field!", "Enter a session first.", loadController.stackpane);
                } else {
                    try {
                        doctorObservableList.clear();
                        showDataIntoTableView();
                        comboBox();
                        loadController.loadBtn.getScene().getWindow().hide();
                        Notifications notifications = Notifications.create()
                                .title("Confirmation massage!")
                                .text("Data set Successfully loaded!")
                                .position(Pos.BOTTOM_RIGHT)
                                .hideAfter(Duration.seconds(5))
                                .graphic(null);
                        notifications.showConfirm();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    @FXML
    void removeBtn(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/remove.fxml"));
        Parent root = loader.load();
        Stage removeStage = new Stage();
        removeStage.setScene(new Scene(root));
        //removeStage.setResizable(false);
        removeStage.initStyle(StageStyle.UNDECORATED);
        removeStage.getIcons().add(new Image("/sample/assests/pill.png"));
        removeStage.show();

        RemoveController removeController = loader.getController();
        removeController.removeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sessionRemove = removeController.getSession();
                if (sessionRemove.isEmpty()) {
                    jfxDialogueBox.materialDialogue("Empty Field!", "Enter a session first.", removeController.stackpane);
                } else {
                    try {
                        databaseHandler.removeSession(sessionRemove);
                        String n = new String(removeController.session.getText().toString());
                        removeController.sessionList.remove(n);
                        doctorObservableList.clear();
                        removeController.session.clear();
                        showDataIntoTableView();
                        comboBox();

                        Notifications notifications = Notifications.create()
                                .title("Confirmation massage!")
                                .text("Remove Successfully complete!")
                                .position(Pos.BOTTOM_RIGHT)
                                .hideAfter(Duration.seconds(5))
                                .graphic(null);
                        notifications.showConfirm();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



    @FXML
    void refresh(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (sessionLoad != null) {
            if (sessionLoad.equals("All")) {
                resultSet = databaseHandler.getAllDoctor();
                refreshTask();
                filter.setItems(filterComboBoxList);
            } else {
                resultSet = databaseHandler.getAllDoctor(sessionLoad);
                refreshTask();
                filter.setItems(filterComboBoxList);
            }
        }




    }



    public void refreshTask() throws SQLException, ClassNotFoundException {
        doctorObservableList.clear();
        filterComboBoxList.clear();
        while (resultSet.next()) {
            doctorObservableList.add(new Doctor(resultSet.getString(Const.DOCTOR_NAME), resultSet.getString(Const.SESSION), resultSet.getString(Const.FINAL_PROF), resultSet.getInt(Const.DOCTOR_ID)));
        }
        resultSet = databaseHandler.getSession();
        while (resultSet.next()) {
            filterComboBoxList.add(resultSet.getString(Const.SESSION));
        }
    }



    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        databaseHandler = DatabaseHandler.getDBInstance();
        doctorObservableList = FXCollections.observableArrayList();
        selectedDoctor = FXCollections.observableArrayList();
        filterComboBoxList = FXCollections.observableArrayList();
        jfxDialogueBox = new CustomDialogueBox();

        treeTableViewColumnName();

        /** Search Filtering */
        searchTextId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tableView.setPredicate(new Predicate<TreeItem<Doctor>>() {
                    @Override
                    public boolean test(TreeItem<Doctor> doctorTreeItem) {
                        String id = String.valueOf(doctorTreeItem.getValue().getId());
                        String lowerCaseFilter = newValue.toLowerCase();
                        Boolean flag = doctorTreeItem.getValue().getName().toLowerCase().contains(lowerCaseFilter) || id.contains(newValue);
                        return flag;
                    }
                });
            }
        });

        newDoctorsButtonAction();

        reportsBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    reportBtn();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    // tree table view full implementation with column data
    public void showDataIntoTableView() throws SQLException, ClassNotFoundException {

        resultSet = databaseHandler.getAllDoctor(sessionLoad);

        name.setCellValueFactory(new TreeItemPropertyValueFactory<Doctor, String>("name"));

        id.setCellValueFactory(new TreeItemPropertyValueFactory<Doctor, Integer>("id"));

        session.setCellValueFactory(new TreeItemPropertyValueFactory<Doctor, String>("session"));

        finalProf.setCellValueFactory(new TreeItemPropertyValueFactory<Doctor, String>("finalProf"));

        /*** action placement */
        placement.setCellValueFactory(new TreeItemPropertyValueFactory<>("DUMMY"));

        Callback<TreeTableColumn<Doctor, String>, TreeTableCell<Doctor, String>> cellFactory = new Callback<TreeTableColumn<Doctor, String>, TreeTableCell<Doctor, String>>() {
            @Override
            public TreeTableCell<Doctor, String> call(TreeTableColumn<Doctor, String> param) {

                final TreeTableCell<Doctor, String> cell = new TreeTableCell<Doctor, String>() {

                    Image checkedImg = new Image("/sample/assests/icons8-checked-48.png");
                    ImageView checked = new ImageView(checkedImg);


                    Image warnImg = new Image("/sample/assests/icons8-brake-warning-48.png");
                    ImageView warned = new ImageView(warnImg);


                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            int doctorid = getTreeTableView().getTreeItem(getIndex()).getValue().getId();
                            //System.out.println(doctorid);

                            int val = 0;


                            try {
                                val = databaseHandler.getAllPlacement(doctorid);

                                //System.out.println(val);

                                if (val == 1) {
                                    setGraphic(checked);
                                    setText(null);
                                } else {
                                    setGraphic(warned);
                                    setText(null);
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                };
                return cell;
            }
        };

        placement.setCellFactory(cellFactory);


        while (resultSet.next()) {
            doctorObservableList.add(new Doctor(resultSet.getString(Const.DOCTOR_NAME), resultSet.getString(Const.SESSION), resultSet.getString(Const.FINAL_PROF), resultSet.getInt(Const.DOCTOR_ID)));
        }

        /** build tree */
        final TreeItem<Doctor> root = new RecursiveTreeItem<Doctor>(doctorObservableList, RecursiveTreeObject::getChildren);


        /** set root */
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        contextMenuItems();
    }



    // tree table view column header implement
    public void treeTableViewColumnName() {

        placement = new TreeTableColumn("#Placement");
        placement.setMaxWidth(140);
        placement.setMinWidth(140);
        placement.setSortable(false);

        name = new TreeTableColumn("#Name");
        name.setMinWidth(250);
        name.setMaxWidth(250);

        id = new TreeTableColumn("#Id");
        id.setMaxWidth(140);
        id.setMinWidth(140);

        session = new TreeTableColumn("#Session");
        session.setSortable(false);

        finalProf = new TreeTableColumn("#Final Prof");
        finalProf.setSortable(false);

        /** Showing all column into table */
        tableView.getColumns().addAll(placement, name, id, session, finalProf);

    }



    // context menu item implementation
    public void contextMenuItems() {


        /** Context Menu */
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        MenuItem placement = new MenuItem("Placement");
        MenuItem print = new MenuItem("Print");

        /** Context menu */
        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(edit, delete, placement, print);
        menu.setStyle("-fx-font-size: 1.3em;");
        tableView.setContextMenu(menu);

        /** edit button */
        edit.setOnAction((ActionEvent event) -> {

            TreeTableView.TreeTableViewSelectionModel<Doctor> sm = tableView.getSelectionModel();

            /** Get the selected Entry */

            int rowIndex = sm.getSelectedIndex();

            TreeItem<Doctor> selectedItem = sm.getModelItem(rowIndex);

            Doctor doctor = selectedItem.getValue();
            //System.out.println(doctor.getName() +"\n"+ doctor.getId() +"\n"+ doctor.getSession() +"\n"+ doctor.getFinalProf());

            selectedDoctor.add(doctor);
            EditController.setDoctorObservableList(selectedDoctor);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/edit.fxml"));
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("/sample/assests/icons8-medical-doctor-80.png"));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            EditController editController = loader.getController();
            editController.updateBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    editController.updateDoctorInfo();
                    doctorObservableList.clear();
                    try {
                        showDataIntoTableView();
                        comboBox();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            });

        });

        /** delete button */
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TreeTableView.TreeTableViewSelectionModel<Doctor> sm = tableView.getSelectionModel();

                /** Get the selected Entry */

                int rowIndex = sm.getSelectedIndex();

                TreeItem<Doctor> selectedItem = sm.getModelItem(rowIndex);

                TreeItem<Doctor> parent = selectedItem.getParent();

                Doctor doctor = selectedItem.getValue();
                System.out.println(doctor.getName() + "\n" + doctor.getId() + "\n" + doctor.getSession() + "\n" + doctor.getFinalProf());

                parent.getChildren().remove(selectedItem);
                try {
                    databaseHandler.deleteDoctor(doctor.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        /** placement button*/
        placement.setOnAction(e -> {

            FXMLLoader loader = new FXMLLoader();
            Parent root = null;
            Stage stage = new Stage();

            TreeTableView.TreeTableViewSelectionModel<Doctor> sm = tableView.getSelectionModel();

            /** Get the selected Entry */

            int rowIndex = sm.getSelectedIndex();

            TreeItem<Doctor> selectedItem = sm.getModelItem(rowIndex);

            Doctor doctor = selectedItem.getValue();
            int value = 0;
            try {
                value = databaseHandler.getAllPlacement(doctor.getId());
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }


            if (value == 1) {
                ShowPlacementController.doctor = doctor;
                loader.setLocation(getClass().getResource("/sample/view/showPlacement.fxml"));
                try {
                    root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("/sample/assests/icons8-medical-doctor-80.png"));
                    stage.show();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else {

                PlacementController.doctorId = String.valueOf(doctor.getId());
                PlacementController.doctorName = doctor.getName();
                PlacementController.doctorSession = doctor.getSession();

                loader.setLocation(getClass().getResource("/sample/view/placement.fxml"));
                try {
                    root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.getIcons().add(new Image("/sample/assests/icons8-medical-doctor-80.png"));
                    stage.showAndWait();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        });

        /** print button*/
        print.setOnAction(e -> {
            TreeTableView.TreeTableViewSelectionModel<Doctor> sm = tableView.getSelectionModel();

            /** Get the selected Entry */

            int rowIndex = sm.getSelectedIndex();

            TreeItem<Doctor> selectedItem = sm.getModelItem(rowIndex);

            Doctor printDoctor = selectedItem.getValue();
            int value = 0;
            try {
                value = databaseHandler.getAllPlacement(printDoctor.getId());
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            if (value == 1) {
                // code here
                ReportController.doctor = printDoctor;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/generateReport.fxml"));
                    Parent root = loader.load();
                    Stage reportStage = new Stage(StageStyle.TRANSPARENT);
                    reportStage.setScene(new Scene(root));
                    reportStage.initStyle(StageStyle.UNDECORATED);
                    reportStage.getIcons().add(new Image("/sample/assests/icons8-medical-doctor-80.png"));
                    reportStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Can not Find placement against this doctor");
                alert.setHeaderText(null);
                alert.setContentText("This id hasn't any placement yet !!");
                alert.showAndWait();
            }

        });


    }



    // combo-box implementation
    public void comboBox() throws SQLException, ClassNotFoundException {
        resultSet = databaseHandler.getSession();
        filterComboBoxList.clear();
        while (resultSet.next()) {
            filterComboBoxList.add(resultSet.getString(Const.SESSION));
        }
        if (filterComboBoxList != null) {
            filterComboBoxList.add(0, "All");
        }
        filter.setItems(filterComboBoxList);

        filter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (filter.getValue() != null) {
                    try {
                        sessionLoad = filter.getValue().toString();
                        filter.setPromptText(sessionLoad);
                        if (sessionLoad.equals("All")) {
                            resultSet = databaseHandler.getDoctorBySession();
                        } else {
                            resultSet = databaseHandler.getDoctorBySession(sessionLoad);
                        }

                        doctorObservableList.clear();
                        while (resultSet.next()) {
                            doctorObservableList.add(new Doctor(resultSet.getString(Const.DOCTOR_NAME), resultSet.getString(Const.SESSION), resultSet.getString(Const.FINAL_PROF), resultSet.getInt(Const.DOCTOR_ID)));
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }



    // new doctor button action
    public void newDoctorsButtonAction() {
        newDoctorBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/new.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("/sample/assests/pill.png"));
                stage.show();

                NewController newController = loader.getController();
                newController.saveBt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        newController.saveDoctorInfo();
                        try {
                            doctorObservableList.clear();
                            showDataIntoTableView();
                            comboBox();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }



    // report button operation
    public void reportBtn() throws IOException, SQLException, ClassNotFoundException {

        resultSet = databaseHandler.getSession();
        List<String> sessionList = new ArrayList<>();

        while (resultSet.next()) {
            sessionList.add(resultSet.getString(Const.SESSION));
        }

        AllReportController.sessionList = sessionList;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/allReport.fxml"));
        Parent root = loader.load();
        Stage allGenerateStage = new Stage();
        allGenerateStage.setScene(new Scene(root));
        allGenerateStage.initStyle(StageStyle.UNDECORATED);
        allGenerateStage.getIcons().add(new Image("/sample/assests/pill.png"));
        allGenerateStage.show();
    }

}

