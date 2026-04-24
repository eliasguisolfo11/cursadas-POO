package com.example.manga.service;

import com.example.manga.model.*;
import com.example.manga.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MangaService {

    private final MangaRepository mangaRepository;
    private final AutorRepository autorRepository;
    private final GeneroRepository generoRepository;
    private final CapituloRepository capituloRepository;

    public MangaService(MangaRepository mangaRepository, AutorRepository autorRepository,
                       GeneroRepository generoRepository, CapituloRepository capituloRepository) {
        this.mangaRepository = mangaRepository;
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
        this.capituloRepository = capituloRepository;
    }

    public List<Manga> findAll() {
        return mangaRepository.findAll();
    }

    public Manga findById(Long id) {
        return mangaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Manga no encontrado con id: " + id));
    }

    public Manga create(Manga manga) {
        if (manga.getAutorId() != null) {
            Autor autor = autorRepository.findById(manga.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + manga.getAutorId()));
            manga.setAutor(autor);
        }

        if (manga.getGenerosIds() != null) {
            for (Long generoId : manga.getGenerosIds()) {
                generoRepository.findById(generoId).ifPresent(manga::addGenero);
            }
        }

        mangaRepository.save(manga);
        return manga;
    }

    public Manga update(Long id, Manga manga) {
        Manga existing = mangaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Manga no encontrado con id: " + id));

        existing.setTitulo(manga.getTitulo());
        existing.setDescripcion(manga.getDescripcion());
        existing.setEstado(manga.getEstado());
        existing.setAnioPublicacion(manga.getAnioPublicacion());

        if (manga.getAutorId() != null) {
            Autor autor = autorRepository.findById(manga.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + manga.getAutorId()));
            existing.setAutor(autor);
        }

        mangaRepository.save(existing);
        return existing;
    }

    public void delete(Long id) {
        mangaRepository.deleteById(id);
    }

    public List<Manga> search(String titulo) {
        return mangaRepository.findByTituloContaining(titulo);
    }

    public List<Manga> findByAutor(Long autorId) {
        return mangaRepository.findByAutorId(autorId);
    }

    public List<Manga> findByEstado(String estado) {
        return mangaRepository.findByEstado(estado);
    }
}