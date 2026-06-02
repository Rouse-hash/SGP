package com.sgp.sgp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sgp.sgp.exception.RecursoNoEncontradoException;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;

/*
    Implementación de la lógica de negocio para Contrato.
*/
@Service
public class ContratoServiceImpl implements ContratoService {

    /*
        Repository de contratos.
    */
    private final ContratoRepository contratoRepository;

    /*
        Repository de empleados.
        Se necesita para validar que el empleado exista.
    */
    private final EmpleadoRepository empleadoRepository;

    /*
        Constructor para inyección de dependencias.
    */
    public ContratoServiceImpl(ContratoRepository contratoRepository,
                               EmpleadoRepository empleadoRepository) {
        this.contratoRepository = contratoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    /*
        Lista todos los contratos registrados.
    */
    @Override
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    /*
        Busca un contrato por su ID.
        Si no existe, lanza una excepción personalizada.
    */
    @Override
    public Contrato buscarContratoPorId(Long idContrato) {
        return contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));
    }

    /*
        Lista contratos asociados a un empleado específico.
    */
    @Override
    public List<Contrato> listarContratosPorEmpleado(Long idEmpleado) {
        if (!empleadoRepository.existsById(idEmpleado)) {
            throw new RecursoNoEncontradoException("Empleado no encontrado con ID: " + idEmpleado);
        }
        return contratoRepository.findByEmpleadoIdEmpleado(idEmpleado);
    }

    /*
        Crea un contrato asociado a un empleado existente.
    */
    @Override
    public Contrato crearContrato(Long idEmpleado, Contrato contrato) {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        contrato.setEmpleado(empleado);
        return contratoRepository.save(contrato);
    }

    /*
        Actualiza un contrato existente.
    */
    @Override
    public Contrato actualizarContrato(Long idContrato, Contrato contrato) {
        Contrato existente = buscarContratoPorId(idContrato);

        existente.setTipoContrato(contrato.getTipoContrato());
        existente.setFechaInicio(contrato.getFechaInicio());
        existente.setFechaFin(contrato.getFechaFin());
        existente.setSalario(contrato.getSalario());

        return contratoRepository.save(existente);
    }

    /*
        Elimina un contrato por ID.
    */
    @Override
    public void eliminarContrato(Long idContrato) {
        Contrato existente = buscarContratoPorId(idContrato);
        contratoRepository.delete(existente);
    }
}

