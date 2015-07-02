
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Paolo
 */
public class Util {

    public static class Film {

        public static final String TABLE_NAME = "Film";

        public static final String COLUMN_ID_FILM = "id_film";
        public static final String COLUMN_TITOLO = "titolo";
        public static final String COLUMN_ID_GENERE = "id_genere";
        public static final String COLUMN_URL_TRAILER = "url_trailer";
        public static final String COLUMN_DURATA = "durata";
        public static final String COLUMN_TRAMA = "trama";
        public static final String COLUMN_URI_LOCANDINA = "uri_locandina";
        public static final String COLUMN_REGISTA = "regista";
        //mazzo
        //lol
        //PUTTANA LA MADONNA SBOLDRA MALEDETTA
        //Questo commento dovrebbe essere nel ramo Prem
        //Dio asinello bello col cappello a puntello
        //modifica del porco dio diocane lamadonna puttana stronza la baldracca troia del porco dio ladro 
        //succhia cazzi in stazione del porc dio la madonna puttana sboldra
    }
    
    public static class Genere {
        
        public static final String TABLE_NAME = "Genere";
        
        public static final String COLUMN_ID_GENERE = "id_genere";
        public static final String COLUMN_DESCRIZIONE = "descrizione";
        
    }
    
    public static class Posto {
        public static final String COLUMN_ID_POSTO = "id_posto";
        public static final String COLUMN_ID_SALA = "id_sala";
        public static final String COLUMN_RIGA = "riga";
        public static final String COLUMN_COLONNA = "colonna";
        public static final String COLUMN_ESISTE = "esiste";
        
    }
    
    public static class Prenotazione {
        public static final String COLUMN_ID_PRENOTAZIONE = "id_prenotazione";
        public static final String COLUMN_ID_UTENTE = "id_utente";
        public static final String COLUMN_ID_SPETTACOLO = "id_spettacolo";
        public static final String COLUMN_ID_PREZZO = "id_prezzo";
        public static final String COLUMN_ID_POSTO = "id_posto";
        public static final String COLUMN_DATA_ORA_PRENOTAZIONE ="data_ora_prenotazione";
    }
    
    public static class Ruolo {
        public static final String COLUMN_ID_RUOLO = "id_ruolo";
        public static final String COLUMN_RUOLO = "ruolo";
    }
    
    public static class TipoBiglietto {
        public static final String COLUMN_ID_PREZZO = "id_prezzo";
        public static final String COLUMN_TIPO = "tipo";
        public static final String COLUMN_PREZZO = "prezzo";
    }
    
    public static class Utente {
        public static final String COLUMN_ID_UTENTE = "id_utente";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_CREDITO = "credito";
        public static final String COLUMN_ID_RUOLO = "ruolo";
        
    }
}
