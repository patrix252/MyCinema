/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Francesco
 */
public class Posto {
    private int id_posto;
    private int id_sala;
    private int riga;
    private int colonna;
    private int esiste;

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
     * @return the riga
     */
    public int getRiga() {
        return riga;
    }

    /**
     * @param riga the riga to set
     */
    public void setRiga(int riga) {
        this.riga = riga;
    }

    /**
     * @return the colonna
     */
    public int getColonna() {
        return colonna;
    }

    /**
     * @param colonna the colonna to set
     */
    public void setColonna(int colonna) {
        this.colonna = colonna;
    }

    /**
     * @return the esiste
     */
    public int getEsiste() {
        return esiste;
    }

    /**
     * @param esiste the esiste to set
     */
    public void setEsiste(int esiste) {
        this.esiste = esiste;
    }
    
    
}
