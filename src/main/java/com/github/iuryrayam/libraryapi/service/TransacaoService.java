package com.github.iuryrayam.libraryapi.service;

import com.github.iuryrayam.libraryapi.model.Autor;
import com.github.iuryrayam.libraryapi.model.GeneroLivro;
import com.github.iuryrayam.libraryapi.model.Livro;
import com.github.iuryrayam.libraryapi.repository.AutorRepository;
import com.github.iuryrayam.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository repository;

    @Transactional
    public void salvarLivroComFoto(){
        // salva o livro
        // repository.save(livro);

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salva foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome do arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png");
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = repository
                .findById(UUID.fromString("61ec6ce8-7862-4737-9908-a0b44efac482"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024, 9, 17));
    }

    @Transactional
    public void executar(){
        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Teste Arthur");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2030, 5, 7));

        autorRepository.save(autor);

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90077-88765");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste Livro do Arthur");
        livro.setDataPublicacao(LocalDate.of(2013, 9, 11));

        livro.setAutor(autor);

        repository.save(livro);

        if (autor.getNome().equals("Teste Arthur")){
            throw new RuntimeException("Rollback!");
        }
    }
}
