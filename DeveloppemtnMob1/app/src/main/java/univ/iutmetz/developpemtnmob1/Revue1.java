package univ.iutmetz.developpemtnmob1;

import java.io.Serializable;

public class Revue1 implements Serializable {
    private int id;
    private int dispo;
    private String reference ;
    private String description;
    private String title;
    private double fee;
    private String periode;
    private String visuel;



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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public Revue1(int id, int dispo, String reference, String description, String title, double fee, String periode, String visuel) {
        this.id = id;
        this.dispo = dispo;
        this.reference = reference;
        this.description = description;
        this.title = title;
        this.fee = fee;
        this.periode = periode;
        this.visuel = visuel;
    }

    public String getPeriode() {
        return periode;
    }

    public Revue1(String periode) {
        periode = periode;
    }

    public void setPeriode(String periode) {
        periode = periode;

    }

    @Override
    public String toString() {
        return  title +" "+"("+(fee +" euros")+")";
    }

    public double getFee() {
        return fee;

    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        visuel = visuel;
    }

    public void setFee(double fee) {
        fee = fee;
    }

    public Revue1(String title, String description, String reference, String periode, double fee, String visuel) {
        this.title = title;
        this.description = description;
        this.reference=reference;
        this.periode=periode;
        this.fee=fee;
        this.visuel=visuel;


    }

}