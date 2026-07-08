package com.sgp.sgp.repository;

// Importa la entidad Usuario que será administrada por el repositorio.
import com.sgp.sgp.model.Usuario;

// Importa la interfaz JpaRepository, la cual proporciona
// automáticamente las operaciones CRUD sobre la base de datos.
import org.springframework.data.jpa.repository.JpaRepository;

// Permite utilizar el tipo Optional para manejar
// búsquedas que pueden o no devolver un resultado.
import java.util.Optional;

/**
 * Repositorio encargado de gestionar el acceso
 * a la información de la entidad Usuario.
 *
 * Esta interfaz pertenece a la capa de persistencia
 * y es la responsable de la comunicación con la
 * base de datos mediante Spring Data JPA.
 *
 * Al extender JpaRepository se heredan automáticamente
 * numerosos métodos CRUD, tales como:
 *
 * • save()           -> Guarda un registro.
 * • findAll()        -> Consulta todos los registros.
 * • findById()       -> Consulta por identificador.
 * • deleteById()     -> Elimina un registro.
 * • existsById()     -> Verifica si existe un registro.
 * • count()          -> Cuenta la cantidad de registros.
 *
 * No es necesario implementar estos métodos,
 * ya que Spring Data JPA los genera automáticamente.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario utilizando su correo electrónico.
     *
     * Spring Data JPA interpreta automáticamente
     * el nombre del método y genera la consulta SQL
     * correspondiente sin necesidad de escribirla.
     *
     * Consulta equivalente:
     *
     * SELECT * FROM usuario
     * WHERE correo = ?
     *
     * Se utiliza principalmente durante
     * el proceso de autenticación (Login).
     *
     * @param correo Correo electrónico del usuario.
     * @return Optional<Usuario> con el usuario encontrado,
     *         o vacío si no existe.
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Verifica si ya existe un usuario registrado
     * con el correo electrónico indicado.
     *
     * Spring Data JPA genera automáticamente
     * una consulta similar a:
     *
     * SELECT COUNT(*) > 0
     * FROM usuario
     * WHERE correo = ?
     *
     * Este método es útil para validar
     * que no existan correos duplicados
     * antes de registrar un nuevo usuario.
     *
     * @param correo Correo electrónico a verificar.
     * @return true si el correo ya existe.
     *         false en caso contrario.
     */
    boolean existsByCorreo(String correo);
}
