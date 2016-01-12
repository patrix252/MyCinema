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

        HttpSession session = ((HttpServletRequest) request).getSession();

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
//SE C'è UN COOKIE CONTROLLO CHE L'IDUTENTE SIA GIUSTO E IN CASO POI FACCIO LOGGARE L'UTENTE
        String idUtente = null;
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        Cookie cookie;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("idUtente")) {
                    idUtente = cookie.getValue();
                }
            }
            if (idUtente != null) {
                boolean controllo = false;
                Utente user = null;
                try {
                    user = manager.trovanome(idUtente);
                } catch (SQLException ex) {
                    controllo=true;
                }
                if(controllo==false){
                    session.setAttribute("utente", user);
                    view = request.getRequestDispatcher("loggato.jsp");
                    view.forward(request, response);
                } else {
                    //PROBLEMA LOGIN, USCIRE
                    view = request.getRequestDispatcher("login.jsp");
                    view.forward(request, response);
                }
            }
        }
//SE NON CI SONO COOKIE ARRIVA QUI, E O ARRIVA DALLA FORM E I VALORI MAIL E PASSWORD NON SONO NULLI
//ALTRIMENTI I VALORI CI SONO E LI CONTROLLO
        String mail = request.getParameter("Mail");
        String password = request.getParameter("Password");
        String metodo = request.getMethod();
        if ("POST".equals(metodo) && password!=null && mail!=null && !"".equals(password) && !"".equals(mail) ) {
            InputStream is = new ByteArrayInputStream(password.getBytes());
            String hashPassword = calcolaHash(is);
            Utente user= new Utente();
            try {
                user=manager.chekpassword(mail,hashPassword);
            } catch (SQLException ex) {
                //AGGIUNGERE ECCEZIONE
                session.setAttribute("problemaConnessione", true);
                view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
            if (user==null){
                session.setAttribute("loginError", true);
                view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            } else {
                if(user.getRuolo()==1){
                    

                    //sei un admin
                    session.setAttribute("admin", true);
                   
                }
                session.setAttribute("utente", user);
                //AGGIUNGERE IL TEMPO DI VITA DEL COOKIE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                Cookie biscotto = new Cookie("idUtente", user.getEmail());
                ((HttpServletResponse) response).addCookie(biscotto);
                if(session.getAttribute("loginAction")==null){
                    view = request.getRequestDispatcher("loggato.jsp");
                    view.forward(request, response);
                } else {
                    String temp = ((StringBuffer)session.getAttribute("loginAction")).toString();
                    //view = request.getRequestDispatcher(temp);
                    session.setAttribute("loginAction", null);
                    ((HttpServletResponse)response).sendRedirect(temp);
                }
                //view.forward(request, response);
            }
        } else {
            view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
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
