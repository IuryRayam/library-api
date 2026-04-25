package com.github.iuryrayam.libraryapi.repository;

import com.github.iuryrayam.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
