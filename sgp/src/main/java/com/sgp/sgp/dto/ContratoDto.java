// Paquete donde se ubican los DTOs
package com.sgp.sgp.dto;

// DTO (Data Transfer Object) para representar contratos en las respuestas del backend.
// Se utiliza para enviar solo la información necesaria al frontend,
// evitando exponer directamente la entidad JPA completa.
public class ContratoDto {

    // Nombre completo del empleado (nombre + apellidos)
    private String nombreEmpleado;

    // Tipo de contrato (Ej: Fijo, Temporal, etc.)
    private String tipoContrato;

    // Fecha de inicio del contrato
    private String fechaInicio;

    // --- Getters y Setters ---
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    // Setter que recibe nombre y apellidos concatenados desde el Service
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}


