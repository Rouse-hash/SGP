package com.sgp.sgp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sgp.sgp.exception.RecursoNoEncontradoException;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.EmpleadoRepository;

/*
    Implementación de la lógica de negocio para Empleado
    trabajando directamente con la entidad JPA.
    Se encarga de manejar las operaciones CRUD con validaciones.
*/
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    /*
        Repository de empleados para acceder a la base de datos.
    */
    private final EmpleadoRepository empleadoRepository;

    /*
        Constructor para inyección de dependencias.
    */
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /*
        Lista todos los empleados registrados en la base de datos.
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
        Se valida que el empleado exista antes de modificarlo.
    */
    @Override
    public Empleado actualizarEmpleado(Long idEmpleado, Empleado empleado) {
        Empleado existente = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        // Actualizar datos básicos
        existente.setNombre(empleado.getNombre());
        existente.setApellidos(empleado.getApellidos());
        existente.setTipoDocumento(empleado.getTipoDocumento());
        existente.setNumeroDocumento(empleado.getNumeroDocumento());
        existente.setFechaNacimiento(empleado.getFechaNacimiento());
        existente.setEstadoCivil(empleado.getEstadoCivil());

        // Guardar cambios
        return empleadoRepository.save(existente);
    }

    /*
        Elimina un empleado por ID.
        Si no existe, lanza excepción.
    */
    @Override
    public void eliminarEmpleado(Long idEmpleado) {
        Empleado existente = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));
        empleadoRepository.delete(existente);
    }
}



