/*
 * Está clase de mometno devuelve JSON que se usará en 
 * una aplicación móvil u otro forma.
 */
package com.josemiguelhyb.citas_sanitarias.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.service.PacienteService;

@RestController
@RequestMapping("/api")
public class PacienteRestController {
	private final PacienteService pacienteService;	
	
	public PacienteRestController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	// Endpoint para devolver la lista
	@GetMapping("/pacientes")
	public List<Paciente> getPacientes() {
		return pacienteService.listarTodos(); // devuelve JSON
	}
}
