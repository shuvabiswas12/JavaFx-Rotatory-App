package sample.database;

import com.sun.istack.internal.NotNull;
import sample.model.Doctor;
import sample.model.GynaeObs;
import sample.model.Medicine;
import sample.model.Surgery;

import java.sql.*;

public class DatabaseHandler extends Config {
    /**
     * Here is an implementation of Singleton Pattern
     * So, according to the rules of singleton pattern, the constructor of the this class is private and for creating
     * instance of this class here is an class member method or public static method
     */

    private Connection Connection;

    private DatabaseHandler() {
        try {
            this.getDbConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DatabaseHandler getDBInstance() {
        return new DatabaseHandler();
    }

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionQuery = "jdbc:mysql://" + DBHOST + ":" + DBPORT + "/" + DBNAME;
        Class.forName("com.mysql.jdbc.Driver");
        Connection = DriverManager.getConnection(connectionQuery, DBUSER, DBPASSWORD);
        return Connection;
    }

    /**
     * This method for inserting data into doctor table
     */

    public void saveDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO " + Const.DOCTOR_TABLE + " ( " + Const.DOCTOR_NAME + " , " + Const.DOCTOR_ID + " , " + Const.SESSION + " , " + Const.FINAL_PROF + " ) VALUES(?,?,?,?)";
        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setString(1, doctor.getName());
        statement.setInt(2, doctor.getId());
        statement.setString(3, doctor.getSession());
        statement.setString(4, doctor.getFinalProf());
        statement.execute();
    }


    /**
     * This method for retrieve all the data from database for showing in table view
     */

