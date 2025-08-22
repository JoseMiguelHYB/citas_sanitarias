package com.josemiguelhyb.citas_sanitarias.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.josemiguelhyb.citas_sanitarias.model.Medico;
import com.josemiguelhyb.citas_sanitarias.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    // Listar todos
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    // Guardar nuevo (con validación de nº colegiado único)
    public Medico guardar(Medico medico) {
        if (medico.getId() == null) { // solo validar en creación
            if (medicoRepository.findByNumeroColegiado(medico.getNumeroColegiado()).isPresent()) {
                throw new IllegalArgumentException("El nº de colegiado ya está registrado");
            }
        }
        return medicoRepository.save(medico);
    }

    // Buscar por id (para editar)
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElse(null);

    }

    // Eliminar por id
    public void eliminar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe médico con id: " + id);
        }
        medicoRepository.deleteById(id);
    }

    // Buscar por nº de colegiado (para login)
    public Medico buscarPorColegiado(String numeroColegiado) {
        return medicoRepository.findByNumeroColegiado(numeroColegiado).orElse(null);
    }
    
    
 // Nuevo método: obtener lista de especialidades únicas
    public List<String> listarEspecialidades() {
        return medicoRepository.findAll()
                               .stream()
                               .map(Medico::getEspecialidad)
                               .distinct()
                               .collect(Collectors.toList());
    }    
}
