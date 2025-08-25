package com.josemiguelhyb.citas_sanitarias.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josemiguelhyb.citas_sanitarias.model.Cita;
import com.josemiguelhyb.citas_sanitarias.model.Medico;
import com.josemiguelhyb.citas_sanitarias.service.CitaService;
import com.josemiguelhyb.citas_sanitarias.service.MedicoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/medicos") // usar singular para ser coherente con login/home NO DEBRIAZ SER MAYUSCULAS
							// PERO BUENO
public class MedicoController {

	private final MedicoService medicoService;
	private final CitaService citaService;

	public MedicoController(MedicoService medicoService, CitaService citaService) {
		this.medicoService = medicoService;
		this.citaService = citaService;
	}

	// Listar todos los médicos (público, opcional)
	@GetMapping
	public String listarMedicos(Model model) {
		model.addAttribute("medicos", medicoService.listarTodos());
		return "medicos";
	}

	@GetMapping("/pacientes")
	public String verPacientesDeMedico(HttpSession session, Model model) {
		Medico medico = (Medico) session.getAttribute("medicoLogueado");
		if (medico == null) {
			return "redirect:/medico/login";
		}

		// Todas las citas de ese médico
		List<Cita> citas = citaService.buscarPorMedico(medico.getId());

		model.addAttribute("citas", citas); // <-- NECESARIO

		return "medico_pacientes";
	}

	@GetMapping("/citas/{id}/atender")
	public String atenderCita(@PathVariable Long id) {
		citaService.cambiarEstado(id, "ACEPTAR");
		return "redirect:/medicos/pacientes";
	}

	@GetMapping("/citas/{id}/cancelar")
	public String cancelarCita(@PathVariable Long id) {
		citaService.cambiarEstado(id, "CANCELADA");
		return "redirect:/medicos/pacientes";
	}

	@GetMapping("/citas/{id}/reprogramar")
	public String reprogramarCita(@PathVariable Long id) {
		citaService.cambiarEstado(id, "REPROGRAMAR");
		return "redirect:/medicos/pacientes";
	}

}
