package com.josemiguelhyb.citas_sanitarias.controller;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josemiguelhyb.citas_sanitarias.model.Cita;
import com.josemiguelhyb.citas_sanitarias.model.EstadoCita;
import com.josemiguelhyb.citas_sanitarias.model.Medico;
import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.service.CitaService;
import com.josemiguelhyb.citas_sanitarias.service.MedicoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/citas")
public class CitaController {

	private final CitaService citaService;
	private final MedicoService medicoService; // 👈 nuevo atributo

	// Constructor con inyección de dependencias
	public CitaController(CitaService citaService, MedicoService medicoService) {
		this.citaService = citaService;
		this.medicoService = medicoService;
	}

	// Listar
	@GetMapping
	public String listarCitas(Model model) {
		model.addAttribute("citas", citaService.listarTodos());
		return "citas"; // busca citas.html en /templates
	}

	// todo relacionado a la cita

	@GetMapping("/crear")
	public String mostrarFormularioNuevaCita(Model model) {
	    model.addAttribute("cita", new Cita());

	    // Sacamos todas las especialidades distintas
	    List<String> especialidades = medicoService.listarEspecialidades(); 
	    model.addAttribute("especialidades", especialidades);

	    return "cita_crear"; 
	}

	@PostMapping("/crear")
	public String guardarCita(@RequestParam String especialidad,
	                          @ModelAttribute Cita cita,
	                          RedirectAttributes redirectAttributes,
	                          HttpSession session) {

	    // 1. Paciente desde sesión
	    Paciente paciente = (Paciente) session.getAttribute("pacienteLogueado");
	    if (paciente == null) {
	        redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para registrar una cita");
	        return "redirect:/login";
	    }
	    cita.setPaciente(paciente);

	    // 2. Buscar médicos por especialidad
	    List<Medico> medicos = medicoService.listarPorEspecialidad(especialidad);

	    if (medicos == null || medicos.isEmpty()) {
	        redirectAttributes.addFlashAttribute("error", "No hay médicos disponibles en la especialidad: " + especialidad);
	        return "redirect:/citas/crear";
	    }

	    // 3. Seleccionar uno al azar
	    Random random = new Random();
	    Medico medicoAsignado = medicos.get(random.nextInt(medicos.size()));
	    cita.setMedico(medicoAsignado);

	    // 4. Estado inicial
	    cita.setEstado(EstadoCita.PENDIENTE);

	    // 5. Guardar
	    citaService.guardar(cita);

	    redirectAttributes.addFlashAttribute("successMessage",
	            "¡Gracias! Su cita ha sido creada con el Dr. " + medicoAsignado.getNombre() + " (" + especialidad + ") 🎉");
	    return "redirect:/paciente/paciente_home";
	}


	/**
	 * @GetMapping("/editar/{id}") public String editarCita(@PathVariable Long id,
	 * Model model) { Cita cita = citaService.buscarPorId(id).orElseThrow(() -> new
	 * IllegalArgumentException("Cita no encontrada")); model.addAttribute("cita",
	 * cita); return "citas/form"; }
	 * 
	 * @GetMapping("/eliminar/{id}") public String eliminarCita(@PathVariable Long
	 * id) { citaService.eliminar(id); return "redirect:/citas"; }
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
	 */

}
