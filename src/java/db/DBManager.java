    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Film;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    
    public List<Film> getFilms() throws SQLException {
        List<Film> films = new ArrayList<Film>();
        
        //RICORDARSI IL ';' ALLA FINE DELLA QUERY
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Film;");
        
        Logger.getLogger(DBManager.class.getName()).info("Query: " + stm.toString());
        
        
        try {
            ResultSet rs = stm.executeQuery();
            try {
                
                while(rs.next()) {
                    Film f = new Film();
//                     id_film, titolo, id_genere, url_trailer, durata, trama, uri_locandina, regista
                    f.setId_film(rs.getInt(Film.COLUMN_ID_FILM));
                    f.setTitolo(rs.getString(Film.COLUMN_TITOLO));
                    f.setId_genere(rs.getInt(Film.COLUMN_ID_FILM));
                    f.setUrl_trailer(rs.getString(Film.COLUMN_URL_TRAILER));
                    f.setDurata(rs.getInt(Film.COLUMN_DURATA));
                    f.setTrama(rs.getString(Film.COLUMN_TRAMA));
                    f.setUri_locandina(rs.getString(Film.COLUMN_URI_LOCANDINA));
                    f.setRegista(rs.getString(Film.COLUMN_REGISTA));
                    
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
    
    public List<Film> getFilms(String regista) throws SQLException {
        List<Film> films = new ArrayList<Film>();
        
        //RICORDARSI IL ';' ALLA FINE DELLA QUERY
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Film WHERE regista = ?;");
        stm.setString(1, regista);
        
        Logger.getLogger(DBManager.class.getName()).info("Query: " + stm.toString());
        
        
        try {
            ResultSet rs = stm.executeQuery();
            try {
                
                while(rs.next()) {
                    Film f = new Film();
//                     id_film, titolo, id_genere, url_trailer, durata, trama, uri_locandina, regista
                    f.setId_film(rs.getInt(Film.COLUMN_ID_FILM));
                    f.setTitolo(rs.getString(Film.COLUMN_TITOLO));
                    f.setId_genere(rs.getInt(Film.COLUMN_ID_FILM));
                    f.setUrl_trailer(rs.getString(Film.COLUMN_URL_TRAILER));
                    f.setDurata(rs.getInt(Film.COLUMN_DURATA));
                    f.setTrama(rs.getString(Film.COLUMN_TRAMA));
                    f.setUri_locandina(rs.getString(Film.COLUMN_URI_LOCANDINA));
                    f.setRegista(rs.getString(Film.COLUMN_REGISTA));
                    
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
    
    public void shutdown() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
        }
    }
    
}
