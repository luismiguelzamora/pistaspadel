<%@page language="java" isErrorPage="true" info="Pagina de error" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error!</title>
    </head>
    <body>
        <h1>Reservas pista de padel</h1>
        <h2>Ha ocurrido un error</h2>
        <pre><%
            if (exception != null) {
                out.println("Tipo de error : " + exception.toString() + "\n");
                StackTraceElement[] stes = exception.getStackTrace();
                if (stes.length != 0) {
                    out.println("\n------------\n StackTrace\n------------\n");
                }
                for (StackTraceElement element : stes) {
                    out.println(element);
                }
            }

            java.lang.Throwable th = (java.lang.Throwable) request.getAttribute("javax.servlet.error.exception");
            java.lang.Integer statCode = (java.lang.Integer) request.getAttribute("javax.servlet.error.status_code");
            java.lang.String servlName = (java.lang.String) request.getAttribute("javax.servlet.error.servlet_name");
            java.lang.String requestUr = (java.lang.String) request.getAttribute("javax.servlet.error.request_uri");

            out.println(" ");
            out.println("    status code         :  " + statCode);
            out.println("    servlet name        :  " + servlName);
            out.println("    throwable           :  " + (th != null ? th.getClass().getName() : "null"));
            out.println("    request uri         :  " + requestUr);
            out.println("    message             :  " + (th != null ? th.getMessage() : "null"));

            %></pre>
    </body>
</html>
