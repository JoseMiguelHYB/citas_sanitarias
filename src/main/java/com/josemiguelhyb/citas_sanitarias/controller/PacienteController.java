// Listar pacientes u operaciones CRUD

package com.josemiguelhyb.citas_sanitarias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.service.PacienteService;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
	
	private final PacienteService pacienteService;

	public PacienteController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	
	// Listar
	@GetMapping
	public String listarPacientes(Model model) {
		model.addAttribute("pacientes", pacienteService.listarTodos());
		return "pacientes"; // busca pacientes.html
	}
	
	// Crear (formulario)
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
	    model.addAttribute("paciente", new Paciente());
	    return "paciente_form";
	}

	// Guardar nuevo
	@PostMapping
	public String guardarPaciente(@ModelAttribute Paciente paciente) {
	    pacienteService.guardar(paciente);
	    return "redirect:/pacientes";
	}

	// Editar
	@GetMapping("/editar/{id}")
	public String editarPaciente(@PathVariable Long id, Model model) {
	    model.addAttribute("paciente", pacienteService.buscarPorId(id));
	    return "paciente_form";
	}

	// Actualizar
	@PostMapping("/actualizar/{id}")
	public String actualizarPaciente(@PathVariable Long id, @ModelAttribute Paciente paciente) {
	    paciente.setId(id);
	    pacienteService.guardar(paciente);
	    return "redirect:/pacientes";
	}

	// Eliminar
	@GetMapping("/eliminar/{id}")
	public String eliminarPaciente(@PathVariable Long id) {
	    pacienteService.eliminar(id);
	    return "redirect:/pacientes";
	}
}
