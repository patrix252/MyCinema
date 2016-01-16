/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Posto;
import beans.Spettacolo;
import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paolo
 */
public class PrenotationServlet extends HttpServlet {
    private DBManager manager;
    private static final int NUMEROSALE = 4;
    private static final int RIGHECINEMA = 11;
    private static final int COLONNECINEMA = 12;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        RequestDispatcher view;
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = ((HttpServletRequest) request).getSession();
        manager = (DBManager)getServletContext().getAttribute("dbmanager");
        List <Spettacolo> spett = null;
        int id = Integer.parseInt (request.getParameter("id"));
        spett = manager.getSpettacoli(id);
        session.setAttribute("spettacoli", spett);
        List <List<Posto>> postiOccupati = new ArrayList<>();
        for(int j=0; j<spett.size(); j++){
            postiOccupati.add(manager.getPostiOccupati(spett.get(j)));
        }
        List <Posto> postiSala = new ArrayList<>();
        String[][] mappePosti = new String[NUMEROSALE][RIGHECINEMA];
        for(int j=1; j<(NUMEROSALE+1); j++){
            postiSala = manager.getPosti(j);
            int x = -1;
            for(int z=0; z<postiSala.size(); z++){
                if(z % COLONNECINEMA == 0)
                    x++;
                if(mappePosti[j-1][x]==null)
                    mappePosti[j-1][x]="";
                if(postiSala.get(z).getEsiste()==1)
                    mappePosti[j-1][x]=(String)mappePosti[j-1][x].concat("f");
                if(postiSala.get(z).getEsiste()==0)
                    mappePosti[j-1][x]=(String)mappePosti[j-1][x].concat("_");
            }
        }
        List<List<String>> posti = new ArrayList<>();
        for(int i=0; i<postiOccupati.size(); i++){
            for(int j=0; j<postiOccupati.get(i).size(); j++){
                int x = postiOccupati.get(i).get(j).getRiga();
                int y = postiOccupati.get(i).get(j).getColonna();
                List<String> t = new ArrayList<>();
                t.add("\""+Integer.toString(i)+"\"");
                t.add("\""+Integer.toString(x)+"_"+Integer.toString(y)+"\"");
                posti.add(t);
            }
        }
        StringBuffer orariSpettacoli = new StringBuffer();
        StringBuffer dateSpettacoli = new StringBuffer();
        for (int i = 0; i < spett.size(); ++i) {
            if (dateSpettacoli.length() > 0)
                dateSpettacoli.append(',');
            if (orariSpettacoli.length() > 0)
                orariSpettacoli.append(',');
            dateSpettacoli.append('"').append(spett.get(i).getData()).append('"');
            orariSpettacoli.append('"').append(spett.get(i).getOra()).append('"');
        }
        Set<Date> insieme = new LinkedHashSet<>();
        for (int i = 0; i < spett.size(); i++) {
            if (i == 0) {
                session.setAttribute("primaData", spett.get(i).getData());
            }
            insieme.add(spett.get(i).getData());
        }
        session.setAttribute("orari", insieme);
        session.setAttribute("dateSpettacoli", dateSpettacoli);
        session.setAttribute("orariSpettacoli", orariSpettacoli);
        session.setAttribute("mappePosti", mappePosti);
        session.setAttribute("postiOccupati", posti);
        view = request.getRequestDispatcher("prenotazione.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PrenotationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PrenotationServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
