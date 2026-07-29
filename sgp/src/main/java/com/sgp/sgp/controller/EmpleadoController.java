package com.sgp.sgp.controller;

import com.sgp.sgp.dto.ResumenEmpleadoDTO;
import com.sgp.sgp.model.ArchivoEmpleado;
import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;
import com.sgp.sgp.service.ArchivoService;
import com.sgp.sgp.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final EmpleadoRepository empleadoRepository;
    private final ContratoRepository contratoRepository;
    private final ArchivoService archivoService;

    public EmpleadoController(EmpleadoService empleadoService,
                              EmpleadoRepository empleadoRepository,
                              ContratoRepository contratoRepository,
                              ArchivoService archivoService) {
        this.empleadoService = empleadoService;
        this.empleadoRepository = empleadoRepository;
        this.contratoRepository = contratoRepository;
        this.archivoService = archivoService;
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable Long idEmpleado) {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(idEmpleado);
        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<Empleado> buscarPorDocumento(@PathVariable String numeroDocumento) {
        return empleadoRepository.findByNumeroDocumento(numeroDocumento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/resumen")
    public ResponseEntity<ResumenEmpleadoDTO> obtenerResumen(@PathVariable Long id) {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        List<Contrato> contratos = contratoRepository.findByEmpleado_IdEmpleado(id);
        List<ArchivoEmpleado> archivos = archivoService.listarArchivos(id);
        return ResponseEntity.ok(new ResumenEmpleadoDTO(empleado, contratos, archivos));
    }

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.crearEmpleado(empleado);
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> actualizarEmpleado(
            @PathVariable Long idEmpleado,
            @RequestBody Empleado empleadoActualizado) {

        Empleado actualizado = empleadoService.actualizarEmpleado(idEmpleado, empleadoActualizado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}
