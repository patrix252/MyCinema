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
            List<FilmSpettacolo> films = null;
            int filmsLength = 0;
            try {
                films = manager.getFilmsAll();
                filmsLength = films.size();
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("filmInProgramma", films);
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
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                ((HttpServletResponse) response).addCookie(cookie);
            }
        } else if ("/MyCinema/pagamento.jsp".equals(url)){
            int i = Integer.parseInt(request.getParameter("ns"));
            Spettacolo spett = ((List<Spettacolo>)session.getAttribute("orariPrenotazione")).get(i);
            session.setAttribute("spettacoloPrenotazione", spett);
            String p = ((HttpServletRequest) request).getParameter("pt");
            String mail = null;
            try{
                mail = ((Utente)session.getAttribute("utente")).getEmail();
            } catch(Exception e){
                
                
                //Controllare di risettare il valore a "LoginServlet" una volta effettuato il login
                session.setAttribute("loginAction", (((HttpServletRequest) request).getRequestURL().append('?').append(((HttpServletRequest) request).getQueryString())));
                
                
                view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
            session.setAttribute("mail", mail);
            //PRENDO IL VALORE DELLA STRINGA SALVATA NELLA GET DELLA REQUEST E PRENDO I VALORI OPPORTUNI PER CREARE UNA LISTA
            //DI POSTI IN CUI SETTO I VALORI DI RIGA E COLONNA
            /*List<Posto> posti = new ArrayList<>();
            for(int j=0; (j+3)<p.length(); j+=4){
                Posto po = new Posto();
                po.setRiga(Character.getNumericValue(p.charAt(j)));
                po.setColonna(Character.getNumericValue(p.charAt(j+2)));
                posti.add(po);
            }
            List<Posto> postiRidotti = new ArrayList<>();
            for(int j=0; (j+3)<pr.length(); j+=4){
                Posto po = new Posto();
                po.setRiga(Character.getNumericValue(pr.charAt(j)));
                po.setColonna(Character.getNumericValue(pr.charAt(j+2)));
                postiRidotti.add(po);
            }
            
            session.setAttribute("postiInteri", posti);
            session.setAttribute("postiRidotti", postiRidotti);
            */
            //CONTROLLARE NEL DATABASE CHE NON CI SIANO GIà PRENOTAZIONI PER QUEI POSTI PER QUELLO SPETTACOLO
            
            //POSTI PASSATI NELLA GET, POSSIBILMENTE MODIFICABILI DALL'UTENTE, AGGIUNGERE CONTROLLO CHE NUMERO RIGA E COLONNA
            //SIA NEL RANGE E CHE IL NUMERO DELLA RIGA SIA DIVERSO DA 5

            //QUERY AL DATABASE CON SPETTACOLO spett E LISTA DI POSTI PRENOTATI DALL'UTENTE IDENTIFICATO DALLA SUA MAIL
        } else if("/MyCinema/pagamentoEffettuato.jsp".equals(url)) {
            
            try {
                 if(manager.addPrenotations((List<Posto>)session.getAttribute("postiInteri"), 
                                            (List<Posto>)session.getAttribute("postiRidotti"), 
                                            (String)session.getAttribute("mail"), 
                                            (Spettacolo)session.getAttribute("spettacoloPrenotazione"))){
                    //I POSTI ERANO LIBERI E CONTINUA LA TRANSAZIONE
                 } else {
                     //I POSTI ERANO OCCUPATI O C'ERA QUALCHE PROBLEMA NELL'URL, QUINDI REINDIRIZZARE VERSO PAGINA DI ERRORE
                 }
            } catch (SQLException ex) {
                Logger.getLogger(RequestQueryFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
