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
import java.sql.SQLException;
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

/**
 *
 * @author Paolo
 */
public class PrenotationServlet extends HttpServlet {
    private DBManager manager;
    private static int numeroSale = 4;
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
        int i = Integer.parseInt (request.getParameter("id"));
        spett = manager.getSpettacoli(i);
        session.setAttribute("spettacoli", spett);
        List <List<Posto>> postiOccupati = new ArrayList<>();
        for(int j=0; j<spett.size(); j++){
            postiOccupati.add(manager.getPostiOccupati(spett.get(j)));
        }
        List <List<Posto>> postiTotali = new ArrayList<>();
        for(int j=1; j<(numeroSale+1); j++){
            postiTotali.add(manager.getPosti(j));
        }
        session.setAttribute("postiTotali", postiTotali);
        session.setAttribute("postiOccupati", postiOccupati);
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