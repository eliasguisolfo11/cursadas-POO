package com.example.manga.service;

import com.example.manga.model.Capitulo;
import com.example.manga.model.Manga;
import com.example.manga.repository.CapituloRepository;
import com.example.manga.repository.MangaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapituloService {

    private final CapituloRepository capituloRepository;
    private final MangaRepository mangaRepository;

    public CapituloService(CapituloRepository capituloRepository, MangaRepository mangaRepository) {
        this.capituloRepository = capituloRepository;
        this.mangaRepository = mangaRepository;
    }

    public List<Capitulo> findAll() {
        return capituloRepository.findAll();
    }

    public Capitulo findById(Long id) {
        return capituloRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Capitulo no encontrado con id: " + id));
    }

    public Capitulo create(Capitulo capitulo) {
        if (capitulo.getMangaId() != null) {
            Manga manga = mangaRepository.findById(capitulo.getMangaId())
                .orElseThrow(() -> new RuntimeException("Manga no encontrado con id: " + capitulo.getMangaId()));
            capitulo.setManga(manga);
        }
        capituloRepository.save(capitulo);
        return capitulo;
    }

    public Capitulo update(Long id, Capitulo capitulo) {
        Capitulo existing = capituloRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Capitulo no encontrado con id: " + id));
        existing.setTitulo(capitulo.getTitulo());
        existing.setNumero(capitulo.getNumero());
        existing.setContenido(capitulo.getContenido());
        capituloRepository.save(existing);
        return existing;
    }

    public void delete(Long id) {
        capituloRepository.deleteById(id);
    }

    public List<Capitulo> findByManga(Long mangaId) {
        return capituloRepository.findByMangaIdOrderByNumero(mangaId);
    }
}