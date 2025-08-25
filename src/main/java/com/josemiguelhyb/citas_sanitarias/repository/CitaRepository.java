package com.josemiguelhyb.citas_sanitarias.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

	List<Cita> findByPacienteId(Long pacienteId);

	Optional<Cita> findById(Long id);
	
    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByPacienteIdOrderByFechaAscHoraAsc(Long pacienteId);


	
}
