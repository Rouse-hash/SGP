package com.sgp.sgp.controller;

import com.sgp.sgp.repository.ContratoRepository;
// Importa List para manejar colecciones
import java.util.List;

// Importa Optional para manejar búsquedas que pueden devolver o no un resultado
import java.util.Optional;

// Importa ResponseEntity para construir respuestas HTTP más controladas
import org.springframework.http.ResponseEntity;

// Importa las anotaciones de Spring Boot para manejar solicitudes HTTP
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importa la entidad Contrato
import com.sgp.sgp.model.Contrato;

// Importa la capa Service de Contrato
import com.sgp.sgp.service.ContratoService;

/*
    @RestController indica que esta clase será un controlador REST.
    Recibirá peticiones HTTP y devolverá respuestas en formato JSON.
*/
@RestController

/*
    @RequestMapping define la ruta base del controlador.
    Todas las rutas de este controlador iniciarán con:
    /api/contratos
*/
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoRepository contratoRepository;
    /*
        Se declara una variable final de ContratoService.
        El Controller se comunica con la capa Service,
        nunca directamente con el Repository.
    */
    private final ContratoService contratoService;

    /*
        Constructor del Controller.
        Spring Boot inyecta automáticamente una instancia de ContratoService.
    */
    public ContratoController(ContratoService contratoService, ContratoRepository contratoRepository) {
        this.contratoService = contratoService;
        this.contratoRepository = contratoRepository;
    }

    /*
        Método para listar todos los contratos.
        Ruta: GET http://localhost:8080/api/contratos
    */
    @GetMapping
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    /*
        Método para buscar un contrato por ID.
        Ruta: GET http://localhost:8080/api/contratos/{idContrato}
    */
    @GetMapping("/{idContrato}")
    public ResponseEntity<Contrato> buscarContratoPorId(@PathVariable Integer idContrato) {
        Optional<Contrato> contrato = contratoService.buscarContratoPorId(idContrato);

        if (contrato.isPresent()) {
            return ResponseEntity.ok(contrato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
        Método para guardar un nuevo contrato.
        Ruta: POST http://localhost:8080/api/contratos
    */
    @PostMapping
    public Contrato guardarContrato(@RequestBody Contrato contrato) {
        return contratoService.guardarContrato(contrato);
    }

    /*
        Método para actualizar un contrato existente.
        Ruta: PUT http://localhost:8080/api/contratos/{idContrato}
    */
    @PutMapping("/{idContrato}")
    public ResponseEntity<Contrato> actualizarContrato(
        @PathVariable Integer idContrato,
        @RequestBody Contrato contrato) {

        Optional<Contrato> contratoExistente = contratoService.buscarContratoPorId(idContrato);

        if (contratoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Asigna el ID recibido en la URL al objeto contrato
        contrato.setIdContrato(idContrato);

        // Guarda los cambios usando la capa Service
        Contrato contratoActualizado = contratoService.guardarContrato(contrato);

        return ResponseEntity.ok(contratoActualizado);
    }

    /*
        Método para eliminar un contrato por ID.
        Ruta: DELETE http://localhost:8080/api/contratos/{idContrato}
    */
    @DeleteMapping("/{idContrato}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Integer idContrato) {
        boolean eliminado = contratoService.eliminarContrato(idContrato);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

