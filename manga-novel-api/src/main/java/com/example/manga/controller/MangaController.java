package com.example.manga.controller;

import com.example.manga.model.Manga;
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
    public ResponseEntity<List<Manga>> findAll() {
        return ResponseEntity.ok(mangaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mangaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Manga> create(@Valid @RequestBody Manga manga) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mangaService.create(manga));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manga> update(@PathVariable Long id, @Valid @RequestBody Manga manga) {
        return ResponseEntity.ok(mangaService.update(id, manga));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mangaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Manga>> search(@RequestParam String titulo) {
        return ResponseEntity.ok(mangaService.search(titulo));
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<Manga>> findByAutor(@PathVariable Long autorId) {
        return ResponseEntity.ok(mangaService.findByAutor(autorId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Manga>> findByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(mangaService.findByEstado(estado));
    }
}