package com.github.iuryrayam.libraryapi.controller;

import com.github.iuryrayam.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.iuryrayam.libraryapi.controller.mappers.LivroMapper;
import com.github.iuryrayam.libraryapi.model.Livro;
import com.github.iuryrayam.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        Livro livro = mapper.toEntity(dto);
        Livro livroSalvo = service.salvar(livro);

        URI url = gerarHeaderLocation(livroSalvo.getId());

        return ResponseEntity.created(url).build();
    }
}
