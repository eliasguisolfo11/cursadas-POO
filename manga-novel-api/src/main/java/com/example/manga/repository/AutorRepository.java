package com.example.manga.repository;

import com.example.manga.model.Autor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AutorRepository extends JsonFileRepository<Autor> {
    public AutorRepository() {
        super(Autor.class, "data/");
    }

    public List<Autor> findByNombreContaining(String nombre) {
        return findAll().stream()
            .filter(a -> a.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .toList();
    }

    public List<Autor> findByPais(String pais) {
        return findAll().stream()
            .filter(a -> a.getPais().equalsIgnoreCase(pais))
            .toList();
    }
}