package com.sgp.sgp.controller;

import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.service.ContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    /*
        Se declara una variable final de tipo ContratoService.
        El Controller se comunica con la capa Service,
        nunca directamente con el Repository.
    */
    private final ContratoService contratoService;

    /*
        Constructor del Controller.
        Spring Boot inyecta automáticamente la dependencia ContratoService.
    */
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    /*
        Método para listar todos los contratos.
        Ruta: GET http://localhost:8080/api/contratos
    */
    @GetMapping
    public List<Contrato> listarContratos() {
        return contratoService.listarContratos();
    }

    /*
        Método para buscar un contrato por ID.
        Ruta: GET http://localhost:8080/api/contratos/{idContrato}
    */
    @GetMapping("/{idContrato}")
    public ResponseEntity<Contrato> buscarContratoPorId(@PathVariable Integer idContrato) {
        Optional<Contrato> contrato = contratoService.buscarContratoPorId(idContrato);

        return contrato.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
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