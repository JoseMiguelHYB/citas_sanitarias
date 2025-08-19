package com.josemiguelhyb.citas_sanitarias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josemiguelhyb.citas_sanitarias.service.PacienteService;

@Controller
@RequestMapping("/vista")
public class PacienteController {
	
	private final PacienteService pacienteService;

	public PacienteController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	
	@GetMapping("/pacientes")
	public String listarPacientes(Model model) {
		model.addAttribute("pacientes", pacienteService.listarTodos());
		return "pacientes"; // busca pacientes.html en /templates		
	}
}
