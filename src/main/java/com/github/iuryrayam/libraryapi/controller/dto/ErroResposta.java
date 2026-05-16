package com.github.iuryrayam.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mesagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mesagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mesagem, List.of());
    }

    public static ErroResposta conflito(String mesagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mesagem, List.of());
    }
}
