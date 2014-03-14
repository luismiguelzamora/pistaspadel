<%

    java.lang.Object objDatosCamposRegistro = request.getAttribute("datosCamposRegistro");
    com.reservas.models.Usuario datosCamposRegistro = new com.reservas.models.Usuario("", "", "");
    if (objDatosCamposRegistro != null && (objDatosCamposRegistro instanceof com.reservas.models.Usuario)) {
        datosCamposRegistro = (com.reservas.models.Usuario) objDatosCamposRegistro;
    }
%>
<form id="registro_form" action="registro.do" method="POST" title="Registro">
    <fieldset>
        <legend>Introduce los siguientes datos</legend>
        <table>
            <tbody>
                <tr>
                    <td><label for="id_nombre">Nombre: </label></td>
                    <td><input type="text" name="nombre" id="id_nombre" value="<%= datosCamposRegistro.getNombre()%>" /></td>
                </tr>
                <tr>
                    <td><label for="id_email">Email: </label></td>
                    <td><input type="text" name="email" id="id_email" value="<%= datosCamposRegistro.getEmail()%>" /></td>
                </tr>
                <tr>
                    <td><label for="id_clave">Clave: </label></td>
                    <td><input type="password" name="clave" id="id_clave" value="<%= datosCamposRegistro.getClave()%>" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="registro" value="Registrar" />&nbsp;&nbsp;
                        <a href="inicio.do" title="volver atr&aacute;s">Volver a inicio</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>
</form>