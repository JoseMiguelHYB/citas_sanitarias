package com.josemiguelhyb.citas_sanitarias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	// Métodos adicionales si los necesitamos (findByDni, etc...)
	
	
}
