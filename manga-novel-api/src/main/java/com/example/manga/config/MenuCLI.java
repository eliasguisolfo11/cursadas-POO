package com.example.manga.config;

import com.example.manga.model.*;
import com.example.manga.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuCLI implements CommandLineRunner {

    @Value("${cli.enabled:true}")
    private boolean cliEnabled;

    private final MangaRepository mangaRepository;
    private final AutorRepository autorRepository;
    private final GeneroRepository generoRepository;
    private final CapituloRepository capituloRepository;

    public MenuCLI(MangaRepository mangaRepository, AutorRepository autorRepository,
                  GeneroRepository generoRepository, CapituloRepository capituloRepository) {
        this.mangaRepository = mangaRepository;
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
        this.capituloRepository = capituloRepository;
    }

    @Override
    public void run(String... args) {
        if (!cliEnabled) {
            return;
        }
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========================================");
            System.out.println("   API Mangas & Web Novels - Menu CLI");
            System.out.println("========================================");
            System.out.println("1. Ver todos los Mangas");
            System.out.println("2. VerTodos los Autores");
            System.out.println("3. Ver Todos los Generos");
            System.out.println("4. Buscar Manga por titulo");
            System.out.println("5. Buscar Manga por autor");
            System.out.println("6. Ver capitulos de un Manga");
            System.out.println("7. Cargar datos de ejemplo");
            System.out.println("8. Ver relaciones del sistema");
            System.out.println("0. Salir");
            System.out.print("\nOpcion: ");
            System.out.flush();

            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> listarMangas();
                case 2 -> listarAutores();
                case 3 -> listarGeneros();
                case 4 -> buscarManga(scanner);
                case 5 -> buscarPorAutor(scanner);
                case 6 -> listarCapitulos(scanner);
                case 7 -> cargarDatosEjemplo();
                case 8 -> verRelaciones();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private void listarMangas() {
        System.out.println("\n--- Lista de Mangas ---");
        System.out.flush();
        var mangas = mangaRepository.findAll();
        if (mangas.isEmpty()) {
            System.out.println("No hay mangas cargados.");
        } else {
            for (var m : mangas) {
                System.out.printf("[%d] %s | Estado: %s | Autor: %s%n",
                    m.getId(), m.getTitulo(), m.getEstado(),
                    m.getAutor() != null ? m.getAutor().getNombre() : "N/A");
            }
        }
        System.out.flush();
    }

    private void listarAutores() {
        System.out.println("\n--- Lista de Autores ---");
        System.out.flush();
        var autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores cargados.");
        } else {
            for (var a : autores) {
                System.out.printf("[%d] %s | Pais: %s%n",
                    a.getId(), a.getNombre(), a.getPais());
            }
        }
        System.out.flush();
    }

    private void listarGeneros() {
        System.out.println("\n--- Lista de Generos ---");
        System.out.flush();
        var generos = generoRepository.findAll();
        if (generos.isEmpty()) {
            System.out.println("No hay generos cargados.");
        } else {
            for (var g : generos) {
                System.out.printf("[%d] %s - %s%n",
                    g.getId(), g.getNombre(), g.getDescripcion());
            }
        }
        System.out.flush();
    }

    private void buscarManga(Scanner scanner) {
        System.out.print("Ingrese titulo a buscar: ");
        System.out.flush();
        String titulo = scanner.nextLine();
        System.out.println("\n--- Resultados ---");
        System.out.flush();
        var resultados = mangaRepository.findByTituloContaining(titulo);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron mangas.");
        } else {
            for (var m : resultados) {
                System.out.printf("[%d] %s%n", m.getId(), m.getTitulo());
            }
        }
        System.out.flush();
    }

