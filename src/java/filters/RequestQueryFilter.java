/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import beans.Film;
import beans.Genere;
import beans.Posto;
import beans.Prenotazione;
import beans.PrenotazioniUtente;
import beans.Spettacolo;
import beans.Utente;
import beans.UtenteSpesa;
import com.itextpdf.text.DocumentException;
import db.DBManager;
import java.io.BufferedReader;
import java.lang.Object;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.Classi;
import util.Classi.FilmSpettacolo;

/**
 *
 * @author Paolo
 */
public class RequestQueryFilter implements Filter {

    private DBManager manager;
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public RequestQueryFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RequestQueryFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RequestQueryFilter:DoAfterProcessing");
        }

    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        RequestDispatcher view;
        manager = (DBManager)getFilterConfig().getServletContext().getAttribute("dbmanager");
        String url = ((HttpServletRequest) request).getRequestURI();       
        //Setto i parametri in modo da non salvare la cache
        ((HttpServletResponse) response).setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        ((HttpServletResponse) response).setHeader("Pragma", "no-cache"); // HTTP 1.0.
        ((HttpServletResponse) response).setDateHeader("Expires", -1); // Proxies.   
        HttpSession session = ((HttpServletRequest) request).getSession();
        String userName = null;
            Cookie[] cookies = ((HttpServletRequest)request).getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("idUtente"))
                        userName = cookie.getValue();
                }
            }
            if (userName != null && session.getAttribute("utente")==null){
               Utente user;
                try {
                    user=manager.trovanome(userName);
                    session.setAttribute("utente", user);
                } catch (SQLException ex) {
                    session.setAttribute("problemaConnessione", true);
                    String referer = ((HttpServletRequest)request).getHeader("Referer");
                    ((HttpServletResponse) response).sendRedirect(referer);
                }
            }
     
        if ("/MyCinema/oggialcinema.jsp".equals(url)) {
            //CHIAMARE QUERY PER PRENDERE FILM DI OGGI
            List<FilmSpettacolo> films = null;
            try {
                films = manager.getFilmstoday();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("filmsOggi", films);

        } else if ("/MyCinema/logout.jsp".equals(url)){
            session.setAttribute("utente", null);
            Cookie[] cookies1 = ((HttpServletRequest) request).getCookies();
            for (Cookie cookie : cookies1) {
                cookie.setMaxAge(0);
                ((HttpServletResponse) response).addCookie(cookie);
            }
        } else if("/MyCinema/filminprogramma.jsp".equals(url)) {
            List<FilmSpettacolo> films = null;
            try {
                films = manager.getFilmsAll();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("filmInProgramma", films);
            
        } else if ("/MyCinema/index.jsp".equals(url)) {
            //getFilmsCarosello
            //getFilm
            List<FilmSpettacolo> films = null;
            List<Film> filmsCarosello = null;
            int filmsLength = 0;
            int filmsCaroselloLength = 0;
            try {
                filmsCarosello = manager.getFilmsCarosello();
                filmsCaroselloLength = filmsCarosello.size();
                 Logger.getLogger("filmcarosello length = " + filmsCaroselloLength);
                films = manager.getFilmsAll();
                filmsLength = films.size();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("filmInProgramma", films);
            session.setAttribute("filmCarosello", filmsCarosello);
            session.setAttribute("filmCaroselloLength", filmsCaroselloLength);
            session.setAttribute("filmInProgrammaLength", filmsLength);
            session.setAttribute("loginAction", null);
            
        } else if("/MyCinema/pagamentoEffettuato.jsp".equals(url)) {
            
                
                try {
                if(manager.addPrenotations((List<Posto>)session.getAttribute("postiInteri"),
                (String)session.getAttribute("mail"),
                (Spettacolo)session.getAttribute("spettacoloPrenotazione"),
                false)){
                    String a= "true";
                //I POSTI ERANO LIBERI E CONTINUA LA TRANSAZIONE
                } else {
                    String a= "false";
                //I POSTI ERANO OCCUPATI O C'ERA QUALCHE PROBLEMA NELL'URL, QUINDI REINDIRIZZARE VERSO PAGINA DI ERRORE
                }
                if(manager.addPrenotations((List<Posto>)session.getAttribute("postiRidotti"),
                (String)session.getAttribute("mail"),
                (Spettacolo)session.getAttribute("spettacoloPrenotazione"),
                true)){
                //I POSTI ERANO LIBERI E CONTINUA LA TRANSAZIONE
                } else {
                //I POSTI ERANO OCCUPATI O C'ERA QUALCHE PROBLEMA NELL'URL, QUINDI REINDIRIZZARE VERSO PAGINA DI ERRORE
                }
                } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Classi.inviaEmail(getFilterConfig().getServletContext(), session);

            
        } else if ("/MyCinema/descrizionefilm.jsp".equals(url)){
            int i = Integer.parseInt(request.getParameter("id"));
            Film f = null;
            try {
                 f = manager.getFilm(i);
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("film", f);
        } else if ("/MyCinema/incassiclientitop.jsp".equals(url)){
            List<UtenteSpesa> utentiTop = new ArrayList<>();
            try {
                utentiTop = manager.getTopUser();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("utentiTop", utentiTop);
        } else if ("/MyCinema/listafilm.jsp".equals(url)){
            List<Film> filmsAll = new ArrayList<>();
            try {
                filmsAll = manager.getFilms();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("filmsAll", filmsAll);
        } else if ("/MyCinema/registroPrenotazioni.jsp".equals(url)){
            List<PrenotazioniUtente> prenotazioniUtente = new ArrayList<>();
            try {
                prenotazioniUtente = manager.getPrenotazioniUtente(((Utente)session.getAttribute("utente")).getEmail());
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("prenotazioniUtente", prenotazioniUtente);
        } else if ("/MyCinema/cancellaprenotazioni.jsp".equals(url)){
            List <Prenotazione> prenotazioni= new ArrayList<>();
            try {
               prenotazioni = manager.getPrenotationFuture();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("prenotazioni",prenotazioni);
            
        
        }

        if (debug) {
            log("RequestQueryFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("RequestQueryFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RequestQueryFilter()");
        }
        StringBuffer sb = new StringBuffer("RequestQueryFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
