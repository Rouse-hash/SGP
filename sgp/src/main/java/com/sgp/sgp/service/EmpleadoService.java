package com.sgp.sgp.service;

import java.util.List;
import com.sgp.sgp.model.Empleado;

/*
    Interfaz que define las operaciones del módulo Empleado.
*/
public interface EmpleadoService {

    /*
        Lista todos los empleados.
    */
    List<Empleado> listarEmpleados();

    /*
        Busca un empleado por ID.
    */
    Empleado buscarEmpleadoPorId(Long idEmpleado);

    /*
        Crea un nuevo empleado.
    */
    Empleado crearEmpleado(Empleado empleado);

    /*
        Actualiza un empleado existente.
    */
    Empleado actualizarEmpleado(Long idEmpleado, Empleado empleado);

    /*
        Elimina un empleado por ID.
    */
    void eliminarEmpleado(Long idEmpleado);
}

