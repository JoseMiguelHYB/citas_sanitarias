package com.josemiguelhyb.citas_sanitarias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.repository.PacienteRepository;

@Service
public class PacienteService {
	private final PacienteRepository pacienteRepository;
		
	public PacienteService(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	// Lista de pacientes simulada (hardcoded)
	/*private List<Paciente> pacientes = Arrays.asList(
			new Paciente(1L, "Juan Peréz", "juan@example.com","54395748T"),
			new Paciente(2L, "María López", "maria@exameple.com", "34567234J"),
			new Paciente(3L, "Carlos García", "carlos@example.com", "1234567K"));
*/
	public List<Paciente> listarTodos() {
		return pacienteRepository.findAll();
	}
}
