package com.sgp.sgp.controller;

import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173") // Permite que React consuma los endpoints
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoRepository contratoRepository;
    private final EmpleadoRepository empleadoRepository;

    // ✅ Inyección por constructor
    public ContratoController(ContratoRepository contratoRepository,
                              EmpleadoRepository empleadoRepository) {
        this.contratoRepository = contratoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    @GetMapping("/{idContrato}")
    public ResponseEntity<Contrato> buscarContratoPorId(@PathVariable Long idContrato) {
        Contrato contrato = contratoRepository.findByIdWithEmpleado(idContrato);
        if (contrato == null) {
            throw new RuntimeException("Contrato no encontrado con ID: " + idContrato);
        }
        return ResponseEntity.ok(contrato);
    }
    // ✅ Crear contrato desde la raíz del módulo, el idEmpleado va en el body
    // POST /api/contratos
    @PostMapping
    public ResponseEntity<Contrato> crearContratoRaiz(@RequestBody Map<String, Object> payload) {
        Object idRaw = payload.get("idEmpleado");
        if (idRaw == null) {
            throw new RuntimeException("El campo 'idEmpleado' es obligatorio en el body");
        }
        Long idEmpleado = Long.valueOf(idRaw.toString());

        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Contrato contrato = new Contrato();
        contrato.setTipoContrato((String) payload.get("tipoContrato"));
        contrato.setFechaInicio(parseFecha(payload.get("fechaInicio")));
        contrato.setFechaFin(parseFecha(payload.get("fechaFin")));
        Object salarioRaw = payload.get("salario");
        if (salarioRaw != null) {
            contrato.setSalario(Double.valueOf(salarioRaw.toString()));
        }
        contrato.setEmpleado(empleado);

        Contrato nuevoContrato = contratoRepository.save(contrato);
        return ResponseEntity.ok(nuevoContrato);
    }

    private LocalDate parseFecha(Object fecha) {
        if (fecha == null || fecha.toString().isBlank()) {
            return null;
        }
        String texto = fecha.toString().trim();
        try {
            return LocalDate.parse(texto);
        } catch (Exception e) {
            return LocalDate.parse(texto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }

    // ✅ Crear contrato asociado a un empleado
    @PostMapping("/empleado/{idEmpleado}")
    public ResponseEntity<Contrato> crearContrato(
            @PathVariable Long idEmpleado,
            @RequestBody Contrato contrato) {

        // Buscar empleado por ID
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Asignar el objeto empleado completo al contrato
        contrato.setEmpleado(empleado);

        // Guardar contrato en BD
        Contrato nuevoContrato = contratoRepository.save(contrato);

        return ResponseEntity.ok(nuevoContrato);
    }

    // ✅ Actualizar contrato existente
    @PutMapping("/{idContrato}")
    @Transactional
    public ResponseEntity<Contrato> actualizarContrato(
            @PathVariable Long idContrato,
            @RequestBody Contrato contratoActualizado) {

        Contrato contrato = contratoRepository.findByIdWithEmpleado(idContrato);
        if (contrato == null) {
            throw new RuntimeException("Contrato no encontrado");
        }

        contrato.setTipoContrato(contratoActualizado.getTipoContrato());
        contrato.setFechaInicio(contratoActualizado.getFechaInicio());
        contrato.setFechaFin(contratoActualizado.getFechaFin());
        contrato.setSalario(contratoActualizado.getSalario());

        return ResponseEntity.ok(contrato);
    }

    // ✅ Eliminar contrato
    @DeleteMapping("/{idContrato}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long idContrato) {
        contratoRepository.deleteById(idContrato);
        return ResponseEntity.noContent().build();
    }
}






