/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.Film;
import beans.Posto;
import beans.Spettacolo;
import beans.UtenteSpesa;
import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Patrix Peterl
 */
 //Coglione patrisio
@WebServlet(urlPatterns = {"/DatabaseTest"})
public class DatabaseTest extends HttpServlet {

        private DBManager manager;
    
    @Override
    public void init() throws ServletException {
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
    }

    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Film> films;
        
        try {
            films = manager.getFilmsCarosello();
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        
        
        
        List<Posto> posti;
        Spettacolo s= new Spettacolo();
        s.setId_spettacolo(2);
        try {
            posti = manager.getPostiOccupati(s);
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
         //TESTING FUNZIONE INSERIMENTO PRENOTAZIONE
        
        List a = new ArrayList();
        List b = new ArrayList();
        Spettacolo q = new Spettacolo();
        q.setId_spettacolo(2);
        q.setId_sala(1);
        Posto x = new Posto();
        Posto y = new Posto();
        x.setId_posto(13);
        x.setColonna(13);
        x.setRiga(13);
        x.setEsiste(1);
        x.setId_sala(1);
        y.setId_posto(15);
        y.setColonna(15);
        y.setRiga(15);
        y.setEsiste(1);
        y.setId_sala(1);
        a.add(x);
        a.add(y);
            try {
                //TESTING FUNZIONE INSERIMENTO PRENOTAZIONE

                boolean RESULTEDEZ=manager.addPrenotations(a, "qwe", q,true);
                String ass = "kdfjvj";
             
            } catch (SQLException ex) {
               
                Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
               
                
            }
            
            //TESTING TOPUSER
            
            List<UtenteSpesa> ioio;
            try {
                

                ioio=manager.getTopUser();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Home</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            out.println("<tr>");
                out.println("<th> id_film </th>");
                out.println("<th> titolo </th>");
                out.println("<th> descrizione </th>");
                out.println("<th> url_trailer </th>");
                out.println("<th> durata </th>");
                out.println("<th> trama </th>");
                out.println("<th> url_locandina </th>");
                out.println("<th> regista </th>");
                out.println("</tr>");
            for(Film f:films){
//                     id_film, titolo, id_genere, url_trailer, durata, trama, uri_locandina, regista
                out.println("<tr>");
                out.println("<td> " + f.getId_film() + "</td>");
                out.println("<td> " + f.getTitolo() + "</td>");
                out.println("<td> " + f.getGenere().getDescrizione() + "</td>");
                out.println("<td> " + f.getUrl_trailer() + "</td>");
                out.println("<td> " + f.getDurata() + "</td>");
                out.println("<td> " + f.getTrama() + "</td>");
                out.println("<td> " + f.getUri_locandina() + "</td>");
                out.println("<td> " + f.getRegista() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            
            
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Idposto </th>");
            out.println("<th> Idsala </th>");
            out.println("<th> Esiste </th>");
            out.println("<th> Riga </th>");
            out.println("<th> Colonna </th>");
            for (Posto p:posti){
                out.println("<tr>");
                out.println("<td> " + p.getId_posto()+ "</td>");
                out.println("<td> " + p.getId_sala() + "</td>");
                out.println("<td> " + p.getEsiste() + "</td>");
                out.println("<td> " + p.getRiga() + "</td>");
                out.println("<td> " + p.getColonna()+ "</td>");
               
                out.println("</tr>");
                }
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
            
            
            
            
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
