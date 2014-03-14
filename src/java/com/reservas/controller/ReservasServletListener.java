package com.reservas.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author luis miguel zamora aviles
 */
public class ReservasServletListener implements ServletContextListener {

    /**
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    /**
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Metodo contextDestroyed() de ReservasServletListener = DESREGISTRANDO DRIVERS");
        java.util.Enumeration<java.sql.Driver> driversCargados = java.sql.DriverManager.getDrivers();
        while (driversCargados.hasMoreElements()) {
            java.sql.Driver driv = driversCargados.nextElement();
            try {
                java.sql.DriverManager.deregisterDriver(driv);
                System.out.println("El driver " + driv + " ha sido desregistrado");
            } catch (java.sql.SQLException sqle) {
                System.out.println("El driver " + driv + " no ha podido ser desregistrado por " + sqle);
            }
        }
        System.out.println("Metodo contextDestroyed() de ReservasServletListener = CERRANDO HILO [Abandoned connection ceanup thread]");
        try {
            com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();
            System.out.println("hilo [Abandoned connection cleanup thread] cerrado");
        } catch (java.lang.InterruptedException ie) {
            System.out.println("Error al cerrar hilo [Abandoned connection cleanup thread] por " + ie);
        }
    }
}
