package com.reservas.models;

/**
 *
 * @author luis miguel zamora aviles
 */
public class Horario {

    private int id;

    private String dia;

    private String hora;

    /**
     *
     */
    public Horario() {
    }

    /**
     *
     * @param id
     * @param dia
     * @param hora
     */
    public Horario(int id, String dia, String hora) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
    }

    /**
     * Get the value of hora
     *
     * @return the value of hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Set the value of hora
     *
     * @param hora new value of hora
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Get the value of dia
     *
     * @return the value of dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * Set the value of dia
     *
     * @param dia new value of dia
     */
    public void setDia(String dia) {
        this.dia = dia;
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

    /**
     *
     * @param id
     * @return
     */
    public static Horario getHorario(int id) {
        java.lang.String[][] filaHorario = com.reservas.util.Conexion.getFila("horario", "id", String.valueOf(id));
        if (filaHorario != null) {
            return new Horario(id, filaHorario[1][1], filaHorario[2][1]);
        }
        return null;
    }

}
