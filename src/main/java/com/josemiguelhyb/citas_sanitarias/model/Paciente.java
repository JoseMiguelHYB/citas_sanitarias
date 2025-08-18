package com.josemiguelhyb.citas_sanitarias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pacientes") // Ahora la tabla en BD se llamará "pacientes"
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombres") // columna en la BD será "nombres"
	private String nombre;
	
	private String email;
	
	private String dni;
	
	// Constructor vacío obligatorio para JPA
	public Paciente() {		
	}
	
	// Constructor con parámetros
	public Paciente(Long id, String nombre, String email, String dni) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
	}

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", dni=" + dni + "]";
	}	
}
