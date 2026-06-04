package com.github.iuryrayam.libraryapi.controller.dto;

import com.github.iuryrayam.libraryapi.model.GeneroLivro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Resultado da Pesquisa de Livro")
public record ResultadoPesquisaLivroDTO(
        UUID id,
        @Schema(name = "isbn")
        String isbn,
        @Schema(name = "titulo")
        String titulo,
        @Schema(name = "dataPublicacao")
        LocalDate dataPublicacao,
        @Schema(name = "genero")
        GeneroLivro genero,
        @Schema(name = "preco")
        BigDecimal preco,
        @Schema(name = "autor")
        AutorDTO autor
) {
}
