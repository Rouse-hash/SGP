package com.sgp.sgp.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato") // PK en la tabla contrato
    private Long idContrato;

    @Column(name = "tipo_contrato", length = 50)
    private String tipoContrato;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "salario")
    private Double salario;

    /*
        Relación con Empleado:
        - Muchos contratos pertenecen a un solo empleado.
        - Usamos @JsonBackReference para evitar ciclos infinitos
          cuando devolvemos empleados con contratos.
    */
    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Empleado empleado;

    // --- Getters y Setters ---
    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}

