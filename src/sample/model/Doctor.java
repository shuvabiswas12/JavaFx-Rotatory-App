package sample.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Doctor extends RecursiveTreeObject<Doctor> {

    private StringProperty name;
    private StringProperty session;
    private StringProperty finalProf;
    private IntegerProperty id;

    public Doctor() {

    }

    public Doctor(String name, String session, String finalProf, int id) {
        this.name = new SimpleStringProperty(name);
        this.session = new SimpleStringProperty(session);
        this.finalProf = new SimpleStringProperty(finalProf);
        this.id = new SimpleIntegerProperty(id);

    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSession() {
        return session.get();
    }

    public StringProperty sessionProperty() {
        return session;
    }

    public void setSession(String session) {
        this.session.set(session);
    }

    public String getFinalProf() {
        return finalProf.get();
    }

    public StringProperty finalProfProperty() {
        return finalProf;
    }

    public void setFinalProf(String finalProf) {
        this.finalProf.set(finalProf);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
