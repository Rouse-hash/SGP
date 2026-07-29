package com.sgp.sgp.service;

import com.sgp.sgp.dto.DashboardResponse;
import com.sgp.sgp.repository.ContratoRepository;
import com.sgp.sgp.repository.EmpleadoRepository;
import com.sgp.sgp.repository.NominaRepository;
import com.sgp.sgp.repository.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmpleadoRepository empleadoRepository;
    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;
    private final NominaRepository nominaRepository;

    public DashboardServiceImpl(EmpleadoRepository empleadoRepository,
                                ContratoRepository contratoRepository,
                                UsuarioRepository usuarioRepository,
                                NominaRepository nominaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.contratoRepository = contratoRepository;
        this.usuarioRepository = usuarioRepository;
        this.nominaRepository = nominaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardResponse obtenerDashboard() {
        long totalEmpleados = empleadoRepository.count();
        long totalContratos = contratoRepository.count();
        long totalUsuarios = usuarioRepository.count();
        long totalNominas = nominaRepository.count();

        Double resumenNomina = nominaRepository.sumTotalPagado();
        if (resumenNomina == null) resumenNomina = 0.0;

        var ultimosEmpleados = empleadoRepository.findAllByOrderByIdEmpleadoDesc(PageRequest.of(0, 5));
        var ultimosContratos = contratoRepository.findAllWithEmpleado();
        var ultimos5Contratos = ultimosContratos.stream()
                .limit(5)
                .toList();

        return new DashboardResponse(
                totalEmpleados, totalContratos, totalUsuarios, totalNominas,
                resumenNomina, ultimosEmpleados, ultimos5Contratos
        );
    }
}
