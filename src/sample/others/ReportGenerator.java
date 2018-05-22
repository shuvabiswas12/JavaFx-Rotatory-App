package sample.others;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

public class ReportGenerator {

    public void sheetProgram(Doctor printDoctor, DatabaseHandler databaseHandler, String path) throws IOException, SQLException, ClassNotFoundException {

        Medicine printmedicine = null;
        Surgery printSurgery = null;
        GynaeObs printGynaeObs = null;

        int placementValue = databaseHandler.getAllPlacement(printDoctor.getId());
        System.out.println("Placement Value = " + placementValue);


        if (placementValue == 1) {

            System.out.println("Inserted");

            ResultSet printResultSetMedicine = databaseHandler.getMedicinePlacement(printDoctor.getId());

            while (printResultSetMedicine.next()) {
                printmedicine = new Medicine(printResultSetMedicine.getString(Const.INTERNAL_MEDICINE_START_1), printResultSetMedicine.getString(Const.INTERNAL_MEDICINE_START_2), printResultSetMedicine.getString(Const.PAEDIATRICS_START), printResultSetMedicine.getString(Const.SKIN_VD_START), printResultSetMedicine.getString(Const.PSYCHIATRY_START), printResultSetMedicine.getString(Const.CARDIOLOGY_START), printResultSetMedicine.getString(Const.GASTROENTEROLOGY_START), printResultSetMedicine.getString(Const.NEPHROLOGY_START), printResultSetMedicine.getString(Const.NEUROMEDICINE_START),
                        printResultSetMedicine.getString(Const.INTERNAL_MEDICINE_END_1), printResultSetMedicine.getString(Const.INTERNAL_MEDICINE_END_2), printResultSetMedicine.getString(Const.PAEDIATRICS_END), printResultSetMedicine.getString(Const.SKIN_VD_END), printResultSetMedicine.getString(Const.PSYCHIATRY_END),
                        printResultSetMedicine.getString(Const.CARDIOLOGY_END), printResultSetMedicine.getString(Const.GASTROENTEROLOGY_END), printResultSetMedicine.getString(Const.NEPHROLOGY_END), printResultSetMedicine.getString(Const.NEUROMEDICINE_END),
                        printDoctor.getId());

            }

            ResultSet printResultSetSurgery = databaseHandler.getSurgeryPlacement(printDoctor.getId());

            while (printResultSetSurgery.next()) {
                printSurgery = new Surgery(printDoctor.getId(), printResultSetSurgery.getString(Const.GENERAL_SURGERY_START_1), printResultSetSurgery.getString(Const.GENERAL_SURGERY_END_1), printResultSetSurgery.getString(Const.ORTHOCASILTY_START), printResultSetSurgery.getString(Const.ORTHOCASILTY_END),
                        printResultSetSurgery.getString(Const.anaesthetia_start), printResultSetSurgery.getString(Const.anaesthetia_end), printResultSetSurgery.getString(Const.opthalmalogy_start), printResultSetSurgery.getString(Const.opthalmalogy_end), printResultSetSurgery.getString(Const.otolaryngoRhinology_start),
                        printResultSetSurgery.getString(Const.otolaryngoRhinology_end), printResultSetSurgery.getString(Const.paediatricSurgery_start), printResultSetSurgery.getString(Const.paediatricSurgery_end), printResultSetSurgery.getString(Const.urology_start), printResultSetSurgery.getString(Const.urology_end),
                        printResultSetSurgery.getString(Const.radiology_start), printResultSetSurgery.getString(Const.radiology_end), printResultSetSurgery.getString(Const.GENERAL_SURGERY_START_2), printResultSetSurgery.getString(Const.GENERAL_SURGERY_END_2));
            }

            ResultSet printResultSetGynae = databaseHandler.getGynaePlacement(printDoctor.getId());

            while (printResultSetGynae.next()) {
                printGynaeObs = new GynaeObs(printResultSetGynae.getString(Const.INDOOR_START_1), printResultSetGynae.getString(Const.INDOOR_END_1), printResultSetGynae.getString(Const.INDOOR_START_2),
                        printResultSetGynae.getString(Const.INDOOR_END_2), printResultSetGynae.getString(Const.FAMILY_PLANNING_START), printResultSetGynae.getString(Const.FAMILY_PLANNING_END),
                        printResultSetGynae.getString(Const.OBSTERICS_EMERGENCY_START), printResultSetGynae.getString(Const.OBSTERICS_EMERGENCY_END), printResultSetGynae.getString(Const.COMMUNITY_START),
                        printResultSetGynae.getString(Const.COMMUNITY_END), printDoctor.getId());
            }

            //FileInputStream fileInputStream = new FileInputStream(new File("C:\\report\\rotatory_Report.xls"));
            FileInputStream fileInputStream = new FileInputStream(new File("rotatory_Report.xls"));

            Workbook workbook = new HSSFWorkbook(fileInputStream); // creating workbook with .xls file
            Sheet sheet = workbook.getSheetAt(0);


            // id and date row ----->>
            Row id_date_row = sheet.getRow(5);

            Cell idCell = id_date_row.getCell(3);

            Cell dateCell = id_date_row.getCell(16);


            // doctor info row (name, finalProf) ------>>
            Row doctor_info_row = sheet.getRow(7);

            Cell nameCell = doctor_info_row.getCell(1);

            Cell finalProfCell = doctor_info_row.getCell(3);


            // doctor info row (session date) ------>>
            Row doctor_info_row_2 = sheet.getRow(8);

            Cell dateCell2 = doctor_info_row_2.getCell(5);


            // medicine row 1 ----->>
            Row medicine_row_1 = sheet.getRow(16);

            Cell IMCellStart1 = medicine_row_1.getCell(0);


            Cell IMCellEnd1 = medicine_row_1.getCell(2);


            Cell paediatricsCellStart = medicine_row_1.getCell(3);


            Cell skin_VD_CellStart = medicine_row_1.getCell(5);


            Cell psychiatryCellStart = medicine_row_1.getCell(6);


            Cell cardiologyCellStart = medicine_row_1.getCell(8);


            Cell gastroenterologyCellStart = medicine_row_1.getCell(10);


            Cell nephrologyCellStart = medicine_row_1.getCell(12);

            Cell neuromedicineCellStart = medicine_row_1.getCell(14);


            // medicine row 2 ----->>
            Row medicine_row_2 = sheet.getRow(17);

            Cell IMCellStart2 = medicine_row_2.getCell(0);


            Cell IMCellEnd2 = medicine_row_2.getCell(2);


            Cell paediatricsCellEnd = medicine_row_2.getCell(3);


            Cell skin_VD_CellEnd = medicine_row_2.getCell(5);


            Cell psychiatryCellEnd = medicine_row_2.getCell(6);


            Cell cardiologyCellEnd = medicine_row_2.getCell(8);


            Cell gastroenterologyCellEnd = medicine_row_2.getCell(10);


            Cell nephrologyCellEnd = medicine_row_2.getCell(12);


            Cell neuromedicineCellEnd = medicine_row_2.getCell(14);


            // surgery row 1 ----->>
            Row surgery_row_1 = sheet.getRow(24);

            Cell generalSurgeryCellStart1 = surgery_row_1.getCell(0);

            Cell generalSurgeryCellEnd1 = surgery_row_1.getCell(2);


            Cell orthoCasualtyCellStart = surgery_row_1.getCell(3);

            Cell anaesthetiaCellStart = surgery_row_1.getCell(5);


            Cell opthalmologyCellStart = surgery_row_1.getCell(6);


            Cell otolaryngoRhinologyCellStart = surgery_row_1.getCell(8);


            Cell pediatricSurgeryCellStart = surgery_row_1.getCell(10);

            Cell urologyCellStart = surgery_row_1.getCell(12);

            Cell radiologyCellStart = surgery_row_1.getCell(14);

            // surgery row 2 ----->>
            Row surgery_row_2 = sheet.getRow(25);

            Cell generalSurgeryCellStart2 = surgery_row_2.getCell(0);

            Cell generalSurgeryCellEnd2 = surgery_row_2.getCell(2);

            Cell orthoCasualtyCellEnd = surgery_row_2.getCell(3);

            Cell anaesthetiaCellEnd = surgery_row_2.getCell(5);

            Cell opthalmologyCellEnd = surgery_row_2.getCell(6);

            Cell otolaryngoRhinologyCellEnd = surgery_row_2.getCell(8);

            Cell pediatricSurgeryCellEnd = surgery_row_2.getCell(10);

            Cell urologyCellEnd = surgery_row_2.getCell(12);

            Cell radiologyCellEnd = surgery_row_2.getCell(14);


            // Gynae & Obs row 1 ----->>
            Row gynae_row_1 = sheet.getRow(31);

            Cell indoorCellStart1 = gynae_row_1.getCell(0);
            Cell indoorCellEnd1 = gynae_row_1.getCell(2);
            Cell familyPlanninigCellStart = gynae_row_1.getCell(3);
            Cell obstetricsEmergencyCellStart = gynae_row_1.getCell(5);
            Cell communityCellStart = gynae_row_1.getCell(7);


            // Gynae & Obs row 2 ----->>
            Row gynae_row_2 = sheet.getRow(32);

            Cell indoorCellStart2 = gynae_row_2.getCell(0);
            Cell indoorCellEnd2 = gynae_row_2.getCell(2);
            Cell familyPlanninigCellEnd = gynae_row_2.getCell(3);
            Cell obstetricsEmergencyCellEnd = gynae_row_2.getCell(5);
            Cell communityCellEnd = gynae_row_2.getCell(7);


            // doctor info to excel
            idCell.setCellValue(printDoctor.getId());
            nameCell.setCellValue(printDoctor.getName());
            dateCell.setCellValue(printDoctor.getSession());
            finalProfCell.setCellValue(printDoctor.getFinalProf());
            dateCell2.setCellValue(printDoctor.getSession());

            // medicine to excel
            IMCellStart1.setCellValue(printmedicine.getInternalMedicine_Start1());
            IMCellEnd1.setCellValue(printmedicine.getInternalMedicine_End1());
            IMCellStart2.setCellValue(printmedicine.getInternalMedicine_Start2());
            IMCellEnd2.setCellValue(printmedicine.getInternalMedicine_End2());
            paediatricsCellStart.setCellValue(printmedicine.getPaediatrics_Start());
            paediatricsCellEnd.setCellValue(printmedicine.getPaediatrics_End());
            skin_VD_CellStart.setCellValue(printmedicine.getSkin_VD_Start());
            skin_VD_CellEnd.setCellValue(printmedicine.getSkin_VD_End());
            psychiatryCellStart.setCellValue(printmedicine.getPsychiatry_Start());
            psychiatryCellEnd.setCellValue(printmedicine.getPsychiatry_End());
            cardiologyCellStart.setCellValue(printmedicine.getCardiology_Start());
            cardiologyCellEnd.setCellValue(printmedicine.getCardiology_End());
            gastroenterologyCellStart.setCellValue(printmedicine.getGastroenterology_Start());
            gastroenterologyCellEnd.setCellValue(printmedicine.getGastroenterology_End());
            nephrologyCellStart.setCellValue(printmedicine.getNephrology_Start());
            nephrologyCellEnd.setCellValue(printmedicine.getNephrology_End());
            neuromedicineCellStart.setCellValue(printmedicine.getNeuromedicine_Start());
            neuromedicineCellEnd.setCellValue(printmedicine.getNeuromedicine_End());

            // surgery to excel
            generalSurgeryCellStart1.setCellValue(printSurgery.getGeneralSurgery_start_1());
            generalSurgeryCellEnd1.setCellValue(printSurgery.getGeneralSurgery_end_1());
            generalSurgeryCellStart2.setCellValue(printSurgery.getGeneralSurgery_start_2());
            generalSurgeryCellEnd2.setCellValue(printSurgery.getGeneralSurgery_end_2());
            orthoCasualtyCellStart.setCellValue(printSurgery.getOrthoCasialty_start());
            orthoCasualtyCellEnd.setCellValue(printSurgery.getOrthoCasialty_end());
            anaesthetiaCellStart.setCellValue(printSurgery.getAnaesthetia_start());
            anaesthetiaCellEnd.setCellValue(printSurgery.getAnaesthetia_end());
            opthalmologyCellStart.setCellValue(printSurgery.getOpthalmalogy_start());
            opthalmologyCellEnd.setCellValue(printSurgery.getOpthalmalogy_end());
            otolaryngoRhinologyCellStart.setCellValue(printSurgery.getOtolaryngoRhinology_start());
            otolaryngoRhinologyCellEnd.setCellValue(printSurgery.getOtolaryngoRhinology_end());

            pediatricSurgeryCellStart.setCellValue(printSurgery.getPaediatricSurgery_start());
            pediatricSurgeryCellEnd.setCellValue(printSurgery.getPaediatricSurgery_end());

            urologyCellStart.setCellValue(printSurgery.getUrology_start());
            urologyCellEnd.setCellValue(printSurgery.getUrology_end());
            radiologyCellStart.setCellValue(printSurgery.getRadiology_start());
            radiologyCellEnd.setCellValue(printSurgery.getRadiology_end());


            // Gynae_&_Obs to excel
            indoorCellStart1.setCellValue(printGynaeObs.getIndoorStart1());
            indoorCellEnd1.setCellValue(printGynaeObs.getIndoorEnd1());
            indoorCellStart2.setCellValue(printGynaeObs.getIndoorStart2());
            indoorCellEnd2.setCellValue(printGynaeObs.getIndoorEnd2());
            familyPlanninigCellStart.setCellValue(printGynaeObs.getFamilyPlanningStart());
            familyPlanninigCellEnd.setCellValue(printGynaeObs.getFamilyPlanningEnd());
            obstetricsEmergencyCellStart.setCellValue(printGynaeObs.getObstericsEmergencyStart());
            obstetricsEmergencyCellEnd.setCellValue(printGynaeObs.getObstericsEmergencyEnd());
            communityCellStart.setCellValue(printGynaeObs.getCommunityStart());
            communityCellEnd.setCellValue(printGynaeObs.getCommunityEnd());


            // file input stream close here
            fileInputStream.close();


            // this is for output file
            FileOutputStream outSheet = new FileOutputStream(path + "\\" + printDoctor.getId() + "-" + printDoctor.getName() + ".xls");
            workbook.write(outSheet);
            workbook.close(); // here is closed the workbook

        } else {
            // txt file generate in here ...

        }


    }
}
