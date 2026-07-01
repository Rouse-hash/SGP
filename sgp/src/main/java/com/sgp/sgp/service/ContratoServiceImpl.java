package com.sgp.sgp.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sgp.sgp.dto.ContratoDto;
import com.sgp.sgp.exception.RecursoNoEncontradoException;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;

/*
    Implementación de la lógica de negocio para Contrato usando DTO.
    Se encarga de convertir entidades Contrato en objetos ContratoDto
    y de manejar las operaciones CRUD con validaciones.
*/
@Service
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final EmpleadoRepository empleadoRepository;

    // Constructor con inyección de dependencias
    public ContratoServiceImpl(ContratoRepository contratoRepository, EmpleadoRepository empleadoRepository) {
        this.contratoRepository = contratoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    /*
        Método privado para convertir una entidad Contrato en un ContratoDto.
        Aquí concatenamos nombre + apellidos del empleado asociado.
    */
    private ContratoDto convertirADTO(Contrato contrato) {
        ContratoDto dto = new ContratoDto();
        dto.setNombreEmpleado(
            contrato.getEmpleado().getNombre() + " " + contrato.getEmpleado().getApellidos()
        );
        dto.setTipoContrato(contrato.getTipoContrato());
        dto.setFechaInicio(contrato.getFechaInicio().toString());
        return dto;
    }

    /*
        Listar todos los contratos registrados en la base de datos.
        Retorna una lista de ContratoDto.
    */
    @Override
    public List<ContratoDto> listarContratos() {
        return contratoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /*
        Buscar un contrato por su ID.
        Si no existe, lanza una excepción personalizada.
    */
    @Override
    public ContratoDto buscarContratoPorId(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));
        return convertirADTO(contrato);
    }

    /*
        Listar contratos asociados a un empleado específico.
        Se busca por el ID del empleado.
    */
    @Override
    public List<ContratoDto> listarContratosPorEmpleado(Long idEmpleado) {
        return contratoRepository.findByEmpleado_IdEmpleado(idEmpleado)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /*
        Crear un nuevo contrato asociado a un empleado.
        Primero se valida que el empleado exista.
    */
    @Override
    public ContratoDto crearContrato(Long idEmpleado, Contrato contrato) {
        // Validar que el empleado exista
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        // Asociar el empleado al contrato
        contrato.setEmpleado(empleado);

        // Guardar contrato en la base de datos
        Contrato nuevo = contratoRepository.save(contrato);
        return convertirADTO(nuevo);
    }

    /*
        Actualizar un contrato existente.
        Se valida tanto el contrato como el empleado.
    */
    @Override
    public ContratoDto actualizarContrato(Long idContrato, Long idEmpleado, Contrato contrato) {
        // Validar que el contrato exista
        Contrato existente = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));

        // Validar que el empleado exista
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        // Actualizar datos del contrato
        existente.setTipoContrato(contrato.getTipoContrato());
        existente.setFechaInicio(contrato.getFechaInicio());
        existente.setFechaFin(contrato.getFechaFin());
        existente.setSalario(contrato.getSalario());
        existente.setEmpleado(empleado);

        // Guardar cambios
        Contrato actualizado = contratoRepository.save(existente);
        return convertirADTO(actualizado);
    }

    /*
        Eliminar un contrato por su ID.
        Si no existe, lanza excepción.
    */
    @Override
    public void eliminarContrato(Long idContrato) {
        Contrato existente = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));
        contratoRepository.delete(existente);
    }
}

