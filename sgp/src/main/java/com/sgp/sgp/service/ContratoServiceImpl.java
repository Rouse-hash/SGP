package com.sgp.sgp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sgp.sgp.exception.RecursoNoEncontradoException;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;

/*
    Implementación de la lógica de negocio para Contrato
    trabajando directamente con la entidad JPA.
    Se encarga de manejar las operaciones CRUD con validaciones.
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
        Listar todos los contratos registrados en la base de datos.
    */
    @Override
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    /*
        Buscar un contrato por su ID.
        Si no existe, lanza una excepción personalizada.
    */
    @Override
    public Contrato buscarContratoPorId(Long idContrato) {
        return contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));
    }

    /*
        Listar contratos asociados a un empleado específico.
    */
    @Override
    public List<Contrato> listarContratosPorEmpleado(Long idEmpleado) {
        return contratoRepository.findByEmpleado_IdEmpleado(idEmpleado);
    }

    /*
        Crear un nuevo contrato asociado a un empleado.
        Primero se valida que el empleado exista.
    */
    @Override
    public Contrato crearContrato(Long idEmpleado, Contrato contrato) {
        // Validar que el empleado exista
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Empleado no encontrado con ID: " + idEmpleado));

        // Asociar el empleado al contrato
        contrato.setEmpleado(empleado);

        // Guardar contrato en la base de datos
        return contratoRepository.save(contrato);
    }

    /*
        Actualizar un contrato existente.
        Se valida tanto el contrato como el empleado.
    */
    @Override
    public Contrato actualizarContrato(Long idContrato, Long idEmpleado, Contrato contrato) {
        Contrato existente = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Contrato no encontrado con ID: " + idContrato));

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
        return contratoRepository.save(existente);
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
