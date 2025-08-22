package com.josemiguelhyb.citas_sanitarias.controller;

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
	
	/*
	 * 
	 *  @GetMapping("/nueva")
    public String mostrarFormularioNuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        return "citas/form"; // formulario para crear/editar
    }
	*/
	
	@PostMapping("/register")
	public String guardarCita(@RequestParam String especialidad,
	                          @RequestParam Long medicoId,
	                          @ModelAttribute Cita cita,
	                          RedirectAttributes redirectAttributes,
	                          HttpSession session) {

	    // 1. Paciente desde sesión
	    Paciente paciente = (Paciente) session.getAttribute("pacienteLogueado");

	    // 🔎 Depuración
	    System.out.println("---- DEPURACIÓN GUARDAR CITA ----");
	    System.out.println("Especialidad recibida: " + especialidad);
	    System.out.println("Médico ID recibido: " + medicoId);
	    System.out.println("Cita (fecha): " + cita.getFecha());
	    System.out.println("Cita (hora): " + cita.getHora());
	    System.out.println("Cita (motivo): " + cita.getMotivo());
	    System.out.println("Paciente en sesión: " + paciente);

	    if (paciente != null) {
	        System.out.println("Paciente ID: " + paciente.getId());
	        System.out.println("Paciente Nombre: " + paciente.getNombre());
	        System.out.println("Paciente DNI: " + paciente.getDni());
	    } else {
	        System.out.println("❌ No se encontró paciente en sesión");
	    }

	    if (paciente == null) {
	        redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para registrar una cita");
	        return "redirect:/login";
	    }

	    cita.setPaciente(paciente); // Guardamos todo la información del paciente

	    // 2. Médico desde BD
	    Medico medico = medicoService.buscarPorId(medicoId);
	    System.out.println("Médico recuperado: " + medico);

	    if (medico == null) {
	        redirectAttributes.addFlashAttribute("error", "El médico no existe");
	        return "redirect:/paciente_home";
	    }
	    cita.setMedico(medico);

	    // 3. Estado inicial
	    cita.setEstado(EstadoCita.PENDIENTE);
	    System.out.println("Estado de la cita: " + cita.getEstado());

	    // 4. Guardar
	    citaService.guardar(cita);
	    System.out.println("✅ Cita guardada correctamente con ID: " + cita.getId());

	    redirectAttributes.addFlashAttribute("successMessage", "¡Gracias! Su cita ha sido creada correctamente 🎉");
	    return "redirect:/paciente/paciente_home";
	}



    /**
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
