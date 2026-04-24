# Manga & Web Novel API

API REST en Spring Boot para gestionar Mangas y Web Novels con relaciones entre entidades.

## Requisitos

- Java 17+
- Maven 3.6+

## Estructura del Proyecto

```
cursadas-POO/
├── manga-novel-api/           # Proyecto principal
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/manga/
│   │       │       ├── MangaNovelaApplication.java
│   │       │       ├── controller/
│   │       │       ├── exception/
│   │       │       ├── model/
│   │       │       ├── repository/
│   │       │       └── service/
│   │       └── resources/
│   │           └── application.yml
│   ├── data/                 # Archivos JSON
│   └── pom.xml
└── tp1/                    # TP1 - Empleados
```

## Clonar y Ejecutar

```bash
# Clonar el repositorio
git clone git@github.com:eliasguisolfo11/cursadas-POO.git
cd cursadas-POO/manga-novel-api

# Compilar
mvn clean compile

# Ejecutar
mvn spring-boot:run
```

O construir JAR:

```bash
mvn clean package
java -jar target/manga-novela-api-1.0.0.jar
```

## Configuración

La ruta de los datos se configura en `application.yml`. Por defecto usa `./data/` (relativo al directorio de ejecución).

```yaml
server:
  port: 8080

spring:
  application:
    name: manga-novela-api

data:
  file:
    path: ${DATA_PATH:./data/}

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /api/docs
```

**Variable de entorno:** `DATA_PATH`
- Default: `./data/`
- Ejemplo: `DATA_PATH=/ruta/absoluta/data/ mvn spring-boot:run`

Swagger disponible en: http://localhost:8080/api/docs

## Entidades y Relaciones

| Entidad | Relación |
|--------|----------|
| Autor | (One-to-Many) -> Manga |
| Manga | (Many-to-One) -> Autor |
| Manga | (Many-to-Many) -> Genero |
| Manga | (One-to-Many) -> Capitulo |
| Capitulo | (Many-to-One) -> Manga |

## Endpoints REST

### Mangas
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/mangas | Listar todos |
| GET | /api/mangas/{id} | Buscar por ID |
| POST | /api/mangas | Crear manga |
| PUT | /api/mangas/{id} | Actualizar manga |
| DELETE | /api/mangas/{id} | Eliminar manga |
| GET | /api/mangas/search?titulo=X | Buscar por título |
| GET | /api/mangas/autor/{autorId} | Mangas por autor |
| GET | /api/mangas/estado/{estado} | Mangas por estado |

### Autores
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/autores | Listar todos |
| GET | /api/autores/{id} | Buscar por ID |
| POST | /api/autores | Crear autor |
| PUT | /api/autores/{id} | Actualizar autor |
| DELETE | /api/autores/{id} | Eliminar autor |
| GET | /api/autores/search?nombre=X | Buscar por nombre |

### Géneros
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/generos | Listar todos |
| GET | /api/generos/{id} | Buscar por ID |
| POST | /api/generos | Crear género |
| PUT | /api/generos/{id} | Actualizar género |
| DELETE | /api/generos/{id} | Eliminar género |

### Capítulos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/capitulos | Listar todos |
| GET | /api/capitulos/{id} | Buscar por ID |
| POST | /api/capitulos | Crear capítulo |
| PUT | /api/capitulos/{id} | Actualizar capítulo |
| DELETE | /api/capitulos/{id} | Eliminar capítulo |
| GET | /api/capitulos/manga/{mangaId} | Capítulos por manga |

## Formato de Datos JSON

Los archivos en `data/` usan IDs para referencias:

```json
[
  {
    "id": 1,
    "titulo": "One Piece",
    "descripcion": "Aventura de Piratas...",
    "estado": "En Publicacion",
    "anioPublicacion": 1997,
    "autorId": 1,
    "generosIds": [1, 3]
  }
]
```

## Persistencia

Los datos se guardan en archivos JSON en `data/`:
- `data/autores.json`
- `data/generos.json`
- `data/mangas.json`
- `data/capitulos.json`

## Tecnologías

- Spring Boot 3.2
- Jackson (JSON)
- Maven
- Java 17+