package com.josemiguelhyb.citas_sanitarias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.citas_sanitarias.model.Medico;
import com.josemiguelhyb.citas_sanitarias.repository.MedicoRepository;

@Service
public class MedicoService {
	private final MedicoRepository medicoRepository;
		
	public MedicoService(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

	public List<Medico> listarTodos() {
		return medicoRepository.findAll();
	}
}