    public ResultSet getAllDoctor(String sessionLoad) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.SESSION + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, sessionLoad);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getAllDoctor() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.DOCTOR_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


    /**
     * update a doctor
     */
    public void updateDoctor(Doctor doctor, int temp_id) throws SQLException, ClassNotFoundException {
        String query = "UPDATE " + Const.DOCTOR_TABLE + " SET " + Const.DOCTOR_NAME + " = ?, " + Const.DOCTOR_ID + " = ?, "
                + Const.SESSION + " = ?, " + Const.FINAL_PROF + " = ? WHERE " + Const.DOCTOR_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, doctor.getName());
        preparedStatement.setInt(2, doctor.getId());
        preparedStatement.setString(3, doctor.getSession());
        preparedStatement.setString(4, doctor.getFinalProf());
        preparedStatement.setInt(5, temp_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    /**
     * Delete Doctor
     */
    public void deleteDoctor(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.DOCTOR_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }


    /**
     * Combo box query
     */
    public ResultSet getSession() throws SQLException, ClassNotFoundException {
        String query = "SELECT DISTINCT " + Const.SESSION + " FROM " + Const.DOCTOR_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /**
     * combo box item finder
     */
    public ResultSet getDoctorBySession(String session) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.SESSION + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, session);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getDoctorBySession() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.DOCTOR_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /**
     * update medicine
     */
    public void medicineToDB(Medicine medicine, int id) throws SQLException, ClassNotFoundException {

        String query = "UPDATE " + Const.MEDICINE_TABLE + " SET " + Const.INTERNAL_MEDICINE_START_1 + " = ? " + ", " + Const.INTERNAL_MEDICINE_START_2 + " = ? " + ", "
                + Const.PAEDIATRICS_START + " = ? " + ", " + Const.SKIN_VD_START + " = ? " + ", " + Const.PSYCHIATRY_START + " = ? " + ", " + Const.CARDIOLOGY_START + " = ? " + ", " +
                Const.GASTROENTEROLOGY_START + " = ? " + ", " + Const.NEPHROLOGY_START + " = ? " + ", " + Const.NEUROMEDICINE_START + " = ? " + ", " +
                Const.INTERNAL_MEDICINE_END_1 + " = ? " + ", " + Const.INTERNAL_MEDICINE_END_2 + " = ? " + ", " + Const.PAEDIATRICS_END + " = ? " + ", " + Const.SKIN_VD_END + " = ? " + ", " + Const.PSYCHIATRY_END + " = ? " + ", " + Const.CARDIOLOGY_END + " = ? " + ", "
                + Const.GASTROENTEROLOGY_END + " = ? " + ", " + Const.NEPHROLOGY_END + " = ? " + ", " + Const.NEUROMEDICINE_END + " = ? " + "WHERE " + Const.MEDICINE_ID + " = ? ";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

        preparedStatement.setString(1, medicine.getInternalMedicine_Start1());
        preparedStatement.setString(2, medicine.getInternalMedicine_Start2());
        preparedStatement.setString(3, medicine.getPaediatrics_Start());
        preparedStatement.setString(4, medicine.getSkin_VD_Start());
        preparedStatement.setString(5, medicine.getPsychiatry_Start());
        preparedStatement.setString(6, medicine.getCardiology_Start());
        preparedStatement.setString(7, medicine.getGastroenterology_Start());
        preparedStatement.setString(8, medicine.getNephrology_Start());
        preparedStatement.setString(9, medicine.getNeuromedicine_Start());
        preparedStatement.setString(10, medicine.getInternalMedicine_End1());
        preparedStatement.setString(11, medicine.getInternalMedicine_End2());
        preparedStatement.setString(12, medicine.getPaediatrics_End());
        preparedStatement.setString(13, medicine.getSkin_VD_End());
        preparedStatement.setString(14, medicine.getPsychiatry_End());
        preparedStatement.setString(15, medicine.getCardiology_End());
        preparedStatement.setString(16, medicine.getGastroenterology_End());
        preparedStatement.setString(17, medicine.getNephrology_End());
        preparedStatement.setString(18, medicine.getNeuromedicine_End());
        preparedStatement.setInt(19, id);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    /**
     * Medicine into database
     */
    public void medicineToDB(Medicine medicine) throws SQLException, ClassNotFoundException {

        String query = "INSERT INTO " + Const.MEDICINE_TABLE + " ( " + Const.MEDICINE_ID + ", " + Const.INTERNAL_MEDICINE_START_1 + ", " + Const.INTERNAL_MEDICINE_START_2 + ", "
                + Const.PAEDIATRICS_START + ", " + Const.SKIN_VD_START + ", " + Const.PSYCHIATRY_START + ", " + Const.CARDIOLOGY_START + ", " +
                Const.GASTROENTEROLOGY_START + ", " + Const.NEPHROLOGY_START + ", " + Const.NEUROMEDICINE_START + ", " +
                Const.INTERNAL_MEDICINE_END_1 + ", " + Const.INTERNAL_MEDICINE_END_2 + ", " + Const.PAEDIATRICS_END + ", " + Const.SKIN_VD_END + ", " + Const.PSYCHIATRY_END + ", " + Const.CARDIOLOGY_END + ", "
                + Const.GASTROENTEROLOGY_END + ", " + Const.NEPHROLOGY_END + ", " + Const.NEUROMEDICINE_END + " ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

        preparedStatement.setInt(1, medicine.getDoctorId());
        preparedStatement.setString(2, medicine.getInternalMedicine_Start1());
        preparedStatement.setString(3, medicine.getInternalMedicine_Start2());
        preparedStatement.setString(4, medicine.getPaediatrics_Start());
        preparedStatement.setString(5, medicine.getSkin_VD_Start());
        preparedStatement.setString(6, medicine.getPsychiatry_Start());
        preparedStatement.setString(7, medicine.getCardiology_Start());
        preparedStatement.setString(8, medicine.getGastroenterology_Start());
        preparedStatement.setString(9, medicine.getNephrology_Start());
        preparedStatement.setString(10, medicine.getNeuromedicine_Start());
        preparedStatement.setString(11, medicine.getInternalMedicine_End1());
        preparedStatement.setString(12, medicine.getInternalMedicine_End2());
        preparedStatement.setString(13, medicine.getPaediatrics_End());
        preparedStatement.setString(14, medicine.getSkin_VD_End());
        preparedStatement.setString(15, medicine.getPsychiatry_End());
        preparedStatement.setString(16, medicine.getCardiology_End());
        preparedStatement.setString(17, medicine.getGastroenterology_End());
        preparedStatement.setString(18, medicine.getNephrology_End());
        preparedStatement.setString(19, medicine.getNeuromedicine_End());

        preparedStatement.execute();
    }

    /**
     * Gynae_obs into database
     */
    public void gynaeObsToDb(GynaeObs gynaeObs) throws SQLException, ClassNotFoundException {

        String query = "INSERT INTO " + Const.GYNAE_OBS_TABLE + " ( " + Const.GYNAE_OBS_ID + ", " + Const.INDOOR_START_1 + ", " +
                Const.INDOOR_END_1 + ", " + Const.INDOOR_START_2 + ", " + Const.INDOOR_END_2 + ", " + Const.FAMILY_PLANNING_START + ", " +
                Const.FAMILY_PLANNING_END + ", " + Const.OBSTERICS_EMERGENCY_START + ", " + Const.OBSTERICS_EMERGENCY_END + ", " +
                Const.COMMUNITY_START + ", " + Const.COMMUNITY_END + " ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, gynaeObs.getGynaeObs_id());
        preparedStatement.setString(2, gynaeObs.getIndoorStart1());
        preparedStatement.setString(3, gynaeObs.getIndoorEnd1());
        preparedStatement.setString(4, gynaeObs.getIndoorStart2());
        preparedStatement.setString(5, gynaeObs.getIndoorEnd2());
        preparedStatement.setString(6, gynaeObs.getFamilyPlanningStart());
        preparedStatement.setString(7, gynaeObs.getFamilyPlanningEnd());
        preparedStatement.setString(8, gynaeObs.getObstericsEmergencyStart());
        preparedStatement.setString(9, gynaeObs.getObstericsEmergencyEnd());
        preparedStatement.setString(10, gynaeObs.getCommunityStart());
        preparedStatement.setString(11, gynaeObs.getCommunityEnd());

        preparedStatement.execute();

    }

    /**
     * Gynae_obs update
     */
    public void gynaeObsToDb(GynaeObs gynaeObs, int id) throws SQLException, ClassNotFoundException {

        String query = "UPDATE " + Const.GYNAE_OBS_TABLE + " SET " + Const.INDOOR_START_1 + " = ? " + ", " +
                Const.INDOOR_END_1 + " = ? " + ", " + Const.INDOOR_START_2 + " = ? " + ", " + Const.INDOOR_END_2 + " = ? " + ", " + Const.FAMILY_PLANNING_START + " = ? " + ", " +
                Const.FAMILY_PLANNING_END + " = ? " + ", " + Const.OBSTERICS_EMERGENCY_START + " = ? " + ", " + Const.OBSTERICS_EMERGENCY_END + " = ? " + ", " +
                Const.COMMUNITY_START + " = ? " + ", " + Const.COMMUNITY_END + " = ? " + "WHERE " + Const.GYNAE_OBS_ID + " = ? ";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

        preparedStatement.setString(1, gynaeObs.getIndoorStart1());
        preparedStatement.setString(2, gynaeObs.getIndoorEnd1());
        preparedStatement.setString(3, gynaeObs.getIndoorStart2());
        preparedStatement.setString(4, gynaeObs.getIndoorEnd2());
        preparedStatement.setString(5, gynaeObs.getFamilyPlanningStart());
        preparedStatement.setString(6, gynaeObs.getFamilyPlanningEnd());
        preparedStatement.setString(7, gynaeObs.getObstericsEmergencyStart());
        preparedStatement.setString(8, gynaeObs.getObstericsEmergencyEnd());
        preparedStatement.setString(9, gynaeObs.getCommunityStart());
        preparedStatement.setString(10, gynaeObs.getCommunityEnd());
        preparedStatement.setInt(11, id);

        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    /**
     * Surgery into database
     */
    public void surgeryToDb(Surgery surgery) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO " + Const.SURGERY_TABLE + " ( " + Const.SURGERY_ID + ", " + Const.GENERAL_SURGERY_START_1 + ", " + Const.GENERAL_SURGERY_END_1 + ", " + Const.GENERAL_SURGERY_START_2 + ", " + Const.GENERAL_SURGERY_END_2 + ", " + Const.ORTHOCASILTY_START + ", " + Const.ORTHOCASILTY_END
                + ", " + Const.anaesthetia_start + ", " + Const.anaesthetia_end + ", " + Const.opthalmalogy_start + ", " + Const.opthalmalogy_end + ", " + Const.otolaryngoRhinology_start + ", " + Const.otolaryngoRhinology_end
                + ", " + Const.paediatricSurgery_start + ", " + Const.paediatricSurgery_end + ", " + Const.urology_start + ", " + Const.urology_end + ", " + Const.radiology_start + ", " + Const.radiology_end
                + " ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, surgery.getSurgery_id());
        preparedStatement.setString(2, surgery.getGeneralSurgery_start_1());
        preparedStatement.setString(3, surgery.getGeneralSurgery_end_1());
        preparedStatement.setString(4, surgery.getGeneralSurgery_start_2());
        preparedStatement.setString(5, surgery.getGeneralSurgery_end_2());
        preparedStatement.setString(6, surgery.getOrthoCasialty_start());
        preparedStatement.setString(7, surgery.getOrthoCasialty_end());
        preparedStatement.setString(8, surgery.getAnaesthetia_start());
        preparedStatement.setString(9, surgery.getAnaesthetia_end());
        preparedStatement.setString(10, surgery.getOpthalmalogy_start());
        preparedStatement.setString(11, surgery.getOpthalmalogy_end());
        preparedStatement.setString(12, surgery.getOtolaryngoRhinology_start());
        preparedStatement.setString(13, surgery.getOtolaryngoRhinology_end());
        preparedStatement.setString(14, surgery.getPaediatricSurgery_start());
        preparedStatement.setString(15, surgery.getPaediatricSurgery_end());
        preparedStatement.setString(16, surgery.getUrology_start());
        preparedStatement.setString(17, surgery.getUrology_end());
        preparedStatement.setString(18, surgery.getRadiology_start());
        preparedStatement.setString(19, surgery.getRadiology_end());

        preparedStatement.execute();

    }

    /**
     * Surgery update
     */
    public void surgeryToDb(Surgery surgery, int id) throws SQLException, ClassNotFoundException {
        String query = "UPDATE  " + Const.SURGERY_TABLE + " SET " + Const.GENERAL_SURGERY_START_1 + " = ? " + ", " + Const.GENERAL_SURGERY_END_1 + " = ? " + ", " + Const.GENERAL_SURGERY_START_2 + " = ? " + ", " + Const.GENERAL_SURGERY_END_2 + " = ? " + ", " + Const.ORTHOCASILTY_START + " = ? " + ", " + Const.ORTHOCASILTY_END + " = ? "
                + ", " + Const.anaesthetia_start + " = ? " + ", " + Const.anaesthetia_end + " = ? " + ", " + Const.opthalmalogy_start + " = ? " + ", " + Const.opthalmalogy_end + " = ? " + ", " + Const.otolaryngoRhinology_start + " = ? " + ", " + Const.otolaryngoRhinology_end + " = ? "
                + ", " + Const.paediatricSurgery_start + " = ? " + ", " + Const.paediatricSurgery_end + " = ? " + ", " + Const.urology_start + " = ? " + ", " + Const.urology_end + " = ? " + ", " + Const.radiology_start + " = ? " + ", " + Const.radiology_end + " = ? " + "WHERE " + Const.SURGERY_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

        preparedStatement.setString(1, surgery.getGeneralSurgery_start_1());
        preparedStatement.setString(2, surgery.getGeneralSurgery_end_1());
        preparedStatement.setString(3, surgery.getGeneralSurgery_start_2());
        preparedStatement.setString(4, surgery.getGeneralSurgery_end_2());
        preparedStatement.setString(5, surgery.getOrthoCasialty_start());
        preparedStatement.setString(6, surgery.getOrthoCasialty_end());
        preparedStatement.setString(7, surgery.getAnaesthetia_start());
        preparedStatement.setString(8, surgery.getAnaesthetia_end());
        preparedStatement.setString(9, surgery.getOpthalmalogy_start());
        preparedStatement.setString(10, surgery.getOpthalmalogy_end());
        preparedStatement.setString(11, surgery.getOtolaryngoRhinology_start());
        preparedStatement.setString(12, surgery.getOtolaryngoRhinology_end());
        preparedStatement.setString(13, surgery.getPaediatricSurgery_start());
        preparedStatement.setString(14, surgery.getPaediatricSurgery_end());
        preparedStatement.setString(15, surgery.getUrology_start());
        preparedStatement.setString(16, surgery.getUrology_end());
        preparedStatement.setString(17, surgery.getRadiology_start());
        preparedStatement.setString(18, surgery.getRadiology_end());
        preparedStatement.setInt(19, id);

        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public int getAllPlacement(int doctorId) throws SQLException, ClassNotFoundException {

        int mVal = 0, sVal = 0, gVal = 0;

        String queryMedicine = "SELECT COUNT(*) FROM " + Const.MEDICINE_TABLE + " WHERE " + Const.MEDICINE_ID + " = ?";
        PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(queryMedicine);
        preparedStatement1.setInt(1, doctorId);
        ResultSet medicineValue = preparedStatement1.executeQuery();

        if (medicineValue.next()) {
            mVal = medicineValue.getInt(1);
        }

        String querySurgery = "SELECT COUNT(*) FROM " + Const.SURGERY_TABLE + " WHERE " + Const.SURGERY_ID + " = ?";
        PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(querySurgery);
        preparedStatement2.setInt(1, doctorId);
        ResultSet surgeryValue = preparedStatement2.executeQuery();
        if (surgeryValue.next()) {
            sVal = surgeryValue.getInt(1);
        }

        String queryGynae = "SELECT COUNT(*) FROM " + Const.GYNAE_OBS_TABLE + " WHERE " + Const.GYNAE_OBS_ID + " = ?";
        PreparedStatement preparedStatement3 = getDbConnection().prepareStatement(queryGynae);
        preparedStatement3.setInt(1, doctorId);
        ResultSet gynaeValue = preparedStatement3.executeQuery();

        if (gynaeValue.next()) {
            gVal = gynaeValue.getInt(1);
        }

        if (mVal == 1 && sVal == 1 && gVal == 1) {
            return 1;
        }

        return 0;
    }

    public ResultSet getMedicinePlacement(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.MEDICINE_TABLE + " WHERE " + Const.MEDICINE_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getMedicinePlacement() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.MEDICINE_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getSurgeryPlacement(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.SURGERY_TABLE + " WHERE " + Const.SURGERY_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getSurgeryPlacement() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.SURGERY_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getGynaePlacement(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.GYNAE_OBS_TABLE + " WHERE " + Const.GYNAE_OBS_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getGynaePlacement() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.GYNAE_OBS_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public int getDoctorById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.DOCTOR_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        int value = 0;
        if (result.next()) {
            value = result.getInt(1);
        }
        System.out.println("Value = "+value);
        return value;
    }

    public ResultSet checkDoctorById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.DOCTOR_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);

        return preparedStatement.executeQuery();
    }

    public ResultSet getDoctor(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.DOCTOR_ID + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    public int getMedicineById(int id) throws SQLException, ClassNotFoundException {
        int value = 0;

        String query = "SELECT COUNT(*) FROM " + Const.MEDICINE_TABLE + " WHERE " + Const.MEDICINE_ID + " = ? ";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            value = resultSet.getInt(1);
        }
        return value;
    }

    public int getSurgeryById(int id) throws SQLException, ClassNotFoundException {
        int value = 0;

        String query = "SELECT COUNT(*) FROM " + Const.SURGERY_TABLE + " WHERE " + Const.SURGERY_ID + " = ? ";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            value = resultSet.getInt(1);
        }
        return value;
    }

    public int getGynaeById(int id) throws SQLException, ClassNotFoundException {
        int value = 0;

        String query = "SELECT COUNT(*) FROM " + Const.GYNAE_OBS_TABLE + " WHERE " + Const.GYNAE_OBS_ID + " = ? ";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            value = resultSet.getInt(1);
        }
        return value;
    }

    public int getNumberOfDoctor() throws SQLException, ClassNotFoundException {

        String query = "SELECT COUNT(*) FROM " + Const.DOCTOR_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt(1);
        }
        return value;
    }

    public int getNumberOfSession() throws SQLException, ClassNotFoundException {
        String query = " SELECT COUNT(DISTINCT "+Const.SESSION+") FROM "+Const.DOCTOR_TABLE;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt(1);
        }
        return value;
    }

    public void removeSession(String session) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.DOCTOR_TABLE + " WHERE " + Const.SESSION + " = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, session);
        preparedStatement.execute();
        preparedStatement.close();

    }


}
