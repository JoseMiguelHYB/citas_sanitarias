package com.josemiguelhyb.citas_sanitarias.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "citas")
public class Cita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
		
	@ManyToOne(optional = false) // una cita pertenece a un paciente
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;
	
	@ManyToOne(optional = false) // una cita pertenece a un médico
	@JoinColumn(name = "medico_id", nullable = false) 
	private Medico medico;
	
	@Column(nullable = false)
	private LocalDate fecha;
	
	@Column(nullable = false)
	private LocalTime hora;
	
	@Column(length = 200)
	private String motivo; // opcional: descripción del motivo de la cita
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private EstadoCita estado; // PENDIENTE, CONFIRMADA, CANCELADA,...	
	
	@Column(name = "tipo_consulta")
	private String TipoConsulta; // Presencial/telefónica/Online
	
	private String ubicacion; // Dirección donde se encuentra la cita	
	
	private String observaciones; // Importante porque indica que el paciente llego con los resultados necesarios
	
	@Column(name = "duracion_minutos")
	private Integer duracionMinutos;
	
	/*@Column(name = "creada_en")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "actualizada_en")
	private LocalDateTime ultimaActualizacion;*/

	public Cita() { 
	}
	
	public Cita(Long id, Paciente paciente, Medico medico, LocalDate fecha, LocalTime hora, String motivo,
			EstadoCita estado, String tipoConsulta, String ubicacion, String observaciones, Integer duracionMinutos,
			LocalDateTime fechaCreacion, LocalDateTime ultimaActualizacion) {
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
		this.estado = estado;
		TipoConsulta = tipoConsulta;
		this.ubicacion = ubicacion;
		this.observaciones = observaciones;
		this.duracionMinutos = duracionMinutos;
		//this.fechaCreacion = fechaCreacion;
		//this.ultimaActualizacion = ultimaActualizacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public EstadoCita getEstado() {
		return estado;
	}

	public void setEstado(EstadoCita estado) {
		this.estado = estado;
	}

	public String getTipoConsulta() {
		return TipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		TipoConsulta = tipoConsulta;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(Integer duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	

	/*public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}*/

	@Override
	public String toString() {
		return "Cita [id=" + id + ", paciente=" + paciente + ", medico=" + medico + ", fecha=" + fecha + ", hora="
				+ hora + ", motivo=" + motivo + ", estado=" + estado + ", TipoConsulta=" + TipoConsulta + ", ubicacion="
				+ ubicacion + ", observaciones=" + observaciones + ", duracionMinutos=" + duracionMinutos + "]";
	}

}
