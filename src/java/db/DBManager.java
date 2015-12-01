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
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import util.Classi;
import util.Classi.FilmSpettacolo;

public class DBManager implements Serializable {
    
    private transient Connection con;
    
    public DBManager(String dburl, String user, String password) throws SQLException{
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception ex){}
        
        
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
        Logger.getLogger(DBManager.class.getName()).info("Query: " + stm.toString());
         Utente user = new Utente();
       

        try {
            ResultSet rs = stm.executeQuery();
            try {

                while (rs.next()) {
                    user.setNome(rs.getString(Util.Utente.COLUMN_NOME));
                    user.setCognome(rs.getString(Util.Utente.COLUMN_COGNOME));
                    user.setEmail(rs.getString(Util.Utente.COLUMN_EMAIL));
                    user.setPassword(rs.getString(Util.Utente.COLUMN_PASSWORD));
                    user.setDataNascita(rs.getDate(Util.Utente.COLUMN_DATA_NASCITA));
                    user.setCredito(rs.getDouble(Util.Utente.COLUMN_CREDITO));
                    user.setRuolo(rs.getInt(Util.Utente.COLUMN_ID_RUOLO));
                }
            } finally {
                rs.close();
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
            ResultSet rs = stm.executeQuery();
            try {

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
            } finally {
                rs.close();
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
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo, myCinema.Film, myCinema.Genere WHERE Film.id_film = Spettacolo.id_film AND Film.id_genere = Genere.id_genere;");

        

        try {
            ResultSet rs = stm.executeQuery();
            try {

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
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }

        return films;
    }
    
    
    
    
   
    //film di oggi con anche le informazioni dello spettacolo FINITA
    
    public List<FilmSpettacolo> getFilmstoday() throws SQLException {
        //query che riporta tutti i dati 
        List<FilmSpettacolo> films = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo, myCinema.Film, myCinema.Genere WHERE data = CURDATE() AND Film.id_film = Spettacolo.id_film AND Film.id_genere = Genere.id_genere;");

        

        try {
            ResultSet rs = stm.executeQuery();
            try {

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
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }

        return films;
    }
    
    
    
   
  //dato l'id del film ritorna tutti gli orari (spettacoli) ordinati per data e ora
    
    public List<Spettacolo> getSpettacoli (int id_film) throws SQLException{
        List <Spettacolo> list = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo WHERE id_film=? ORDER BY data,ora ;");
        stm.setInt(1, id_film);

        try {
            ResultSet rs = stm.executeQuery();
            try {

                while (rs.next()) {
                    Spettacolo s = new Spettacolo();
                    s.setId_film(id_film);
                    s.setId_sala(rs.getInt(Util.Spettacolo.COLUMN_ID_SALA));
                    s.setId_spettacolo(rs.getInt(Util.Spettacolo.COLUMN_ID_SPETTACOLO));
                    s.setData(rs.getDate(Util.Spettacolo.COLUMN_DATA));
                    s.setOra(rs.getTime(Util.Spettacolo.COLUMN_ORA));
                    list.add(s);
                }
            } finally {
                rs.close();
            }

        } finally {
            stm.close();
        }

        return list;
    }

    
    
    
   
    //solo per vedere come funziona il ? nelle query
    public List<Film> getFilms(String regista) throws SQLException {
        List<Film> films = new ArrayList<Film>();
        
        //RICORDARSI IL ';' ALLA FINE DELLA QUERY
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Film WHERE regista = ?;");
        //primo punto di domanda uguale regista
        stm.setString(1, regista);
        
        Logger.getLogger(DBManager.class.getName()).info("Query: " + stm.toString());
                
        
        try {
            ResultSet rs = stm.executeQuery();
            try {
                
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
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
        
        return films;
    }
    
    
    //ritorna i num_recenti  fimls piu recenti
    public List<Film> getFilmsCarosello() throws SQLException{
        List<Film> films = new ArrayList<Film>();
        
        //INSERITI FILM CON DATA MAGGIORE DI OGGI AL MASSIMO 5 
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Film, myCinema.Genere, myCinema.Spettacolo WHERE Film.id_genere = Genere.id_genere AND Film.id_film = Spettacolo.id_film AND data > CURDATE() LIMIT 5;");
        
        try {
            ResultSet rs = stm.executeQuery();
            try {
                
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
            } finally {
                rs.close();
            }
        } finally {
            stm.close();
        }
       
        return films;
         
    }
    /*
    
    
    */
    /**
     * 
     * @param s Spettacolo
     * @return posti occupati
     * @throws SQLException 
     * Ritorna tutti i posti occupati passato come input uno spettacolo 
     */
   
    public List<Posto> getPostiOccupati (Spettacolo s) throws SQLException{
        List<Posto> posti = new ArrayList<Posto>();
        PreparedStatement stm = con.prepareStatement("SELECT Posto.id_posto, id_sala, riga, colonna, esiste FROM myCinema.Posto INNER JOIN myCinema.Prenotazione WHERE Posto.id_posto=Prenotazione.id_posto AND Prenotazione.id_spettacolo=?;");
        
        stm.setInt(1,s.getId_spettacolo());
        
        try {
            ResultSet rs = stm.executeQuery();
            try {
                while (rs.next()) {
                    Posto p = new Posto();
                    p.setId_posto(rs.getInt(Util.Posto.COLUMN_ID_POSTO));
                    p.setId_sala(rs.getInt(Util.Posto.COLUMN_ID_SALA));
                    p.setEsiste(rs.getInt(Util.Posto.COLUMN_ESISTE));
                    p.setRiga(rs.getInt(Util.Posto.COLUMN_RIGA));
                    p.setColonna(rs.getInt(Util.Posto.COLUMN_COLONNA));
                    posti.add(p);
                }
            } finally {
                rs.close();
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
    */
    
    public boolean addPrenotations (List <Posto> posti, String email,Spettacolo s ) throws SQLException {
        
        
        while (posti.iterator().hasNext()){
            PreparedStatement stm = con.prepareStatement("SELECT id_posto, id_sala, esiste FROM myCinema.Posto WHERE riga=? AND colonna=? ;");
            Posto p = posti.iterator().next();
            stm.setInt(1,p.getRiga());
            stm.setInt(2,p.getColonna());
            try {
                ResultSet rs = stm.executeQuery();
                try {
                    while (rs.next()) {
                        p.setId_posto(rs.getInt(Util.Posto.COLUMN_ID_POSTO));
                        p.setId_sala(rs.getInt(Util.Posto.COLUMN_ID_SALA));
                    }
                } finally {

                }
            } finally {

            }

        }
        return true;

    }
        
                
        
      
    
    
    
    
    public List<Utente> getTopUser (int n) throws SQLException{
       
        
        
    
    return null;
    }
    
    public Film getFilm (int n) throws SQLException{
        Film f = new Film();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Film, myCinema.Genere WHERE Film.id_genere = Genere.id_genere AND Film.id_film = ?;");
        stm.setInt(1, n);        
        try {
            ResultSet rs = stm.executeQuery();
            try {
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
                
            } finally {
                rs.close();
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
