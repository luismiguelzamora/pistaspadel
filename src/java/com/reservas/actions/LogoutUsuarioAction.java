package com.reservas.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis miguel zamora aviles
 */
public class LogoutUsuarioAction extends HttpServlet {

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

        javax.servlet.RequestDispatcher rd;
        javax.servlet.http.HttpSession session;
        try {
            // elimino session
            session = request.getSession(true);
            java.lang.Object idSessionUsuario = session.getAttribute(com.reservas.controller.ControllerServlet.getKEY_SESSION_USUARIO());
            session.removeAttribute(com.reservas.controller.ControllerServlet.getKEY_SESSION_USUARIO());
            if (idSessionUsuario != null) {
                System.out.println("session " + com.reservas.controller.ControllerServlet.getKEY_SESSION_USUARIO() + " eliminada ");
            }

            // elimino cookie
            javax.servlet.http.Cookie[] cookies = null;
            cookies = request.getCookies();
            if (cookies != null) {
                for (javax.servlet.http.Cookie c : cookies) {
                    if (c.getName().equals(com.reservas.controller.ControllerServlet.getKEY_COOKIE_USUARIO())) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                        System.out.println("cookie " + com.reservas.controller.ControllerServlet.getKEY_COOKIE_USUARIO() + " value " + c.getValue() + " eliminada ");
                    }
                }
            }

            request.setAttribute("mostrarLogin", true);
            request.setAttribute("vista", "pistas.jsp");
            request.setAttribute("mensaje", "<p>Has cerrado sesion</p>");
            request.setAttribute("tipoMensaje", "inf");

            rd = request.getRequestDispatcher(com.reservas.controller.Rutas.RUTA_PLANTILLA_PUBLICA.getRuta());
            rd.forward(request, response);
            return;

        } finally {

        }
    }

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
    }

}
