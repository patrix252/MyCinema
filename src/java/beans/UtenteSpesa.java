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
public class UtenteSpesa {
    private Utente utente =new Utente();
    private Double spesa;

    /**
     * @return the utente
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * @param utente the utente to set
     */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * @return the spesa
     */
    public Double getSpesa() {
        return spesa;
    }

    /**
     * @param spesa the spesa to set
     */
    public void setSpesa(Double spesa) {
        this.spesa = spesa;
    }
    
    
}
