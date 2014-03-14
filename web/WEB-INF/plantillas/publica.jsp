<%@page language="java" errorPage="error.jsp" isErrorPage="false" info="Pagina para usuario no conectado" contentType="text/html" pageEncoding="UTF-8"%>
<%!
    final java.lang.String VISTAS = "../vistas/";
%>
<%
    java.lang.Object objVista = request.getAttribute("vista");
    java.lang.String vista = VISTAS + "default.jsp";
    if (objVista != null && (objVista instanceof java.lang.String)) {
        vista = VISTAS + (java.lang.String) objVista;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%
            java.lang.Object objTitulo = request.getAttribute("titulo");
            java.lang.String titulo = "Reservas pistas";
            if (objTitulo != null && (objTitulo instanceof java.lang.String)) {
                titulo = (java.lang.String) objTitulo;
            }
            out.print(titulo);
            %></title>
        <script type="text/javascript" src="js/script.js"></script>
        <link href="favicon.ico" type="image/x-icon" rel="icon" />
        <link href="favicon.ico" type="image/x-icon" rel="shortcut icon" />
        <link href="css/screen.css" rel="stylesheet" type="text/css" media="all" />
    </head>
    <body>
        <div id="wrapper_container">
            <div id="container">
                <div id="wrapper_header">
                    <div id="header">
                        <h1>Reservas pistas de padel</h1>
                        <%
                            java.lang.Object objMensaje = request.getAttribute("mensaje");
                            java.lang.Object objTipoMensaje = request.getAttribute("tipoMensaje");
                            java.lang.String mensaje = "", clase = null;
                            if (objMensaje != null && (objMensaje instanceof java.lang.String)) {
                                mensaje = (java.lang.String) objMensaje;
                                clase = "err";
                                if (objTipoMensaje != null && (objTipoMensaje instanceof java.lang.String)) {
                                    clase = (java.lang.String) objTipoMensaje;
                                }
                            }
                            out.print("<div id=\"mensaje\""
                                    + (clase == null ? ""
                                    : clase.equals("err") ? " class=\"mensaje_err\" "
                                    : " class=\"mensaje_inf\" ") + ">" + mensaje + "</div>");

                            java.lang.Object objLogin = request.getAttribute("mostrarLogin");
                            java.lang.Boolean mostrarLogin = false;
                            if (objLogin != null && (objLogin instanceof java.lang.Boolean)) {
                                mostrarLogin = (java.lang.Boolean) objLogin;
                            }
                            if (mostrarLogin.booleanValue()) {
                                pageContext.include(VISTAS + "login.jsp");
                            } else if (!vista.equals(VISTAS + "registro.jsp")) {
                                pageContext.include(VISTAS + "usuario.jsp");
                            }
                        %>
                    </div>
                </div>
                <div id="wrapper_content">
                    <div id="content">
                        <%
                            pageContext.include(vista);
                        %>
                    </div>
                </div>
                <div id="wrapper_footer">
                    <div id="footer">
                        <p>webmaster : nmcqmh_lmza@hotmail.com</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
