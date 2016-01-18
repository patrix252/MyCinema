/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Spettacolo;
import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paolo
 */
@WebServlet(name = "IncassiServlet", urlPatterns = {"/IncassiServlet"})
public class IncassiServlet extends HttpServlet {
    private DBManager manager;
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
        response.setContentType("text/html;charset=UTF-8");
        String id_film = request.getParameter("q");
        String id_spettacolo = request.getParameter("s");
        if(id_film!=null){
            List<Spettacolo> spettacoli = new ArrayList<>();
            //HttpSession session = ((HttpServletRequest) request).getSession();
            manager = (DBManager)getServletContext().getAttribute("dbmanager");
            spettacoli = manager.getSpettacoli(Integer.parseInt(id_film));
            response.getWriter().write("");
             response.getWriter().append("<option>-- Scegli uno spettacolo --</option>");
            for(int i=0; i<spettacoli.size(); i++){
                response.getWriter().append("<option value=\"" + spettacoli.get(i).getId_spettacolo() + "\">" + spettacoli.get(i).getData()+ " " + spettacoli.get(i).getOra() + "</option>");
            }
        } else if(id_spettacolo!=null){
            double incasso = (double)manager.incassospettacolo(Integer.parseInt(id_spettacolo));
            response.getWriter().write("Incasso: "+String.valueOf(incasso)+"€");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IncassiServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IncassiServlet.class.getName()).log(Level.SEVERE, null, ex);
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
