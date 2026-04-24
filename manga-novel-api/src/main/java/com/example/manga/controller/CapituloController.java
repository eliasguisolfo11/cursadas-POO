package com.example.manga.controller;

import com.example.manga.model.Capitulo;
import com.example.manga.service.CapituloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capitulos")
public class CapituloController {

    private final CapituloService capituloService;

    public CapituloController(CapituloService capituloService) {
        this.capituloService = capituloService;
    }

    @GetMapping
    public ResponseEntity<List<Capitulo>> findAll() {
        return ResponseEntity.ok(capituloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Capitulo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(capituloService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Capitulo> create(@Valid @RequestBody Capitulo capitulo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(capituloService.create(capitulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Capitulo> update(@PathVariable Long id, @Valid @RequestBody Capitulo capitulo) {
        return ResponseEntity.ok(capituloService.update(id, capitulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        capituloService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manga/{mangaId}")
    public ResponseEntity<List<Capitulo>> findByManga(@PathVariable Long mangaId) {
        return ResponseEntity.ok(capituloService.findByManga(mangaId));
    }
}