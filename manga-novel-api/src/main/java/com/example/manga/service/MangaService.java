package com.example.manga.service;

import com.example.manga.dto.*;
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

    public List<MangaDTO> findAll() {
        return mangaRepository.findAll().stream()
            .map(this::toMangaDTO)
            .collect(Collectors.toList());
    }

    public MangaDTO findById(Long id) {
        Manga manga = mangaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Manga no encontrado con id: " + id));
        return toMangaDTO(manga);
    }

    public MangaDTO create(MangaDTO dto) {
        Autor autor = autorRepository.findById(dto.getAutorId())
            .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + dto.getAutorId()));

        Manga manga = new Manga();
        manga.setTitulo(dto.getTitulo());
        manga.setDescripcion(dto.getDescripcion());
        manga.setEstado(dto.getEstado());
        manga.setAnioPublicacion(dto.getAnioPublicacion());
        manga.setAutor(autor);

        if (dto.getGenerosIds() != null) {
            for (Long generoId : dto.getGenerosIds()) {
                generoRepository.findById(generoId).ifPresent(manga::addGenero);
            }
        }

        mangaRepository.save(manga);
        return toMangaDTO(manga);
    }

    public MangaDTO update(Long id, MangaDTO dto) {
        Manga manga = mangaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Manga no encontrado con id: " + id));

        manga.setTitulo(dto.getTitulo());
        manga.setDescripcion(dto.getDescripcion());
        manga.setEstado(dto.getEstado());
        manga.setAnioPublicacion(dto.getAnioPublicacion());

        if (dto.getAutorId() != null) {
            Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + dto.getAutorId()));
            manga.setAutor(autor);
        }

        mangaRepository.save(manga);
        return toMangaDTO(manga);
    }

    public void delete(Long id) {
        mangaRepository.deleteById(id);
    }

    public List<MangaDTO> search(String titulo) {
        return mangaRepository.findByTituloContaining(titulo).stream()
            .map(this::toMangaDTO)
            .collect(Collectors.toList());
    }

    public List<MangaDTO> findByAutor(Long autorId) {
        return mangaRepository.findByAutorId(autorId).stream()
            .map(this::toMangaDTO)
            .collect(Collectors.toList());
    }

    public List<MangaDTO> findByEstado(String estado) {
        return mangaRepository.findByEstado(estado).stream()
            .map(this::toMangaDTO)
            .collect(Collectors.toList());
    }

    private MangaDTO toMangaDTO(Manga manga) {
        MangaDTO dto = new MangaDTO();
        dto.setId(manga.getId());
        dto.setTitulo(manga.getTitulo());
        dto.setDescripcion(manga.getDescripcion());
        dto.setEstado(manga.getEstado());
        dto.setAnioPublicacion(manga.getAnioPublicacion());
        if (manga.getAutor() != null) {
            dto.setAutorId(manga.getAutor().getId());
        }
        if (manga.getGeneros() != null) {
            dto.setGenerosIds(manga.getGeneros().stream().map(Genero::getId).collect(Collectors.toList()));
        }
        return dto;
    }
}