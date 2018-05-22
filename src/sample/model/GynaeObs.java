package sample.model;

public class GynaeObs {

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
    private int gynaeObs_id;

    public GynaeObs() {

    }

    public GynaeObs(String indoorStart1, String indoorEnd1, String familyPlanningStart, String familyPlanningEnd, String obstericsEmergencyStart, String obstericsEmergencyEnd, String communityStart, String communityEnd, int gynaeObs_id) {
        this.indoorStart1 = indoorStart1;
        this.indoorEnd1 = indoorEnd1;
        this.familyPlanningStart = familyPlanningStart;
        this.familyPlanningEnd = familyPlanningEnd;
        this.obstericsEmergencyStart = obstericsEmergencyStart;
        this.obstericsEmergencyEnd = obstericsEmergencyEnd;
        this.communityStart = communityStart;
        this.communityEnd = communityEnd;
        this.gynaeObs_id = gynaeObs_id;
    }

    public GynaeObs(String indoorStart1, String indoorEnd1, String indoorStart2, String indoorEnd2, String familyPlanningStart, String familyPlanningEnd, String obstericsEmergencyStart, String obstericsEmergencyEnd, String communityStart, String communityEnd, int gynaeObs_id) {
        this(indoorStart1, indoorEnd1, familyPlanningStart, familyPlanningEnd, obstericsEmergencyStart, obstericsEmergencyEnd, communityStart, communityEnd, gynaeObs_id);
        this.indoorStart2 = indoorStart2;
        this.indoorEnd2 = indoorEnd2;
    }

    public String getIndoorStart1() {
        return indoorStart1;
    }

    public void setIndoorStart1(String indoorStart1) {
        this.indoorStart1 = indoorStart1;
    }

    public String getIndoorEnd1() {
        return indoorEnd1;
    }

    public void setIndoorEnd1(String indoorEnd1) {
        this.indoorEnd1 = indoorEnd1;
    }

    public String getIndoorStart2() {
        return indoorStart2;
    }

    public void setIndoorStart2(String indoorStart2) {
        this.indoorStart2 = indoorStart2;
    }

    public String getIndoorEnd2() {
        return indoorEnd2;
    }

    public void setIndoorEnd2(String indoorEnd2) {
        this.indoorEnd2 = indoorEnd2;
    }

    public String getFamilyPlanningStart() {
        return familyPlanningStart;
    }

    public void setFamilyPlanningStart(String familyPlanningStart) {
        this.familyPlanningStart = familyPlanningStart;
    }

    public String getFamilyPlanningEnd() {
        return familyPlanningEnd;
    }

    public void setFamilyPlanningEnd(String familyPlanningEnd) {
        this.familyPlanningEnd = familyPlanningEnd;
    }

    public String getObstericsEmergencyStart() {
        return obstericsEmergencyStart;
    }

    public void setObstericsEmergencyStart(String obstericsEmergencyStart) {
        this.obstericsEmergencyStart = obstericsEmergencyStart;
    }

    public String getObstericsEmergencyEnd() {
        return obstericsEmergencyEnd;
    }

    public void setObstericsEmergencyEnd(String obstericsEmergencyEnd) {
        this.obstericsEmergencyEnd = obstericsEmergencyEnd;
    }

    public String getCommunityStart() {
        return communityStart;
    }

    public void setCommunityStart(String communityStart) {
        this.communityStart = communityStart;
    }

    public String getCommunityEnd() {
        return communityEnd;
    }

    public void setCommunityEnd(String communityEnd) {
        this.communityEnd = communityEnd;
    }

    public int getGynaeObs_id() {
        return gynaeObs_id;
    }

    public void setGynaeObs_id(int gynaeObs_id) {
        this.gynaeObs_id = gynaeObs_id;
    }
}
