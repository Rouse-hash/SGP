package com.sgp.sgp.service;

import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.model.Usuario;
import com.sgp.sgp.repository.EmpleadoRepository;
import com.sgp.sgp.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              EmpleadoRepository empleadoRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        Empleado empleado = empleadoRepository.findById(usuario.getEmpleado().getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + usuario.getEmpleado().getIdEmpleado()));
        usuario.setEmpleado(empleado);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        Empleado empleado = empleadoRepository.findById(usuario.getEmpleado().getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + usuario.getEmpleado().getIdEmpleado()));

        existente.setEmpleado(empleado);
        existente.setCorreo(usuario.getCorreo());
        existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        existente.setRol(usuario.getRol());
        existente.setActivo(usuario.getActivo());

        return usuarioRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    @Transactional
    public Usuario guardarUsuarioMigrado(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}

