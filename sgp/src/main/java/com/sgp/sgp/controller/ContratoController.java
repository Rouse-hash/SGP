package com.sgp.sgp.controller;

import com.sgp.sgp.dto.ContratoDTO;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.service.ContratoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @RestController indica que esta clase será un controlador REST.
    Recibirá peticiones HTTP y devolverá respuestas en formato JSON.
*/
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    /*
     * Método para listar todos los contratos.
     * Ruta: GET http://localhost:8080/api/contratos
     */
    @GetMapping
    public List<ContratoDTO> listarContratos() {
        return contratoService.listarContratos()
                .stream()
                .map(contratoService::convertirADTO)
                .toList();
    }

    /*
     * Método para buscar un contrato por ID.
     * Ruta: GET http://localhost:8080/api/contratos/{id}
     */
   @GetMapping("/{id}")
public ContratoDTO obtenerContrato(@PathVariable Integer id) {
    Contrato contrato = contratoService.obtenerContratoConEmpleado(id);
    return contratoService.convertirADTO(contrato);
}

    /*
     * Método para guardar un nuevo contrato.
     * Ruta: POST http://localhost:8080/api/contratos
     */
    @PostMapping
    public ContratoDTO guardarContrato(@RequestBody Contrato contrato) {
        Contrato contratoGuardado = contratoService.guardarContrato(contrato);
        return contratoService.convertirADTO(contratoGuardado);
    }
}
