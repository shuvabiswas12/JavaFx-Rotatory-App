package sample.others;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Doctor;
import sample.model.GynaeObs;
import sample.model.Medicine;
import sample.model.Surgery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordsBackup {

    /**
     * Creating the workbook ------------------
     */
    private Workbook output_workbook;
    private Workbook import_workbook;

    /**
     * Creating sheet in an xls file -------------------
     */
    private Sheet doctorSheet;
    private Sheet medicineSheet;
    private Sheet surgerySheet;
    private Sheet gynaeSheet;


    private ResultSet resultSet;

    public RecordsBackup() {
        output_workbook = new HSSFWorkbook();
    }


    public void createDoctorSheet() {
        doctorSheet = output_workbook.createSheet("doctor");
        Row header = doctorSheet.createRow(0);

        Cell name = header.createCell(0);
        name.setCellValue("Name");

        Cell id = header.createCell(1);
        id.setCellValue("Id");

        Cell session = header.createCell(2);
        session.setCellValue("Session");

        Cell final_prof = header.createCell(3);
        final_prof.setCellValue("Final Prof");
    }

    public void createMedecineSheet() {

        medicineSheet = output_workbook.createSheet("medicine");
        Row header = medicineSheet.createRow(0);

        Cell medicine_id = header.createCell(0);
        medicine_id.setCellValue(Const.MEDICINE_ID);

        Cell internal_medicine_start_1 = header.createCell(1);
        internal_medicine_start_1.setCellValue(Const.INTERNAL_MEDICINE_START_1);

        Cell internal_medicine_start_2 = header.createCell(2);
        internal_medicine_start_2.setCellValue(Const.INTERNAL_MEDICINE_START_2);

        Cell paediatrics_start = header.createCell(3);
        paediatrics_start.setCellValue(Const.PAEDIATRICS_START);

        Cell skin_vd_start = header.createCell(4);
        skin_vd_start.setCellValue(Const.SKIN_VD_START);

        Cell psychiatry_start = header.createCell(5);
        psychiatry_start.setCellValue(Const.PSYCHIATRY_START);

        Cell cardiology_start = header.createCell(6);
        cardiology_start.setCellValue(Const.CARDIOLOGY_START);

        Cell gastroenterology_start = header.createCell(7);
        gastroenterology_start.setCellValue(Const.GASTROENTEROLOGY_START);

        Cell nephrology_start = header.createCell(8);
        nephrology_start.setCellValue(Const.NEPHROLOGY_START);

        Cell neuromedicine_start = header.createCell(9);
        neuromedicine_start.setCellValue(Const.NEUROMEDICINE_START);

        Cell internal_medicine_end_1 = header.createCell(10);
        internal_medicine_end_1.setCellValue(Const.INTERNAL_MEDICINE_END_1);

        Cell internal_medicine_end_2 = header.createCell(11);
        internal_medicine_end_2.setCellValue(Const.INTERNAL_MEDICINE_END_2);

        Cell paediatrics_end = header.createCell(12);
        paediatrics_end.setCellValue(Const.PAEDIATRICS_END);

        Cell skin_vd_end = header.createCell(13);
        skin_vd_end.setCellValue(Const.SKIN_VD_END);

        Cell psychiatry_end = header.createCell(14);
        psychiatry_end.setCellValue(Const.PSYCHIATRY_END);

        Cell cardiology_end = header.createCell(15);
        cardiology_end.setCellValue(Const.CARDIOLOGY_END);

        Cell gastroenterology_end = header.createCell(16);
        gastroenterology_end.setCellValue(Const.GASTROENTEROLOGY_END);

        Cell nephrology_end = header.createCell(17);
        nephrology_end.setCellValue(Const.NEPHROLOGY_END);

        Cell neuromedicine_end = header.createCell(18);
        neuromedicine_end.setCellValue(Const.NEUROMEDICINE_END);


    }

    public void createSurgerySheet() {
        surgerySheet = output_workbook.createSheet("surgery");
        Row header = surgerySheet.createRow(0);

        header.createCell(0).setCellValue(Const.SURGERY_ID);
        header.createCell(1).setCellValue(Const.GENERAL_SURGERY_START_1);
        header.createCell(2).setCellValue(Const.GENERAL_SURGERY_END_1);
        header.createCell(3).setCellValue(Const.GENERAL_SURGERY_START_2);
        header.createCell(4).setCellValue(Const.GENERAL_SURGERY_END_2);
        header.createCell(5).setCellValue(Const.ORTHOCASILTY_START);
        header.createCell(6).setCellValue(Const.ORTHOCASILTY_END);
        header.createCell(7).setCellValue(Const.anaesthetia_start);
        header.createCell(8).setCellValue(Const.anaesthetia_end);
        header.createCell(9).setCellValue(Const.opthalmalogy_start);
        header.createCell(10).setCellValue(Const.opthalmalogy_end);
        header.createCell(11).setCellValue(Const.otolaryngoRhinology_start);
        header.createCell(12).setCellValue(Const.otolaryngoRhinology_end);
        header.createCell(13).setCellValue(Const.paediatricSurgery_start);
        header.createCell(14).setCellValue(Const.paediatricSurgery_end);
        header.createCell(15).setCellValue(Const.urology_start);
        header.createCell(16).setCellValue(Const.urology_end);
        header.createCell(17).setCellValue(Const.radiology_start);
        header.createCell(18).setCellValue(Const.radiology_end);
    }

    public void createGynaeSheet() {
        gynaeSheet = output_workbook.createSheet("gynae_Obs");
        Row header = gynaeSheet.createRow(0);

        Cell gynae_id = header.createCell(0);
        gynae_id.setCellValue(Const.GYNAE_OBS_ID);

        Cell indoor_start_1 = header.createCell(1);
        indoor_start_1.setCellValue(Const.INDOOR_START_1);

        Cell indoor_end_1 = header.createCell(2);
        indoor_end_1.setCellValue(Const.INDOOR_END_1);

        Cell indoor_start_2 = header.createCell(3);
        indoor_start_2.setCellValue(Const.INDOOR_START_2);

        Cell indoor_end_2 = header.createCell(4);
        indoor_end_2.setCellValue(Const.INDOOR_END_2);


        Cell family_planning_start = header.createCell(5);
        family_planning_start.setCellValue(Const.FAMILY_PLANNING_START);

        Cell family_planning_end = header.createCell(6);
        family_planning_end.setCellValue(Const.FAMILY_PLANNING_END);

        Cell obsterics_start = header.createCell(7);
        obsterics_start.setCellValue(Const.OBSTERICS_EMERGENCY_START);

        Cell obsterics_end = header.createCell(8);
        obsterics_end.setCellValue(Const.OBSTERICS_EMERGENCY_END);

        Cell community_start = header.createCell(9);
        community_start.setCellValue(Const.COMMUNITY_START);

        Cell community_end = header.createCell(10);
        community_end.setCellValue(Const.COMMUNITY_END);

    }


    // export to excel
    public void createBackup(DatabaseHandler databaseHandler, String path) throws SQLException, ClassNotFoundException, IOException {

        doctorBackup(databaseHandler);
        medicineBackup(databaseHandler);
        surgeryBackup(databaseHandler);
        gynaeBackup(databaseHandler);

        closeWorkbook(path);

    }

    private void doctorBackup(DatabaseHandler databaseHandler) throws SQLException, ClassNotFoundException, IOException {

        resultSet = databaseHandler.getAllDoctor();
        int index = 1;
        while (resultSet.next()) {
            Row row = doctorSheet.createRow(index++);
            row.createCell(0).setCellValue(resultSet.getString(Const.DOCTOR_NAME));
            row.createCell(1).setCellValue(resultSet.getInt(Const.DOCTOR_ID));
            row.createCell(2).setCellValue(resultSet.getString(Const.SESSION));
            row.createCell(3).setCellValue(resultSet.getString(Const.FINAL_PROF));
        }
    }

    private void medicineBackup(DatabaseHandler databaseHandler) throws SQLException, ClassNotFoundException, IOException {

        resultSet = databaseHandler.getMedicinePlacement();
        int index = 1;
        while (resultSet.next()) {
            Row row = medicineSheet.createRow(index++);
            row.createCell(0).setCellValue(resultSet.getInt(Const.MEDICINE_ID));
            row.createCell(1).setCellValue(resultSet.getString(Const.INTERNAL_MEDICINE_START_1));
            row.createCell(2).setCellValue(resultSet.getString(Const.INTERNAL_MEDICINE_START_2));
            row.createCell(3).setCellValue(resultSet.getString(Const.PAEDIATRICS_START));
            row.createCell(4).setCellValue(resultSet.getString(Const.SKIN_VD_START));
            row.createCell(5).setCellValue(resultSet.getString(Const.PSYCHIATRY_START));
            row.createCell(6).setCellValue(resultSet.getString(Const.CARDIOLOGY_START));
            row.createCell(7).setCellValue(resultSet.getString(Const.GASTROENTEROLOGY_START));
            row.createCell(8).setCellValue(resultSet.getString(Const.NEPHROLOGY_START));
            row.createCell(9).setCellValue(resultSet.getString(Const.NEUROMEDICINE_START));

            row.createCell(10).setCellValue(resultSet.getString(Const.INTERNAL_MEDICINE_END_1));
            row.createCell(11).setCellValue(resultSet.getString(Const.INTERNAL_MEDICINE_END_2));
            row.createCell(12).setCellValue(resultSet.getString(Const.PAEDIATRICS_END));
            row.createCell(13).setCellValue(resultSet.getString(Const.SKIN_VD_END));
            row.createCell(14).setCellValue(resultSet.getString(Const.PSYCHIATRY_END));
            row.createCell(15).setCellValue(resultSet.getString(Const.CARDIOLOGY_END));
            row.createCell(16).setCellValue(resultSet.getString(Const.GASTROENTEROLOGY_END));
            row.createCell(17).setCellValue(resultSet.getString(Const.NEPHROLOGY_END));
            row.createCell(18).setCellValue(resultSet.getString(Const.NEUROMEDICINE_END));
        }
    }

    private void surgeryBackup(DatabaseHandler databaseHandler) throws SQLException, ClassNotFoundException, IOException {

        resultSet = databaseHandler.getSurgeryPlacement();
        int index = 1;
        while (resultSet.next()) {
            Row row = surgerySheet.createRow(index++);
            row.createCell(0).setCellValue(resultSet.getInt(Const.SURGERY_ID));
            row.createCell(1).setCellValue(resultSet.getString(Const.GENERAL_SURGERY_START_1));
            row.createCell(2).setCellValue(resultSet.getString(Const.GENERAL_SURGERY_END_1));
            row.createCell(3).setCellValue(resultSet.getString(Const.GENERAL_SURGERY_START_2));
            row.createCell(4).setCellValue(resultSet.getString(Const.GENERAL_SURGERY_END_2));
            row.createCell(5).setCellValue(resultSet.getString(Const.ORTHOCASILTY_START));
            row.createCell(6).setCellValue(resultSet.getString(Const.ORTHOCASILTY_END));
            row.createCell(7).setCellValue(resultSet.getString(Const.anaesthetia_start));
            row.createCell(8).setCellValue(resultSet.getString(Const.anaesthetia_end));
            row.createCell(9).setCellValue(resultSet.getString(Const.opthalmalogy_start));
            row.createCell(10).setCellValue(resultSet.getString(Const.opthalmalogy_end));
            row.createCell(11).setCellValue(resultSet.getString(Const.otolaryngoRhinology_start));
            row.createCell(12).setCellValue(resultSet.getString(Const.otolaryngoRhinology_end));
            row.createCell(13).setCellValue(resultSet.getString(Const.paediatricSurgery_start));
            row.createCell(14).setCellValue(resultSet.getString(Const.paediatricSurgery_end));
            row.createCell(15).setCellValue(resultSet.getString(Const.urology_start));
            row.createCell(16).setCellValue(resultSet.getString(Const.urology_end));
            row.createCell(17).setCellValue(resultSet.getString(Const.radiology_start));
            row.createCell(18).setCellValue(resultSet.getString(Const.radiology_end));
        }


    }

    private void gynaeBackup(DatabaseHandler databaseHandler) throws IOException, SQLException, ClassNotFoundException {

        resultSet = databaseHandler.getGynaePlacement();
        int index = 1;
        while (resultSet.next()) {
            Row row = gynaeSheet.createRow(index++);
            row.createCell(0).setCellValue(resultSet.getInt(Const.GYNAE_OBS_ID));
            row.createCell(1).setCellValue(resultSet.getString(Const.INDOOR_START_1));
            row.createCell(2).setCellValue(resultSet.getString(Const.INDOOR_END_1));
            row.createCell(3).setCellValue(resultSet.getString(Const.INDOOR_START_2));
            row.createCell(4).setCellValue(resultSet.getString(Const.INDOOR_END_2));
            row.createCell(5).setCellValue(resultSet.getString(Const.FAMILY_PLANNING_START));
            row.createCell(6).setCellValue(resultSet.getString(Const.FAMILY_PLANNING_END));
            row.createCell(7).setCellValue(resultSet.getString(Const.OBSTERICS_EMERGENCY_START));
            row.createCell(8).setCellValue(resultSet.getString(Const.OBSTERICS_EMERGENCY_END));
            row.createCell(9).setCellValue(resultSet.getString(Const.COMMUNITY_START));
            row.createCell(10).setCellValue(resultSet.getString(Const.COMMUNITY_END));
        }

    }

    private void closeWorkbook(String path) throws IOException {

        // this is for output file
        FileOutputStream fileOutputStream = new FileOutputStream(path + "\\" + "backup.xls");
        output_workbook.write(fileOutputStream);
        fileOutputStream.close();
        output_workbook.close();  // here is closed the workbook
    }


    // import to database
    public void importToDB(DatabaseHandler databaseHandler, File file) throws IOException, SQLException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        import_workbook = new HSSFWorkbook(fileInputStream);

        importDoctor(databaseHandler);
        importMedicine(databaseHandler);
        importSurgery(databaseHandler);
        importGynae(databaseHandler);

        import_workbook.close();
        fileInputStream.close();

    }

    private void importDoctor(DatabaseHandler databaseHandler) throws SQLException, ClassNotFoundException {
        Sheet sheet = import_workbook.getSheetAt(0);
        Row row;
        Doctor doctor;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);

            Double d = row.getCell(1).getNumericCellValue();
            Integer value = d.intValue();

            doctor = new Doctor(row.getCell(0).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), value);

            int count = databaseHandler.getDoctorById(doctor.getId());
            if (count == 1)
                System.out.println("The doctor has there already !");

            else
                databaseHandler.saveDoctor(doctor);

        }
    }

    private void importMedicine(DatabaseHandler databaseHandler) throws SQLException, ClassNotFoundException {
        Sheet sheet = import_workbook.getSheetAt(1);
        Row row;
        Medicine medicine;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            Double d = row.getCell(0).getNumericCellValue();
            Integer value = d.intValue();

            medicine = new Medicine(row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(),
                    row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue(), row.getCell(6).getStringCellValue(),
                    row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue(),
                    row.getCell(10).getStringCellValue(), row.getCell(11).getStringCellValue(), row.getCell(12).getStringCellValue(),
                    row.getCell(13).getStringCellValue(), row.getCell(14).getStringCellValue(), row.getCell(15).getStringCellValue(),
                    row.getCell(16).getStringCellValue(), row.getCell(17).getStringCellValue(), row.getCell(18).getStringCellValue(), value);

            int count = databaseHandler.getMedicineById(medicine.getDoctorId());
            if (count == 1)
                System.out.println("The doctor has already in there !");

            else
                databaseHandler.medicineToDB(medicine);


        }
    }

    private void importSurgery(DatabaseHandler databaseHandler) throws SQLException, ClassNotFoundException {
        Sheet sheet = import_workbook.getSheetAt(2);
        Row row;
        Surgery surgery;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);

            double d = row.getCell(0).getNumericCellValue();
            int value = (int) d;

            surgery = new Surgery(value, row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(5).getStringCellValue(),
                    row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue(),
                    row.getCell(9).getStringCellValue(), row.getCell(10).getStringCellValue(), row.getCell(11).getStringCellValue(),
                    row.getCell(12).getStringCellValue(), row.getCell(13).getStringCellValue(), row.getCell(14).getStringCellValue(),
                    row.getCell(15).getStringCellValue(), row.getCell(16).getStringCellValue(), row.getCell(17).getStringCellValue(),
                    row.getCell(18).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue());

            int count = databaseHandler.getSurgeryById(surgery.getSurgery_id());
            if (count == 1)
                System.out.println("There has an another doctor !");
            else
                databaseHandler.surgeryToDb(surgery);
        }

    }

    private void importGynae(DatabaseHandler databaseHandler) throws SQLException, ClassNotFoundException {
        Sheet sheet = import_workbook.getSheetAt(3);
        Row row;
        GynaeObs gynaeObs;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            Double d = row.getCell(0).getNumericCellValue();
            Integer value = d.intValue();
            gynaeObs = new GynaeObs(row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(),
                    row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue(), row.getCell(6).getStringCellValue(),
                    row.getCell(8).getStringCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue(), row.getCell(10).getStringCellValue(), value);

            int count = databaseHandler.getGynaeById(gynaeObs.getGynaeObs_id());
            if (count == 1)
                System.out.println("There has an duplicate doctor as this doctor !");
            else
                databaseHandler.gynaeObsToDb(gynaeObs);


        }
    }
}
