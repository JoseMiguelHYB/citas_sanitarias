package com.josemiguelhyb.citas_sanitarias.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.service.PacienteService;

@Controller
@RequestMapping("/paciente")
public class PacienteAuthController {

	private static final Logger log = LoggerFactory.getLogger(PacienteAuthController.class);

	private final PacienteService pacienteService;

	public PacienteAuthController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	@GetMapping("/register")
	public String mostrarFormularioRegistro(Model model) {
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
			return "paciente_login"; // redirigir a login tras registro exitoso

		} catch (IllegalArgumentException ex) {
			log.warn("Paciente con DNI {} ya está registrado ❌", paciente.getDni());
			model.addAttribute("errorMessage", ex.getMessage()); // "El DNI ya está registrado"
			return "paciente_registro"; // vuelve al formulario mostrando toast
		}
	}
}
