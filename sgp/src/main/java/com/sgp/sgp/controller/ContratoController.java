package com.sgp.sgp.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.service.ContratoService;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    // Busca un contrato por su ID
    @GetMapping("/{idContrato}")
    public ResponseEntity<Contrato> buscarContratoPorId(@PathVariable Long idContrato) {
        Contrato contrato = contratoService.buscarContratoPorId(idContrato);
        return ResponseEntity.ok(contrato);
    }

    // Lista contratos asociados a un empleado
    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<Contrato>> listarContratosPorEmpleado(@PathVariable Long idEmpleado) {
        List<Contrato> contratos = contratoService.listarContratosPorEmpleado(idEmpleado);
        return ResponseEntity.ok(contratos);
    }

    // Crea un contrato asociado a un empleado
    @PostMapping("/empleado/{idEmpleado}")
    public ResponseEntity<Contrato> crearContrato(@PathVariable Long idEmpleado, @RequestBody Contrato contrato) {
        Contrato nuevoContrato = contratoService.crearContrato(idEmpleado, contrato);
        return ResponseEntity.ok(nuevoContrato);
    }

    // Actualiza un contrato existente
    @PutMapping("/{idContrato}")
    public ResponseEntity<Contrato> actualizarContrato(@PathVariable Long idContrato, @RequestBody Contrato contrato) {
        Contrato contratoActualizado = contratoService.actualizarContrato(idContrato, contrato);
        return ResponseEntity.ok(contratoActualizado);
    }

    // Elimina un contrato por su ID
    @DeleteMapping("/{idContrato}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long idContrato) {
        contratoService.eliminarContrato(idContrato);
        return ResponseEntity.noContent().build();
    }
}


