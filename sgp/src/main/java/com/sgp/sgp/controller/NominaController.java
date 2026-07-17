package com.sgp.sgp.controller;

import com.sgp.sgp.model.Nomina;
import com.sgp.sgp.service.NominaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nominas")
@CrossOrigin(origins = "http://localhost:5173")
public class NominaController {

    private final NominaService nominaService;

    public NominaController(NominaService nominaService) {
        this.nominaService = nominaService;
    }

    /*
     * Lista todas las nóminas.
     * GET /api/nominas
     */
    @GetMapping
    public List<Nomina> listarNominas() {
        return nominaService.listarNominas();
    }

    /*
     * Busca una nómina por ID.
     * GET /api/nominas/{idNomina}
     */
    @GetMapping("/{idNomina}")
    public ResponseEntity<Nomina> buscarPorId(@PathVariable Long idNomina) {
        return nominaService.buscarPorId(idNomina)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * Crea una nueva nómina.
     * POST /api/nominas
     */
    @PostMapping
    public Nomina crearNomina(@Valid @RequestBody Nomina nomina) {
        return nominaService.guardarNomina(nomina);
    }

    /*
     * Actualiza una nómina existente.
     * PUT /api/nominas/{idNomina}
     */
    @PutMapping("/{idNomina}")
    public ResponseEntity<Nomina> actualizarNomina(
            @PathVariable Long idNomina,
            @Valid @RequestBody Nomina nomina) {

        Nomina actualizada = nominaService.actualizarNomina(idNomina, nomina);
        return ResponseEntity.ok(actualizada);
    }

    /*
     * Elimina una nómina por ID.
     * DELETE /api/nominas/{idNomina}
     */
    @DeleteMapping("/{idNomina}")
    public ResponseEntity<Void> eliminarNomina(@PathVariable Long idNomina) {
        nominaService.eliminarNomina(idNomina);
        return ResponseEntity.noContent().build();
    }

    /*
     * Lista nóminas por departamento.
     * GET /api/nominas/departamento/{departamento}
     */
    @GetMapping("/departamento/{departamento}")
    public List<Nomina> listarPorDepartamento(@PathVariable String departamento) {
        return nominaService.listarPorDepartamento(departamento);
    }

    /*
     * Lista nóminas por municipio.
     * GET /api/nominas/municipio/{municipio}
     */
    @GetMapping("/municipio/{municipio}")
    public List<Nomina> listarPorMunicipio(@PathVariable String municipio) {
        return nominaService.listarPorMunicipio(municipio);
    }
}
