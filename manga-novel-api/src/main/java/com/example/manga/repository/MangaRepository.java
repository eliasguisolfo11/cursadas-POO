package com.example.manga.repository;

import com.example.manga.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MangaRepository extends JsonFileRepository<Manga> {

    private final AutorRepository autorRepository;
    private final GeneroRepository generoRepository;

    public MangaRepository(
            @Value("${data.file.path}") String dataPath,
            AutorRepository autorRepository,
            GeneroRepository generoRepository) {
        super(Manga.class, dataPath);
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
    }

    @Override
    public List<Manga> findAll() {
        List<Manga> result = super.findAll();
        resolveRelations(result);
        return result;
    }

    @Override
    public Optional<Manga> findById(Long id) {
        Optional<Manga> opt = super.findById(id);
        opt.ifPresent(m -> resolveRelations(List.of(m)));
        return opt;
    }

    private void resolveRelations(List<Manga> mangas) {
        for (Manga manga : mangas) {
            if (manga.getAutor() == null && manga.getAutorId() != null) {
                autorRepository.findById(manga.getAutorId()).ifPresent(manga::setAutor);
            }
            if ((manga.getGeneros() == null || manga.getGeneros().isEmpty()) && manga.getGenerosIds() != null) {
                for (Long genId : manga.getGenerosIds()) {
                    generoRepository.findById(genId).ifPresent(manga::addGenero);
                }
            }
        }
    }

    public List<Manga> findByTituloContaining(String titulo) {
        return findAll().stream()
            .filter(m -> m.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Manga> findByAutorId(Long autorId) {
        return findAll().stream()
            .filter(m -> m.getAutor() != null && m.getAutor().getId().equals(autorId))
            .collect(Collectors.toList());
    }

    public List<Manga> findByEstado(String estado) {
        return findAll().stream()
            .filter(m -> m.getEstado().equalsIgnoreCase(estado))
            .collect(Collectors.toList());
    }
}