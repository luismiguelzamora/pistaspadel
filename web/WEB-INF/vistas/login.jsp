<%

    java.lang.Object objDatosCamposLogin = request.getAttribute("datosCamposLogin");
    com.reservas.models.Usuario datosCamposLogin = new com.reservas.models.Usuario("", "", "");
    if (objDatosCamposLogin != null && (objDatosCamposLogin instanceof com.reservas.models.Usuario)) {
        datosCamposLogin = (com.reservas.models.Usuario) objDatosCamposLogin;
    }
%>
<form id="login_form" action="login.do" method="POST" autocomplete="off" title="Accede para poder reservar una pista">
    <fieldset>
        <legend>Introduce tu email y contrase&ntilde;a</legend>
        <table>
            <tbody>
                <tr>
                    <td>
                        <div>
                            <label for="id_email">Email:</label>
                        </div>
                        <div>
                            <input type="text" name="email" id="id_email" value="<%=datosCamposLogin.getEmail()%>" />
                        </div>
                    </td>
                    <td>
                        <div>
                            <label for="id_clave">Clave:</label>
                        </div>
                        <div>
                            <input type="password" name="clave" id="id_clave" value="<%=datosCamposLogin.getClave()%>" />
                        </div>
                    </td>
                    <td>
                        <div>
                            &nbsp;
                        </div>
                        <div>
                            <input type="submit" name="login" value="Login" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" value="recordar" name="recordar_login" id="id_recordar_login" />
                        <label for="id_recordar_login"> No cerrar sesi&oacute;n</label>
                    </td>
                    <td colspan="2">
                        <a href="registro.do">Reg&iacute;strate</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>
</form>