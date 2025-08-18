package com.josemiguelhyb.citas_sanitarias.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicos") // Ahora la tabla en BD se llamará "pacientes"
public class Medico extends Usuario {
	
	private String especialidad;

	private String numeroColegiado;
	
    // Horario de atención se representa
	// ahora con dos campos horaInicio
	// y horaFin
	
	private LocalTime horaInicio;
	
	private LocalTime horaFin;

	private String consulta;

	@Column(name = "centro_salud")
	private String centroSalud;

	@Column(name = "direccion_centro")
	private String direccionCentro;

	@Column(name = "codigo_postal_centro")
	private String codigoPostalCentro;

	// Constructor vacío obligatorio para JPA
	public Medico() {
	}

	// Constructor con parámetros
	public Medico(String especialidad, String numeroColegiado, LocalTime horarioAtencion, LocalTime horaInicio,
			LocalTime horaFin, String consulta, String centroSalud, String direccionCentro, String codigoPostalCentro) {
		super();
		this.especialidad = especialidad;
		this.numeroColegiado = numeroColegiado;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.consulta = consulta;
		this.centroSalud = centroSalud;
		this.direccionCentro = direccionCentro;
		this.codigoPostalCentro = codigoPostalCentro;
	}

	// Getters y setters
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNumeroColegiado() {
		return numeroColegiado;
	}

	public void setNumeroColegiado(String numeroColegiado) {
		this.numeroColegiado = numeroColegiado;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getCentroSalud() {
		return centroSalud;
	}

	public void setCentroSalud(String centroSalud) {
		this.centroSalud = centroSalud;
	}

	public String getDireccionCentro() {
		return direccionCentro;
	}

	public void setDireccionCentro(String direccionCentro) {
		this.direccionCentro = direccionCentro;
	}

	public String getCodigoPostalCentro() {
		return codigoPostalCentro;
	}

	public void setCodigoPostalCentro(String codigoPostalCentro) {
		this.codigoPostalCentro = codigoPostalCentro;
	}

	@Override
	public String toString() {
		return "Medico [especialidad=" + especialidad + ", numeroColegiado=" + numeroColegiado + ", horarioAtencion="
				+ ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", consulta=" + consulta
				+ ", centroSalud=" + centroSalud + ", direccionCentro=" + direccionCentro + ", codigoPostalCentro="
				+ codigoPostalCentro + "]";
	}	
}
