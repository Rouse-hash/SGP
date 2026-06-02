package com.sgp.sgp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sgp.sgp.exception.RecursoNoEncontradoException;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.EmpleadoRepository;

/*
    Implementación de la lógica de negocio para Empleado.
*/
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    /*
        Repository de empleados.
    */
    private final EmpleadoRepository empleadoRepository;

    /*
        Constructor para inyección de dependencias.
    */
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /*
        Lista todos los empleados registrados.
    */
    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    /*
        Busca un empleado por su ID.
        Si no existe, lanza una excepción personalizada.
    */
    @Override
    public Empleado buscarEmpleadoPorId(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));
    }

    /*
        Crea un nuevo empleado.
    */
    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /*
        Actualiza un empleado existente.
    */
    @Override
    public Empleado actualizarEmpleado(Long idEmpleado, Empleado empleado) {
        Empleado existente = buscarEmpleadoPorId(idEmpleado);

        existente.setNombre(empleado.getNombre());
        existente.setApellidos(empleado.getApellidos());
        existente.setTipoDocumento(empleado.getTipoDocumento());
        existente.setNumeroDocumento(empleado.getNumeroDocumento());
        existente.setFechaNacimiento(empleado.getFechaNacimiento());
        existente.setEstadoCivil(empleado.getEstadoCivil());

        return empleadoRepository.save(existente);
    }

    /*
        Elimina un empleado por ID.
    */
    @Override
    public void eliminarEmpleado(Long idEmpleado) {
        Empleado existente = buscarEmpleadoPorId(idEmpleado);
        empleadoRepository.delete(existente);
    }
}
