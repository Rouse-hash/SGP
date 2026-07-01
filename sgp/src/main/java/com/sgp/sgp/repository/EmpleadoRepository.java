package com.sgp.sgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sgp.sgp.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    /*
        Aquí puedes agregar métodos personalizados si lo necesitas.
        Por ejemplo:
        Optional<Empleado> findByCorreo(String correo);
    */
}

