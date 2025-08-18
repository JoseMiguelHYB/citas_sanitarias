package com.josemiguelhyb.citas_sanitarias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.citas_sanitarias.model.Medico;
import com.josemiguelhyb.citas_sanitarias.repository.MedicoRepository;

@Service
public class MedicoService {
	private final MedicoRepository medicoRepository;
		
	public MedicoService(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

	// Lista de pacientes simulada (hardcoded)
	/*private List<Paciente> pacientes = Arrays.asList(
			new Paciente(1L, "Juan Peréz", "juan@example.com","54395748T"),
			new Paciente(2L, "María López", "maria@exameple.com", "34567234J"),
			new Paciente(3L, "Carlos García", "carlos@example.com", "1234567K"));
*/
	public List<Medico> listarTodos() {
		return medicoRepository.findAll();
	}
}
