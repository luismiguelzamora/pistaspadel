package com.reservas.models;

/**
 *
 * @author luis miguel zamora aviles
 */
public class Usuario {

    private int id;

    private String email;

    private String clave;

    private String nombre;

    /**
     *
     */
    public Usuario() {
    }

    /**
     *
     * @param id
     * @param email
     * @param clave
     * @param nombre
     */
    public Usuario(int id, String email, String clave, String nombre) {
        this.id = id;
        this.email = email;
        this.clave = clave;
        this.nombre = nombre;
    }

    /**
     *
     * @param email
     * @param clave
     * @param nombre
     */
    public Usuario(String email, String clave, String nombre) {
        this.email = email;
        this.clave = clave;
        this.nombre = nombre;
    }

    /**
     *
     * @param email
     * @param clave
     */
    public Usuario(String email, String clave) {
        this.email = email;
        this.clave = clave;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of clave
     *
     * @return the value of clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Set the value of clave
     *
     * @param clave new value of clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param email
     * @return
     */
    public static boolean existeEmail(java.lang.String email) {
        email = email.toLowerCase().trim();

        // evita inyeccion sql
        email = email.replaceAll("\u0027", "\u0027\u0027");

        java.lang.String sql = "SELECT * FROM `usuarios` WHERE `email` = '" + email + "'";
        int rows = com.reservas.util.Conexion.getNumeroFilasSql(sql);
        if (rows > 0) {
            return true;

        }
        return false;
    }

    /**
     *
     * @param email
     * @return
     */
    public static Usuario getUsuario(java.lang.String email) {
        java.lang.String[][] filaUsuario = com.reservas.util.Conexion.getFila("usuarios", "email", email);
        if (filaUsuario != null) {
            return new Usuario(Integer.parseInt(filaUsuario[0][1]), filaUsuario[1][1], filaUsuario[2][1], filaUsuario[3][1]);
        }
        return null;
    }

    /**
     *
     * @param email
     * @param clave
     * @return
     */
    public static boolean esLoginCorrecto(java.lang.String email, java.lang.String clave) {
        java.lang.String claveMd5 = clave;
        email = email.toLowerCase().trim();

        // evita inyeccion sql
        email = email.replaceAll("\u0027", "\u0027\u0027");

        // encripto clave con md5
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(clave.getBytes(), 0, clave.length());
            claveMd5 = (new java.math.BigInteger(1, md.digest())).toString(16);
            claveMd5 = claveMd5.replaceAll("\u0027", "\u0027\u0027");
        } catch (java.security.NoSuchAlgorithmException nsae) {
            System.out.println("Error al encriptar clave: " + nsae);
        }

        java.lang.String sql = "SELECT * FROM `usuarios` WHERE `email` = '"
                + email + "' AND `clave` = '" + claveMd5 + "'";
        int rows = com.reservas.util.Conexion.getNumeroFilasSql(sql);
        if (rows > 0) {
            return true;

        }
        return false;
    }

    /**
     *
     * @param email
     * @param clave
     * @param nombre
     * @return boolean
     */
    public static boolean insertarUsuario(java.lang.String email, java.lang.String clave, java.lang.String nombre) {
        java.lang.String claveMd5 = clave;
        email = email.toLowerCase().trim();

        // evita inyeccion sql
        email = email.replaceAll("\u0027", "\u0027\u0027");
        nombre = nombre.replaceAll("\u0027", "\u0027\u0027");

        // encripto clave con md5
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(clave.getBytes(), 0, clave.length());
            claveMd5 = (new java.math.BigInteger(1, md.digest())).toString(16);
            claveMd5 = claveMd5.replaceAll("\u0027", "\u0027\u0027");
        } catch (java.security.NoSuchAlgorithmException nsae) {
            System.out.println("Error al encriptar clave: " + nsae);
        }

        int filasAfectadas = -1;
        java.lang.String sql = "INSERT INTO `" + com.reservas.util.Conexion.getDB()
                + "`.`usuarios` (`id`, `email`, `clave`, `nombre`) VALUES (NULL, '"
                + email + "', '" + claveMd5 + "', '" + nombre + "')";
        filasAfectadas = com.reservas.util.Conexion.ejecutarUpdate(sql);
        System.out.println(sql + " ha devuelto " + filasAfectadas + " filas afectadas");
        if (filasAfectadas < 1) {
            return false;
        }
        return true;
    }
}
