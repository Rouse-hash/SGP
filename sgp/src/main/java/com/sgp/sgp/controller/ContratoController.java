package com.sgp.sgp.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgp.sgp.dto.ContratoDto;   // Importamos el DTO
import com.sgp.sgp.model.Contrato;   // La entidad se usa solo como entrada
import com.sgp.sgp.service.ContratoService;

@CrossOrigin(origins = "http://localhost:5173")
/*
    Controlador REST para manejar las operaciones
    relacionadas con los contratos usando DTO.
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
        Lista todos los contratos en formato DTO.
        Ejemplo: GET /api/contratos
    */
    @GetMapping
    public ResponseEntity<List<ContratoDto>> listarContratos() {
        List<ContratoDto> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }

    /*
        Busca un contrato por su ID y lo devuelve como DTO.
        Ejemplo: GET /api/contratos/10
    */
    @GetMapping("/{idContrato}")
    public ResponseEntity<ContratoDto> buscarContratoPorId(@PathVariable Long idContrato) {
        ContratoDto contrato = contratoService.buscarContratoPorId(idContrato);
        return ResponseEntity.ok(contrato);
    }

    /*
        Lista contratos asociados a un empleado específico en formato DTO.
        Ejemplo: GET /api/contratos/empleado/5
    */
    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<ContratoDto>> listarContratosPorEmpleado(@PathVariable Long idEmpleado) {
        List<ContratoDto> contratos = contratoService.listarContratosPorEmpleado(idEmpleado);
        return ResponseEntity.ok(contratos);
    }

    /*
        Crea un contrato asociado a un empleado existente.
        Recibe la entidad completa en el cuerpo de la petición,
        pero devuelve solo el DTO.
        Ejemplo: POST /api/contratos/empleado/5
    */
    @PostMapping("/empleado/{idEmpleado}")
    public ResponseEntity<ContratoDto> crearContrato(@PathVariable Long idEmpleado, @RequestBody Contrato contrato) {
        ContratoDto nuevoContrato = contratoService.crearContrato(idEmpleado, contrato);
        return ResponseEntity.ok(nuevoContrato);
    }

    /*
        Actualiza un contrato existente asociado a un empleado.
        Recibe la entidad completa en el cuerpo de la petición,
        pero devuelve solo el DTO.
        Ejemplo: PUT /api/contratos/10/empleado/5
    */
    @PutMapping("/{idContrato}/empleado/{idEmpleado}")
    public ResponseEntity<ContratoDto> actualizarContrato(
            @PathVariable Long idContrato,
            @PathVariable Long idEmpleado,
            @RequestBody Contrato contrato) {

        ContratoDto contratoActualizado = contratoService.actualizarContrato(idContrato, idEmpleado, contrato);
        return ResponseEntity.ok(contratoActualizado);
    }

    /*
        Elimina un contrato por su ID.
        Ejemplo: DELETE /api/contratos/10
        No devuelve contenido, solo código 204 (No Content).
    */
    @DeleteMapping("/{idContrato}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long idContrato) {
        contratoService.eliminarContrato(idContrato);
        return ResponseEntity.noContent().build();
    }
}




