package sample.model;

public class Medicine {

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

    private int doctorId;

    public Medicine() {

    }

    public Medicine(String internalMedicine_Start1, String paediatrics_Start, String skin_VD_Start, String psychiatry_Start, String cardiology_Start, String gastroenterology_Start, String nephrology_Start, String neuromedicine_Start, String internalMedicine_End1, String paediatrics_End, String skin_VD_End, String psychiatry_End, String cardiology_End, String gastroenterology_End, String nephrology_End, String neuromedicine_End, int doctorId) {
        this.internalMedicine_Start1 = internalMedicine_Start1;
        this.paediatrics_Start = paediatrics_Start;
        this.skin_VD_Start = skin_VD_Start;
        this.psychiatry_Start = psychiatry_Start;
        this.cardiology_Start = cardiology_Start;
        this.gastroenterology_Start = gastroenterology_Start;
        this.nephrology_Start = nephrology_Start;
        this.neuromedicine_Start = neuromedicine_Start;
        this.internalMedicine_End1 = internalMedicine_End1;
        this.paediatrics_End = paediatrics_End;
        this.skin_VD_End = skin_VD_End;
        this.psychiatry_End = psychiatry_End;
        this.cardiology_End = cardiology_End;
        this.gastroenterology_End = gastroenterology_End;
        this.nephrology_End = nephrology_End;
        this.neuromedicine_End = neuromedicine_End;
        this.doctorId = doctorId;
    }

    public Medicine(String internalMedicine_Start1, String internalMedicine_Start2, String paediatrics_Start, String skin_VD_Start, String psychiatry_Start, String cardiology_Start, String gastroenterology_Start, String nephrology_Start, String neuromedicine_Start, String internalMedicine_End1, String internalMedicine_End2, String paediatrics_End, String skin_VD_End, String psychiatry_End, String cardiology_End, String gastroenterology_End, String nephrology_End, String neuromedicine_End, int doctorId) {
        this(internalMedicine_Start1, paediatrics_Start, skin_VD_Start, psychiatry_Start, cardiology_Start, gastroenterology_Start, nephrology_Start, neuromedicine_Start, internalMedicine_End1, paediatrics_End, skin_VD_End, psychiatry_End, cardiology_End, gastroenterology_End, nephrology_End, neuromedicine_End, doctorId);
        this.internalMedicine_Start2 = internalMedicine_Start2;
        this.internalMedicine_End2 = internalMedicine_End2;

    }

    public String getInternalMedicine_Start1() {
        return internalMedicine_Start1;
    }

    public void setInternalMedicine_Start1(String internalMedicine_Start1) {
        this.internalMedicine_Start1 = internalMedicine_Start1;
    }

    public String getInternalMedicine_Start2() {
        return internalMedicine_Start2;
    }

    public void setInternalMedicine_Start2(String internalMedicine_Start2) {
        this.internalMedicine_Start2 = internalMedicine_Start2;
    }

    public String getPaediatrics_Start() {
        return paediatrics_Start;
    }

    public void setPaediatrics_Start(String paediatrics_Start) {
        this.paediatrics_Start = paediatrics_Start;
    }

    public String getSkin_VD_Start() {
        return skin_VD_Start;
    }

    public void setSkin_VD_Start(String skin_VD_Start) {
        this.skin_VD_Start = skin_VD_Start;
    }

    public String getPsychiatry_Start() {
        return psychiatry_Start;
    }

    public void setPsychiatry_Start(String psychiatry_Start) {
        this.psychiatry_Start = psychiatry_Start;
    }

    public String getCardiology_Start() {
        return cardiology_Start;
    }

    public void setCardiology_Start(String cardiology_Start) {
        this.cardiology_Start = cardiology_Start;
    }

    public String getGastroenterology_Start() {
        return gastroenterology_Start;
    }

    public void setGastroenterology_Start(String gastroenterology_Start) {
        this.gastroenterology_Start = gastroenterology_Start;
    }

    public String getNephrology_Start() {
        return nephrology_Start;
    }

    public void setNephrology_Start(String nephrology_Start) {
        this.nephrology_Start = nephrology_Start;
    }

    public String getNeuromedicine_Start() {
        return neuromedicine_Start;
    }

    public void setNeuromedicine_Start(String neuromedicine_Start) {
        this.neuromedicine_Start = neuromedicine_Start;
    }

    public String getInternalMedicine_End1() {
        return internalMedicine_End1;
    }

    public void setInternalMedicine_End1(String internalMedicine_End1) {
        this.internalMedicine_End1 = internalMedicine_End1;
    }

    public String getInternalMedicine_End2() {
        return internalMedicine_End2;
    }

    public void setInternalMedicine_End2(String internalMedicine_End2) {
        this.internalMedicine_End2 = internalMedicine_End2;
    }

    public String getPaediatrics_End() {
        return paediatrics_End;
    }

    public void setPaediatrics_End(String paediatrics_End) {
        this.paediatrics_End = paediatrics_End;
    }

    public String getSkin_VD_End() {
        return skin_VD_End;
    }

    public void setSkin_VD_End(String skin_VD_End) {
        this.skin_VD_End = skin_VD_End;
    }

    public String getPsychiatry_End() {
        return psychiatry_End;
    }

    public void setPsychiatry_End(String psychiatry_End) {
        this.psychiatry_End = psychiatry_End;
    }

    public String getCardiology_End() {
        return cardiology_End;
    }

    public void setCardiology_End(String cardiology_End) {
        this.cardiology_End = cardiology_End;
    }

    public String getGastroenterology_End() {
        return gastroenterology_End;
    }

    public void setGastroenterology_End(String gastroenterology_End) {
        this.gastroenterology_End = gastroenterology_End;
    }

    public String getNephrology_End() {
        return nephrology_End;
    }

    public void setNephrology_End(String nephrology_End) {
        this.nephrology_End = nephrology_End;
    }

    public String getNeuromedicine_End() {
        return neuromedicine_End;
    }

    public void setNeuromedicine_End(String neuromedicine_End) {
        this.neuromedicine_End = neuromedicine_End;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDoctorId() {
        return doctorId;
    }
}
