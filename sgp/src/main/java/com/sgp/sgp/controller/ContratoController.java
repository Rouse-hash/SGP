package com.sgp.sgp.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.service.ContratoService;

/*
    Controlador REST para manejar las operaciones
    relacionadas con los contratos.
*/
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    /*
        Constructor para inyección de dependencias.
    */
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    /*
        Lista todos los contratos.
        Ejemplo: GET /api/contratos
    */
    @GetMapping
    public ResponseEntity<List<Contrato>> listarContratos() {
        List<Contrato> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }

    /*
        Busca un contrato por su ID.
        Ejemplo: GET /api/contratos/10
    */
    @GetMapping("/{idContrato}")
    public ResponseEntity<Contrato> buscarContratoPorId(@PathVariable Long idContrato) {
        Contrato contrato = contratoService.buscarContratoPorId(idContrato);
        return ResponseEntity.ok(contrato);
    }

    /*
        Lista contratos asociados a un empleado específico.
        Ejemplo: GET /api/contratos/empleado/5
    */
    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<Contrato>> listarContratosPorEmpleado(@PathVariable Long idEmpleado) {
        List<Contrato> contratos = contratoService.listarContratosPorEmpleado(idEmpleado);
        return ResponseEntity.ok(contratos);
    }

    /*
        Crea un contrato asociado a un empleado existente.
        Ejemplo: POST /api/contratos/empleado/5
    */
    @PostMapping("/empleado/{idEmpleado}")
    public ResponseEntity<Contrato> crearContrato(@PathVariable Long idEmpleado, @RequestBody Contrato contrato) {
        Contrato nuevoContrato = contratoService.crearContrato(idEmpleado, contrato);
        return ResponseEntity.ok(nuevoContrato);
    }

    /*
        Actualiza un contrato existente.
        Ejemplo: PUT /api/contratos/10
    */
    @PutMapping("/{idContrato}")
    public ResponseEntity<Contrato> actualizarContrato(@PathVariable Long idContrato, @RequestBody Contrato contrato) {
        Contrato contratoActualizado = contratoService.actualizarContrato(idContrato, contrato);
        return ResponseEntity.ok(contratoActualizado);
    }

    /*
        Elimina un contrato por su ID.
        Ejemplo: DELETE /api/contratos/10
    */
    @DeleteMapping("/{idContrato}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long idContrato) {
        contratoService.eliminarContrato(idContrato);
        return ResponseEntity.noContent().build();
    }
}



