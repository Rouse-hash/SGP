package com.sgp.sgp.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /*
        Lista todos los empleados.
        Ejemplo: GET /api/empleados
    */
    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        return ResponseEntity.ok(empleados);
    }

    /*
        Busca un empleado por su ID.
        Ejemplo: GET /api/empleados/5
    */
    @GetMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable Long idEmpleado) {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(idEmpleado);
        return ResponseEntity.ok(empleado);
    }

    /*
        Crea un nuevo empleado.
        Ejemplo: POST /api/empleados
    */
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    /*
        Actualiza un empleado existente.
        Ejemplo: PUT /api/empleados/5
    */
    @PutMapping("/{idEmpleado}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(idEmpleado, empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    /*
        Elimina un empleado por su ID.
        Ejemplo: DELETE /api/empleados/5
    */
    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}


