package com.josemiguelhyb.citas_sanitarias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josemiguelhyb.citas_sanitarias.service.MedicoService;

@Controller
@RequestMapping("/vista")
public class MedicoController {
	
	private final MedicoService medicoService;

	public MedicoController(MedicoService medicoService) {
		this.medicoService = medicoService;
	}
	
	@GetMapping("/medicos")
	public String listarMedicos(Model model) {
		model.addAttribute("medicos", medicoService.listarTodos());
		return "medicos"; // busca vista_pacientes.html en /templates		
	}
}
