// Paquete donde se ubican los DTOs
package com.sgp.sgp.dto;

/*
    DTO (Data Transfer Object) para representar empleados en las respuestas del backend.
    Se utiliza para enviar solo la información necesaria al frontend,
    evitando exponer directamente la entidad JPA completa.
*/
public class EmpleadoDto {

    // Identificador único del empleado
    private Long idEmpleado;

    // Nombre del empleado
    private String nombre;

    // Apellidos del empleado
    private String apellidos;

    // Tipo de documento (Ej: CC, TI, Pasaporte, etc.)
    private String tipoDocumento;

    // Número de documento
    private String numeroDocumento;

    // --- Getters y Setters ---
    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
