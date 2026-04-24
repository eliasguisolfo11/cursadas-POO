package com.example.manga.service;

import com.example.manga.dto.AutorDTO;
import com.example.manga.model.Autor;
import com.example.manga.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<AutorDTO> findAll() {
        return autorRepository.findAll().stream()
            .map(this::toAutorDTO)
            .collect(Collectors.toList());
    }

    public AutorDTO findById(Long id) {
        Autor autor = autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
        return toAutorDTO(autor);
    }

    public AutorDTO create(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setNombre(dto.getNombre());
        autor.setPais(dto.getPais());
        autor.setFechaNacimiento(dto.getFechaNacimiento());
        autorRepository.save(autor);
        return toAutorDTO(autor);
    }

    public AutorDTO update(Long id, AutorDTO dto) {
        Autor autor = autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
        autor.setNombre(dto.getNombre());
        autor.setPais(dto.getPais());
        autor.setFechaNacimiento(dto.getFechaNacimiento());
        autorRepository.save(autor);
        return toAutorDTO(autor);
    }

    public void delete(Long id) {
        autorRepository.deleteById(id);
    }

    public List<AutorDTO> search(String nombre) {
        return autorRepository.findByNombreContaining(nombre).stream()
            .map(this::toAutorDTO)
            .collect(Collectors.toList());
    }

    private AutorDTO toAutorDTO(Autor autor) {
        AutorDTO dto = new AutorDTO();
        dto.setId(autor.getId());
        dto.setNombre(autor.getNombre());
        dto.setPais(autor.getPais());
        dto.setFechaNacimiento(autor.getFechaNacimiento());
        return dto;
    }
}