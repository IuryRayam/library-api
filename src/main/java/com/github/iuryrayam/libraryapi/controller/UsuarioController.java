package com.github.iuryrayam.libraryapi.controller;

import com.github.iuryrayam.libraryapi.controller.dto.UsuarioDTO;
import com.github.iuryrayam.libraryapi.controller.mappers.UsuarioMapper;
import com.github.iuryrayam.libraryapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Tag(name = "usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar", description = "Cadastrar novo usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Usuario já está cadastrado.")
    })
    public void salvar(@RequestBody @Valid UsuarioDTO dto){
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }
}
