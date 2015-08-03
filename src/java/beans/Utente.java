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
public class Utente {
    private String id_utente;
    private String email;
    private String password;
    private double credito;
    private int ruolo;

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the credito
     */
    public double getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(double credito) {
        this.credito = credito;
    }

    /**
     * @return the ruolo
     */
    public int getRuolo() {
        return ruolo;
    }

    /**
     * @param ruolo the ruolo to set
     */
    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }
    
    
}
