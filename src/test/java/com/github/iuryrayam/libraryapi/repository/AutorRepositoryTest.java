package com.github.iuryrayam.libraryapi.repository;

import com.github.iuryrayam.libraryapi.model.Autor;
import com.github.iuryrayam.libraryapi.model.GeneroLivro;
import com.github.iuryrayam.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Flávia");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2005, 6, 17));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        Autor autor = repository.findById(UUID.fromString("971a927b-249f-4638-bae9-ab9c1032720f")).orElse(null);
        if (autor != null) {

            System.out.println("Dados do Autor:");
            System.out.println(autor);

            autor.setNome("Flávia Lais");
            repository.save(autor);
        } else
            System.out.println("Autor não encontrado!");
    }

    @Test
    public void listarTest(){
        List<Autor> autors = repository.findAll();
        autors.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("971a927b-249f-4638-bae9-ab9c1032720f");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        Optional<Autor> entityAutor = repository.findById(UUID.fromString("9653fdc3-f628-4e00-9db1-8fb95c399169"));
        entityAutor.ifPresent(autor -> repository.delete(autor));
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Feyre");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(2001, 8, 10));

        Livro livro = new Livro();
        livro.setIsbn("99999-80995");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("A Fuga");
        livro.setDataPublicacao(LocalDate.of(2012, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90077-88765");
        livro2.setPreco(BigDecimal.valueOf(650));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("UFO");
        livro2.setDataPublicacao(LocalDate.of(2009, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("a5fb2401-643d-462e-b715-5c612b95d333");
        var autor = repository.findById(id).orElse(null);

        List<Livro> listaLivros = livroRepository.findByAutor(autor);
        autor.setLivros(listaLivros);

        autor.getLivros().forEach(System.out::println);
    }
}
