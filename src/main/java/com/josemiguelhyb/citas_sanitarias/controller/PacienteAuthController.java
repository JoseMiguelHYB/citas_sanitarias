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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.service.MedicoService;
import com.josemiguelhyb.citas_sanitarias.service.PacienteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/paciente")
public class PacienteAuthController {

	private static final Logger log = LoggerFactory.getLogger(PacienteAuthController.class);

	private final PacienteService pacienteService;
	private final MedicoService medicoService;

	public PacienteAuthController(PacienteService pacienteService, MedicoService medicoService) {
		this.pacienteService = pacienteService;
		this.medicoService = medicoService;
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
	    log.info("Contraseña (antes de encriptar): {}", paciente.getPassword()); // ⚠️ solo para debug
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
	        // Encriptar contraseña con BCrypt
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String hashedPassword = passwordEncoder.encode(paciente.getPassword());
	        
	        // Debug
	        log.info("DEBUG Registro → contraseña en claro: {}", paciente.getPassword());
	        log.info("DEBUG Registro → hash guardado en BD: {}", hashedPassword);
	        
	        paciente.setPassword(hashedPassword);
	        pacienteService.guardar(paciente);
	        
	        log.info("Paciente guardado correctamente en la base de datos ✅");
	        model.addAttribute("successMessage", "Paciente registrado correctamente");
	        return "redirect:/paciente/login";

	    } catch (IllegalArgumentException ex) {
	        log.warn("Paciente con DNI {} ya está registrado ❌", paciente.getDni());
	        model.addAttribute("errorMessage", ex.getMessage());
	        return "redirect:/paciente/registro";
	    }
	}

	// --------------------- Login ---------------------

	@GetMapping("/login")
	public String mostrarLogin(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "paciente_login"; // esto es un paciente_login.html
	}

	// Usando ByCrypt
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

	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    
	    
	    //  Debug
	    log.info("DEBUG Login → contraseña introducida: {}", password);
	    log.info("DEBUG Login → hash recuperado de BD: {}", paciente.getPassword());
	    log.info("DEBUG Login → resultado comparación: {}", 
	             passwordEncoder.matches(password, paciente.getPassword()));
	    
	    

	    // Comprobamos con BCrypt
	    if (!passwordEncoder.matches(password, paciente.getPassword())) {
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
	        return "redirect:/paciente/login";
	    }

	    // Datos del paciente para saludo
	    model.addAttribute("paciente", paciente);

	    // Especialidades desde la BD (no hardcodeadas)
	    model.addAttribute("especialidades", medicoService.listarEspecialidades());

	    // Lista de médicos completa
	    model.addAttribute("medicos", medicoService.listarTodos());

	    return "paciente_home";
	}


	// --------------------- Logout ---------------------

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Invalida toda la sesión
		return "redirect:/paciente/login"; // Vuelve al inicio
	}

}
