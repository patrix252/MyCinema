/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Francesco
 */
public class Spettacolo {
    private int id_spettacolo;
    private int id_film;
    private int id_sala;
    private Date data;
    private Time ora;

    /**
     * @return the id_spettacolo
     */
    public int getId_spettacolo() {
        return id_spettacolo;
    }

    /**
     * @param id_spettacolo the id_spettacolo to set
     */
    public void setId_spettacolo(int id_spettacolo) {
        this.id_spettacolo = id_spettacolo;
    }

    /**
     * @return the id_film
     */
    public int getId_film() {
        return id_film;
    }

    /**
     * @param id_film the id_film to set
     */
    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    /**
     * @return the id_sala
     */
    public int getId_sala() {
        return id_sala;
    }

    /**
     * @param id_sala the id_sala to set
     */
    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the ora
     */
    public Time getOra() {
        return ora;
    }

    /**
     * @param ora the ora to set
     */
    public void setOra(Time ora) {
        this.ora = ora;
    }
}
