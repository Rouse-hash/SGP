package com.sgp.sgp.service;

import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    @Service indica que esta clase pertenece a la capa de servicio.
    Aquí se implementa la lógica de negocio para manejar contratos.
    El Controller se comunica con esta capa, y esta capa con el Repository.
*/
@Service
public class ContratoService {

    /*
        Se declara una variable final de tipo ContratoRepository.
        El Service se comunica directamente con el Repository.
    */
    private final ContratoRepository contratoRepository;

    /*
        Constructor del Service.
        Spring Boot inyecta automáticamente el ContratoRepository.
    */
    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    /*
        Método para listar todos los contratos.
        Retorna una lista con todos los registros de la tabla contrato.
    */
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    /*
        Método para buscar un contrato por ID.
        Retorna un Optional<Contrato>, que puede estar vacío si no existe.
    */
    public Optional<Contrato> buscarContratoPorId(Integer idContrato) {
        return contratoRepository.findById(idContrato);
    }

    /*
        Método para guardar un contrato.
        Sirve tanto para crear uno nuevo como para actualizar uno existente.
    */
    public Contrato guardarContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    /*
        Método para eliminar un contrato por ID.
        Retorna true si se eliminó correctamente, false si no existía.
    */
    public boolean eliminarContrato(Integer idContrato) {
        if (contratoRepository.existsById(idContrato)) {
            contratoRepository.deleteById(idContrato);
            return true;
        }
        return false;
    }
}