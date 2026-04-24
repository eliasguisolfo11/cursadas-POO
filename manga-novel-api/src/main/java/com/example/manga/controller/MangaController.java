package com.example.manga.controller;

import com.example.manga.dto.MangaDTO;
import com.example.manga.service.MangaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mangas")
public class MangaController {

    private final MangaService mangaService;

    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping
    public ResponseEntity<List<MangaDTO>> findAll() {
        return ResponseEntity.ok(mangaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MangaDTO> create(@Valid @RequestBody MangaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mangaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MangaDTO> update(@PathVariable Long id, @Valid @RequestBody MangaDTO dto) {
        return ResponseEntity.ok(mangaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mangaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MangaDTO>> search(@RequestParam String titulo) {
        return ResponseEntity.ok(mangaService.search(titulo));
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<MangaDTO>> findByAutor(@PathVariable Long autorId) {
        return ResponseEntity.ok(mangaService.findByAutor(autorId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MangaDTO>> findByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(mangaService.findByEstado(estado));
    }
}