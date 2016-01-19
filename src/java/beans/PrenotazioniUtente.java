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
 * @author Paolo
 */
public class PrenotazioniUtente {
    private String titoloFilm;
    private int id_prenotazione;
    private int riga_posto;
    private int colonna_posto;
    private int id_sala;
    private int prezzo;
    private Date data_spettacolo;
    private Time ora_spettacolo;
    private Date data_prenotazione;
    private Time ora_prenotazione;

    /**
     * @return the titoloFilm
     */
    public String getTitoloFilm() {
        return titoloFilm;
    }

    /**
     * @param titoloFilm the titoloFilm to set
     */
    public void setTitoloFilm(String titoloFilm) {
        this.titoloFilm = titoloFilm;
    }

    /**
     * @return the id_prenotazione
     */
    public int getId_prenotazione() {
        return id_prenotazione;
    }

    /**
     * @param id_prenotazione the id_prenotazione to set
     */
    public void setId_prenotazione(int id_prenotazione) {
        this.id_prenotazione = id_prenotazione;
    }

    /**
     * @return the riga_posto
     */
    public int getRiga_posto() {
        return riga_posto;
    }

    /**
     * @param riga_posto the riga_posto to set
     */
    public void setRiga_posto(int riga_posto) {
        this.riga_posto = riga_posto;
    }

    /**
     * @return the colonna_posto
     */
    public int getColonna_posto() {
        return colonna_posto;
    }

    /**
     * @param colonna_posto the colonna_posto to set
     */
    public void setColonna_posto(int colonna_posto) {
        this.colonna_posto = colonna_posto;
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
     * @return the prezzo
     */
    public int getPrezzo() {
        return prezzo;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * @return the data_spettacolo
     */
    public Date getData_spettacolo() {
        return data_spettacolo;
    }

    /**
     * @param data_spettacolo the data_spettacolo to set
     */
    public void setData_spettacolo(Date data_spettacolo) {
        this.data_spettacolo = data_spettacolo;
    }

    /**
     * @return the ora_spettacolo
     */
    public Time getOra_spettacolo() {
        return ora_spettacolo;
    }

    /**
     * @param ora_spettacolo the ora_spettacolo to set
     */
    public void setOra_spettacolo(Time ora_spettacolo) {
        this.ora_spettacolo = ora_spettacolo;
    }

    /**
     * @return the data_prenotazione
     */
    public Date getData_prenotazione() {
        return data_prenotazione;
    }

    /**
     * @param data_prenotazione the data_prenotazione to set
     */
    public void setData_prenotazione(Date data_prenotazione) {
        this.data_prenotazione = data_prenotazione;
    }

    /**
     * @return the ora_prenotazione
     */
    public Time getOra_prenotazione() {
        return ora_prenotazione;
    }

    /**
     * @param ora_prenotazione the ora_prenotazione to set
     */
    public void setOra_prenotazione(Time ora_prenotazione) {
        this.ora_prenotazione = ora_prenotazione;
    }
}
