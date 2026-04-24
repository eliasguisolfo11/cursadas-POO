package com.example.manga.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Capitulo {

    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotNull(message = "El numero es obligatorio")
    private Integer numero;

    private String contenido;

    private Manga manga;

    public Capitulo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public Manga getManga() { return manga; }
    public void setManga(Manga manga) { this.manga = manga; }
}