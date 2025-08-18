package com.josemiguelhyb.citas_sanitarias.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
// También se podría usar @Inheritance(strategy = InheritanceType.JOINED)
// si quieres que Usuario sea tabla en BD, pero como base basta con @MappedSuperclass
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombres", nullable = false) // columna en la BD será "nombres"
	private String nombre;

	@Column(name = "apellidos", nullable = false)
	private String apellido;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String dni;

	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;

	private String email;

	private String telefono;

	private String genero;
	
	// ---- Constructores ----
	public Usuario() {}

	public Usuario(Long id, String nombre, String apellido, String password, String dni, LocalDate fechaNacimiento,
			String email, String telefono, String genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.telefono = telefono;
		this.genero = genero;
	}

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", password=" + password
				+ ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", email=" + email + ", telefono="
				+ telefono + ", genero=" + genero + "]";
	}
}
