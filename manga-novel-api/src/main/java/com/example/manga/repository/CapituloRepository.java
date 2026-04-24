package com.example.manga.repository;

import com.example.manga.model.Capitulo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CapituloRepository extends JsonFileRepository<Capitulo> {
    public CapituloRepository(@Value("${data.file.path}") String dataPath) {
        super(Capitulo.class, dataPath);
    }

    public List<Capitulo> findByMangaId(Long mangaId) {
        return findAll().stream()
            .filter(c -> c.getManga() != null && c.getManga().getId().equals(mangaId))
            .toList();
    }

    public List<Capitulo> findByMangaIdOrderByNumero(Long mangaId) {
        return findByMangaId(mangaId).stream()
            .sorted((c1, c2) -> Integer.compare(c1.getNumero(), c2.getNumero()))
            .toList();
    }
}