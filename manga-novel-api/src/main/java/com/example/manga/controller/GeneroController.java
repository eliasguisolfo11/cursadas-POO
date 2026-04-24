package com.example.manga.controller;

import com.example.manga.model.Genero;
import com.example.manga.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<Genero>> findAll() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> findById(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Genero> create(@Valid @RequestBody Genero genero) {
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.create(genero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> update(@PathVariable Long id, @Valid @RequestBody Genero genero) {
        return ResponseEntity.ok(generoService.update(id, genero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        generoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}