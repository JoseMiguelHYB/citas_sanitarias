package com.josemiguelhyb.citas_sanitarias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "index"; // busca templates/index.html
	}
	
	@GetMapping("/paciente")
	public String paciente() {
		return "paciente_acceso"; // busca templates/paciente_acceso.html
	}
	
	@GetMapping("/medico")
	public String medico() {
		return "medico_acceso"; // busca templates/medico_acceso.html
	}
}
