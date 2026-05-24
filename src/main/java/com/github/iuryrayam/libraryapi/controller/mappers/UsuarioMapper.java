package com.github.iuryrayam.libraryapi.controller.mappers;

import com.github.iuryrayam.libraryapi.controller.dto.UsuarioDTO;
import com.github.iuryrayam.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
