package com.reservas.util;

/**
 *
 * @author luis miguel zamora aviles
 */
public class Conexion {

    private static final java.lang.String DB = "pistaspadelreservas";
    private static final java.lang.String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final java.lang.String DB_URL = "jdbc:mysql://localhost:3306/" + DB;
    private static final java.lang.String USER = "root";
    private static final java.lang.String PASS = "";

    private static java.sql.Connection conn = null;
    private static java.sql.Statement stmt = null;
    private static java.sql.ResultSet rs = null;

    /**
     *
     * @return
     */
    public static java.lang.String getDB() {
        return DB;
    }

    /**
     *
     * @return
     */
    private static boolean conectar() {
        boolean conectado = false;

        try {
            if (conn != null && !conn.isClosed()) {
                return true;
            }
            Class.forName(JDBC_DRIVER);
            conn = java.sql.DriverManager.getConnection(DB_URL, USER, PASS);
            conectado = true;

        } catch (java.lang.ClassNotFoundException cnfe) {
            System.out.println(cnfe.toString());
        } catch (java.sql.SQLException sqle) {
            System.out.println(sqle.toString());
        }
        return conectado;
    }

    /**
     *
     */
    private static void desconectar() {
        if (conn == null) {
            return;
        }
        try {
            if (conn.isClosed()) {
                return;
            }
            conn.close();

        } catch (java.sql.SQLException sqle) {
            System.out.println(sqle.toString());
        } finally {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (java.sql.SQLException sqle) {
                System.out.println(sqle);
            }
        }
    }

    /**
     *
     * @param sql
     * @return
     */
    public static java.lang.String[][] getResultSql(java.lang.String sql) {
        java.lang.String[][] resultado = null;
        java.sql.ResultSetMetaData rsmd;
        int filas, columnas;
        String[] nombresColumnas;
        if (conectar()) {
            try {
                stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT);
                rs = stmt.executeQuery(sql);
                rsmd = rs.getMetaData();
                columnas = rsmd.getColumnCount();
                nombresColumnas = new String[columnas];
                for (int e = 0; e < columnas; e++) {
                    nombresColumnas[e] = rsmd.getColumnLabel(e + 1);
                }
                rs.last();
                filas = rs.getRow();
                resultado = new java.lang.String[filas][columnas];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    for (int p = 0; p < columnas; p++) {
                        resultado[i][p] = rs.getString(nombresColumnas[p]);
                    }
                    i++;
                }

                stmt.close();
                rs.close();
            } catch (java.sql.SQLException sqle) {
                System.out.println(sqle.toString());
            } finally {
                try {
                    if (stmt != null && !stmt.isClosed()) {
                        stmt.close();
                    }
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                } catch (java.sql.SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
        }
        desconectar();
        return resultado;
    }

    /**
     *
     * <pre>
     *    ejemplo
     *    getFila("usuarios", "email", "nmcqmh_lmza@hotmail.com");
     *    devuelve:
     *    [0][0] = "id"      [0][1] = "10"
     *    [1][0] = "email"   [1][1] = "nmcqmh_lmza@hotmail.com"
     *    [2][0] = "clave"   [2][1] = "jhd763ggdhd8hh4jf87"
     *    [3][0] = "nombre"  [3][1] = "luis miguel"
     * </pre>
     *
     * @param tabla
     * @param key
     * @param value
     * @return
     */
    public static java.lang.String[][] getFila(java.lang.String tabla, java.lang.String key, java.lang.String value) {
        java.lang.String[][] resultado = null;
        java.lang.String sql = "SELECT * FROM `" + tabla + "` WHERE `" + key + "` = '" + value + "' LIMIT 0, 5";
        java.sql.ResultSetMetaData rsmd;
        java.lang.String nombreColumna;
        int columnas;
        if (conectar()) {
            try {
                stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT);
                rs = stmt.executeQuery(sql);
                rsmd = rs.getMetaData();
                columnas = rsmd.getColumnCount();
                if (rs.next()) {
                    resultado = new java.lang.String[columnas][2];
                    for (int y = 0; y < columnas; y++) {
                        nombreColumna = rsmd.getColumnLabel(y + 1);
                        resultado[y][0] = nombreColumna;
                        resultado[y][1] = rs.getString(nombreColumna);
                    }
                }

                stmt.close();
                rs.close();
            } catch (java.sql.SQLException sqle) {
                System.out.println(sqle.toString());
            } finally {
                try {
                    if (stmt != null && !stmt.isClosed()) {
                        stmt.close();
                    }
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                } catch (java.sql.SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
        }
        desconectar();
        return resultado;
    }

    /**
     *
     * @param sql
     * @return
     */
    public static int getNumeroFilasSql(java.lang.String sql) {
        int numeroFilas = -1;
        if (conectar()) {
            try {
                stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT);
                rs = stmt.executeQuery(sql);
                if (rs.last()) {
                    numeroFilas = rs.getRow();
                } else {
                    numeroFilas = 0;
                }

                stmt.close();
                rs.close();
            } catch (java.sql.SQLException sqle) {
                System.out.println(sqle.toString());
            } finally {
                try {
                    if (stmt != null && !stmt.isClosed()) {
                        stmt.close();
                    }
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                } catch (java.sql.SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
        }
        desconectar();
        return numeroFilas;
    }

    /**
     *
     * @param sql
     * @return
     */
    public static int ejecutarUpdate(java.lang.String sql) {
        int filasAfectadas = -1;
        if (conectar()) {
            try {
                stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT);
                filasAfectadas = stmt.executeUpdate(sql);

                stmt.close();
            } catch (java.sql.SQLTimeoutException sqlte) {
                System.out.println(sqlte.toString());
            } catch (java.sql.SQLException sqle) {
                System.out.println(sqle.toString());
            } finally {
                try {
                    if (stmt != null && !stmt.isClosed()) {
                        stmt.close();
                    }
                } catch (java.sql.SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
        }
        desconectar();
        return filasAfectadas;
    }
}
