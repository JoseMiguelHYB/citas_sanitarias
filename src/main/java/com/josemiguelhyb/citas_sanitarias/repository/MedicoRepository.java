package com.josemiguelhyb.citas_sanitarias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemiguelhyb.citas_sanitarias.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	// Métodos adicionales si los necesitamos (findByDni, etc...)
    Optional<Medico> findByNumeroColegiado(String numeroColegiado);
	
}
