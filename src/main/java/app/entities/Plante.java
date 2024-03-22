package app.entities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author Daniel Rouvillain
 */

public class Plante {


    private int planteId;
    private String navn;
    private String placering;

    private int userId;

    private LocalDate dato;

    private int vandTidId;

    public Plante(int planteId, String navn, String placering, int userId) {
        this.planteId = planteId;
        this.navn = navn;
        this.placering = placering;
        this.userId = userId;
    }

    public Plante( int userId,String placering,String navn,LocalDate dato) {
        this.dato=dato;
        this.navn=navn;
        this.planteId=planteId;
    }
    public Plante(int planteId, String navn, String placering, int userId, LocalDate dato) {
        this.planteId = planteId;
        this.navn = navn;
        this.placering = placering;
        this.userId = userId;
        this.dato = dato;
    }

    public int getPlanteId() {
        return planteId;
    }

    public String getNavn() {
        return navn;
    }

    public String getPlacering() {
        return placering;
    }

    public LocalDate getDato(){
        return dato;
    }



    public int getVandTidId() {
        return vandTidId;
    }

    public int getUserId() {
        return userId;
    }

    public void setPlanteId(int planteId) {
        this.planteId = planteId;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setPlacering(String placering) {
        this.placering = placering;
    }



    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Plante{" +
                "planteId=" + planteId +
                ", navn='" + navn + '\'' +
                ", placering='" + placering + '\'' +
                ", userId=" + userId +
                '}';
    }
}
