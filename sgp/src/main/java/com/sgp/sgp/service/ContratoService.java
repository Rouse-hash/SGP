package com.sgp.sgp.service;

import com.sgp.sgp.dto.ContratoDTO;
import com.sgp.sgp.exception.RecursoNoEncontradoException;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Clase de servicio para manejar la lógica de negocio de Contrato.
 * El Controller se comunica con esta capa, y esta capa con el Repository.
 */
@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;

    // Constructor para inyección de dependencias
    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    /*
     * Lista todos los contratos registrados.
     */
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    /*
     * Busca un contrato por su ID.
     * Si no existe, lanza una excepción personalizada.
     */
    public Contrato buscarContratoPorId(Integer idContrato) {
        return contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));
    }

    /*
     * Guarda un contrato nuevo o actualiza uno existente.
     */
    public Contrato guardarContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    /*
     * Elimina un contrato por ID.
     */
    public void eliminarContrato(Integer idContrato) {
        Contrato contrato = buscarContratoPorId(idContrato);
        contratoRepository.delete(contrato);
    }

    /*
     * Convierte un Contrato en ContratoDTO para controlar la salida JSON.
     */
    public ContratoDTO convertirADTO(Contrato contrato) {
        ContratoDTO dto = new ContratoDTO();
        dto.setIdContrato(contrato.getIdContrato());
        dto.setTipoContrato(contrato.getTipoContrato());
        dto.setFechaInicio(contrato.getFechaInicio());
        dto.setFechaFin(contrato.getFechaFin());
        dto.setSalario(contrato.getSalario());

        if (contrato.getEmpleado() != null) {
            dto.setIdEmpleado(contrato.getEmpleado().getIdEmpleado());
            dto.setNombre(contrato.getEmpleado().getNombre());
            dto.setApellidos(contrato.getEmpleado().getApellidos());
            dto.setTipoDocumento(contrato.getEmpleado().getTipoDocumento());
            dto.setNumeroDocumento(contrato.getEmpleado().getNumeroDocumento());
        }

        return dto;
    }

    /*
     * Obtiene un contrato con su empleado cargado usando JOIN FETCH.
     */
    public Contrato obtenerContratoConEmpleado(Integer idContrato) {
        return contratoRepository.findByIdWithEmpleado(idContrato);
    }
}
