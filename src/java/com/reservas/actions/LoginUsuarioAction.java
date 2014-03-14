package com.reservas.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis miguel zamora
 */
public class LoginUsuarioAction extends HttpServlet {

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
        com.reservas.models.Usuario usuarioConectado;
        java.lang.String email, clave, recordarLogin, idSessionUsuario;
        com.reservas.models.Usuario usuario;
        javax.servlet.http.HttpSession session;

        try {

            email = request.getParameter("email");
            clave = request.getParameter("clave");
            recordarLogin = request.getParameter("recordar_login");

            if (com.reservas.util.Tools.comprobarCamposObligatorios(email, clave)) {
                if (!com.reservas.util.Tools.validarEmail(email)) {
                    usuario = new com.reservas.models.Usuario(email, clave);
                    redireccion(request, response, true,
                            "pistas.jsp",
                            "<p>El email introducido no es v&aacute;lido</p>",
                            "err", usuario, null);
                    return;
                }
                if (!com.reservas.models.Usuario.esLoginCorrecto(email, clave)) {
                    usuario = new com.reservas.models.Usuario(email, clave);
                    redireccion(request, response, true,
                            "pistas.jsp",
                            "<p>Has introducido un email o clave erronea, vuelve a intentarlo.</p>",
                            "err", usuario, null);
                    return;
                }
            } else {
                usuario = new com.reservas.models.Usuario(email, clave);
                redireccion(request, response, true,
                        "pistas.jsp",
                        "<p>Debes rellenar todos los campos</p>",
                        "err", usuario, null);
                return;
            }

            // session
            session = request.getSession(true);
            System.out.println("Clase LoginUsuarioAction : creacion de sesion : " + session.getId());
            idSessionUsuario = email;
            session.setAttribute(com.reservas.controller.ControllerServlet.getKEY_SESSION_USUARIO(), idSessionUsuario);
            System.out.println("session " + com.reservas.controller.ControllerServlet.getKEY_SESSION_USUARIO() + " value " + idSessionUsuario + " creada");
            if (session.isNew()) {
                System.out.println("Session creada : session.getId() : " + session.getId());
            } else {
                System.out.println("La session ya fue creada : session.getId() : " + session.getId());
            }

            // cookie
            if (recordarLogin != null) {
                javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(com.reservas.controller.ControllerServlet.getKEY_COOKIE_USUARIO(), idSessionUsuario);
                cookie.setMaxAge(86400);
                response.addCookie(cookie);
                System.out.println("cookie " + com.reservas.controller.ControllerServlet.getKEY_COOKIE_USUARIO() + " value " + idSessionUsuario + " creada");
            }

            // en ControllerServlet compruebo session y cookie
            response.sendRedirect("inicio.do");

            return;

        } finally {

        }
    }

    /**
     *
     * @param request
     * @param response
     * @param mostrarLogin
     * @param vista
     * @param mensaje
     * @param tipoMensaje
     * @param datosCamposLogin
     * @param usuarioConectado
     * @throws ServletException
     * @throws IOException
     */
    private void redireccion(HttpServletRequest request,
            HttpServletResponse response,
            boolean mostrarLogin,
            java.lang.String vista,
            java.lang.String mensaje,
            java.lang.String tipoMensaje,
            com.reservas.models.Usuario datosCamposLogin,
            com.reservas.models.Usuario usuarioConectado) throws ServletException, IOException {
        javax.servlet.RequestDispatcher rd;
        request.setAttribute("mostrarLogin", mostrarLogin);
        request.setAttribute("vista", vista);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("tipoMensaje", tipoMensaje);
        request.setAttribute("datosCamposLogin", datosCamposLogin);
        request.setAttribute("usuarioConectado", usuarioConectado);
        rd = request.getRequestDispatcher(com.reservas.controller.Rutas.RUTA_PLANTILLA_PUBLICA.getRuta());
        rd.forward(request, response);

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
