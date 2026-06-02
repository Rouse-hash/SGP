package com.sgp.sgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sgp.sgp.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    /*
        Aquí puedes agregar métodos personalizados si lo necesitas.
        Por ejemplo:
        Optional<Empleado> findByCorreo(String correo);
    */
}

