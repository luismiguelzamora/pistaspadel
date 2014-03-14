<%

    java.lang.Object objUsuarioConectado = request.getAttribute("usuarioConectado");
    com.reservas.models.Usuario usuarioConectado = new com.reservas.models.Usuario("", "", "");
    if (objUsuarioConectado != null && (objUsuarioConectado instanceof com.reservas.models.Usuario)) {
        usuarioConectado = (com.reservas.models.Usuario) objUsuarioConectado;
    }
    int[] ua = com.reservas.util.Tools.traducirFecha(session.getLastAccessedTime());
%>
<div id="usuarioconectado">
    <p>
        <em>Bienvenido <%=usuarioConectado.getNombre()%></em>
        &nbsp;&nbsp;
        <span style="float: right;">[<a href="logout.do">Cerrar sesi&oacute;n</a>]</span>
    </p>
    <p>Tu email es <strong><%=usuarioConectado.getEmail()%></strong></p>
    <p>
        La fecha de tu &uacute;ltimo acceso: El d&iacute;a 
        <%= ua[0] + " de " + com.reservas.util.Tools.mes(ua[1]) + " del " + ua[2] + " a las "
                + java.lang.String.format("%02d:%02d:%02d", ua[3], ua[4], ua[5]) + " horas."%>
    </p>
</div>