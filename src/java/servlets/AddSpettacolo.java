/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Film;
import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
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
 * @author Patrix
 */
@WebServlet(name = "AddSpettacolo", urlPatterns = {"/AddSpettacolo"})
public class AddSpettacolo extends HttpServlet {
    DBManager manager;
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
        manager = (DBManager)getServletContext().getAttribute("dbmanager");
        String id_film = request.getParameter("film");
        String id_sala = request.getParameter("sala");
        String data = request.getParameter("data");
        String ora = request.getParameter("ora");
        int x = Integer.parseInt(request.getParameter("x"));
        int length = Integer.parseInt(request.getParameter("length"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = null;
        try {
            date = sdf1.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(AddSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date dataFinale = null;
        if(date!=null){
            dataFinale = new Date(date.getTime());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        long ms = 0;
        try {
            ms = sdf.parse(ora).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(AddSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
        }
        Time t = new Time(ms);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataFinale);
        calendar.add(Calendar.MILLISECOND, (int)t.getTime());
        calendar.add(Calendar.HOUR, 1);
        if (request.getParameter("one") != null) {
            for(int i=0; i<length; i++){
                if(i==0){
                    Random rand = new Random();
                    int r = rand.nextInt(11);
                    calendar.add(Calendar.MINUTE, r);
                }
                try {
                    manager.insertSpettacolo(Integer.parseInt(id_film), Integer.parseInt(id_sala), new Date(calendar.getTime().getTime()), new Time(calendar.getTime().getTime()));
                } catch (SQLException ex) {
                    Logger.getLogger(AddSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
                }
                calendar.add(Calendar.MINUTE, x);
            }
        } else if (request.getParameter("all") != null) {
            List<Film> films = (List<Film>)session.getAttribute("films");
            for(int j=0; j<films.size(); j++){
                calendar.setTime(dataFinale);
                calendar.add(Calendar.MILLISECOND, (int)t.getTime());
                calendar.add(Calendar.HOUR, 1);
                for(int i=0; i<length; i++){
                    if(i==0){
                        Random rand = new Random();
                        int r = rand.nextInt(11);
                        calendar.add(Calendar.MINUTE, r);
                    }
                    Random rand = new Random();
                    int r = rand.nextInt((4 - 1) + 1) + 1;
                    try {
                        manager.insertSpettacolo(films.get(j).getId_film(), r, new Date(calendar.getTime().getTime()), new Time(calendar.getTime().getTime()));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    calendar.add(Calendar.MINUTE, x);
                }
            }
        }
        
        
        response.sendRedirect("aggiungiSpettacolo.jsp?ok=1");
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
