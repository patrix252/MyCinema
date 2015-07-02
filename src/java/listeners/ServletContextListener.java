/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import db.DBManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;

/**
 * Web application lifecycle listener.
 *
 * @author Patrix
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private DBManager manager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String dburl = sce.getServletContext().getInitParameter("dburl");
        String user = sce.getServletContext().getInitParameter("user");
        String psw = sce.getServletContext().getInitParameter("password");

        try {

            manager = new DBManager(dburl, user, psw);

            sce.getServletContext().setAttribute("dbmanager", manager);

        } catch (SQLException ex) {

            Logger.getLogger(getClass().getName()).severe(ex.toString());

            throw new RuntimeException(ex);

        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
         manager.shutdown();
    }
}
