package com.example.manga.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public abstract class JsonFileRepository<T> {

    private final Class<T> clazz;
    private final ConcurrentHashMap<Long, T> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final ObjectMapper objectMapper;
    private final String filePath;

    public JsonFileRepository(Class<T> clazz, @Value("${data.file.path:data/}") String dataPath) {
        this.clazz = clazz;
        this.filePath = dataPath + clazz.getSimpleName().toLowerCase() + "s.json";
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @PostConstruct
    public void init() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                List<T> items = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
                for (T item : items) {
                    Long id = getId(item);
                    storage.put(id, item);
                    if (id >= idGenerator.get()) {
                        idGenerator.set(id + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado o vacio, se iniciara vacio: " + filePath);
        }
    }

    private Long getId(T entity) {
        try {
            var idField = entity.getClass().getMethod("getId");
            return (Long) idField.invoke(entity);
        } catch (Exception e) {
            return 0L;
        }
    }

    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public T save(T entity) {
        Long id = getId(entity);
        if (id == null || id == 0) {
            id = idGenerator.getAndIncrement();
            setId(entity, id);
        }
        storage.put(id, entity);
        saveToFile();
        return entity;
    }

    public void deleteById(Long id) {
        storage.remove(id);
        saveToFile();
    }

    public List<T> findByField(String fieldName, Object value) {
        return storage.values().stream()
            .filter(item -> {
                try {
                    var method = item.getClass().getMethod("get" + capitalize(fieldName));
                    return value.equals(method.invoke(item));
                } catch (Exception e) {
                    return false;
                }
            })
            .collect(Collectors.toList());
    }

    private void setId(T entity, Long id) {
        try {
            entity.getClass().getMethod("setId", Long.class).invoke(entity, id);
        } catch (Exception e) {
            throw new RuntimeException("Error setting id", e);
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private synchronized void saveToFile() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            objectMapper.writeValue(file, new ArrayList<>(storage.values()));
        } catch (IOException e) {
            throw new RuntimeException("Error guardando en archivo: " + filePath, e);
        }
    }
}