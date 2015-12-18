/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import beans.Film;
import beans.Genere;
import beans.Posto;
import beans.Spettacolo;
import beans.Utente;
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

	// Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RequestQueryFilter:DoAfterProcessing");
        }

	// Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
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
        try {
            manager = new DBManager("jdbc:mysql://46.101.19.71/myCinema", "user", "asdd");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = ((HttpServletRequest) request).getSession();

        String url = ((HttpServletRequest) request).getRequestURI();
        
        ((HttpServletResponse) response).setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        ((HttpServletResponse) response).setHeader("Pragma", "no-cache"); // HTTP 1.0.
        ((HttpServletResponse) response).setDateHeader("Expires", 0); // Proxies.
        
        
        if ("/MyCinema/oggialcinema.jsp".equals(url)) {
            //CHIAMARE QUERY PER PRENDERE FILM DI OGGI
            List<FilmSpettacolo> films = null;
            try {
                films = manager.getFilmstoday();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("filmsOggi", films);

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
            
        } else if ("/MyCinema/prenotazione.jsp".equals(url)) {
            
            List <Spettacolo> spett = null;
            int i = Integer.parseInt (request.getParameter("id"));
            try {
                spett = manager.getSpettacoli(i);
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("orariPrenotazione", spett);
            List <List<Posto>> posti = new ArrayList<>();
            for(int j=0; j<spett.size(); j++){
                try {
                    posti.add(manager.getPostiOccupati(spett.get(j)));
                } catch (SQLException ex) {
                    Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            session.setAttribute("postiOccupati", posti);
        
        } else if ("/MyCinema/logout.jsp".equals(url)){
            session.setAttribute("utente", null);
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                ((HttpServletResponse) response).addCookie(cookie);
            }
        } else if ("/MyCinema/pagamento.jsp".equals(url)){
   
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
            if(jason!=null){
                try {
                    pi = jason.getJSONArray("postiInteri");
                    pr = jason.getJSONArray("postiRidotti");
                    ns = jason.getString("ns");
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
                Spettacolo spett = ((List<Spettacolo>)session.getAttribute("orariPrenotazione")).get(Integer.parseInt(ns));
                session.setAttribute("spettacoloPrenotazione", spett);
                session.setAttribute("postiInteri", postiInteri);
                session.setAttribute("postiRidotti", postiRidotti);
            }
            
            
            String mail = null;
            try{
                mail = ((Utente)session.getAttribute("utente")).getEmail();
            } catch(Exception e){
                
                
                //Controllare di risettare il valore a "LoginServlet" una volta effettuato il login
                session.setAttribute("loginAction", (((HttpServletRequest) request).getRequestURL()));
                
                
                view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);

            }
            session.setAttribute("mail", mail);
                
                
        } else if("/MyCinema/pagamentoEffettuato.jsp".equals(url)) {
            
            try {
                 if(manager.addPrenotations((List<Posto>)session.getAttribute("postiInteri"), 
                                            (String)session.getAttribute("mail"), 
                                            (Spettacolo)session.getAttribute("spettacoloPrenotazione"),
                                            false)){
                    //I POSTI ERANO LIBERI E CONTINUA LA TRANSAZIONE
                 } else {
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
            
            
            //AGGIUNGERE O QUI O IN UN ALTRO FILE L'INVIO DEL QR-CODE PER EMAIL
            
            
            
        } else if ("/MyCinema/descrizionefilm.jsp".equals(url)){
            int i = Integer.parseInt(request.getParameter("id"));
            Film f = null;
            try {
                 f = manager.getFilm(i);
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("film", f);
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
