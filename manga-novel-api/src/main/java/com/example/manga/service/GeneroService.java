package com.example.manga.service;

import com.example.manga.dto.GeneroDTO;
import com.example.manga.model.Genero;
import com.example.manga.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public List<GeneroDTO> findAll() {
        return generoRepository.findAll().stream()
            .map(this::toGeneroDTO)
            .collect(Collectors.toList());
    }

    public GeneroDTO findById(Long id) {
        Genero genero = generoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado con id: " + id));
        return toGeneroDTO(genero);
    }

    public GeneroDTO create(GeneroDTO dto) {
        Genero genero = new Genero();
        genero.setNombre(dto.getNombre());
        genero.setDescripcion(dto.getDescripcion());
        generoRepository.save(genero);
        return toGeneroDTO(genero);
    }

    public GeneroDTO update(Long id, GeneroDTO dto) {
        Genero genero = generoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado con id: " + id));
        genero.setNombre(dto.getNombre());
        genero.setDescripcion(dto.getDescripcion());
        generoRepository.save(genero);
        return toGeneroDTO(genero);
    }

    public void delete(Long id) {
        generoRepository.deleteById(id);
    }

    private GeneroDTO toGeneroDTO(Genero genero) {
        GeneroDTO dto = new GeneroDTO();
        dto.setId(genero.getId());
        dto.setNombre(genero.getNombre());
        dto.setDescripcion(genero.getDescripcion());
        return dto;
    }
}