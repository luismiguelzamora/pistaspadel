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
public class ReservarPistaAction extends HttpServlet {

    private static int pistaId = -1;

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

        java.lang.String fechaReserva = "", txt;
        com.reservas.models.Usuario usuarioConectado;
        int horarioId, usuarioId;

        int diaSemanaActual = com.reservas.util.Tools.getDiaSemanaActual();
        int recorrerDesde = (diaSemanaActual * 14 - 14) + 1;

        try {

            java.lang.Object objIdPista = request.getParameter("pista");
            if (objIdPista != null && (objIdPista instanceof java.lang.String)) {
                txt = (java.lang.String) objIdPista;
                pistaId = Integer.parseInt(txt.substring(2, txt.length()));
            } else {
                redireccionar(request, response, false, "pistas.jsp",
                        "<p>La url de reserva no existe, por lo que no ha podido reservar</p>", "err");
                return;
            }

            java.lang.Object objUsuarioConectado = request.getAttribute("usuarioConectado");
            if (objUsuarioConectado != null && (objUsuarioConectado instanceof com.reservas.models.Usuario)) {
                usuarioConectado = (com.reservas.models.Usuario) objUsuarioConectado;
                usuarioId = usuarioConectado.getId();
            } else {
                redireccionar(request, response, true, "pistas.jsp",
                        "<p>Para poder reservar debes acceder con tu cuenta de usuario</p>", "err");
                return;
            }

            java.lang.Object objIdHorarioReserva = request.getParameter("reservar");
            if (objIdHorarioReserva != null && (objIdHorarioReserva instanceof java.lang.String)) {
                txt = (java.lang.String) objIdHorarioReserva;
                horarioId = Integer.parseInt(txt.substring(2, txt.length()));
                if (horarioId < 1 || horarioId > 98) {
                    redireccionar(request, response, false, "pistas.jsp",
                            "<p>La url de reserva no existe, por lo que no ha podido reservar</p>", "err");
                    return;
                }
                // calculo horario caducado
                if (horarioId >= recorrerDesde && horarioId <= recorrerDesde + 13) {
                    // comparo hora actual con la del horarioId
                    java.lang.String horaHorario = com.reservas.models.Horario.getHorario(horarioId).getHora();
                    horaHorario = horaHorario.substring(0, 2);
                    java.util.Date date = new java.util.Date();
                    java.util.Calendar calendar = new java.util.GregorianCalendar();
                    calendar.setTime(date);
                    int horaActual = calendar.get(java.util.Calendar.HOUR_OF_DAY);
                    int horaHorarioParse = Integer.parseInt(horaHorario);
                    if (horaActual >= horaHorarioParse) {
                        redireccionar(request, response, false, "pistas.jsp",
                                "<p>La hora que intentas reservar ya expir&oacute!</p>", "err");
                        return;
                    }
                }

                java.lang.Object objFechaReserva = request.getParameter("fech");

                if (objFechaReserva != null && (objFechaReserva instanceof java.lang.String)) {
                    fechaReserva = (java.lang.String) objFechaReserva;
                    fechaReserva = fechaReserva.replace('s', ' ');
                    fechaReserva = fechaReserva + ":00:00.0";
                } else {
                    redireccionar(request, response, false, "pistas.jsp",
                            "<p>La url de reserva no existe, por lo que no ha podido reservar</p>", "err");
                    return;
                }

                com.reservas.models.Reserva reserva = com.reservas.models.Reserva.getReserva(fechaReserva);
                if (reserva != null) {
                    if (reserva.getUsuarioId() == usuarioId) {
                        // cancelar cuenta
                        if (com.reservas.models.Reserva.cancelarReserva(reserva.getId())) {
                            redireccionar(request, response, false, "pistas.jsp",
                                    "<p>Has cancelado tu reserva satisfactoriamente</p>", "inf");
                            return;
                        } else {
                            redireccionar(request, response, false, "pistas.jsp",
                                    "<p>No se ha podido cancelar tu reserva por un problema de conexi&oacute;n, intentalo m&aacute;s tarde.</p>", "err");
                            return;
                        }
                    } else {
                        redireccionar(request, response, false, "pistas.jsp",
                                "<p>La hora seleccionada esta reservada por otro usuario!</p>", "err");
                        return;
                    }

                }

            } else {
                redireccionar(request, response, false, "pistas.jsp",
                        "<p>La url de reserva no existe, por lo que no ha podido reservar</p>", "err");
                return;
            }

            if (com.reservas.models.Reserva.insertarReserva(horarioId, usuarioId, pistaId, fechaReserva)) {
                redireccionar(request, response, false, "pistas.jsp",
                        "<p>La reserva se ha realizado correctamente</p>", "inf");
                return;
            } else {
                redireccionar(request, response, false, "pistas.jsp",
                        "<p>Ha ocurrido un error de conexi&oacute;n, int&eacute;ntelo m&aacute;s tarde.</p>", "err");
                return;
            }

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
     */
    private void redireccionar(HttpServletRequest request,
            HttpServletResponse response,
            boolean mostrarLogin,
            java.lang.String vista,
            java.lang.String mensaje,
            java.lang.String tipoMensaje) throws ServletException, IOException {
        javax.servlet.http.HttpSession session;
        session = request.getSession(true);
        session.setAttribute("mensajeSession", mensaje);
        session.setAttribute("tipoMensajeSession", tipoMensaje);
        if (pistaId != -1) {
            session.setAttribute("pistaId", pistaId);
        }
        response.sendRedirect("inicio.do");
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
