package com.github.iuryrayam.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Schema(name = "Usuario")
public record UsuarioDTO(
        @NotBlank(message = "campo obrigatorio")
        @Schema(name = "login")
        String login,
        @Email(message = "inválido")
        @NotBlank(message = "campo obrigatorio")
        @Schema(name = "email")
        String email,
        @NotBlank(message = "campo obrigatorio")
        @Schema(name = "senha")
        String senha,
        @Schema(name = "roles")
        List<String> roles) {
}
