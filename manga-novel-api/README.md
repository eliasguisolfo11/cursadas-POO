# Manga & Web Novel API

API REST en Spring Boot para gestionar Mangas y Web Novels con relaciones entre entidades.

## Requisitos

- Java 17+
- Maven 3.6+

## Clonar y Ejecutar

```bash
# Clonar el repositorio
git clone <url-del-repositorio>
cd cursadas-POO

# Compilar
mvn clean compile

# Ejecutar
mvn spring-boot:run
```

O también:

```bash
mvn clean package
java -jar target/manga-novela-api-1.0.0.jar
```

## Configuración de Ejecución

La aplicación puede ejecutarse de dos formas: **CLI** (consola interactiva) o **Web** (servidor REST con documentación Swagger).

Editar `src/main/resources/application.yml`:

### Modo CLI

```yaml
server:
  port: 8080

spring:
  application:
    name: manga-novela-api

data:
  file:
    path: data/

cli:
  enabled: true

logging:
  level:
    com.example.manga: INFO
```

Al ejecutar, aparecerá un menú interactivo:

```
========================================
   API Mangas & Web Novels - Menu CLI
========================================
1. Ver todos los Mangas
2. VerTodos los Autores
3. Ver Todos los Generos
4. Buscar Manga por titulo
5. Buscar Manga por autor
6. Ver capitulos de un Manga
7. Cargar datos de ejemplo
8. Ver relaciones del sistema
0. Salir
```

### Modo Web

```yaml
server:
  port: 8080

spring:
  application:
    name: manga-novela-api

data:
  file:
    path: data/

cli:
  enabled: false

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /api/docs

logging:
  level:
    com.example.manga: INFO
```

Acceder a:
- **Swagger UI:** http://localhost:8080/api/docs
- **OpenAPI JSON:** http://localhost:8080/api-docs

## Estructura del Proyecto

```
src/main/java/com/example/manga/
├── MangaNovelaApplication.java    # Clase principal
├── config/
│   └── MenuCLI.java               # Menú CLI interactivo
├── controller/
│   ├── MangaController.java       # CRUD de Mangas
│   ├── AutorController.java      # CRUD de Autores
│   ├── GeneroController.java     # CRUD de Géneros
│   └── CapituloController.java    # CRUD de Capítulos
├── dto/
│   ├── MangaDTO.java
│   ├── AutorDTO.java
│   ├── GeneroDTO.java
│   └── CapituloDTO.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   ├── Manga.java                 # Entidad con relaciones
│   ├── Autor.java
│   ├── Genero.java
│   └── Capitulo.java
├── repository/
│   ├── JsonFileRepository.java    # Persistencia en archivo JSON
│   ├── MangaRepository.java
│   ├── AutorRepository.java
│   ├── GeneroRepository.java
│   └── CapituloRepository.java
└── service/
    ├── MangaService.java
    ├── AutorService.java
    ├── GeneroService.java
    └── CapituloService.java
```

## Entidades y Relaciones

1. **Autor** - (One-to-Many) -> Manga
2. **Manga** - (Many-to-One) -> Autor
3. **Manga** - (Many-to-Many) -> Genero
4. **Manga** - (One-to-Many) -> Capitulo
5. **Capitulo** - (Many-to-One) -> Manga

## Endpoints REST

### Mangas
- `GET /api/mangas` - Listar todos
- `GET /api/mangas/{id}` - Buscar por ID
- `POST /api/mangas` - Crear manga
- `PUT /api/mangas/{id}` - Actualizar manga
- `DELETE /api/mangas/{id}` - Eliminar manga
- `GET /api/mangas/search?titulo=X` - Buscar por titulo
- `GET /api/mangas/autor/{autorId}` - Mangas por autor
- `GET /api/mangas/estado/{estado}` - Mangas por estado

### Autores
- `GET /api/autores` - Listar todos
- `GET /api/autores/{id}` - Buscar por ID
- `POST /api/autores` - Crear autor
- `PUT /api/autores/{id}` - Actualizar autor
- `DELETE /api/autores/{id}` - Eliminar autor
- `GET /api/autores/search?nombre=X` - Buscar por nombre

### Generos
- `GET /api/generos` - Listar todos
- `GET /api/generos/{id}` - Buscar por ID
- `POST /api/generos` - Crear genero
- `PUT /api/generos/{id}` - Actualizar genero
- `DELETE /api/generos/{id}` - Eliminar genero

### Capitulos
- `GET /api/capitulos` - Listar todos
- `GET /api/capitulos/{id}` - Buscar por ID
- `POST /api/capitulos` - Crear capitulo
- `PUT /api/capitulos/{id}` - Actualizar capitulo
- `DELETE /api/capitulos/{id}` - Eliminar capitulo
- `GET /api/capitulos/manga/{mangaId}` - Capitulos por manga

## Persistencia

Los datos se guardan en archivos JSON en la carpeta `data/`:
- `data/autores.json`
- `data/generos.json`
- `data/mangas.json`
- `data/capitulos.json`