package com.sgp.sgp.model;

// Importa las clases necesarias
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

/*
    @Entity indica que esta clase representa
    una tabla en la base de datos.
*/
@Entity
@Table(name = "contrato")
public class Contrato {

    // Llave primaria: corresponde a la columna idContrato en MySQL
    // AUTO_INCREMENT en MySQL se traduce como GenerationType.IDENTITY en JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContrato")
    private Integer idContrato;

    // Columna "tipo_contrato" en MySQL → atributo tipoContrato en Java
    @Column(name = "tipo_contrato", nullable = false, length = 50)
    private String tipoContrato;

    // Columna "fecha_inicio" en MySQL → atributo fechaInicio en Java
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    // Columna "fecha_fin" en MySQL → atributo fechaFin en Java
    @Column(name = "fecha_fin")
    private Date fechaFin;

    // Columna "salario" en MySQL → atributo salario en Java
    @Column(name = "salario")
    private BigDecimal salario;

    // Relación muchos-a-uno con la tabla empleado
    // La columna "id_Empleado" en contrato apunta a "id_Empleado" en empleado
    // Ignora propiedades internas de Hibernate y evita ciclos infinitos
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contratos"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    // ====================
    // GETTERS Y SETTERS
    // ====================

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
