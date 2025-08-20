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

	public List<Paciente> listarTodos() {
		return pacienteRepository.findAll();
	}

	public Paciente guardar(Paciente paciente) {
		// Verificar si ya existe un paciente con ese DNI
		if (pacienteRepository.findByDni(paciente.getDni()).isPresent()) {
			throw new IllegalArgumentException("El DNI ya está registrado");
		}
		return pacienteRepository.save(paciente);
	}
	
	// Método para Login / búsqueda por DNI
	public Paciente buscarPorDni(String dni) {
		return pacienteRepository.findByDni(dni).orElse(null);		
	}	
}
