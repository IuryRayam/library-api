package com.github.iuryrayam.libraryapi.service;

import com.github.iuryrayam.libraryapi.model.Autor;
import com.github.iuryrayam.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void delete(Autor autor){
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if (nome != null && nacionalidade != null){
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null){
            return repository.findByNome(nome);
        }

        if (nacionalidade != null){
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();
    }

    public void atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor ja esteja salvo na base.");
        }
        repository.save(autor);
    }
}
