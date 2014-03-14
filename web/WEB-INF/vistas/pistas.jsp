<%!
    private static final int[] QQQ = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
        28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
        42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55,
        56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
        70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83,
        84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97,
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
        28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
        42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55,
        56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
        70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83,
        84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97,
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
        28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
        42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55,
        56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
        70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83,
        84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97,
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
        28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
        42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55,
        56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
        70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83,
        84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97
    };
%>
<%

    java.lang.Object objHorarioPistaSeleccionada = request.getAttribute("horarioPistaSeleccionada");
    java.lang.String[][] horarioPistaSeleccionada = new String[98][6];
    if (objHorarioPistaSeleccionada != null && (objHorarioPistaSeleccionada instanceof java.lang.String[][])) {
        horarioPistaSeleccionada = (java.lang.String[][]) objHorarioPistaSeleccionada;
    }
    int diaSemanaActual = com.reservas.util.Tools.getDiaSemanaActual();
    int recorrerDesde = diaSemanaActual * 14 - 14;

    java.util.Date date = new java.util.Date();
    java.util.Calendar calendar = new java.util.GregorianCalendar();
    calendar.setTime(date);
    java.lang.String[] datosFecha;
    int[] numerosFecha;
    int hoy = calendar.get(java.util.Calendar.DAY_OF_MONTH);
    int hora = calendar.get(java.util.Calendar.HOUR_OF_DAY);
    java.lang.String horaAux, clase;
%>
<h3>Reserva pista: haz clic sobre la hora</h3>
<div id="wrapper_tablahorarios">
    <table id="tablahorarios">
        <tbody>
            <tr>
                <td colspan="7">
                    <%
                        java.lang.String[][] pistas = null;

                        java.lang.Object objPistas = request.getAttribute("pistas");
                        if (objPistas != null && (objPistas instanceof java.lang.String[][])) {
                            pistas = (java.lang.String[][]) objPistas;
                        }
                        int pistaSeleccionada = -1;

                        java.lang.Object objPistaSeleccionada = request.getAttribute("pistaSeleccionada");
                        if (objPistaSeleccionada != null && (objPistaSeleccionada instanceof java.lang.Integer)) {
                            pistaSeleccionada = (java.lang.Integer) objPistaSeleccionada;
                        }
                        if (pistas != null && pistaSeleccionada != -1) {
                            // ya que pista seleccionada contiene id y no el indice en array
                            for (int x = 0; x < pistas.length; x++) {
                                if (pistas[x][0].equals(String.valueOf(pistaSeleccionada))) {
                                    pistaSeleccionada = x;
                                    break;
                                }
                            }
                            out.write("<h2>" + pistas[pistaSeleccionada][1] + "</h2><p>"
                                    + pistas[pistaSeleccionada][2] + "</p><p><em>Precio "
                                    + pistas[pistaSeleccionada][3] + " &euro;</em></p>");

                        }
                    %>
                </td>
            </tr>
            <tr>
                <% for (int y = 0, fila = 0; y < 7; y++) { %>
                <td>
                    <table>
                        <tbody>
                            <tr>
                                <td class="tdhora">
                                    <%

                                        calendar.set(java.util.Calendar.DAY_OF_MONTH, hoy + y);
                                        datosFecha = com.reservas.util.Tools.fechaHorario(calendar.getTimeInMillis());
                                        out.write("<div>" + datosFecha[0]
                                                + " - " + datosFecha[1]
                                                + "</div><div>" + datosFecha[2] + " de " + datosFecha[3] + "</div>");
                                        numerosFecha = com.reservas.util.Tools.traducirFecha(calendar.getTimeInMillis());
                                        java.lang.String dd, mm, aaaa;
                                        dd = java.lang.String.format("%02d", numerosFecha[0]);
                                        mm = java.lang.String.format("%02d", numerosFecha[1] + 1);
                                        aaaa = java.lang.String.valueOf(numerosFecha[2]);

                                    %>
                                </td>
                            </tr>
                            <% for (int k = 0; k < 14; k++, fila++) {%>
                            <tr>
                                <td class="cuadrohora">
                                    <a href="reservar.do?reservar=id<%=horarioPistaSeleccionada[QQQ[fila + recorrerDesde]][0] + "&pista=id" + pistas[pistaSeleccionada][0] + "&fech=" + aaaa + "-" + mm + "-" + dd + "s" + horarioPistaSeleccionada[QQQ[fila + recorrerDesde]][2].substring(0, 2)%>"
                                       <%
                                           clase = "";
                                           if (horarioPistaSeleccionada[QQQ[fila + recorrerDesde]][3] != null) {
                                               clase = "  class=\"reservado\" ";
                                           }
                                           if (y == 0) {
                                               horaAux = horarioPistaSeleccionada[QQQ[fila + recorrerDesde]][2];
                                               horaAux = horaAux.substring(0, 2);
                                               if (Integer.parseInt(horaAux) <= hora) {
                                                   clase = "  class=\"fechapasada\" ";
                                               }
                                           }
                                           out.write(clase);


                                       %> 
                                       >&nbsp;<%=horarioPistaSeleccionada[QQQ[fila + recorrerDesde]][2]%>&nbsp;
                                    </a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </td>
                <% }%>
            </tr>
            <tr>
                <td colspan="7">
                    <div id="navegadorpistas">
                        <%
                            if (pistas != null) {
                                out.write("<p>Seleccione otra pista:</p><p>");
                                for (int g = 0; g < pistas.length; g++) {
                                    out.write("&nbsp;&nbsp;<a href=\"inicio.do?verpista=id" + pistas[g][0]
                                            + "\" >" + pistas[g][1] + "</a>&nbsp;&nbsp;&nbsp;&nbsp;");
                                }
                                out.write("</p>");
                            }
                        %>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</div>

