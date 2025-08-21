// Se encarga de login, register, home, logout todo relacionado con la identididad del 
// paciente y la sesión

package com.josemiguelhyb.citas_sanitarias.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.service.PacienteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/paciente")
public class PacienteAuthController {

	private static final Logger log = LoggerFactory.getLogger(PacienteAuthController.class);

	private final PacienteService pacienteService;

	public PacienteAuthController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	// --------------------- Register ---------------------

	@GetMapping("/register")
	public String mostrarRegistro(Model model) {
		model.addAttribute("paciente", new Paciente());
		log.info("Se muestra formulario de registro con paciente vacío");
		return "paciente_registro";
	}

	@PostMapping("/register")
	public String registrarPaciente(@ModelAttribute Paciente paciente, Model model) {
		log.info("=== DATOS RECIBIDOS DEL PACIENTE ===");
		log.info("Nombre: {}", paciente.getNombre());
		log.info("Apellido: {}", paciente.getApellido());
		log.info("DNI: {}", paciente.getDni());
		log.info("Contraseña: {}", paciente.getPassword()); // ⚠️ cuidado en producción
		log.info("Fecha de nacimiento: {}", paciente.getFechaNacimiento());
		log.info("Email: {}", paciente.getEmail());
		log.info("Teléfono: {}", paciente.getTelefono());
		log.info("Género: {}", paciente.getGenero());
		log.info("Dirección: {}", paciente.getDireccion());
		log.info("Localidad: {}", paciente.getLocalidad());
		log.info("Código postal: {}", paciente.getCodigoPostal());
		log.info("Nº Seguridad Social: {}", paciente.getNumeroSeguridadSocial());
		log.info("Grupo sanguíneo: {}", paciente.getGrupoSanguineo());
		log.info("Alergias: {}", paciente.getAlergias());
		log.info("Contacto emergencia: {}", paciente.getContactoEmergencia());
		log.info("Seguro médico / mutua: {}", paciente.getSeguroMedicoMutua());
		log.info("===================================");

		try {
			pacienteService.guardar(paciente);
			log.info("Paciente guardado correctamente en la base de datos ✅");
			model.addAttribute("successMessage", "Paciente registrado correctamente");
			return "redirect:/paciente/login"; // redirigir a este endpoint

		} catch (IllegalArgumentException ex) {
			log.warn("Paciente con DNI {} ya está registrado ❌", paciente.getDni());
			model.addAttribute("errorMessage", ex.getMessage()); // "El DNI ya está registrado"
			return "redirect:/paciente/registro"; // redirige a este endpoint
		}
	}
	
	// --------------------- Login ---------------------

	@GetMapping("/login")
	public String mostrarLogin(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "paciente_login";
	}	
	
	@PostMapping("/login")
	public String loginPaciente(@RequestParam String dni,
	                            @RequestParam String password,
	                            HttpSession session,
	                            RedirectAttributes redirectAttrs) {

	    Paciente paciente = pacienteService.buscarPorDni(dni);

	    if (paciente == null) {
	        redirectAttrs.addFlashAttribute("error", "El DNI no está registrado ❌");
	        return "redirect:/paciente/login";
	    }

	    if (!paciente.getPassword().equals(password)) {
	        redirectAttrs.addFlashAttribute("error", "La contraseña es incorrecta 🔑");
	        return "redirect:/paciente/login";
	    }

	    // Si llega aquí, login correcto
	    session.setAttribute("pacienteLogueado", paciente);
	    return "redirect:/paciente/paciente_home";
	}
	
	
	// --------------------- Home ---------------------
	
	// Aquí estoy usando HttpSession para loguear al paciente en ese momento
	@GetMapping("/paciente_home")
	public String mostrarDashboard(HttpSession session, Model model) {
	    Paciente paciente = (Paciente) session.getAttribute("pacienteLogueado");

	    if (paciente == null) {
	        return "redirect:/paciente/login"; // si no hay sesión, vuelve a login
	    }

	    model.addAttribute("paciente", paciente);
	    return "paciente_home";
	}		
	
	// --------------------- Logout ---------------------
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // Invalida toda la sesión
	    return "redirect:/paciente/login";  // Vuelve al inicio
	}
	

}
