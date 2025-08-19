package com.josemiguelhyb.citas_sanitarias.model;

public enum EstadoCita {
	PENDIENTE,		// La cita está registrada pero no confirmada
	CONFIRMADA,		// El paciente/centro confirmó la asistencia
	CANCELADA,		// Se anuló antes de realizarse
	EN_CURSO,		// El paciente está siendo atendido
	COMPLETADA,		// Ya se realizó exitosamente
	AUSENTE,			// El paciente no asistió
	REPROGRAMADA,		// La cita fue movida a otra fecha/hora
	REALIZADA
}
