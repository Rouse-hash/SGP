package com.sgp.sgp.service;

import java.util.List;
import com.sgp.sgp.dto.EmpleadoDto;
import com.sgp.sgp.model.Empleado;

/*
    Interfaz que define las operaciones del módulo Empleado usando DTO.
*/
public interface EmpleadoService {

    /*
        Lista todos los empleados en formato DTO.
    */
    List<EmpleadoDto> listarEmpleados();

    /*
        Busca un empleado por ID y lo devuelve como DTO.
    */
    EmpleadoDto buscarEmpleadoPorId(Long idEmpleado);

    /*
        Crea un nuevo empleado y devuelve su DTO.
    */
    EmpleadoDto crearEmpleado(Empleado empleado);

    /*
        Actualiza un empleado existente y devuelve su DTO.
    */
    EmpleadoDto actualizarEmpleado(Long idEmpleado, Empleado empleado);

    /*
        Elimina un empleado por ID.
    */
    void eliminarEmpleado(Long idEmpleado);
}


