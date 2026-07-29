package com.sgp.sgp.controller;

import com.sgp.sgp.model.ArchivoEmpleado;
import com.sgp.sgp.service.ArchivoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = "http://localhost:5173")
public class ArchivoController {

    private final ArchivoService archivoService;

    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    @PostMapping("/empleado/{idEmpleado}")
    public ResponseEntity<ArchivoEmpleado> subirArchivo(
            @PathVariable Long idEmpleado,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("tipo") String tipo) {

        ArchivoEmpleado guardado = archivoService.guardarArchivo(idEmpleado, archivo, tipo);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<ArchivoEmpleado>> listarArchivos(@PathVariable Long idEmpleado) {
        return ResponseEntity.ok(archivoService.listarArchivos(idEmpleado));
    }

    @GetMapping("/{idArchivo}/descargar")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable Long idArchivo) {
        ArchivoEmpleado archivo = archivoService.buscarPorId(idArchivo);
        Resource resource = archivoService.descargarArchivo(idArchivo);

        String contentType = archivo.getMimeType() != null ? archivo.getMimeType() : "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + archivo.getNombreOriginal() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{idArchivo}")
    public ResponseEntity<Void> eliminarArchivo(@PathVariable Long idArchivo) {
        archivoService.eliminarArchivo(idArchivo);
        return ResponseEntity.noContent().build();
    }
}
