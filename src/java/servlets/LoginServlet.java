/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Utente;
import db.DBManager;
import filters.RequestQueryFilter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paolo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
        
        HttpSession session = ((HttpServletRequest) request).getSession();
        RequestDispatcher view;
        // get request parameters for mail and password
        String mail = request.getParameter("Mail");
        String password = request.getParameter("Password");
        InputStream is = new ByteArrayInputStream(password.getBytes());
        String hashPassword = calcolaHash(is);
        Utente user= new Utente();
        try {
            user=manager.chekpassword(mail,hashPassword);
        } catch (SQLException ex) {
            session.setAttribute("problemaConnessione", true);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
        }
        if (user==null){
            session.setAttribute("loginError", true);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
        } else {
            //controllo se le credenziali sono quelle dell'admin
            if(user.getRuolo()==1){
                session.setAttribute("admin", true);
                String referer = request.getHeader("Referer");
                response.sendRedirect(referer);
            }
            session.setAttribute("utente", user);
            Cookie biscotto = new Cookie("idUtente", user.getEmail());
            biscotto.setMaxAge(60);
            ((HttpServletResponse) response).addCookie(biscotto);
            //loginAction mi serve per proseguire alla pagina di pagamento
            
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
            
        }        
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
            while (output.length() < 32) {
                output = "0" + output;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }

        return output;
    }

}
