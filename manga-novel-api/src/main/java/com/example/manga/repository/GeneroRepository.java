package com.example.manga.repository;

import com.example.manga.model.Genero;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GeneroRepository extends JsonFileRepository<Genero> {
    public GeneroRepository() {
        super(Genero.class, "data/");
    }

    public Genero findByNombre(String nombre) {
        return findAll().stream()
            .filter(g -> g.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }
}