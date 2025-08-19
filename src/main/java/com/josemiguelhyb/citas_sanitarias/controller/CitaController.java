package com.josemiguelhyb.citas_sanitarias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josemiguelhyb.citas_sanitarias.service.CitaService;

@Controller
@RequestMapping("/vista")
public class CitaController {
	
	private final CitaService citaService;

	public CitaController(CitaService citaService) {
		this.citaService = citaService;
	}
	
	@GetMapping("/citas")
	public String listarCitas(Model model) {
		model.addAttribute("citas", citaService.listarTodos());
		return "citas"; // busca citas.html en /templates		
	}
	
	// todo relacionado a la cita
	
	/*
	 * 
	 *  @GetMapping("/nueva")
    public String mostrarFormularioNuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        return "citas/form"; // formulario para crear/editar
    }

    @PostMapping
    public String guardarCita(@ModelAttribute("cita") Cita cita) {
        citaService.guardar(cita);
        return "redirect:/citas";
    }

    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        Cita cita = citaService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        model.addAttribute("cita", cita);
        return "citas/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminar(id);
        return "redirect:/citas";
    }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	
}
