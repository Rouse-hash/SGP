package com.sgp.sgp.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sgp.sgp.dto.EmpleadoDto;
import com.sgp.sgp.exception.RecursoNoEncontradoException;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.EmpleadoRepository;

/*
    Implementación de la lógica de negocio para Empleado usando DTO.
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
        Convierte una entidad Empleado en EmpleadoDTO.
    */
    private EmpleadoDto convertirADTO(Empleado empleado) {
        EmpleadoDto dto = new EmpleadoDto();
        dto.setIdEmpleado(empleado.getIdEmpleado());
        dto.setNombre(empleado.getNombre());
        dto.setApellidos(empleado.getApellidos());
        dto.setTipoDocumento(empleado.getTipoDocumento());
        dto.setNumeroDocumento(empleado.getNumeroDocumento());
        return dto;
    }

    /*
        Lista todos los empleados registrados en formato DTO.
    */
    @Override
    public List<EmpleadoDto> listarEmpleados() {
        return empleadoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /*
        Busca un empleado por su ID y lo devuelve como DTO.
        Si no existe, lanza una excepción personalizada.
    */
    @Override
    public EmpleadoDto buscarEmpleadoPorId(Long idEmpleado) {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));
        return convertirADTO(empleado);
    }

    /*
        Crea un nuevo empleado y devuelve su DTO.
    */
    @Override
    public EmpleadoDto crearEmpleado(Empleado empleado) {
        Empleado nuevo = empleadoRepository.save(empleado);
        return convertirADTO(nuevo);
    }

    /*
        Actualiza un empleado existente y devuelve su DTO.
    */
    @Override
    public EmpleadoDto actualizarEmpleado(Long idEmpleado, Empleado empleado) {
        Empleado existente = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        existente.setNombre(empleado.getNombre());
        existente.setApellidos(empleado.getApellidos());
        existente.setTipoDocumento(empleado.getTipoDocumento());
        existente.setNumeroDocumento(empleado.getNumeroDocumento());
        existente.setFechaNacimiento(empleado.getFechaNacimiento());
        existente.setEstadoCivil(empleado.getEstadoCivil());

        Empleado actualizado = empleadoRepository.save(existente);
        return convertirADTO(actualizado);
    }

    /*
        Elimina un empleado por ID.
    */
    @Override
    public void eliminarEmpleado(Long idEmpleado) {
        Empleado existente = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));
        empleadoRepository.delete(existente);
    }
}

