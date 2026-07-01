package com.sgp.sgp.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sgp.sgp.dto.EmpleadoDto;   // Importamos el DTO
import com.sgp.sgp.model.Empleado;   // La entidad se usa solo como entrada
import com.sgp.sgp.service.EmpleadoService;
import org.springframework.web.bind.annotation.CrossOrigin;

// Permite que React, ejecutado en localhost:5173,
// consuma los endpoints de este controlador
@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /*
        Lista todos los empleados en formato DTO.
        Ejemplo: GET /api/empleados
        Devuelve solo los campos definidos en EmpleadoDto.
    */
    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> listarEmpleados() {
        List<EmpleadoDto> empleados = empleadoService.listarEmpleados();
        return ResponseEntity.ok(empleados);
    }

    /*
        Busca un empleado por su ID y lo devuelve como DTO.
        Ejemplo: GET /api/empleados/5
    */
    @GetMapping("/{idEmpleado}")
    public ResponseEntity<EmpleadoDto> buscarEmpleadoPorId(@PathVariable Long idEmpleado) {
        EmpleadoDto empleado = empleadoService.buscarEmpleadoPorId(idEmpleado);
        return ResponseEntity.ok(empleado);
    }

    /*
        Crea un nuevo empleado.
        Recibe la entidad completa en el cuerpo de la petición,
        pero devuelve solo el DTO.
        Ejemplo: POST /api/empleados
    */
    @PostMapping
    public ResponseEntity<EmpleadoDto> crearEmpleado(@RequestBody Empleado empleado) {
        EmpleadoDto nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    /*
        Actualiza un empleado existente.
        Recibe la entidad completa en el cuerpo de la petición,
        pero devuelve solo el DTO.
        Ejemplo: PUT /api/empleados/5
    */
    @PutMapping("/{idEmpleado}")
    public ResponseEntity<EmpleadoDto> actualizarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        EmpleadoDto empleadoActualizado = empleadoService.actualizarEmpleado(idEmpleado, empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    /*
        Elimina un empleado por su ID.
        Ejemplo: DELETE /api/empleados/5
        No devuelve contenido, solo código 204 (No Content).
    */
    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}



