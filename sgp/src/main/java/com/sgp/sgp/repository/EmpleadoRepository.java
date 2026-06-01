package com.sgp.sgp.repository;

// Importa JpaRepository, que proporciona métodos CRUD automáticos
import org.springframework.data.jpa.repository.JpaRepository;

// Importa la anotación Repository para indicar que esta interfaz pertenece a la capa de acceso a datos
import org.springframework.stereotype.Repository;

// Importa la entidad Cliente que será administrada por este repositorio
import  com.sgp.sgp.model.Empleado;

/*
    @Repository indica que esta interfaz hace parte
    de la capa de acceso a datos de la aplicación.

    Spring Boot la detecta automáticamente y crea
    una implementación interna en tiempo de ejecución.
*/
@Repository

/*
    ClienteRepository es una interfaz porque Spring Data JPA
    se encarga de generar automáticamente la lógica interna
    para acceder a la base de datos.
*/
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    /*
        Al extender JpaRepository<Cliente, Long>, esta interfaz
        hereda métodos CRUD listos para usar, como:

        save(cliente)        -> Guarda o actualiza un cliente.
        findAll()            -> Consulta todos los clientes.
        findById(id)         -> Busca un cliente por su ID.
        deleteById(id)       -> Elimina un cliente por su ID.
        existsById(id)       -> Verifica si existe un cliente por su ID.

        Cliente = entidad que administra este repositorio.
        Long    = tipo de dato de la llave primaria idCliente.
    */

}
