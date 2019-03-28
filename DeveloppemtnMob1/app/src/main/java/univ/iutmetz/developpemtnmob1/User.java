package univ.iutmetz.developpemtnmob1;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String mail;
    private String mdp;
    private int droit;

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public User(int id, String mail, String mdp, int droit) {
        this.id = id;
        this.mail = mail;
        this.mdp = mdp;
        this.droit = droit;
    }

    @Override
    public String toString() {
        return  mail +" "+"vous avez créé un compte";
    }

    public int isDroit() {
        return droit;
    }

    public void setDroit(int droit) {
        this.droit = droit;
    }

    public User(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }



    public int compare(User u) {
        if(this.mail.equals(u.getMail())&&this.mdp.equals(u.getMdp())){
            if(u.droit==1){
                return 1;
            }
            else{
                return 2;
            }
        }
        else{
            return 0;
        }
    }


}



