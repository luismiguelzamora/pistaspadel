package com.reservas.models;

/**
 *
 * @author miguel
 */
public class Reserva {

    private int id;

    private int horarioId;

    private int usuarioId;

    private int pistaId;

    private String fechaReserva;

    /**
     *
     */
    public Reserva() {
    }

    /**
     *
     * @param id
     * @param horarioId
     * @param usuarioId
     * @param pistaId
     * @param fechaReserva
     */
    public Reserva(int id, int horarioId, int usuarioId, int pistaId, String fechaReserva) {
        this.id = id;
        this.horarioId = horarioId;
        this.usuarioId = usuarioId;
        this.pistaId = pistaId;
        this.fechaReserva = fechaReserva;
    }

    /**
     * Get the value of fechaReserva
     *
     * @return the value of fechaReserva
     */
    public String getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Set the value of fechaReserva
     *
     * @param fechaReserva new value of fechaReserva
     */
    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Get the value of pistaId
     *
     * @return the value of pistaId
     */
    public int getPistaId() {
        return pistaId;
    }

    /**
     * Set the value of pistaId
     *
     * @param pistaId new value of pistaId
     */
    public void setPistaId(int pistaId) {
        this.pistaId = pistaId;
    }

    /**
     * Get the value of usuarioId
     *
     * @return the value of usuarioId
     */
    public int getUsuarioId() {
        return usuarioId;
    }

    /**
     * Set the value of usuarioId
     *
     * @param usuarioId new value of usuarioId
     */
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Get the value of horarioId
     *
     * @return the value of horarioId
     */
    public int getHorarioId() {
        return horarioId;
    }

    /**
     * Set the value of horarioId
     *
     * @param horarioId new value of horarioId
     */
    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    public static boolean cancelarReserva(int idReserva) {
        java.lang.String sql = "DELETE FROM `" + com.reservas.util.Conexion.getDB()
                + "`.`reservas` WHERE `id` = " + idReserva;
        int filasAfectadas = -1;
        filasAfectadas = com.reservas.util.Conexion.ejecutarUpdate(sql);
        if (filasAfectadas < 1) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param fechaReserva
     * @return
     */
    public static boolean existeReserva(java.lang.String fechaReserva) {
        java.lang.String sql = "SELECT * FROM `reservas` WHERE `fecha_reserva` = '" + fechaReserva + "'";
        int rows = com.reservas.util.Conexion.getNumeroFilasSql(sql);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param fechaReserva
     * @return
     */
    public static Reserva getReserva(java.lang.String fechaReserva) {
        java.lang.String[][] filaReserva = com.reservas.util.Conexion.getFila("reservas", "fecha_reserva", fechaReserva);
        if (filaReserva != null) {
            try {
                int id, horarioId, usuarioId, pistaId;
                id = Integer.parseInt(filaReserva[0][1]);
                horarioId = Integer.parseInt(filaReserva[1][1]);
                usuarioId = Integer.parseInt(filaReserva[2][1]);
                pistaId = Integer.parseInt(filaReserva[3][1]);
                return new Reserva(id, horarioId, usuarioId, pistaId, filaReserva[4][1]);
            } catch (java.lang.NumberFormatException nfe) {
                System.out.println(nfe.toString());
            } catch (java.lang.ArrayIndexOutOfBoundsException aioobe) {
                System.out.println(aioobe.toString());
            }
        }
        return null;
    }

    /**
     *
     * @param horarioId
     * @param usuarioId
     * @param pistaId
     * @param fechaReserva
     * @return
     */
    public static boolean insertarReserva(int horarioId, int usuarioId, int pistaId, java.lang.String fechaReserva) {
        int filasAfectadas = -1;
        java.lang.String sql = "INSERT INTO `" + com.reservas.util.Conexion.getDB()
                + "`.`reservas` (`id`, `horario_id`, `usuario_id`, `pista_id`, `fecha_reserva`)  "
                + " VALUES (NULL," + horarioId + ", " + usuarioId + ", " + pistaId + ", '"
                + fechaReserva + "')";
        filasAfectadas = com.reservas.util.Conexion.ejecutarUpdate(sql);
        System.out.println(sql + " ha devuelto " + filasAfectadas + " filas afectadas");
        if (filasAfectadas < 1) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param pistaId
     * @param fechaActual
     * @return
     */
    public static java.lang.String[][] getHorarioPistaSeleccionada(int pistaId, java.lang.String fechaActual) {
        java.lang.String sql
                = " SELECT `horario`.`id` AS `iddiahora`, `horario`.`dia`, `horario`.`hora`, "
                + " `pr`.`id` AS `idreserva`, `pr`.`usuario_id` AS `usuarioid`, "
                + " `pr`.`fecha_reserva` AS `fechareserva` "
                + " FROM `horario` LEFT OUTER JOIN  "
                + " (SELECT * FROM `reservas` WHERE `reservas`.`pista_id` = " + pistaId
                + " AND `reservas`.`fecha_reserva` > '" + fechaActual + "') AS `pr`  "
                + "  ON `horario`.`id` = `pr`.`horario_id` ORDER BY `horario`.`id` ASC  LIMIT 0,98;  ";
        java.lang.String[][] resultado;
        resultado = com.reservas.util.Conexion.getResultSql(sql);
        return resultado;
    }

}
