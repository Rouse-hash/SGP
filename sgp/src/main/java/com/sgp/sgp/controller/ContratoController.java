package com.sgp.sgp.controller;

import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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






