package com.sgp.sgp.controller;

import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.repository.EmpleadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173") // Permite que React consuma los endpoints

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoRepository empleadoRepository;

    // ✅ Inyección por constructor (mejor práctica)
    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> actualizarEmpleado(
            @PathVariable Long idEmpleado,
            @RequestBody Empleado empleadoActualizado) {

        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setNombre(empleadoActualizado.getNombre());
        empleado.setApellidos(empleadoActualizado.getApellidos());
        empleado.setTipoDocumento(empleadoActualizado.getTipoDocumento());
        empleado.setNumeroDocumento(empleadoActualizado.getNumeroDocumento());
        empleado.setFechaNacimiento(empleadoActualizado.getFechaNacimiento());
        empleado.setEstadoCivil(empleadoActualizado.getEstadoCivil());

        Empleado guardado = empleadoRepository.save(empleado);
        return ResponseEntity.ok(guardado);
    }

    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}

