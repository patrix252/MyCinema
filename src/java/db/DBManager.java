    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import util.Util;
import beans.Film;
import beans.Genere;
import beans.Utente;
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
  
        String id_utente=user.getId_utente();  
        String password = user.getPassword();
        String nome = user.getNome();
        String cognome = user.getCognome();
        String email = user.getEmail();
        Date data = user.getDataNascita();
       
    String test = "INSERT INTO myCinema.Utente (id_utente, email, password, nome, cognome, dataNascita) VALUES (\""+id_utente+"\",\""+email+"\",\""+password+"\",\""+nome+"\",\""+cognome+"\",\""+data+"\");";
            Statement stm = con.createStatement();
            
            stm.executeUpdate(test);
    
    
    
    
    }
    
    

//passato l'id utente restituire il bean corrispondente FINITA
    public Utente trovanome(String id_utente) throws SQLException {

        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Utente WHERE id_utente= ?;");
        stm.setString(1, id_utente);
        Logger.getLogger(DBManager.class.getName()).info("Query: " + stm.toString());
         Utente user = new Utente();
       

        try {
            ResultSet rs = stm.executeQuery();
            try {

                while (rs.next()) {
                    user.setId_utente(rs.getString(Util.Utente.COLUMN_ID_UTENTE));
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
                    user.setId_utente(rs.getString(Util.Utente.COLUMN_ID_UTENTE));
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
        String id = user.getId_utente();
        PreparedStatement stm = con.prepareStatement("DELETE  FROM myCinema.Utente WHERE id_utente=?;");
        stm.setString(1, id);
        stm.executeUpdate();
    }
        
  
   
    //film di oggi con anche le informazioni dello spettacolo
    
    public List<FilmSpettacolo> getFilmstoday() throws SQLException {
        //query che riporta tutti i dati 
        List<FilmSpettacolo> films = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Spettacolo, myCinema.Film, myCinema.Genere WHERE data = CURDATE() AND Film.id_film = Spettacolo.id_film AND Film.id_genere = Genere.id_genere;");

        

        try {
            ResultSet rs = stm.executeQuery();
            try {

                while (rs.next()) {
                    FilmSpettacolo h = new FilmSpettacolo();
                    Genere g = new Genere();
                    g.setId_genere(rs.getInt(Util.Genere.COLUMN_ID_GENERE));
                    g.setDescrizione(rs.getString(Util.Genere.COLUMN_DESCRIZIONE));

                    h.f.setId_film(rs.getInt(Util.Film.COLUMN_ID_FILM));
                    h.f.setDurata(rs.getInt(Util.Film.COLUMN_DURATA));
                    h.f.setGenere(g);
                    h.f.setIs3D(rs.getInt(Util.Film.COLUMN_IS3D));
                    h.f.setRegista(rs.getString(Util.Film.COLUMN_REGISTA));
                    h.f.setTitolo(rs.getString(Util.Film.COLUMN_TITOLO));
                    h.f.setTrama(rs.getString(Util.Film.COLUMN_TRAMA));
                    h.f.setUri_locandina(rs.getString(Util.Film.COLUMN_URI_LOCANDINA));
                    h.f.setUrl_trailer(rs.getString(Util.Film.COLUMN_URL_TRAILER));

                    h.s.setId_film(rs.getInt(Util.Spettacolo.COLUMN_ID_FILM));
                    h.s.setId_sala(rs.getInt(Util.Spettacolo.COLUMN_ID_SALA));
                    h.s.setId_spettacolo(rs.getInt(Util.Spettacolo.COLUMN_ID_SPETTACOLO));
                    //data
                    h.s.setData(rs.getDate(Util.Spettacolo.COLUMN_DATA));
                    h.s.setOra(rs.getTime(Util.Spettacolo.COLUMN_ORA));

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
    
    
    
    public List<Film> getFilmsAll() throws SQLException {
        List<Film> films = new ArrayList<Film>();
        
        //RICORDARSI IL ';' ALLA FINE DELLA QUERY
        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Film INNER JOIN myCinema.Genere WHERE Film.id_genere=Genere.id_genere;");
        
        Logger.getLogger(DBManager.class.getName()).info("Query: " + stm.toString());
        
        
        try {
            ResultSet rs = stm.executeQuery();
            try {
                
                while(rs.next()) {
                    Genere g = new Genere();
                    g.setId_genere(rs.getInt(Util.Genere.COLUMN_ID_GENERE));
                    g.setDescrizione(rs.getString(Util.Genere.COLUMN_DESCRIZIONE));
                    Film f = new Film();
//                    id_film, titolo, id_genere, url_trailer, durata, trama, uri_locandina, regista
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
    public List<Film> getFilmsCarosello(int num_recenti) throws SQLException{
        List<Film> films = new ArrayList<Film>();
        
        //da controllare... necessario anche sapere da data odierna?? minkiaboh!
        PreparedStatement stm = con.prepareStatement("SELECT  * FROM myCinema.Film INNER JOIN myCinema.Spettacolo INNER JOIN myCinema.Genere ORDER BY data ASC, ora ASC LIMIT  5 ;");
        stm.setString(1,Integer.toString(num_recenti));
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
    
    
    
    
    
    public List<Utente> getTopUser (int n) throws SQLException{
       
        
        
    
    return null;
    }
    
    public void shutdown() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
        }
    }
    
}
