package sample.model;

public class Surgery {

    private int surgery_id;

    private String generalSurgery_start_1;
    private String generalSurgery_end_1;
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

    private String generalSurgery_start_2;
    private String generalSurgery_end_2;

    public Surgery() {

    }

    public Surgery(int surgery_id, String generalSurgery_start_1, String generalSurgery_end_1, String orthoCasialty_start, String orthoCasialty_end, String anaesthetia_start, String anaesthetia_end, String opthalmalogy_start, String opthalmalogy_end, String otolaryngoRhinology_start, String otolaryngoRhinology_end, String paediatricSurgery_start, String paediatricSurgery_end, String urology_start, String urology_end, String radiology_start, String radiology_end) {
        this.surgery_id = surgery_id;
        this.generalSurgery_start_1 = generalSurgery_start_1;
        this.generalSurgery_end_1 = generalSurgery_end_1;
        this.orthoCasialty_start = orthoCasialty_start;
        this.orthoCasialty_end = orthoCasialty_end;
        this.anaesthetia_start = anaesthetia_start;
        this.anaesthetia_end = anaesthetia_end;
        this.opthalmalogy_start = opthalmalogy_start;
        this.opthalmalogy_end = opthalmalogy_end;
        this.otolaryngoRhinology_start = otolaryngoRhinology_start;
        this.otolaryngoRhinology_end = otolaryngoRhinology_end;
        this.paediatricSurgery_start = paediatricSurgery_start;
        this.paediatricSurgery_end = paediatricSurgery_end;
        this.urology_start = urology_start;
        this.urology_end = urology_end;
        this.radiology_start = radiology_start;
        this.radiology_end = radiology_end;
    }

    public Surgery(int surgery_id, String generalSurgery_start_1, String generalSurgery_end_1, String orthoCasialty_start, String orthoCasialty_end, String anaesthetia_start, String anaesthetia_end, String opthalmalogy_start, String opthalmalogy_end, String otolaryngoRhinology_start, String otolaryngoRhinology_end, String paediatricSurgery_start, String paediatricSurgery_end, String urology_start, String urology_end, String radiology_start, String radiology_end, String generalSurgery_start_2, String generalSurgery_end_2) {
        this(surgery_id, generalSurgery_start_1, generalSurgery_end_1, orthoCasialty_start, orthoCasialty_end, anaesthetia_start, anaesthetia_end, opthalmalogy_start, opthalmalogy_end, otolaryngoRhinology_start, otolaryngoRhinology_end, paediatricSurgery_start, paediatricSurgery_end, urology_start, urology_end, radiology_start, radiology_end);
        this.generalSurgery_start_2 = generalSurgery_start_2;
        this.generalSurgery_end_2 = generalSurgery_end_2;
    }

    public int getSurgery_id() {
        return surgery_id;
    }

    public void setSurgery_id(int surgery_id) {
        this.surgery_id = surgery_id;
    }

    public String getGeneralSurgery_start_1() {
        return generalSurgery_start_1;
    }

    public void setGeneralSurgery_start_1(String generalSurgery_start_1) {
        this.generalSurgery_start_1 = generalSurgery_start_1;
    }

    public String getGeneralSurgery_end_1() {
        return generalSurgery_end_1;
    }

    public void setGeneralSurgery_end_1(String generalSurgery_end_1) {
        this.generalSurgery_end_1 = generalSurgery_end_1;
    }

    public String getOrthoCasialty_start() {
        return orthoCasialty_start;
    }

    public void setOrthoCasialty_start(String orthoCasialty_start) {
        this.orthoCasialty_start = orthoCasialty_start;
    }

    public String getOrthoCasialty_end() {
        return orthoCasialty_end;
    }

    public void setOrthoCasialty_end(String orthoCasialty_end) {
        this.orthoCasialty_end = orthoCasialty_end;
    }

    public String getAnaesthetia_start() {
        return anaesthetia_start;
    }

    public void setAnaesthetia_start(String anaesthetia_start) {
        this.anaesthetia_start = anaesthetia_start;
    }

    public String getAnaesthetia_end() {
        return anaesthetia_end;
    }

    public void setAnaesthetia_end(String anaesthetia_end) {
        this.anaesthetia_end = anaesthetia_end;
    }

    public String getOpthalmalogy_start() {
        return opthalmalogy_start;
    }

    public void setOpthalmalogy_start(String opthalmalogy_start) {
        this.opthalmalogy_start = opthalmalogy_start;
    }

    public String getOpthalmalogy_end() {
        return opthalmalogy_end;
    }

    public void setOpthalmalogy_end(String opthalmalogy_end) {
        this.opthalmalogy_end = opthalmalogy_end;
    }

    public String getOtolaryngoRhinology_start() {
        return otolaryngoRhinology_start;
    }

    public void setOtolaryngoRhinology_start(String otolaryngoRhinology_start) {
        this.otolaryngoRhinology_start = otolaryngoRhinology_start;
    }

    public String getOtolaryngoRhinology_end() {
        return otolaryngoRhinology_end;
    }

    public void setOtolaryngoRhinology_end(String otolaryngoRhinology_end) {
        this.otolaryngoRhinology_end = otolaryngoRhinology_end;
    }

    public String getPaediatricSurgery_start() {
        return paediatricSurgery_start;
    }

    public void setPaediatricSurgery_start(String paediatricSurgery_start) {
        this.paediatricSurgery_start = paediatricSurgery_start;
    }

    public String getPaediatricSurgery_end() {
        return paediatricSurgery_end;
    }

    public void setPaediatricSurgery_end(String paediatricSurgery_end) {
        this.paediatricSurgery_end = paediatricSurgery_end;
    }

    public String getUrology_start() {
        return urology_start;
    }

    public void setUrology_start(String urology_start) {
        this.urology_start = urology_start;
    }

    public String getUrology_end() {
        return urology_end;
    }

    public void setUrology_end(String urology_end) {
        this.urology_end = urology_end;
    }

    public String getRadiology_start() {
        return radiology_start;
    }

    public void setRadiology_start(String radiology_start) {
        this.radiology_start = radiology_start;
    }

    public String getRadiology_end() {
        return radiology_end;
    }

    public void setRadiology_end(String radiology_end) {
        this.radiology_end = radiology_end;
    }

    public String getGeneralSurgery_start_2() {
        return generalSurgery_start_2;
    }

    public void setGeneralSurgery_start_2(String generalSurgery_start_2) {
        this.generalSurgery_start_2 = generalSurgery_start_2;
    }

    public String getGeneralSurgery_end_2() {
        return generalSurgery_end_2;
    }

    public void setGeneralSurgery_end_2(String generalSurgery_end_2) {
        this.generalSurgery_end_2 = generalSurgery_end_2;
    }
}
