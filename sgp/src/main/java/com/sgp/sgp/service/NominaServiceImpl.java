package com.sgp.sgp.service;

import com.sgp.sgp.model.Contrato;
import com.sgp.sgp.model.Empleado;
import com.sgp.sgp.model.Nomina;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;
import com.sgp.sgp.repository.NominaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NominaServiceImpl implements NominaService {

    private final NominaRepository nominaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ContratoRepository contratoRepository;

    public NominaServiceImpl(NominaRepository nominaRepository,
                             EmpleadoRepository empleadoRepository,
                             ContratoRepository contratoRepository) {
        this.nominaRepository = nominaRepository;
        this.empleadoRepository = empleadoRepository;
        this.contratoRepository = contratoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nomina> listarNominas() {
        return nominaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Nomina> buscarPorId(Long idNomina) {
        return nominaRepository.findById(idNomina);
    }

    @Override
    @Transactional
    public Nomina guardarNomina(Nomina nomina) {
        Empleado empleado = empleadoRepository.findById(nomina.getEmpleado().getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + nomina.getEmpleado().getIdEmpleado()));
        Contrato contrato = contratoRepository.findById(nomina.getContrato().getIdContrato())
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado con ID: " + nomina.getContrato().getIdContrato()));

        nomina.setEmpleado(empleado);
        nomina.setContrato(contrato);
        nomina.setTotalPagado(calcularTotal(nomina));

        return nominaRepository.save(nomina);
    }

    @Override
    @Transactional
    public Nomina actualizarNomina(Long idNomina, Nomina nomina) {
        Nomina existente = nominaRepository.findById(idNomina)
                .orElseThrow(() -> new RuntimeException("Nómina no encontrada con ID: " + idNomina));

        Empleado empleado = empleadoRepository.findById(nomina.getEmpleado().getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + nomina.getEmpleado().getIdEmpleado()));
        Contrato contrato = contratoRepository.findById(nomina.getContrato().getIdContrato())
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado con ID: " + nomina.getContrato().getIdContrato()));

        existente.setEmpleado(empleado);
        existente.setContrato(contrato);
        existente.setFechaPago(nomina.getFechaPago());
        existente.setSalarioBase(nomina.getSalarioBase());
        existente.setDeducciones(nomina.getDeducciones());
        existente.setBonificaciones(nomina.getBonificaciones());
        existente.setTotalPagado(calcularTotal(nomina));
        existente.setEstado(nomina.getEstado());
        existente.setDepartamento(nomina.getDepartamento());
        existente.setMunicipio(nomina.getMunicipio());

        return nominaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarNomina(Long idNomina) {
        if (!nominaRepository.existsById(idNomina)) {
            throw new RuntimeException("Nómina no encontrada con ID: " + idNomina);
        }
        nominaRepository.deleteById(idNomina);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nomina> listarPorDepartamento(String departamento) {
        return nominaRepository.findByDepartamento(departamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nomina> listarPorMunicipio(String municipio) {
        return nominaRepository.findByMunicipio(municipio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nomina> listarPorEmpleado(Long idEmpleado) {
        return nominaRepository.findByEmpleadoIdEmpleado(idEmpleado);
    }

    private Double calcularTotal(Nomina nomina) {
        double base = nomina.getSalarioBase() != null ? nomina.getSalarioBase() : 0;
        double ded = nomina.getDeducciones() != null ? nomina.getDeducciones() : 0;
        double bon = nomina.getBonificaciones() != null ? nomina.getBonificaciones() : 0;
        return base + bon - ded;
    }
}

