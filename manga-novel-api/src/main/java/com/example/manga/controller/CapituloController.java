package com.example.manga.controller;

import com.example.manga.dto.CapituloDTO;
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
    public ResponseEntity<List<CapituloDTO>> findAll() {
        return ResponseEntity.ok(capituloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CapituloDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(capituloService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CapituloDTO> create(@Valid @RequestBody CapituloDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(capituloService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CapituloDTO> update(@PathVariable Long id, @Valid @RequestBody CapituloDTO dto) {
        return ResponseEntity.ok(capituloService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        capituloService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manga/{mangaId}")
    public ResponseEntity<List<CapituloDTO>> findByManga(@PathVariable Long mangaId) {
        return ResponseEntity.ok(capituloService.findByManga(mangaId));
    }
}