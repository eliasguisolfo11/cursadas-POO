package com.example.manga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Manga {

    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    private Integer anioPublicacion;

    private Autor autor;

    private List<Genero> generos = new ArrayList<>();

    private List<Capitulo> capitulos = new ArrayList<>();

    private Long autorId;
    private List<Long> generosIds;

    public Manga() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(Integer anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public List<Genero> getGeneros() { return generos; }
    public void setGeneros(List<Genero> generos) { this.generos = generos; }
    public List<Capitulo> getCapitulos() { return capitulos; }
    public void setCapitulos(List<Capitulo> capitulos) { this.capitulos = capitulos; }

    public Long getAutorId() { return autorId != null ? autorId : (autor != null ? autor.getId() : null); }
    public void setAutorId(Long autorId) { this.autorId = autorId; }
    public List<Long> getGenerosIds() { 
        if (generosIds != null) return generosIds;
        if (generos != null) {
            return generos.stream().map(g -> g.getId()).toList();
        }
        return null;
    }
    public void setGenerosIds(List<Long> generosIds) { this.generosIds = generosIds; }

    public void addGenero(Genero genero) {
        this.generos.add(genero);
        genero.getMangas().add(this);
    }

    public void removeGenero(Genero genero) {
        this.generos.remove(genero);
        genero.getMangas().remove(this);
    }

    public void addCapitulo(Capitulo capitulo) {
        this.capitulos.add(capitulo);
        capitulo.setManga(this);
    }
}