/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Francesco
 */
public class Prenotazione {
    private int id_prenotazione;
    private String id_utente;
    private int id_spettacolo;
    private int id_prezzo;
    private int id_posto;
    //da verificare se è compatibile con il database forse quello stronzo di patrizio vuole modificarlo
    private Date data_ora_operazione;

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
     * @return the id_utente
     */
    public String getId_utente() {
        return id_utente;
    }

    /**
     * @param id_utente the id_utente to set
     */
    public void setId_utente(String id_utente) {
        this.id_utente = id_utente;
    }

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
     * @return the id_prezzo
     */
    public int getId_prezzo() {
        return id_prezzo;
    }

    /**
     * @param id_prezzo the id_prezzo to set
     */
    public void setId_prezzo(int id_prezzo) {
        this.id_prezzo = id_prezzo;
    }

    /**
     * @return the id_posto
     */
    public int getId_posto() {
        return id_posto;
    }

    /**
     * @param id_posto the id_posto to set
     */
    public void setId_posto(int id_posto) {
        this.id_posto = id_posto;
    }

    /**
     * @return the data_ora_operazione
     */
    public Date getData_ora_operazione() {
        return data_ora_operazione;
    }

    /**
     * @param data_ora_operazione the data_ora_operazione to set
     */
    public void setData_ora_operazione(Date data_ora_operazione) {
        this.data_ora_operazione = data_ora_operazione;
    }

    
    
    
}
