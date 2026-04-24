package com.example.manga.service;

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

    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    public Genero findById(Long id) {
        return generoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado con id: " + id));
    }

    public Genero create(Genero genero) {
        generoRepository.save(genero);
        return genero;
    }

    public Genero update(Long id, Genero genero) {
        Genero existing = generoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Genero no encontrado con id: " + id));
        existing.setNombre(genero.getNombre());
        existing.setDescripcion(genero.getDescripcion());
        generoRepository.save(existing);
        return existing;
    }

    public void delete(Long id) {
        generoRepository.deleteById(id);
    }
}