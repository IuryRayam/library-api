package com.github.iuryrayam.libraryapi.controller.mappers;

import com.github.iuryrayam.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.iuryrayam.libraryapi.model.Livro;
import com.github.iuryrayam.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper{

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

}
