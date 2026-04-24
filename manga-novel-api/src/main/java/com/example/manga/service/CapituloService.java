package com.example.manga.service;

import com.example.manga.dto.CapituloDTO;
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

    public List<CapituloDTO> findAll() {
        return capituloRepository.findAll().stream()
            .map(this::toCapituloDTO)
            .collect(Collectors.toList());
    }

    public CapituloDTO findById(Long id) {
        Capitulo capitulo = capituloRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Capitulo no encontrado con id: " + id));
        return toCapituloDTO(capitulo);
    }

    public CapituloDTO create(CapituloDTO dto) {
        Manga manga = mangaRepository.findById(dto.getMangaId())
            .orElseThrow(() -> new RuntimeException("Manga no encontrado con id: " + dto.getMangaId()));

        Capitulo capitulo = new Capitulo();
        capitulo.setTitulo(dto.getTitulo());
        capitulo.setNumero(dto.getNumero());
        capitulo.setContenido(dto.getContenido());
        capitulo.setManga(manga);
        capituloRepository.save(capitulo);
        return toCapituloDTO(capitulo);
    }

    public CapituloDTO update(Long id, CapituloDTO dto) {
        Capitulo capitulo = capituloRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Capitulo no encontrado con id: " + id));
        capitulo.setTitulo(dto.getTitulo());
        capitulo.setNumero(dto.getNumero());
        capitulo.setContenido(dto.getContenido());
        capituloRepository.save(capitulo);
        return toCapituloDTO(capitulo);
    }

    public void delete(Long id) {
        capituloRepository.deleteById(id);
    }

    public List<CapituloDTO> findByManga(Long mangaId) {
        return capituloRepository.findByMangaIdOrderByNumero(mangaId).stream()
            .map(this::toCapituloDTO)
            .collect(Collectors.toList());
    }

    private CapituloDTO toCapituloDTO(Capitulo capitulo) {
        CapituloDTO dto = new CapituloDTO();
        dto.setId(capitulo.getId());
        dto.setTitulo(capitulo.getTitulo());
        dto.setNumero(capitulo.getNumero());
        dto.setContenido(capitulo.getContenido());
        if (capitulo.getManga() != null) {
            dto.setMangaId(capitulo.getManga().getId());
        }
        return dto;
    }
}