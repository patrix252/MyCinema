/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.Film;
import db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Patrix
 */
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
            films = manager.getFilms();
        } catch (SQLException ex) {
            throw new ServletException(ex);
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
                out.println("<th> id_genere </th>");
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
                out.println("<td> " + f.getId_genere() + "</td>");
                out.println("<td> " + f.getUrl_trailer() + "</td>");
                out.println("<td> " + f.getDurata() + "</td>");
                out.println("<td> " + f.getTrama() + "</td>");
                out.println("<td> " + f.getUri_locandina() + "</td>");
                out.println("<td> " + f.getRegista() + "</td>");
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
