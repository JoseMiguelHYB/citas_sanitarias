package com.josemiguelhyb.citas_sanitarias.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemiguelhyb.citas_sanitarias.model.Cita;

public interface CitaRepository extends JpaRepository <Cita, Long> {
	
	// Ejemplo: buscar citas por pacientes
	// List<Cita> findByPaciente(Long pacienteId);
	
	// Ejemplo: buscar citas por medico
	// List<Cita> findByMedicoId(Long medicoId);
	
	// Ejemplo: buscar citas por estado
	// List<Cita> findByEstado(EstadoCita estado);	
	
	boolean existsByPacienteIdAndMedicoIdAndFechaAndHora(Long pacienteId, Long medicoId, LocalDate fecha, LocalTime hora);

	boolean existsByPacienteIdAndMedicoIdAndFechaAndHoraAndIdNot(Long pacienteId, Long medicoId, LocalDate fecha, LocalTime hora, Long id);

}
