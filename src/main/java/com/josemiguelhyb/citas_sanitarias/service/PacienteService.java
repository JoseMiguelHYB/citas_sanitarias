package com.josemiguelhyb.citas_sanitarias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.citas_sanitarias.model.Paciente;
import com.josemiguelhyb.citas_sanitarias.repository.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // Listar todos
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    // Guardar nuevo (con validación de DNI único)
    public Paciente guardar(Paciente paciente) {
        if (paciente.getId() == null) { // solo validar si es creación
            if (pacienteRepository.findByDni(paciente.getDni()).isPresent()) {
                throw new IllegalArgumentException("El DNI ya está registrado");
            }
        }
        return pacienteRepository.save(paciente);
    }

    // Buscar por id (para editar)
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con id: " + id));
    }

    // Eliminar por id
    public void eliminar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe paciente con id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    // Buscar por DNI (para login)
    public Paciente buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni).orElse(null);
    }
}
