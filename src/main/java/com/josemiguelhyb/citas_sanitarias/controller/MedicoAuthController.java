//Se encarga de login, register, home, logout todo relacionado con la identidad del 
//médico y la sesión

package com.josemiguelhyb.citas_sanitarias.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josemiguelhyb.citas_sanitarias.model.Medico;
import com.josemiguelhyb.citas_sanitarias.service.MedicoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/medico")
public class MedicoAuthController {

	private static final Logger log = LoggerFactory.getLogger(MedicoAuthController.class);

	private final MedicoService medicoService;

	public MedicoAuthController(MedicoService medicoService) {
		this.medicoService = medicoService;
	}

	// --------------------- Register ---------------------

	@GetMapping("/register")
	public String mostrarRegistro(Model model) {
		model.addAttribute("medico", new Medico());
		log.info("Se muestra formulario de registro con médico vacío");
		return "medico_registro";
	}

	@PostMapping("/register")
	public String registrarMedico(@ModelAttribute Medico medico, Model model) {
		log.info("=== DATOS RECIBIDOS DEL MÉDICO ===");
		log.info("Nombre: {}", medico.getNombre());
		log.info("Apellido: {}", medico.getApellido());
		log.info("Nº Colegiado: {}", medico.getNumeroColegiado());
		log.info("Especialidad: {}", medico.getEspecialidad());
		log.info("Contraseña (antes de encriptar): {}", medico.getPassword()); // ⚠️ solo debug
		log.info("Email: {}", medico.getEmail());
		log.info("Teléfono: {}", medico.getTelefono());
		log.info("===================================");

		try {
			// Encriptar contraseña con BCrypt
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(medico.getPassword());

			// Debug
			log.info("DEBUG Registro → contraseña en claro: {}", medico.getPassword());
			log.info("DEBUG Registro → hash guardado en BD: {}", hashedPassword);

			medico.setPassword(hashedPassword);
			medicoService.guardar(medico);

			log.info("Médico guardado correctamente en la base de datos ✅");
			model.addAttribute("successMessage", "Médico registrado correctamente");
			return "redirect:/medico/login";

		} catch (IllegalArgumentException ex) {
			log.warn("Médico con colegiado {} ya está registrado ❌", medico.getNumeroColegiado());
			model.addAttribute("errorMessage", ex.getMessage());
			return "redirect:/medico/register";
		}
	}

	// --------------------- Login ---------------------

	@GetMapping("/login")
	public String mostrarLogin(Model model) {
		model.addAttribute("medico", new Medico());
		return "medico_login";
	}

	@PostMapping("/login")
	public String loginMedico(@RequestParam String numeroColegiado, @RequestParam String password, HttpSession session,
			RedirectAttributes redirectAttrs) {

		Medico medico = medicoService.buscarPorColegiado(numeroColegiado);

		if (medico == null) {
			redirectAttrs.addFlashAttribute("error", "El nº de colegiado no está registrado ❌");
			return "redirect:/medico/login";
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// Debug
		log.info("DEBUG Login → contraseña introducida: {}", password);
		log.info("DEBUG Login → hash recuperado de BD: {}", medico.getPassword());
		log.info("DEBUG Login → resultado comparación: {}", passwordEncoder.matches(password, medico.getPassword()));

		// Comprobamos con BCrypt
		if (!passwordEncoder.matches(password, medico.getPassword())) {
			redirectAttrs.addFlashAttribute("error", "La contraseña es incorrecta 🔑");
			return "redirect:/medico/login";
		}

		// Si llega aquí, login correcto
		session.setAttribute("medicoLogueado", medico);
		return "redirect:/medico/medico_home";
	}

	// --------------------- Home ---------------------

	@GetMapping("/medico_home")
	public String mostrarDashboard(HttpSession session, Model model) {
		Medico medico = (Medico) session.getAttribute("medicoLogueado");

		if (medico == null) {
			return "redirect:/medico/login";
		}

		model.addAttribute("medico", medico);
		return "medico_home";
	}

	// --------------------- Logout ---------------------

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/medico/login";
	}
}
