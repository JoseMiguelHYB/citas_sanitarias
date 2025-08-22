package com.josemiguelhyb.citas_sanitarias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.citas_sanitarias.model.Cita;
import com.josemiguelhyb.citas_sanitarias.repository.CitaRepository;

@Service
public class CitaService {
	private final CitaRepository citaRepository;

	public CitaService(CitaRepository citaRepository) {
		this.citaRepository = citaRepository;
	}
	
	public List<Cita> listarTodos() {
		return citaRepository.findAll();
	}
	
	
	 // Guardar nueva cita con validaciones
	public Cita guardar(Cita cita) {
	    // Validar: no puede haber otra cita igual (mismo paciente, médico, fecha y hora)
	    boolean existeConflicto;

	    if (cita.getId() == null) {
	        // Creación → basta con comprobar si existe
	        existeConflicto = citaRepository.existsByPacienteIdAndMedicoIdAndFechaAndHora(
	                cita.getPaciente().getId(),
	                cita.getMedico().getId(),
	                cita.getFecha(),
	                cita.getHora()
	        );
	    } else {
	        // Edición → excluir la misma cita
	        existeConflicto = citaRepository.existsByPacienteIdAndMedicoIdAndFechaAndHoraAndIdNot(
	                cita.getPaciente().getId(),
	                cita.getMedico().getId(),
	                cita.getFecha(),
	                cita.getHora(),
	                cita.getId()
	        );
	    }

	    if (existeConflicto) {
	        throw new IllegalArgumentException("Ya existe una cita con ese médico en esa fecha y hora");
	    }

	    // Validar fecha
	    if (cita.getFecha().isBefore(java.time.LocalDate.now())) {
	        throw new IllegalArgumentException("No puedes reservar una cita en una fecha pasada");
	    }

	    return citaRepository.save(cita);
	}

	
}
