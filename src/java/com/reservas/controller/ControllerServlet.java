package com.reservas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis miguel zamora aviles
 */
public class ControllerServlet extends HttpServlet {

    private int n = 0;
    private static final java.lang.String KEY_SESSION_USUARIO = "MISESSIONUSUARIORESERVAS";
    private static final java.lang.String KEY_COOKIE_USUARIO = "MICOOKIEUSUARIORESERVAS";

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

        java.lang.String ruta, metodo, accion, idSessionUsuario, fechaActualSql;
        javax.servlet.RequestDispatcher rd;

        javax.servlet.http.HttpSession session;
        javax.servlet.http.Cookie[] cookies;
        boolean estaConectado = false;
        int pistaSeleccionada;
        com.reservas.models.Usuario usuarioConectado;
        java.lang.String[][] pistas, horarioPistaSeleccionada;

        try {

            session = request.getSession(true);
            //horario pista seleccionada
            pistas = com.reservas.util.Conexion.getResultSql("SELECT * FROM `pistas`");

            request.setAttribute("pistas", pistas);
            pistaSeleccionada = Integer.parseInt(pistas[0][0]);
            // calcular pista seleccionada
            java.lang.Object objPistaSeleccionada = request.getParameter("verpista");
            if (objPistaSeleccionada != null && (objPistaSeleccionada instanceof java.lang.String)) {
                session.removeAttribute("pistaId");
                java.lang.String txt = (java.lang.String) objPistaSeleccionada;
                pistaSeleccionada = Integer.parseInt(txt.substring(2, txt.length()));
            } else {
                objPistaSeleccionada = session.getAttribute("pistaId");
                if (objPistaSeleccionada != null && (objPistaSeleccionada instanceof java.lang.Integer)) {
                    pistaSeleccionada = (java.lang.Integer) objPistaSeleccionada;

                }
            }

            request.setAttribute("pistaSeleccionada", pistaSeleccionada);
            fechaActualSql = com.reservas.util.Tools.getFechaActualSql();
            horarioPistaSeleccionada = com.reservas.models.Reserva.getHorarioPistaSeleccionada(pistaSeleccionada, fechaActualSql);

            request.setAttribute("horarioPistaSeleccionada", horarioPistaSeleccionada);

            System.out.println("accesos a ControllerServlet: " + n++);

            request.setAttribute("mostrarLogin", true);

            ruta = request.getServletPath();
            metodo = request.getMethod();

            java.lang.Object objMensajeSession = session.getAttribute("mensajeSession");
            if (objMensajeSession != null && (objMensajeSession instanceof java.lang.String)) {
                java.lang.String mensaje = new java.lang.String((java.lang.String) objMensajeSession);
                request.setAttribute("mensaje", mensaje);
                session.removeAttribute("mensajeSession");
            }

            java.lang.Object objTipoMensajeSession = session.getAttribute("tipoMensajeSession");
            if (objTipoMensajeSession != null && (objTipoMensajeSession instanceof java.lang.String)) {
                java.lang.String tipoMensaje = new java.lang.String((java.lang.String) objTipoMensajeSession);
                request.setAttribute("tipoMensaje", tipoMensaje);
                session.removeAttribute("tipoMensajeSession");
            }

            idSessionUsuario = (java.lang.String) session.getAttribute(ControllerServlet.KEY_SESSION_USUARIO);

            if (idSessionUsuario != null) {
                estaConectado = true;
                System.out.println("ControllerServlet : Existe sesion usuario : " + idSessionUsuario);
            }

            cookies = request.getCookies();
            for (javax.servlet.http.Cookie c : cookies) {
                if (c.getName().equals(ControllerServlet.KEY_COOKIE_USUARIO)) {
                    estaConectado = true;
                    session.setAttribute(ControllerServlet.KEY_SESSION_USUARIO, c.getValue());
                    System.out.println("ControllerServlet : Existe cookie de usuario : " + c.getValue());
                    break;
                }
            }

            if (estaConectado) {
                request.setAttribute("mostrarLogin", false);
                java.lang.String email = (java.lang.String) session.getAttribute(ControllerServlet.KEY_SESSION_USUARIO);
                usuarioConectado = com.reservas.models.Usuario.getUsuario(email);

                request.setAttribute("usuarioConectado", usuarioConectado);
            }

            if (!validarRuta(ruta, estaConectado)) {
                response.sendRedirect("inicio.do");
                return;
            }

            if (ruta.equals("/inicio.do")) {
                request.setAttribute("vista", "pistas.jsp");
                rd = request.getRequestDispatcher(Rutas.RUTA_PLANTILLA_PUBLICA.getRuta());
                rd.forward(request, response);
                return;

            } else if (ruta.equals("/login.do")) {
                if (metodo.equals("POST")) {
                    accion = getServletConfig().getInitParameter("login");
                    rd = getServletContext().getNamedDispatcher(accion);
                    if (rd != null) {
                        rd.forward(request, response);
                        return;
                    }
                }
                request.setAttribute("vista", "pistas.jsp");
                rd = request.getRequestDispatcher(Rutas.RUTA_PLANTILLA_PUBLICA.getRuta());
                rd.forward(request, response);
                return;

            } else if (ruta.equals("/registro.do")) {
                request.setAttribute("mostrarLogin", false);
                if (metodo.equals("POST")) {
                    accion = getServletConfig().getInitParameter("registro");
                    rd = getServletContext().getNamedDispatcher(accion);
                    if (rd != null) {
                        rd.forward(request, response);
                        return;
                    }
                }
                request.setAttribute("vista", "registro.jsp");
                rd = request.getRequestDispatcher(Rutas.RUTA_PLANTILLA_PUBLICA.getRuta());
                rd.forward(request, response);
                return;

            } else if (ruta.equals("/logout.do")) {
                request.setAttribute("vista", "pistas.jsp");
                accion = getServletConfig().getInitParameter("logout");
                rd = getServletContext().getNamedDispatcher(accion);
                if (rd != null) {
                    rd.forward(request, response);
                    return;
                }

            } else if (ruta.equals("/reservar.do")) {
                accion = getServletConfig().getInitParameter("reservar");
                rd = getServletContext().getNamedDispatcher(accion);
                if (rd != null) {
                    rd.forward(request, response);
                    return;
                }

            } else {
                rd = request.getRequestDispatcher(Rutas.RUTA_PLANTILLA_ERROR.getRuta());
                rd.forward(request, response);
                return;

            }
        } finally {

        }
    }

    /**
     *
     * @param rutaIntroducida
     * @return
     */
    private boolean validarRuta(java.lang.String rutaIntroducida, boolean estaConectado) {
        java.lang.String[] rutasConectado = {"/inicio.do", "/logout.do", "/reservar.do"};
        java.lang.String[] rutasDesconectado = {"/inicio.do", "/login.do", "/registro.do", "/reservar.do"};
        if (estaConectado) {
            for (java.lang.String rc : rutasConectado) {
                if (rc.equals(rutaIntroducida)) {
                    return true;
                }
            }
        } else {
            for (java.lang.String rd : rutasDesconectado) {
                if (rd.equals(rutaIntroducida)) {
                    return true;
                }
            }
        }
        return false;
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

    /**
     *
     */
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Metodo destroy() de ControllerServlet = DESREGISTRANDO DRIVERS");
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
    }

    /**
     *
     * @return
     */
    public static String getKEY_SESSION_USUARIO() {
        return KEY_SESSION_USUARIO;
    }

    /**
     *
     * @return
     */
    public static String getKEY_COOKIE_USUARIO() {
        return KEY_COOKIE_USUARIO;
    }

}
