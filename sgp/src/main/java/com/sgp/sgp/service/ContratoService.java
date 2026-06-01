package com.sgp.sgp.service;

// Importa las clases necesarias
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.repository.ContratoRepository;

/*
    @Service indica que esta clase pertenece a la capa de servicio.
    Aquí se implementa la lógica de negocio para manejar contratos.
*/
@Service
public class ContratoService {

    // Inyección del ContratoRepository
    private final ContratoRepository contratoRepository;

    // Constructor con inyección de dependencias
    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    /*
        Método para listar todos los contratos.
        Devuelve una lista con todos los registros de la tabla contrato.
    */
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    /*
        Método para buscar un contrato por ID.
        Devuelve Optional porque puede existir o no el contrato.
    */
    public Optional<Contrato> buscarContratoPorId(Integer idContrato) {
        return contratoRepository.findById(idContrato);
    }

    /*
        Método para guardar un contrato nuevo o actualizar uno existente.
        Si el contrato tiene idContrato nulo, se inserta.
        Si tiene idContrato existente, se actualiza.
    */
    public Contrato guardarContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    /*
        Método para eliminar un contrato por ID.
        Devuelve true si se eliminó correctamente, false si no existe.
    */
    public boolean eliminarContrato(Integer idContrato) {
        if (contratoRepository.existsById(idContrato)) {
            contratoRepository.deleteById(idContrato);
            return true;
        } else {
            return false;
        }
    }
}