/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;


/**
 *
 * @author Paolo
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher view = new RequestDispatcher() {

            @Override
            public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
            String nome = request.getParameter("Nome");
            String cognome = request.getParameter("Cognome");
            //POSSIBILMENTE USARE IL TIPO DATE
            //String data = request.getParameter("Data");
            String test = request.getParameter("Giorno");
            String mail = request.getParameter("Mail");
            String password = request.getParameter("Password");
            
            //CREO L'HASH DELLA PASSWORD E L'HASH PER L'ID UTENTE
            String hashPassword;
            InputStream is = new ByteArrayInputStream( password.getBytes() );
            hashPassword = calcolaHash(is);
            
            String hashUtente;
            String supporto = nome+cognome+/*data+*/mail+password;
            is = new ByteArrayInputStream(supporto.getBytes() );
            hashUtente = calcolaHash(is);

            //SISTEMARE DATABASE CHE L'ID UTENTE è UN NUMERO E INVECE CI SERVE UNA STRINGA DI LUNGHEZZA
            //SUPERIORE A 64 (MI SEMBRA SIA SEMPRE QUELLA LA LUNGHEZZA)
            //DA FARE CON FRA
            
            //AGGIUNGERE CREDENZIALI AL DATABASE CONTROLLANDO CHE NON CI SIANO GIà UTENTI CON QUELL'EMAIL
            //IN CASO DI COLLISIONE AVVERTIRE L'UTENTE CHE L'EMAIL NON è VALIDA PERCHé C'è GIà UN UTENTE CON 
            //QUELL'EMAIL E RIMANDARLO ALLA PAGINA DI REGISTRAZIONE (INSERIRE L'ERRORE A FONDO PAGINA IN ROSSO)
            
            //CREARE COOKIE CON QUELL'ID UTENTE E PASSARLO AL CLIENT
            Cookie cookie = new Cookie("idUtente" , hashUtente);
            response.addCookie(cookie);
            
            view = request.getRequestDispatcher("loggato.jsp");
            view.forward(request, response);
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
    
    public static String calcolaHash(InputStream is) {
    String output;
    int read;
    byte[] buffer = new byte[8192];

    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        while ((read = is.read(buffer)) > 0) {
            digest.update(buffer, 0, read);
        }
        byte[] hash = digest.digest();
        BigInteger bigInt = new BigInteger(1, hash);
        output = bigInt.toString(16);
        while ( output.length() < 32 ) {
            output = "0"+output;
        }
    } 
    catch (Exception e) {
        e.printStackTrace(System.err);
        return null;
    }

    return output;
}

}
