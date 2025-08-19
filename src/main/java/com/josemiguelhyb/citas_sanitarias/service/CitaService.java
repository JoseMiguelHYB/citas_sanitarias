package com.josemiguelhyb.citas_sanitarias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.citas_sanitarias.model.Cita;
import com.josemiguelhyb.citas_sanitarias.repository.CitaRepository;

@Service
public class CitaService {
	private final CitaRepository citaRepository;

	public CitaService(CitaRepository citaRepository) {
		this.citaRepository = citaRepository;
	}
	
	public List<Cita> listarTodos() {
		return citaRepository.findAll();
	}
}
