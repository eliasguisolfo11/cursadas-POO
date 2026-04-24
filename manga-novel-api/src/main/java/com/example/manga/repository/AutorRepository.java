package com.example.manga.repository;

import com.example.manga.model.Autor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AutorRepository extends JsonFileRepository<Autor> {
    public AutorRepository(@Value("${data.file.path}") String dataPath) {
        super(Autor.class, dataPath);
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