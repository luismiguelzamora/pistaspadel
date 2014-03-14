package com.reservas.util;

/**
 *
 * @author luis miguel zamora aviles
 */
public class Tools {

    private static final java.lang.String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     *
     * @param campos
     * @return
     */
    public static boolean comprobarCamposObligatorios(java.lang.String... campos) {
        for (String c : campos) {
            if (c != null && c.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param email
     * @return
     */
    public static boolean validarEmail(java.lang.String email) {
        java.util.regex.Pattern pattern;
        java.util.regex.Matcher matcher;
        email = email.toLowerCase();
        pattern = java.util.regex.Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @param str
     * @return
     */
    public static java.lang.String md5(java.lang.String str) {
        java.lang.String strMd5 = str;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(str.getBytes(), 0, str.length());
            strMd5 = (new java.math.BigInteger(1, md.digest())).toString(16);
            //strMd5 = strMd5.replaceAll("\u0027", "\u0027\u0027");
        } catch (java.security.NoSuchAlgorithmException nsae) {
            System.out.println("Error al encriptar clave: " + nsae);
        }
        return strMd5;
    }

    /**
     * <pre>
     *  [0] = dia
     *  [1] = mes
     *  [2] = anio
     *  [3] = hora
     *  [4] = minuto
     *  [5] = segundo
     * </pre>
     *
     * @param fecha
     * @return
     */
    public static int[] traducirFecha(long fecha) {
        java.util.Date date = new java.util.Date(fecha);
        java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(date);
        int dia = calendar.get(java.util.GregorianCalendar.DAY_OF_MONTH);
        int mes = calendar.get(java.util.GregorianCalendar.MONTH);
        int anio = calendar.get(java.util.GregorianCalendar.YEAR);
        int hora = calendar.get(java.util.GregorianCalendar.HOUR_OF_DAY);
        int minuto = calendar.get(java.util.GregorianCalendar.MINUTE);
        int segundo = calendar.get(java.util.GregorianCalendar.SECOND);
        int[] vectorFecha = {dia, mes, anio, hora, minuto, segundo};
        return vectorFecha;
    }

    /**
     *
     * @param mes
     * @return
     */
    public static java.lang.String mes(int mes) {
        if (mes > 11 || mes < 0) {
            return "mes invalido";
        }
        java.lang.String[] meses
                = {
                    "Enero",
                    "Febrero",
                    "Marzo",
                    "Abril",
                    "Mayo",
                    "Junio",
                    "Julio",
                    "Agosto",
                    "Septiembre",
                    "Octubre",
                    "Nobiembre",
                    "Diciembre"
                };
        return meses[mes];
    }

    /**
     * devuelve fecha actual con minutos segundos a 0. Ejemplo
     * <PRE>2014-03-11 12:00:00.0</PRE>
     *
     * @return
     */
    public static java.lang.String getFechaActualSql() {
        java.util.Date date = new java.util.Date();
        java.util.Calendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(date);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        calendar.set(java.util.Calendar.MILLISECOND, 0);
        java.sql.Timestamp timeSQL = new java.sql.Timestamp(calendar.getTimeInMillis());
        return timeSQL.toString();
    }

    /**
     * <pre>
     *   7 domingo
     *   1 lunes
     *   2 martes
     *   3 miercoles
     *   4 jueves
     *   5 viernes
     *   6 sabado
     * </pre>
     *
     * @return
     */
    public static int getDiaSemanaActual() {
        int[] dias = {7, 1, 2, 3, 4, 5, 6};
        java.util.Date date = new java.util.Date();
        java.util.Calendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(date);
        int diaSemana = calendar.get(java.util.Calendar.DAY_OF_WEEK);
        return dias[diaSemana - 1];
    }

    /**
     *
     * <pre>
     *     [0] "Martes"
     *     [1] "11"
     *     [2] "Marzo"
     *     [3] "2014"
     * </pre>
     *
     * @param fecha
     * @return
     */
    public static java.lang.String[] fechaHorario(long fecha) {
        java.lang.String[] resultado = new String[4];
        java.lang.String[] diasSemana = {"Domingo", "Lunes", "Martes", "Mi&eacute;rcoles", "Jueves", "Viernes", "S&aacute;bado"};
        java.util.Date date = new java.util.Date(fecha);
        java.util.Calendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(date);
        int diaSemana = calendar.get(java.util.Calendar.DAY_OF_WEEK) - 1;
        int dia = calendar.get(java.util.GregorianCalendar.DAY_OF_MONTH);
        int mes = calendar.get(java.util.GregorianCalendar.MONTH);
        int anio = calendar.get(java.util.GregorianCalendar.YEAR);
        resultado[0] = diasSemana[diaSemana];
        resultado[1] = String.valueOf(dia);
        resultado[2] = mes(mes);
        resultado[3] = String.valueOf(anio);
        return resultado;
    }
}
