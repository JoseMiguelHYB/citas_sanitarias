package com.josemiguelhyb.citas_sanitarias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes") // Ahora la tabla en BD se llamará "pacientes"
public class Paciente extends Usuario {

	private String direccion;

	private String localidad;

	@Column(name = "codigo_postal")
	private String codigoPostal;


	@Column(name = "numero_seguridad_social")
	private String numeroSeguridadSocial;

	@Column(name = "grupo_sanguineo")
	private String grupoSanguineo;

	private String alergias;

	@Column(name = "contacto_emergencia")
	private String contactoEmergencia;

	@Column(name = "seguro_medico_mutua")
	private String seguroMedicoMutua;

	// Constructor vacío obligatorio para JPA
	public Paciente() {
	}

	// Constructor con parámetros
	public Paciente(String direccion, String localidad, String codigoPostal, String numeroSeguridadSocial,
			String grupoSanguineo, String alergias, String contactoEmergencia, String seguroMedicoMutua) {
		super(); // Inicializo los atributos de Usuario
		this.direccion = direccion;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.numeroSeguridadSocial = numeroSeguridadSocial;
		this.grupoSanguineo = grupoSanguineo;
		this.alergias = alergias;
		this.contactoEmergencia = contactoEmergencia;
		this.seguroMedicoMutua = seguroMedicoMutua;
	}
	
	// Getters y setters
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	public String getSeguroMedicoMutua() {
		return seguroMedicoMutua;
	}

	public void setSeguroMedicoMutua(String seguroMedicoMutua) {
		this.seguroMedicoMutua = seguroMedicoMutua;
	}

	@Override
	public String toString() {
		return "Paciente [direccion=" + direccion + ", localidad=" + localidad + ", codigoPostal=" + codigoPostal
				+ ", numeroSeguridadSocial=" + numeroSeguridadSocial + ", grupoSanguineo=" + grupoSanguineo
				+ ", alergias=" + alergias + ", contactoEmergencia=" + contactoEmergencia + ", seguroMedicoMutua="
				+ seguroMedicoMutua + "]";
	}	
}
