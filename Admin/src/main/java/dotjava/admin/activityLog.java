package dotjava.admin;

import com.sun.source.doctree.ReferenceTree;

public class activityLog {

    private int idAct;
    private int idUser;
    private String date;
    private String time;
    private String username;
    private String typeAct;

    // Getters (already present)
    public int getIdAct() {
        return idAct;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public String getTypeAct() {
        return typeAct;
    }

    public String getTime() {
        return time;
    }

    public int getIdUser() {
        return idUser;
    }

    // Setters (added)
    public void setIdAct(int idAct) {
        this.idAct = idAct;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTypeAct(String typeAct) {
        this.typeAct = typeAct;
    }
}
