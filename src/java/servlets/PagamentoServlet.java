/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Posto;
import beans.Spettacolo;
import beans.Utente;
import filters.RequestQueryFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Paolo
 */
public class PagamentoServlet extends HttpServlet {

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
        RequestDispatcher view;
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = ((HttpServletRequest) request).getSession();
        session.setAttribute("controlloreCodice",false);
            String str = null;
            StringBuffer jb = new StringBuffer();
            BufferedReader reader = ((HttpServletRequest) request).getReader();
            while ((str = reader.readLine()) != null){
                jb.append(str);
            }
            
            JSONObject jason = null;
            try {
                jason = new JSONObject(jb.toString());
            } catch (JSONException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONArray pi = null;
            JSONArray pr = null;
            String ns = null;
            String totale = null;
            if(jason!=null){
                try {
                    pi = jason.getJSONArray("postiInteri");
                    pr = jason.getJSONArray("postiRidotti");
                    ns = jason.getString("ns");
                    totale = jason.getString("totale");
                } catch (JSONException ex) {
                    Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            List<Posto> postiInteri = new ArrayList();
            if(pi!=null){
                for(int z=0; z<pi.length(); z++){
                    String temp;
                    Posto pt = new Posto();
                    try {
                        temp = pi.getString(z);
                        pt.setRiga(Character.getNumericValue(temp.charAt(0)));
                        pt.setColonna(Character.getNumericValue(temp.charAt(2)));
                        postiInteri.add(pt);
                    } catch (JSONException ex) {
                        Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            List<Posto> postiRidotti = new ArrayList();
            if(pr!=null){
                for(int z=0; z<pr.length(); z++){
                    String temp;
                    Posto pt = new Posto();
                    try {
                        temp = pr.getString(z);
                        pt.setRiga(Character.getNumericValue(temp.charAt(0)));
                        pt.setColonna(Character.getNumericValue(temp.charAt(2)));
                        postiRidotti.add(pt);
                    } catch (JSONException ex) {
                        Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(ns!=null){
                Spettacolo spett = ((List<Spettacolo>)session.getAttribute("spettacoli")).get(Integer.parseInt(ns));
                session.setAttribute("totale", totale);
                session.setAttribute("spettacoloPrenotazione", spett);
                session.setAttribute("postiInteri", postiInteri);
                session.setAttribute("postiRidotti", postiRidotti);
            }
            
            
            String mail = null;
            //Provo a prendere la mail di chi sta facendo la prenotazione, se fallisco vuol dire che non è loggato
            //e lo reindirizzo alla pagina di login
            try{
                mail = ((Utente)session.getAttribute("utente")).getEmail();
            } catch(Exception e){    
               
                view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);

            }
            session.setAttribute("mail", mail);
            view = request.getRequestDispatcher("pagamento.jsp");
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

}
