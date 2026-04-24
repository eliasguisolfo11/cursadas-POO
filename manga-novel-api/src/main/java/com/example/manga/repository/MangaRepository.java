package com.example.manga.repository;

import com.example.manga.model.Manga;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MangaRepository extends JsonFileRepository<Manga> {
    public MangaRepository() {
        super(Manga.class, "data/");
    }

    public List<Manga> findByTituloContaining(String titulo) {
        return findAll().stream()
            .filter(m -> m.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
            .toList();
    }

    public List<Manga> findByAutorId(Long autorId) {
        return findAll().stream()
            .filter(m -> m.getAutor() != null && m.getAutor().getId().equals(autorId))
            .toList();
    }

    public List<Manga> findByEstado(String estado) {
        return findAll().stream()
            .filter(m -> m.getEstado().equalsIgnoreCase(estado))
            .toList();
    }
}