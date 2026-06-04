package com.github.iuryrayam.libraryapi.controller.dto;

import com.github.iuryrayam.libraryapi.model.GeneroLivro;
import com.github.iuryrayam.libraryapi.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Cadastro de Livros")
public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "campo obrigatorio")
        @Schema(name = "isbn")
        String isbn,
        @NotBlank(message = "campo obrigatorio")
        @Schema(name = "titulo")
        String titulo,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "não pode ser uma data futura")
        @Schema(name = "dataPublicacao")
        LocalDate dataPublicacao,
        @Schema(name = "genero")
        GeneroLivro genero,
        @Schema(name = "preco")
        BigDecimal preco,
        @NotNull(message = "campo obrigatorio")
        @Schema(name = "idAutor")
        UUID idAutor
) {
}
