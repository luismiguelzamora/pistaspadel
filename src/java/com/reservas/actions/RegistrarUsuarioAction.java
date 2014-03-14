package com.reservas.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis miguel miguel zamora
 */
public class RegistrarUsuarioAction extends HttpServlet {

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
        java.lang.String nombre, email, clave;
        com.reservas.models.Usuario usuario;

        try {
            nombre = request.getParameter("nombre");
            email = request.getParameter("email");
            clave = request.getParameter("clave");

            if (com.reservas.util.Tools.comprobarCamposObligatorios(nombre, email, clave)) {
                if (!com.reservas.util.Tools.validarEmail(email)) {
                    usuario = new com.reservas.models.Usuario(email, clave, nombre);
                    redireccion(request, response, false,
                            "registro.jsp",
                            "<p>El email introducido no es correcto</p>",
                            "err", usuario);
                    return;
                }
                if (com.reservas.models.Usuario.existeEmail(email)) {
                    usuario = new com.reservas.models.Usuario(email, clave, nombre);
                    redireccion(request, response, false,
                            "registro.jsp",
                            "<p>Ya existe un usuario con el email introducido</p>",
                            "err", usuario);
                    return;
                }
            } else {
                usuario = new com.reservas.models.Usuario(email, clave, nombre);
                redireccion(request, response, false,
                        "registro.jsp",
                        "<p>Debes rellenar todos los campos</p>",
                        "err", usuario);
                return;
            }
            if (com.reservas.models.Usuario.insertarUsuario(email, clave, nombre)) {
                redireccion(request, response, true,
                        "pistas.jsp",
                        "<p>&iexcl;Ya has sido registrado!, introduce tu email y clave para reservar</p>",
                        "inf", null);
                return;
            }
            redireccion(request, response, true,
                    "pistas.jsp",
                    "<p>Ha ocurrido un error con la conexion a la base de datos. Intentalo m&aacute;s tarde.</p>",
                    "err", null);
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
     * @param datosCamposRegistro
     * @throws ServletException
     * @throws IOException
     */
    private void redireccion(HttpServletRequest request,
            HttpServletResponse response,
            boolean mostrarLogin,
            java.lang.String vista,
            java.lang.String mensaje,
            java.lang.String tipoMensaje,
            com.reservas.models.Usuario datosCamposRegistro) throws ServletException, IOException {
        javax.servlet.RequestDispatcher rd;
        request.setAttribute("mostrarLogin", mostrarLogin);
        request.setAttribute("vista", vista);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("tipoMensaje", tipoMensaje);
        request.setAttribute("datosCamposRegistro", datosCamposRegistro);
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
