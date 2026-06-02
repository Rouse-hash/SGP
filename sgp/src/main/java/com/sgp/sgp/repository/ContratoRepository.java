package com.sgp.sgp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sgp.sgp.model.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    /*
        Busca todos los contratos asociados a un empleado por su ID.
        Spring Data JPA interpreta "EmpleadoIdEmpleado" como:
        Contrato → empleado → idEmpleado
    */
    List<Contrato> findByEmpleadoIdEmpleado(Long idEmpleado);

    /*
        Consulta todos los contratos con su empleado cargado (JOIN FETCH).
        Evita problemas de LazyInitialization y asegura que el empleado
        venga incluido en la respuesta.
    */
    @org.springframework.data.jpa.repository.Query("SELECT c FROM Contrato c JOIN FETCH c.empleado")
    List<Contrato> findAllWithEmpleado();

    /*
        Busca un contrato específico con su empleado cargado.
        Útil cuando necesitas el contrato y su relación en una sola consulta.
    */
    @org.springframework.data.jpa.repository.Query("SELECT c FROM Contrato c JOIN FETCH c.empleado WHERE c.idContrato = :id")
    Contrato findByIdWithEmpleado(@org.springframework.data.repository.query.Param("id") Long id);
}

