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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    
    //inserimento utente nell database
    public void inserisciUtente (String id_utente, String password,String nome, String cognome, String email, String data) throws SQLException{
   //PreparedStatement stm = con.prepareStatement("INSERT INTO myCinema.Utente (id_utente, email, password, nome, cognome, dataNascita) VALUES (?,?,?,?,?,?);");
        
    String test = "INSERT INTO myCinema.Utente (id_utente, email, password, nome, cognome, dataNascita) VALUES (\""+id_utente+"\",\""+email+"\",\""+password+"\",\""+nome+"\",\""+cognome+"\",\""+data+"\");";
            Statement stm = con.createStatement();
            
            stm.executeUpdate(test);
    
    
    
            /*
            stm.setString(1, "\""+id_utente+"\"");
            stm.setString(2, "\""+email+"\"");
            stm.setString(3, "\""+password+"\"");
            stm.setString(4, "\""+nome+"\"");
            stm.setString(5, "\""+cognome+"\"");
            stm.setString(6, "\""+data+"\"");
        */  
    
    
    
    }
    
    

//passato l'id utente restituire il bean corrispondente
    public Utente trovanome(String id_utente) throws SQLException {

        PreparedStatement stm = con.prepareStatement("SELECT * FROM myCinema.Utente WHERE id_utente= ?;");
        stm.setString(1, id_utente);
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
                   // user.setDataNascita(rs.getDate(Util.Utente.COLUMN_DATA_NASCITA));
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
    
        
  
   

    
    
    //
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
