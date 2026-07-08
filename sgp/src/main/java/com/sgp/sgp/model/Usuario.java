package com.sgp.sgp.model;
// Importa las anotaciones de JPA para mapear la clase
// como una entidad de la base de datos.
import jakarta.persistence.*;

// Permite validar que el correo tenga un formato válido.
import jakarta.validation.constraints.Email;

// Permite validar que un atributo no esté vacío ni sea nulo.
import jakarta.validation.constraints.NotBlank;

/**
 * Entidad que representa la información de un usuario
 * dentro del sistema ServiGestor360.
 *
 * Esta clase será mapeada a la tabla "usuario"
 * de la base de datos MySQL mediante JPA/Hibernate.
 */
@Entity 

// Especifica el nombre de la tabla en la base de datos.
@Table(name = "usuario")
public class Usuario {

    /**
     * Identificador único del usuario.
     *
     * Se define como llave primaria (Primary Key)
     * y su valor se genera automáticamente mediante
     * auto incremento (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombres del usuario.
     *
     * No puede ser nulo ni estar vacío.
     */
    @NotBlank
    private String nombres;

    /**
     * Apellidos del usuario.
     *
     * Campo obligatorio.
     */
    @NotBlank
    private String apellidos;

    /**
     * Correo electrónico del usuario.
     *
     * Debe tener un formato válido.
     * Además, debe ser único en la base de datos,
     * evitando usuarios con el mismo correo.
     */
    @Email
    @NotBlank
    @Column(unique = true)
    private String correo;

    /**
     * Contraseña del usuario.
     *
     * Campo obligatorio.
     *
     * Actualmente se almacena en texto plano
     * únicamente con fines académicos.
     *
     * En una aplicación empresarial se recomienda
     * almacenar la contraseña utilizando BCrypt.
     */
    @NotBlank
    private String password;

    /**
     * Rol asignado al usuario.
     *
     * Ejemplos:
     * ADMIN
     * TECNICO
     * CLIENTE
     */
    @NotBlank
    private String rol;

    /**
     * Estado del usuario.
     *
     * true  = Usuario activo.
     * false = Usuario inactivo.
     *
     * Por defecto todo usuario nuevo
     * queda activo.
     */
    private Boolean activo = true;

    /**
     * Constructor vacío.
     *
     * Es requerido por JPA/Hibernate para
     * crear objetos automáticamente al consultar
     * la base de datos.
     */
    public Usuario() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * Facilita la creación de objetos Usuario
     * inicializando toda su información.
     */
    public Usuario(Long id,
                   String nombres,
                   String apellidos,
                   String correo,
                   String password,
                   String rol,
                   Boolean activo) {

        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

    /**
     * Obtiene el identificador del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene los nombres del usuario.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene los apellidos del usuario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Obtiene la contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Obtiene el estado del usuario.
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Asigna el identificador del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Asigna los nombres del usuario.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Asigna los apellidos del usuario.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Asigna el correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Asigna la contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Asigna el rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Cambia el estado del usuario.
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
