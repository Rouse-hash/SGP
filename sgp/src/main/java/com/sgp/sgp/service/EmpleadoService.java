// Paquete correcto para tu proyecto SGP
package com.sgp.sgp.service;

// Importa la clase List para manejar colecciones de clientes
import java.util.List;

// Importa Optional para manejar búsquedas que pueden o no devolver un resultado
import java.util.Optional;

// Importa la anotación Service para indicar que esta clase pertenece a la capa de lógica de negocio
import org.springframework.stereotype.Service;

// Importa la entidad Cliente
import com.sgp.sgp.model.Empleado;

// Importa el repositorio ClienteRepository para acceder a la base de datos
import com.sgp.sgp.repository.EmpleadoRepository;

/*
    @Service indica que esta clase pertenece
    a la capa de servicios.

    Esta capa contiene la lógica del negocio
    y sirve como intermediaria entre el Controller
    y el Repository.
*/
@Service
public class EmpleadoService {

    /*
        Se declara una variable final del repositorio.

        El Repository permite acceder a los datos
        de la tabla cliente en MySQL.
    */
    private final EmpleadoRepository empleadoRepository;

    /*
        Constructor de la clase ClienteService.

        Spring Boot utiliza este constructor para inyectar
        automáticamente una instancia de ClienteRepository.

        Esta es una forma recomendada de inyección de dependencias.
    */
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /*
        Método para listar todos los clientes.

        Llama al método findAll() del Repository,
        el cual consulta todos los registros de la tabla cliente.
    */
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    /*
        Método para buscar un cliente por su ID.

        Retorna un Optional<Cliente>, porque puede ocurrir
        que el cliente exista o que no exista en la base de datos.
    */
    public Optional<Empleado> buscarEmpleadoPorId(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado);
    }

    /*
        Método para guardar un cliente.

        Si el cliente no tiene ID, se crea un nuevo registro.
        Si el cliente ya tiene ID, se actualiza el registro existente.

        Aquí más adelante podemos agregar validaciones de negocio.
    */
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /*
        Método para eliminar un cliente por su ID.

        Antes de eliminar, se valida si el cliente existe.
        Esto evita intentar eliminar registros inexistentes.
    */
    public boolean eliminarEmpleado(Long idEmpleado) {

        // Verifica si existe un cliente con el ID recibido
        if (empleadoRepository.existsById(idEmpleado)) {

            // Elimina el cliente por ID
            empleadoRepository.deleteById(idEmpleado);

            // Retorna true para indicar que sí se eliminó
            return true;
        }

        // Retorna false si el cliente no existe
        return false;
    }
}


