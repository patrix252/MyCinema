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
import beans.UtenteSpesa;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Classi;
import util.Classi.FilmSpettacolo;

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
                Classi.FilmSpettacolo h = new Classi.FilmSpettacolo();
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
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo, myCinema.Film, myCinema.Genere WHERE data = CURDATE() AND Film.id_film = Spettacolo.id_film AND Film.id_genere = Genere.id_genere;"); ResultSet rs = stm.executeQuery()) {
            
            while (rs.next()) {
                Classi.FilmSpettacolo h = new Classi.FilmSpettacolo();
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

    
    
    
   
    //solo per vedere come funziona il ? nelle query
    public List<Film> getFilms(String regista) throws SQLException {
        List<Film> films = new ArrayList<>();
        
        //RICORDARSI IL ';' ALLA FINE DELLA QUERY
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Film WHERE regista = ?;");
        //primo punto di domanda uguale regista
        stm.setString(1, regista);
        
        Logger.getLogger(DBManager.class.getName()).log(Level.INFO, "Query: {0}", stm.toString());
                
        
        try {
            try (ResultSet rs = stm.executeQuery()) {
                
                while(rs.next()) {
                    Film f = new Film();
//                     id_film, titolo, id_genere, url_trailer, durata, trama, uri_locandina, regista
                    f.setId_film(rs.getInt(Util.Film.COLUMN_ID_FILM));
                    f.setTitolo(rs.getString(Util.Film.COLUMN_TITOLO));
                    //f.setGenere(rs.getInt(Util.Film.COLUMN_ID_FILM));
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
        PreparedStatement stm = con.prepareStatement("SELECT Posto.id_posto, id_sala, riga, colonna, esiste FROM myCinema.Posto INNER JOIN myCinema.Prenotazione WHERE Posto.id_posto=Prenotazione.id_posto AND Prenotazione.id_spettacolo=?;");
        
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
    
    /* 
    array ["x,y"] dove x e y sono riga e colonna del posto prenotato ,email, spettacolo
    Ritorno: query che prende i posti avendo l'array di x e y e poi per ogni posto salvi una prenotazione per l'email fornita 
    per la data e l'ora al momento della query prendi la data e l'ora attuali
    1.controllo che i posti non siano gia prenotati
    2.inserisco una prenotazione per ogni posto
    
    
    
    */
    /*
    public boolean addPrenotations (List <Posto> posti, String email,Spettacolo s,boolean ridotto ) throws SQLException {
            
                
               
            //inserisco le prenotazioni
            
        while (posti.iterator().hasNext()) {
            PreparedStatement stm = con.prepareStatement("SELECT id_posto FROM myCinema.Posto WHERE id_sala=? AND riga=? AND colonna=?;");
            stm.setInt(1, s.getId_sala());
            stm.setInt(2,posti.iterator().next().getRiga());
            stm.setInt(3,posti.iterator().next().getColonna());
        try {
            try(ResultSet rs = stm.executeQuery()) {
                while (rs.next()){
                    int id_posto = rs.getInt(Util.Posto.COLUMN_ID_POSTO);
                    //controllo che non ci sia gia una prenotazione con questo posto
                    
                    PreparedStatement stm2 = con.prepareStatement("SELECT id_posto FROM myCinema.Prenotazione WHERE id_spettacolo=?;");
                    stm2.setInt(1,s.getId_spettacolo());
                    try{
                        try(ResultSet prenotazioni = stm2.executeQuery()) {
                        while(prenotazioni.next()){
                            if ((prenotazioni.getInt(Util.Prenotazione.COLUMN_ID_POSTO)) == id_posto){
                                return false;
                            }
                        }
                        }
                    } finally {
                        stm2.close();
                    }
                 
                    
                    
                    
                    PreparedStatement insert = con.prepareStatement("INSERT INTO myCinema.Prenotazione (id_spettacolo, id_prezzo, id_posto, email, data, ora) VALUES (?,?,?,?,?,?);");
                        insert.setInt(1,s.getId_spettacolo());
                        if (ridotto){
                        insert.setInt(2,1);
                        } else {
                        insert.setInt(2,0);
                        }
                        insert.setInt(3,id_posto);
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
            }
        } finally{
            stm.close();
        }   
            
            
            }
      return true;     
    }
      */      
            
            
            
     public boolean addPrenotations (List <Posto> posti, String email,Spettacolo s,boolean ridotto ) throws SQLException {
         
        
         //prendo i posti occupati per fare il controllo
         List <Posto> postioccupati = this.getPostiOccupati(s);
        
        
        
         
     
         
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
                 if (p.getId_posto()==q.getId_posto())
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
                                                            "    Utente.email, SUM(prezzo) AS incasso\n" +
                                                            "FROM\n" +
                                                            "    myCinema.Prenotazione\n" +
                                                            "        NATURAL JOIN\n" +
                                                            "    myCinema.Utente\n" +
                                                            "        NATURAL JOIN\n" +
                                                            "    myCinema.TipoBiglietto\n" +
                                                            "GROUP BY email\n" +
                                                            "ORDER BY incasso ASC;"); 
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
    
    public void shutdown() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
        }
    }
    
}
