    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import util.Util;
import beans.Film;
import beans.Genere;
import beans.Spettacolo;
import beans.Utente;
import beans.Posto;
import beans.Prenotazione;
import beans.PrenotazioniUtente;
import beans.UtenteSpesa;
import beans.FilmSpettacolo;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Classi;

public class DBManager implements Serializable {
    
    private transient Connection con;
    
    public DBManager(String dburl, String user, String password) throws SQLException{
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex){}
        
        
        // dburl = jdbc:mysql://localhost/test?user=minty&password=greatsqldb
        
        try {
            this.con = DriverManager.getConnection(dburl, user, password);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }
    
    //inserimento utente nell database FINITA
    public void inserisciUtente (Utente user) throws SQLException{
  
        String password = user.getPassword();
        String nome = user.getNome();
        String cognome = user.getCognome();
        String email = user.getEmail();
        Date data = user.getDataNascita();
       
    String test = "INSERT INTO myCinema.Utente (email, password, nome, cognome, dataNascita) VALUES (\""+email+"\",\""+password+"\",\""+nome+"\",\""+cognome+"\",\""+data+"\");";
            Statement stm = con.createStatement();
            
            stm.executeUpdate(test);

    }
    
    

//passato l'id utente restituire il bean corrispondente FINITA
    public Utente trovanome(String email) throws SQLException {

        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Utente WHERE email= ?;");
        stm.setString(1, email);
        Logger.getLogger(DBManager.class.getName()).log(Level.INFO, "Query: {0}", stm.toString());
         Utente user = new Utente();
       

        try {
            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    user.setNome(rs.getString(Util.Utente.COLUMN_NOME));
                    user.setCognome(rs.getString(Util.Utente.COLUMN_COGNOME));
                    user.setEmail(rs.getString(Util.Utente.COLUMN_EMAIL));
                    user.setPassword(rs.getString(Util.Utente.COLUMN_PASSWORD));
                    user.setDataNascita(rs.getDate(Util.Utente.COLUMN_DATA_NASCITA));
                    user.setCredito(rs.getDouble(Util.Utente.COLUMN_CREDITO));
                    user.setRuolo(rs.getInt(Util.Utente.COLUMN_ID_RUOLO));
                }
            }
        } finally {
            stm.close();
        }

        return user;
    }
   
    
    
