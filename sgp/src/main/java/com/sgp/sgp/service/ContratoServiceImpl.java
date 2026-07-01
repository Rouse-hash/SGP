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
*/
@Service
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final EmpleadoRepository empleadoRepository;

    public ContratoServiceImpl(ContratoRepository contratoRepository, EmpleadoRepository empleadoRepository) {
        this.contratoRepository = contratoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    private ContratoDto convertirADTO(Contrato contrato) {
        ContratoDto dto = new ContratoDto();
        dto.setNombreEmpleado(contrato.getEmpleado().getNombre());
        dto.setTipoContrato(contrato.getTipoContrato());
        dto.setFechaInicio(contrato.getFechaInicio().toString());
        return dto;
    }

    @Override
    public List<ContratoDto> listarContratos() {
        return contratoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContratoDto buscarContratoPorId(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));
        return convertirADTO(contrato);
    }

    @Override
    public List<ContratoDto> listarContratosPorEmpleado(Long idEmpleado) {
        return contratoRepository.findByEmpleado_IdEmpleado(idEmpleado) // 
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContratoDto crearContrato(Long idEmpleado, Contrato contrato) {
        // Validar que el empleado exista
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        // Asociar el empleado al contrato
        contrato.setEmpleado(empleado);

        Contrato nuevo = contratoRepository.save(contrato);
        return convertirADTO(nuevo);
    }

    @Override
    public ContratoDto actualizarContrato(Long idContrato, Long idEmpleado, Contrato contrato) {
        Contrato existente = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));

        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        existente.setTipoContrato(contrato.getTipoContrato());
        existente.setFechaInicio(contrato.getFechaInicio());
        existente.setFechaFin(contrato.getFechaFin());
        existente.setSalario(contrato.getSalario());
        existente.setEmpleado(empleado);

        Contrato actualizado = contratoRepository.save(existente);
        return convertirADTO(actualizado);
    }

    @Override
    public void eliminarContrato(Long idContrato) {
        Contrato existente = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));
        contratoRepository.delete(existente);
    }
}
