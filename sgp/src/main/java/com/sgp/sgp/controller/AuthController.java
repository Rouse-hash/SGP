package com.sgp.sgp.controller;

import com.sgp.sgp.dto.LoginRequest;
import com.sgp.sgp.dto.LoginResponse;
import com.sgp.sgp.model.Usuario;
import com.sgp.sgp.service.UsuarioService;
import com.sgp.sgp.util.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario encontrado = usuarioService.buscarPorCorreo(loginRequest.getCorreo()).orElse(null);
        if (encontrado != null && encontrado.getPassword().equals(loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(encontrado.getCorreo(), encontrado.getRol());
            LoginResponse response = new LoginResponse(token, encontrado.getCorreo(), encontrado.getRol(), "Login exitoso");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}