//chekpassword ritorna null se mail errata o password errata FINITA
    
    public Utente chekpassword (String email, String password) throws SQLException{
        
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Utente WHERE email=?;");
        stm.setString(1,email);
        Utente user = null;
        
        
       

        try {
            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    user = new Utente();
                    user.setNome(rs.getString(Util.Utente.COLUMN_NOME));
                    user.setCognome(rs.getString(Util.Utente.COLUMN_COGNOME));
                    user.setEmail(rs.getString(Util.Utente.COLUMN_EMAIL));
                    user.setPassword(rs.getString(Util.Utente.COLUMN_PASSWORD));
                    user.setDataNascita(rs.getDate(Util.Utente.COLUMN_DATA_NASCITA));
                    user.setCredito(rs.getDouble(Util.Utente.COLUMN_CREDITO));
                    user.setRuolo(rs.getInt(Util.Utente.COLUMN_ID_RUOLO));
                }
            }
        } finally {
            stm.close();
        }
        if(user!=null){
            if (user.getPassword().equals(password)){
                return user;
            } else return null;
        } else return null;
        
        
        
        
    
    
    
    }
    
    
    //da testare
    public void eliminautente (Utente user) throws SQLException{
        String id = user.getEmail();
        PreparedStatement stm = con.prepareStatement("DELETE  FROM myCinema.Utente WHERE email=?;");
        stm.setString(1, id);
        stm.executeUpdate();
    }
        
  
    
    //prende tutti i film con info dello spettacolo FINITA
    
    public List<FilmSpettacolo> getFilmsAll() throws SQLException {
        //query che riporta tutti i dati 
        List<FilmSpettacolo> films = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo, myCinema.Film, myCinema.Genere WHERE Film.id_film = Spettacolo.id_film AND Film.id_genere = Genere.id_genere;"); ResultSet rs = stm.executeQuery()) {
            
            while (rs.next()) {
                FilmSpettacolo h = new FilmSpettacolo();
                Film f = new Film();
                Spettacolo s = new Spettacolo();
                Genere g = new Genere();
                g.setId_genere(rs.getInt(Util.Genere.COLUMN_ID_GENERE));
                g.setDescrizione(rs.getString(Util.Genere.COLUMN_DESCRIZIONE));
                
                f.setId_film(rs.getInt(Util.Film.COLUMN_ID_FILM));
                f.setDurata(rs.getInt(Util.Film.COLUMN_DURATA));
                f.setGenere(g);
                f.setIs3D(rs.getInt(Util.Film.COLUMN_IS3D));
                f.setRegista(rs.getString(Util.Film.COLUMN_REGISTA));
                f.setTitolo(rs.getString(Util.Film.COLUMN_TITOLO));
                f.setTrama(rs.getString(Util.Film.COLUMN_TRAMA));
                f.setUri_locandina(rs.getString(Util.Film.COLUMN_URI_LOCANDINA));
                f.setUrl_trailer(rs.getString(Util.Film.COLUMN_URL_TRAILER));
                
                s.setId_film(rs.getInt(Util.Spettacolo.COLUMN_ID_FILM));
                s.setId_sala(rs.getInt(Util.Spettacolo.COLUMN_ID_SALA));
                s.setId_spettacolo(rs.getInt(Util.Spettacolo.COLUMN_ID_SPETTACOLO));
                //data
                s.setData(rs.getDate(Util.Spettacolo.COLUMN_DATA));
                s.setOra(rs.getTime(Util.Spettacolo.COLUMN_ORA));
                h.setF(f);
                h.setS(s);
                films.add(h);
                
            }
        }

        return films;
    }
    
    
    
    
   
    //film di oggi con anche le informazioni dello spettacolo FINITA
    
    public List<FilmSpettacolo> getFilmstoday() throws SQLException {
        //query che riporta tutti i dati 
        List<FilmSpettacolo> films = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo, myCinema.Film, myCinema.Genere WHERE data = CURDATE() AND Film.id_film = Spettacolo.id_film AND Film.id_genere = Genere.id_genere;"); 
            try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                FilmSpettacolo h = new FilmSpettacolo();
                Film f = new Film();
                Spettacolo s = new Spettacolo();
                Genere g = new Genere();
                g.setId_genere(rs.getInt(Util.Genere.COLUMN_ID_GENERE));
                g.setDescrizione(rs.getString(Util.Genere.COLUMN_DESCRIZIONE));
                
                f.setId_film(rs.getInt(Util.Film.COLUMN_ID_FILM));
                f.setDurata(rs.getInt(Util.Film.COLUMN_DURATA));
                f.setGenere(g);
                f.setIs3D(rs.getInt(Util.Film.COLUMN_IS3D));
                f.setRegista(rs.getString(Util.Film.COLUMN_REGISTA));
                f.setTitolo(rs.getString(Util.Film.COLUMN_TITOLO));
                f.setTrama(rs.getString(Util.Film.COLUMN_TRAMA));
                f.setUri_locandina(rs.getString(Util.Film.COLUMN_URI_LOCANDINA));
                f.setUrl_trailer(rs.getString(Util.Film.COLUMN_URL_TRAILER));
                
                s.setId_film(rs.getInt(Util.Spettacolo.COLUMN_ID_FILM));
                s.setId_sala(rs.getInt(Util.Spettacolo.COLUMN_ID_SALA));
                s.setId_spettacolo(rs.getInt(Util.Spettacolo.COLUMN_ID_SPETTACOLO));
                //data
                s.setData(rs.getDate(Util.Spettacolo.COLUMN_DATA));
                s.setOra(rs.getTime(Util.Spettacolo.COLUMN_ORA));
                h.setF(f);
                h.setS(s);
                films.add(h);
                
            }
        }

        return films;
    }
    
    
    
   
  //dato l'id del film ritorna tutti gli orari (spettacoli) ordinati per data e ora
    
    public List<Spettacolo> getSpettacoli (int id_film) throws SQLException{
        List <Spettacolo> list = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo WHERE id_film=? ORDER BY data,ora ;");
        stm.setInt(1, id_film);

        try {
            try (ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Spettacolo s = new Spettacolo();
                    s.setId_film(id_film);
                    s.setId_sala(rs.getInt(Util.Spettacolo.COLUMN_ID_SALA));
                    s.setId_spettacolo(rs.getInt(Util.Spettacolo.COLUMN_ID_SPETTACOLO));
                    s.setData(rs.getDate(Util.Spettacolo.COLUMN_DATA));
                    s.setOra(rs.getTime(Util.Spettacolo.COLUMN_ORA));
                    list.add(s);
                }
            }

        } finally {
            stm.close();
        }

        return list;
    }

    
    
    
   

    public List<Film> getFilms() throws SQLException {
        List<Film> films = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Film, myCinema.Genere WHERE Film.id_genere = Genere.id_genere;");     
        Logger.getLogger(DBManager.class.getName()).log(Level.INFO, "Query: {0}", stm.toString());     
        try {
            try (ResultSet rs = stm.executeQuery()) {          
                while(rs.next()) {
                    Film f = new Film();
                    Genere g = new Genere();
                    g.setId_genere(rs.getInt(Util.Genere.COLUMN_ID_GENERE));
                    g.setDescrizione(rs.getString(Util.Genere.COLUMN_DESCRIZIONE));
                    f.setId_film(rs.getInt(Util.Film.COLUMN_ID_FILM));
                    f.setTitolo(rs.getString(Util.Film.COLUMN_TITOLO));
                    f.setGenere(g);
                    f.setUrl_trailer(rs.getString(Util.Film.COLUMN_URL_TRAILER));
                    f.setDurata(rs.getInt(Util.Film.COLUMN_DURATA));
                    f.setTrama(rs.getString(Util.Film.COLUMN_TRAMA));
                    f.setUri_locandina(rs.getString(Util.Film.COLUMN_URI_LOCANDINA));
                    f.setRegista(rs.getString(Util.Film.COLUMN_REGISTA));
                    
                    films.add(f);
                }
            }
        } finally {
            stm.close();
        } 
        return films;
    }
    
    
    //ritorna i num_recenti  fimls piu recenti TESTATA E FUNZIONANTE
    public List<Film> getFilmsCarosello() throws SQLException{
        List<Film> films = new ArrayList<>();
        
        try ( //INSERITI FILM CON DATA MAGGIORE DI OGGI AL MASSIMO 5
                PreparedStatement stm = con.prepareStatement("SELECT DISTINCT myCinema.Genere.id_genere, myCinema.Genere.descrizione, myCinema.Film.url_trailer,  myCinema.Film.id_film, myCinema.Film.trama, myCinema.Film.durata, myCinema.Film.regista, myCinema.Film.titolo, myCinema.Film.uri_locandina  FROM myCinema.Film, myCinema.Genere, myCinema.Spettacolo WHERE Film.id_genere = Genere.id_genere AND Film.id_film = Spettacolo.id_film AND data > CURDATE() LIMIT 5;"); 
                ResultSet rs = stm.executeQuery()) {
            while(rs.next()) {
                Genere g = new Genere();
                g.setId_genere(rs.getInt(Util.Genere.COLUMN_ID_GENERE));
                g.setDescrizione(rs.getString(Util.Genere.COLUMN_DESCRIZIONE));
                Film f = new Film();
//                     id_film, titolo, id_genere, url_trailer, durata, trama, uri_locandina, regista
                f.setId_film(rs.getInt(Util.Film.COLUMN_ID_FILM));
                f.setTitolo(rs.getString(Util.Film.COLUMN_TITOLO));
                f.setGenere(g);
                f.setUrl_trailer(rs.getString(Util.Film.COLUMN_URL_TRAILER));
                f.setDurata(rs.getInt(Util.Film.COLUMN_DURATA));
                f.setTrama(rs.getString(Util.Film.COLUMN_TRAMA));
                f.setUri_locandina(rs.getString(Util.Film.COLUMN_URI_LOCANDINA));
                f.setRegista(rs.getString(Util.Film.COLUMN_REGISTA));
                
                films.add(f);
            }
        }
       
        return films;
         
    }
    
    
    
    public List<Posto> getPosti (int id_sala) throws SQLException{
        List<Posto> posti = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT Posto.id_posto, id_sala, riga, colonna, esiste FROM myCinema.Posto WHERE Posto.id_sala=?;");
        stm.setInt(1,id_sala);    
        try {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Posto p = new Posto();
                    p.setId_posto(rs.getInt(Util.Posto.COLUMN_ID_POSTO));
                    p.setId_sala(rs.getInt(Util.Posto.COLUMN_ID_SALA));
                    p.setEsiste(rs.getInt(Util.Posto.COLUMN_ESISTE));
                    p.setRiga(rs.getInt(Util.Posto.COLUMN_RIGA));
                    p.setColonna(rs.getInt(Util.Posto.COLUMN_COLONNA));
                    posti.add(p);
                }
            }
        } finally {
            stm.close();
        }
       return posti;
    }
    
    
    
    /**
     * 
     * @param s Spettacolo
     * @return posti occupati
     * @throws SQLException 
     * Ritorna tutti i posti occupati passato come input uno spettacolo 
     */
   
    public List<Posto> getPostiOccupati (Spettacolo s) throws SQLException{
        List<Posto> posti = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT Posto.id_posto, id_sala, riga, colonna, esiste FROM myCinema.Posto INNER JOIN myCinema.Prenotazione ON Posto.id_posto=Prenotazione.id_posto WHERE Prenotazione.id_spettacolo=?;");
        
        stm.setInt(1,s.getId_spettacolo());
        
        try {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Posto p = new Posto();
                    p.setId_posto(rs.getInt(Util.Posto.COLUMN_ID_POSTO));
                    p.setId_sala(rs.getInt(Util.Posto.COLUMN_ID_SALA));
                    p.setEsiste(rs.getInt(Util.Posto.COLUMN_ESISTE));
                    p.setRiga(rs.getInt(Util.Posto.COLUMN_RIGA));
                    p.setColonna(rs.getInt(Util.Posto.COLUMN_COLONNA));
                    posti.add(p);
                }
            }

        } finally {
            stm.close();
        }
        /*if(posti.isEmpty()){
            posti.add(new Posto());
        }*/
       return posti;
    
    
    }
    
        
            
                   
     public boolean addPrenotations (List <Posto> posti, String email,Spettacolo s,boolean ridotto ) throws SQLException {
         
        
         //prendo i posti occupati per fare il controllo
         List <Posto> postioccupati = this.getPostiOccupati(s);
         //controllo spettacolo non ancora iniziato
         PreparedStatement controllo = con.prepareStatement(    "SELECT \n" +
                                                                "    CONCAT(Spettacolo.data, ' ', Spettacolo.ora) < NOW() AS isPassed\n" +
                                                                "FROM\n" +
                                                                "    myCinema.Spettacolo\n" +
                                                                "WHERE id_spettacolo=?;");
        try {
            try (ResultSet rs = controllo.executeQuery()){
                while (rs.next()){
                    if (rs.getInt("isPassed")==1){
                        return false;
                    }
                }
            }
        
        } finally {
            controllo.close();
        }
        
         
     
         
         //trovo i posti nel db  perche sono senza id, senza sala 
         for (Posto post:posti){
             PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Posto WHERE id_sala=? AND riga=? AND colonna=?;");
             stm.setInt(1,s.getId_sala());
             stm.setInt(2,post.getRiga());
             stm.setInt(3,post.getColonna());
             String a = stm.toString();
             try {
                    try (ResultSet rs = stm.executeQuery()){
                    while (rs.next()){
                        post.setId_posto(rs.getInt(Util.Posto.COLUMN_ID_POSTO));
                        post.setEsiste(rs.getInt(Util.Posto.COLUMN_ESISTE));
                        post.setId_sala(s.getId_sala());
                        }
                    
                    } 
                 
                 
                 
                 
             
                 } finally {stm.close();}
                 
             
             
         }
         
         //controllo che non ci siano già posti occupati confrontando le liste posti (riempita sopra) e postioccupati 
         
         for (Posto p:posti){
             for (Posto q:postioccupati){
                 if (p.getId_posto()==q.getId_posto()||((p.getRiga()==q.getRiga())&&(p.getColonna()==q.getColonna())))
                     return false;
             
             }
         }
        
         
         //inserisco le prenotazioni
         
         for (Posto pos:posti){
             PreparedStatement insert = con.prepareStatement("INSERT INTO myCinema.Prenotazione (id_spettacolo, id_prezzo, id_posto, email, data, ora) VALUES (?,?,?,?,?,?);");
             insert.setInt(1,s.getId_spettacolo());
                        if (ridotto){
                        insert.setInt(2,1);
                        } else {
                        insert.setInt(2,0);
                        }
                        insert.setInt(3,pos.getId_posto());
                        insert.setString(4,email);
                        //data odierna
                        
                        //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date data = new java.util.Date();
                        String datestring = dateformat.format(data);
                        insert.setString(5,datestring);
                        //ora odierna
                        DateFormat dateformatime = new SimpleDateFormat("HH:mm:ss");
                        String timestring = dateformatime.format(data);
                        insert.setString(6,timestring);
                        //la salta
                        String queryparametrizzata = insert.toString();
                        insert.executeUpdate();
         }
     
     
     
     return true;
     }        
            
            
            
              
                
        
      
    
    
    
    //TESTATA E FUNZIONANTE
    public List<UtenteSpesa> getTopUser() throws SQLException {
        
        List <UtenteSpesa> utentespese = new ArrayList<>();
        
        try (PreparedStatement stm = con.prepareStatement(  "SELECT \n" +
                                                            "    Utente.email, password, credito, id_ruolo, nome, Utente.cognome, dataNascita, SUM(prezzo) AS incasso\n" +
                                                            "FROM\n" +
                                                            "    myCinema.Prenotazione\n" +
                                                            "        NATURAL JOIN\n" +
                                                            "    myCinema.Utente\n" +
                                                            "        NATURAL JOIN\n" +
                                                            "    myCinema.TipoBiglietto\n" +
                                                            "GROUP BY email\n" +
                                                            "ORDER BY incasso DESC;"); 
            ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                UtenteSpesa utentespesa = new UtenteSpesa();
                Utente utente = new Utente();
                utente.setEmail(rs.getString(Util.Utente.COLUMN_EMAIL));
                utente.setPassword(rs.getString(Util.Utente.COLUMN_PASSWORD));
                utente.setNome(rs.getString(Util.Utente.COLUMN_NOME));
                utente.setCognome(rs.getString(Util.Utente.COLUMN_COGNOME));
                utente.setDataNascita(rs.getDate(Util.Utente.COLUMN_DATA_NASCITA));
                utente.setCredito(rs.getDouble(Util.Utente.COLUMN_CREDITO));
                utente.setRuolo(rs.getInt(Util.Utente.COLUMN_ID_RUOLO));
                
                utentespesa.setUtente(utente);
                utentespesa.setSpesa(rs.getDouble("incasso"));
                
                utentespese.add(utentespesa);
                
            }
        }

        return utentespese;
    }
    
    //incasso di uno spettacolo
    public Double incassospettacolo(int id_spettacolo) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT \n"
                + "    SUM(prezzo) AS incasso\n"
                + "FROM\n"
                + "    myCinema.Prenotazione\n"
                + "        LEFT JOIN\n"
                + "    myCinema.TipoBiglietto ON TipoBiglietto.id_prezzo = Prenotazione.id_prezzo\n"
                + "WHERE\n"
                + "    Prenotazione.id_spettacolo = ?;");

        
        stm.setInt(1, id_spettacolo);
       Double x = null;
        try {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                   x = rs.getDouble("incasso");
                }
            }
        } finally {
            stm.close();
        }

        return x;

    }
    
    //incasso di un film
    
    public double incassofilm (int id_film) throws SQLException{
        PreparedStatement stm = con.prepareStatement(   "SELECT \n" +
                                                        "    SUM(prezzo) AS incasso\n" +
                                                        "FROM\n" +
                                                        "	\n" +
                                                        "	myCinema.Spettacolo\n" +
                                                        "		INNER JOIN \n" +
                                                        "    myCinema.Prenotazione\n" +
                                                        "		ON Spettacolo.id_spettacolo=Prenotazione.id_spettacolo\n" +
                                                        "		NATURAL JOIN \n" +
                                                        "	myCinema.Film\n" +
                                                        "		NATURAL JOIN \n" +
                                                        "	myCinema.TipoBiglietto\n" +
                                                        "WHERE id_film=?;");
        
        stm.setInt(1,id_film);
        Double x=null;
        try {
            try (ResultSet rs = stm.executeQuery();){
                while (rs.next()){
                x=rs.getDouble("incasso");
                }
            
            }
        
        } finally {stm.close();}
    
    if (x!=null){
        return x;
        } else {
        return 0;
        }
    }
 
    
    public Film getFilm (int n) throws SQLException{
        Film f = new Film();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Film, myCinema.Genere WHERE Film.id_genere = Genere.id_genere AND Film.id_film = ?;");
        stm.setInt(1, n);        
        try {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Genere g = new Genere();
                    g.setId_genere(rs.getInt(Util.Genere.COLUMN_ID_GENERE));
                    g.setDescrizione(rs.getString(Util.Genere.COLUMN_DESCRIZIONE));
                    f.setGenere(g);
                    f.setId_film(rs.getInt(Util.Film.COLUMN_ID_FILM));
                    f.setTitolo(rs.getString(Util.Film.COLUMN_TITOLO));
                    f.setUrl_trailer(rs.getString(Util.Film.COLUMN_URL_TRAILER));
                    f.setDurata(rs.getInt(Util.Film.COLUMN_DURATA));
                    f.setTrama(rs.getString(Util.Film.COLUMN_TRAMA));
                    f.setUri_locandina(rs.getString(Util.Film.COLUMN_URI_LOCANDINA));
                    f.setRegista(rs.getString(Util.Film.COLUMN_REGISTA));
                }
                
            }
        } finally {
            stm.close();
        }
        
        return f;
    }
    
    //SE NON CI SONO PRENOTAZIONI RITORNARE NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public List<PrenotazioniUtente> getPrenotazioniUtente(String mail) throws SQLException{
        List <PrenotazioniUtente> list = new ArrayList<>();
        
        PreparedStatement stm = con.prepareStatement(   "SELECT \n" +
                                                        "    Film.titolo,\n" +
                                                        "    Prenotazione.id_prenotazione,\n" +
                                                        "    Posto.riga,\n" +
                                                        "    Posto.colonna,\n" +
                                                        "    Spettacolo.id_sala,\n" +
                                                        "    TipoBiglietto.prezzo,\n" +
                                                        "    Spettacolo.data AS data_spettacolo,\n" +
                                                        "    Spettacolo.ora AS ora_spettacolo,\n" +
                                                        "    Prenotazione.data AS data_prenotazione,\n" +
                                                        "    Prenotazione.ora AS ora_prenotazione\n" +
                                                        "FROM\n" +
                                                        "    myCinema.Prenotazione\n" +
                                                        "        INNER JOIN\n" +
                                                        "    myCinema.Spettacolo ON Prenotazione.id_spettacolo = Spettacolo.id_spettacolo\n" +
                                                        "        INNER JOIN\n" +
                                                        "    myCinema.TipoBiglietto ON TipoBiglietto.id_prezzo = Prenotazione.id_prezzo\n" +
                                                        "        INNER JOIN\n" +
                                                        "    myCinema.Posto ON Prenotazione.id_posto = Posto.id_posto\n" +
                                                        "        INNER JOIN\n" +
                                                        "    myCinema.Film ON Spettacolo.id_film = Film.id_film\n" +
                                                        "WHERE\n" +
                                                        "    Prenotazione.email = ? ;");
        
        stm.setString(1, mail);
        
        try {
            try (ResultSet rs = stm.executeQuery()){
                while (rs.next()){
                    PrenotazioniUtente x = new PrenotazioniUtente();
                    x.setTitoloFilm(rs.getString(Util.Film.COLUMN_TITOLO));
                    x.setId_prenotazione(rs.getInt(Util.Prenotazione.COLUMN_ID_PRENOTAZIONE));
                    x.setRiga_posto(rs.getInt(Util.Posto.COLUMN_RIGA));
                    x.setColonna_posto(rs.getInt(Util.Posto.COLUMN_COLONNA));
                    x.setId_sala(rs.getInt(Util.Spettacolo.COLUMN_ID_SALA));
                    x.setPrezzo((int) rs.getDouble(Util.TipoBiglietto.COLUMN_PREZZO));
                    
                    x.setData_spettacolo(rs.getDate("data_spettacolo"));
                    x.setOra_spettacolo(rs.getTime("ora_spettacolo"));
                    
                    x.setData_prenotazione(rs.getDate("data_prenotazione"));
                    x.setOra_prenotazione(rs.getTime("ora_prenotazione"));
                    
                    list.add(x);
                    
                    
                
                }
            
            }
            
            
            
        } finally {
            stm.close();
        } 
        
        if (list.isEmpty()){
            return null;
        } else {
            return list;
        }
        
    }
    
    public boolean deletePrenotation(int id_prenotazione) throws SQLException{
        /*
        controlla che la prenotazione da cancellare sia relativa a uno spettacolo che deve ancora cominciare, riaccredita
        l'80% del biglietto all'utente che ha prenotato, cancella la prenotazione
        */
        
        PreparedStatement stm = con.prepareStatement (  "SELECT \n" +
                                                        "    CONCAT(Spettacolo.data, ' ', Spettacolo.ora) < NOW() AS isPassed\n" +
                                                        "FROM\n" +
                                                        "    myCinema.Prenotazione\n" +
                                                        "        INNER JOIN\n" +
                                                        "    myCinema.Spettacolo ON Prenotazione.id_spettacolo = Spettacolo.id_spettacolo\n" +
                                                        "WHERE\n" +
                                                        "    id_prenotazione = ?;");
        stm.setInt(1, id_prenotazione);
        try {
            try (ResultSet rs = stm.executeQuery()){
                while (rs.next()){
                     if (rs.getInt("isPassed")==1){
                         return false;
                     }
                }
            }
        } finally {
            stm.close();
        
        }
        
        
        
        
        //riaccredito dell 80% 
        PreparedStatement stm2 = con.prepareStatement(  "UPDATE myCinema.Prenotazione\n" +
                                                        "        NATURAL JOIN\n" +
                                                        "    myCinema.Utente\n" +
                                                        "        NATURAL JOIN\n" +
                                                        "    myCinema.TipoBiglietto \n" +
                                                        "SET \n" +
                                                        "    credito = credito + TipoBiglietto.prezzo*0.8\n" +
                                                        "WHERE id_prenotazione= ?;");
        stm2.setInt(1, id_prenotazione);
        
        try {
            stm2.executeUpdate();
        } finally {
            stm2.close();
        }
        
        
        
        
        
        //cancellazione prenotazione
        PreparedStatement stm3 = con.prepareStatement(  "DELETE FROM myCinema.Prenotazione \n" +
                                                        "WHERE\n" +
                                                        "    Prenotazione.id_prenotazione = ?;");
        
        stm3.setInt(1, id_prenotazione);
        try {
            stm3.executeUpdate();
        
        } finally {
            stm3.close();
        }
        return true;
    }
    
    public List <Prenotazione> getPrenotationFuture() throws SQLException{
        
        List <Prenotazione> prenotazioni = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement(   "SELECT \n" +
                                                        "    Prenotazione.id_prenotazione,\n" +
                                                        "    Prenotazione.id_spettacolo,\n" +
                                                        "    Prenotazione.id_prezzo,\n" +
                                                        "    Prenotazione.id_posto,\n" +
                                                        "    Prenotazione.email,\n" +
                                                        "    Prenotazione.data,\n" +
                                                        "    Prenotazione.ora\n" +
                                                        "FROM\n" +
                                                        "    myCinema.Prenotazione\n" +
                                                        "        INNER JOIN\n" +
                                                        "    myCinema.Spettacolo ON Spettacolo.id_spettacolo = Prenotazione.id_spettacolo\n" +
                                                        "WHERE\n" +
                                                        "    CONCAT(Spettacolo.data, ' ', Spettacolo.ora) > NOW();");
        
        try {
            try (ResultSet rs = stm.executeQuery()){
                while(rs.next()){
                    Prenotazione a = new Prenotazione();
                    a.setId_prenotazione(rs.getInt(Util.Prenotazione.COLUMN_ID_PRENOTAZIONE));
                    a.setId_spettacolo(rs.getInt(Util.Prenotazione.COLUMN_ID_SPETTACOLO));
                    a.setId_posto(rs.getInt (Util.Prenotazione.COLUMN_ID_POSTO));
                    a.setId_prezzo(rs.getInt(Util.Prenotazione.COLUMN_ID_PREZZO));
                    a.setData(rs.getDate(Util.Prenotazione.COLUMN_DATA));
                    a.setId_utente(rs.getString(Util.Prenotazione.COLUMN_EMAIL));
                    a.setOra(rs.getTime(Util.Prenotazione.COLUMN_ORA));
                    prenotazioni.add(a);
                }
            
            }
        } finally {
            stm.close();
        }
    return prenotazioni;
    }
    
    public String ritornaPassword(String mail) throws SQLException{
        String psw = null;
        PreparedStatement stm = con.prepareStatement("SELECT password FROM myCinema.Utente WHERE Utente.email = ?;");
        stm.setString(1, mail);        
        try {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    psw = (rs.getString(Util.Utente.COLUMN_PASSWORD));
                }
                
            }
        } finally {
            stm.close();
        }
        return psw;
    }
    
    public void shutdown() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
        }
    }
    
}
