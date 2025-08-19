package com.josemiguelhyb.citas_sanitarias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemiguelhyb.citas_sanitarias.model.Cita;

public interface CitaRepository extends JpaRepository <Cita, Long> {
	
	// Ejemplo: buscar citas por pacientes
	// List<Cita> findByPaciente(Long pacienteId);
	
	// Ejemplo: buscar citas por medico
	// List<Cita> findByMedicoId(Long medicoId);
	
	// Ejemplo: buscar citas por estado
	// List<Cita> findByEstado(EstadoCita estado);	
}
