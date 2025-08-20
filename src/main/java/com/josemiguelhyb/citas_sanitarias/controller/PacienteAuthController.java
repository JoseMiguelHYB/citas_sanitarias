package com.josemiguelhyb.citas_sanitarias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.service.PacienteService;

@Controller
@RequestMapping("vista/pacientes")
public class PacienteAuthController {

	private final PacienteService pacienteService;

	public PacienteAuthController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	
	@GetMapping("/register")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "paciente_registro"; 		
	}
	
	@PostMapping("/register")
	public String registrarPaciente(@ModelAttribute Paciente paciente) {
		pacienteService.guardar(paciente);
		return "redirect:/vista/pacientes"; //redirige a lista o login	
	}	
}  