private void buscarPorAutor(Scanner scanner) {
        System.out.print("Ingrese ID del autor: ");
        System.out.flush();
        try {
            Long autorId = Long.parseLong(scanner.nextLine().trim());
            System.out.println("\n--- Mangas del Autor ---");
            System.out.flush();
            var mangas = mangaRepository.findByAutorId(autorId);
            if (mangas.isEmpty()) {
                System.out.println("No hay mangas de este autor.");
            } else {
                for (var m : mangas) {
                    System.out.printf("[%d] %s%n", m.getId(), m.getTitulo());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido");
        }
        System.out.flush();
    }

    private void listarCapitulos(Scanner scanner) {
        System.out.print("Ingrese ID del Manga: ");
        System.out.flush();
        try {
            Long mangaId = Long.parseLong(scanner.nextLine().trim());
            System.out.println("\n--- Capitulos ---");
            System.out.flush();
            var capitulos = capituloRepository.findByMangaIdOrderByNumero(mangaId);
            if (capitulos.isEmpty()) {
                System.out.println("No hay capitulos para este manga.");
            } else {
                for (var c : capitulos) {
                    System.out.printf("Capitulo %d: %s%n", c.getNumero(), c.getTitulo());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("ID invalido");
        }
        System.out.flush();
    }

    private void cargarDatosEjemplo() {
        if (!autorRepository.findAll().isEmpty()) {
            System.out.println("Los datos de ejemplo ya fueron cargados.");
            return;
        }

        Autor autor1 = new Autor();
        autor1.setNombre("Eiichiro Oda");
        autor1.setPais("Japon");
        autor1.setFechaNacimiento("01/01/1975");
        autorRepository.save(autor1);

        Autor autor2 = new Autor();
        autor2.setNombre("Tite Kubo");
        autor2.setPais("Japon");
        autor2.setFechaNacimiento("26/06/1970");
        autorRepository.save(autor2);

        Autor autor3 = new Autor();
        autor3.setNombre("Jiang Nan");
        autor3.setPais("China");
        autor3.setFechaNacimiento("05/11/1980");
        autorRepository.save(autor3);

        Genero genero1 = new Genero();
        genero1.setNombre("Shonen");
        genero1.setDescripcion("Manga dirigido a jovenes adolescentes");
        generoRepository.save(genero1);

        Genero genero2 = new Genero();
        genero2.setNombre("Fantasia");
        genero2.setDescripcion("Historias con elementos magicos y fantasticos");
        generoRepository.save(genero2);

        Genero genero3 = new Genero();
        genero3.setNombre("Accion");
        genero3.setDescripcion("Historia centrada en batalla y conflicto");
        generoRepository.save(genero3);

        Genero genero4 = new Genero();
        genero4.setNombre("Wuxia");
        genero4.setDescripcion("Novelas chinese de artes marciales");
        generoRepository.save(genero4);

        Manga manga1 = new Manga();
        manga1.setTitulo("One Piece");
        manga1.setDescripcion("Aventura de Piratas en busca del One Piece");
        manga1.setEstado("En Publicacion");
        manga1.setAnioPublicacion(1997);
        manga1.setAutor(autor1);
        manga1.addGenero(genero1);
        manga1.addGenero(genero3);
        mangaRepository.save(manga1);

        Manga manga2 = new Manga();
        manga2.setTitulo("Bleach");
        manga2.setDescripcion("Soul Reapers y el mundo de los espiritus");
        manga2.setEstado("Finalizado");
        manga2.setAnioPublicacion(2001);
        manga2.setAutor(autor2);
        manga2.addGenero(genero1);
        manga2.addGenero(genero2);
        mangaRepository.save(manga2);

        Manga manga3 = new Manga();
        manga3.setTitulo("Mercenary Enrollment");
        manga3.setDescripcion("Web Novel coresiana convertida a manga");
        manga3.setEstado("En Publicacion");
        manga3.setAnioPublicacion(2020);
        manga3.setAutor(autor3);
        manga3.addGenero(genero2);
        manga3.addGenero(genero3);
        mangaRepository.save(manga3);

        Manga manga4 = new Manga();
        manga4.setTitulo("Solo Leveling");
        manga4.setDescripcion("El mas debil se vuelve el mas fuerte");
        manga4.setEstado("Finalizado");
        manga4.setAnioPublicacion(2018);
        manga4.setAutor(autor3);
        manga4.addGenero(genero2);
        manga4.addGenero(genero3);
        mangaRepository.save(manga4);

        Capitulo capitulo1 = new Capitulo();
        capitulo1.setTitulo("La era de los piratas");
        capitulo1.setNumero(1);
        capitulo1.setManga(manga1);
        capituloRepository.save(capitulo1);

        Capitulo capitulo2 = new Capitulo();
        capitulo2.setTitulo("Luffy reaparece");
        capitulo2.setNumero(2);
        capitulo2.setManga(manga1);
        capituloRepository.save(capitulo2);

        Capitulo capitulo3 = new Capitulo();
        capitulo3.setTitulo("El dia del Soul Reaper");
        capitulo3.setNumero(1);
        capitulo3.setManga(manga2);
        capituloRepository.save(capitulo3);

        System.out.println("Datos de ejemplo cargados exitosamente!");
        System.out.println("- 3 Autores");
        System.out.println("- 4 Generos");
        System.out.println("- 4 Mangas");
        System.out.println("- 3 Capitulos");
    }

    private void verRelaciones() {
        System.out.println("\n=== RELACIONES DEL SISTEMA ===");
        System.out.println("\n1. Relacion Manga -> Autor (Many-to-One)");
        System.out.println("   Un Manga tiene un Autor");
        System.out.println("   Un Autor puede tener Muchos Mangas");

        System.out.println("\n2. Relacion Manga <-> Genero (Many-to-Many)");
        System.out.println("   Un Manga puede pertenecer a muchos Generos");
        System.out.println("   Un Genero puede tener muchos Mangas");

        System.out.println("\n3. Relacion Manga -> Capitulo (One-to-Many)");
        System.out.println("   Un Manga tiene muchos Capitulos");
        System.out.println("   Un Capitulo pertenece a un Manga");

        System.out.println("\n4. Relacion Capitulo -> Manga (Many-to-One)");
        System.out.println("   Un Capitulo tiene un Manga");

        System.out.println("\n=== CONSULTAS DISPONIBLES ===");
        System.out.println("- Buscar Manga por titulo");
        System.out.println("- Buscar Manga por autor");
        System.out.println("- Buscar Manga por estado");
        System.out.println("- Listar capitulos por manga");
    }
}