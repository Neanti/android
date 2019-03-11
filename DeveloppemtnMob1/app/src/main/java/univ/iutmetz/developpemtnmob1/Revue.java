package univ.iutmetz.developpemtnmob1;

import java.io.Serializable;
import java.lang.ref.Reference;

public class Revue implements Serializable {
    private String reference ;
    private String description;
    private String title;
    private float Fee;
    private int Periode;
    private String Visuel;

    public Revue() {

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Revue(String reference, String description, String Visuel ,String title, float fee) {
        this.reference = reference;
        this.Visuel = Visuel;
        this.description = description;
        this.title = title;
        Fee = fee;
    }

    public int getPeriode() {
        return Periode;
    }

    public Revue(int periode) {
        Periode = periode;
    }

    public void setPeriode(int periode) {
        Periode = periode;

    }

    @Override
    public String toString() {
        return  title +" "+"("+(Fee +" euros")+")";
    }

    public float getFee() {
        return Fee;

    }

    public String getVisuel() {
        return Visuel;
    }

    public void setVisuel(String visuel) {
        Visuel = visuel;
    }

    public void setFee(float fee) {
        Fee = fee;
    }

}
