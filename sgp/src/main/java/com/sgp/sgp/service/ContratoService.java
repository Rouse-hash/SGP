package com.sgp.sgp.service;

import java.util.List;
import com.sgp.sgp.dto.ContratoDto;
import com.sgp.sgp.model.Contrato;

/*
    Interfaz que define las operaciones del módulo Contrato usando DTO.
*/
public interface ContratoService {

    /*
        Lista todos los contratos en formato DTO.
    */
    List<ContratoDto> listarContratos();

    /*
        Busca un contrato por ID y lo devuelve como DTO.
    */
    ContratoDto buscarContratoPorId(Long idContrato);

    /*
        Lista contratos por empleado en formato DTO.
    */
    List<ContratoDto> listarContratosPorEmpleado(Long idEmpleado);

    /*
        Crea un contrato asociado a un empleado existente y devuelve su DTO.
    */
    ContratoDto crearContrato(Long idEmpleado, Contrato contrato);

    /*
        Actualiza un contrato existente y devuelve su DTO.
    */
    ContratoDto actualizarContrato(Long idContrato, Long idEmpleado, Contrato contrato);

    /*
        Elimina un contrato por ID.
    */
    void eliminarContrato(Long idContrato);
}




