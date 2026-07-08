package com.sgp.sgp.service;

// Importa la entidad Usuario que será administrada
// por la capa de servicio.
import com.sgp.sgp.model.Usuario;

// Importa el repositorio encargado del acceso
// a la base de datos.
import com.sgp.sgp.repository.UsuarioRepository;

// Indica que esta clase pertenece a la capa Service
// y será administrada automáticamente por Spring.
import org.springframework.stereotype.Service;

// Permite trabajar con listas de objetos.
import java.util.List;

// Permite manejar resultados que pueden existir
// o no existir, evitando errores por valores nulos.
import java.util.Optional;

/**
 * Implementación de la interfaz UsuarioService.
 *
 * Esta clase contiene la lógica de negocio
 * relacionada con la gestión de usuarios.
 *
 * Su responsabilidad es recibir las solicitudes
 * provenientes del Controller, aplicar las reglas
 * de negocio necesarias y comunicarse con el
 * Repository para acceder a la base de datos.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    /**
     * Repositorio encargado de realizar las operaciones
     * CRUD sobre la entidad Usuario.
     *
     * Se inyecta mediante el constructor siguiendo
     * el principio de Inversión de Dependencias (SOLID).
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor utilizado por Spring para inyectar
     * automáticamente el repositorio.
     *
     * @param usuarioRepository Repositorio de usuarios.
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Consulta todos los usuarios registrados.
     *
     * Método utilizado por el controlador
     * cuando se realiza una petición GET.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario mediante su identificador.
     *
     * Si el usuario existe devuelve un Optional
     * con la información encontrada.
     *
     * @param id Identificador del usuario.
     * @return Optional<Usuario>.
     */
    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * Esta operación es utilizada por el método POST
     * del controlador.
     *
     * En futuras versiones aquí podrán implementarse
     * reglas de negocio como:
     *
     * • Verificar si el correo ya existe.
     * • Cifrar la contraseña con BCrypt.
     * • Asignar un rol por defecto.
     *
     * @param usuario Información del usuario.
     * @return Usuario registrado.
     */
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * Primero consulta el usuario en la base de datos.
     * Si no existe genera una excepción.
     *
     * Si existe, actualiza cada uno de sus atributos
     * y finalmente guarda nuevamente la información.
     *
     * Método utilizado por el endpoint PUT.
     *
     * @param id Identificador del usuario.
     * @param usuario Información actualizada.
     * @return Usuario actualizado.
     */
    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {

        // Busca el usuario por su ID.
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        // Actualiza cada atributo del usuario.
        existente.setNombres(usuario.getNombres());
        existente.setApellidos(usuario.getApellidos());
        existente.setCorreo(usuario.getCorreo());
        existente.setPassword(usuario.getPassword());
        existente.setRol(usuario.getRol());
        existente.setActivo(usuario.getActivo());

        // Guarda los cambios realizados.
        return usuarioRepository.save(existente);
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * Antes de eliminar verifica que el usuario exista.
     * Si no existe genera una excepción.
     *
     * Método utilizado por el endpoint DELETE.
     *
     * @param id Identificador del usuario.
     */
    @Override
    public void eliminarUsuario(Long id) {

        // Verifica la existencia del usuario.
        if (!usuarioRepository.existsById(id)) {

            throw new RuntimeException("Usuario no encontrado");
        }

        // Elimina el registro.
        usuarioRepository.deleteById(id);
    }

    /**
     * Busca un usuario utilizando
     * su correo electrónico.
     *
     * Este método es utilizado principalmente
     * durante el proceso de autenticación (Login).
     *
     * También puede emplearse para validar
     * que un correo no esté registrado previamente.
     *
     * @param correo Correo electrónico.
     * @return Optional<Usuario>.
     */
    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}

