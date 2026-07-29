package com.sgp.sgp.controller;

import com.sgp.sgp.dto.DashboardResponse;
import com.sgp.sgp.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> obtenerDashboard() {
        return ResponseEntity.ok(dashboardService.obtenerDashboard());
    }
}
