package com.example.manga.service;

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

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public Autor findById(Long id) {
        return autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
    }

    public Autor create(Autor autor) {
        autorRepository.save(autor);
        return autor;
    }

    public Autor update(Long id, Autor autor) {
        Autor existing = autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
        existing.setNombre(autor.getNombre());
        existing.setPais(autor.getPais());
        existing.setFechaNacimiento(autor.getFechaNacimiento());
        autorRepository.save(existing);
        return existing;
    }

    public void delete(Long id) {
        autorRepository.deleteById(id);
    }

    public List<Autor> search(String nombre) {
        return autorRepository.findByNombreContaining(nombre);
    }
}