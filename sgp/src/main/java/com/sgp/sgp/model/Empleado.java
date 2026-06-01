package com.sgp.sgp.model;
// Importa la anotación Entity
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import java.util.List;


import java.sql.Date;

import jakarta.persistence.Column;

/*
    @Entity indica que esta clase
    representa una tabla en la base de datos.
*/
@Entity
@Table(name = "empleado")
public class Empleado {

    // Llave primaria: corresponde a la columna id_Empleado en MySQL
    // AUTO_INCREMENT en MySQL se traduce como GenerationType.IDENTITY en JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Empleado")
    private Long idEmpleado;

    // Columna "nombre" en MySQL → atributo nombre en Java
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    // Columna "apellidos" en MySQL → atributo apellidos en Java
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    // Columna "tipoDocumento" en MySQL → atributo tipoDocumento en Java
    @Column(name = "tipoDocumento", length = 45)
    private String tipoDocumento;

    // Columna "numeroDocumento" en MySQL → atributo numeroDocumento en Java
    @Column(name = "numeroDocumento", length = 20)
    private String numeroDocumento;

    // Columna "fechaNacimiento" en MySQL → atributo fechaNacimiento en Java
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;

    // Columna "estadoCivil" en MySQL → atributo estadoCivil en Java
    @Column(name = "estadoCivil", length = 20)
    private String estadoCivil;

 // Relación uno-a-muchos con Contrato
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Contrato> contratos;

    // ====================
    // GETTERS Y SETTERS
    // ====================

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}

