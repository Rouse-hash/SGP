package com.sgp.sgp.service;

import java.util.List;
import com.sgp.sgp.model.Contrato;

/*
    Interfaz que define las operaciones del módulo Contrato.
*/
public interface ContratoService {

    /*
        Lista todos los contratos.
    */
    List<Contrato> listarContratos();

    /*
        Busca un contrato por ID.
    */
    Contrato buscarContratoPorId(Long idContrato);

    /*
        Lista contratos por empleado.
    */
    List<Contrato> listarContratosPorEmpleado(Long idEmpleado);

    /*
        Crea un contrato asociado a un empleado existente.
    */
    Contrato crearContrato(Long idEmpleado, Contrato contrato);

    /*
        Actualiza un contrato existente.
    */
    Contrato actualizarContrato(Long idContrato, Contrato contrato);

    /*
        Elimina un contrato por ID.
    */
    void eliminarContrato(Long idContrato);
}



