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
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90077-88765");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciencias");
        livro.setDataPublicacao(LocalDate.of(2013, 9, 11));

        Autor autor = autorRepository
                .findById(UUID.fromString("b71f610f-b767-42b4-ae81-0829dbc782ad"))
                .orElse(null);

//        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("90077-88765");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Terceiro Livro");
        livro.setDataPublicacao(LocalDate.of(2013, 9, 11));

        Autor autor = new Autor();
        autor.setNome("Paulo");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2030, 5, 7));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90077-88765");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2013, 9, 11));

        Autor autor = new Autor();
        autor.setNome("Eleanor");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2030, 5, 7));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        var id = UUID.fromString("8962afdb-7cea-467a-8eb5-5b5440a459ed");
        Livro livro = repository.findById(id).orElse(null);

        var autorId = UUID.fromString("e3d76fcc-2761-4551-9c2c-02d7a7342470");
        Autor eleanor = autorRepository.findById(autorId).orElse(null);

        livro.setAutor(eleanor);

        repository.save(livro);
    }

    @Test
    void deletar(){
        var id = UUID.fromString("8962afdb-7cea-467a-8eb5-5b5440a459ed");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        var id = UUID.fromString("1c35ec2a-28cc-4373-8ec9-c495450dcd30");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("e8178e67-0657-46ae-9f1e-bf622d546cfe");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Titulo:");
        System.out.println(livro.getTitulo());
        System.out.println("Autor(a):");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaLivroPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaLivroPorIsbnTest(){
        List<Livro> lista = repository.findByIsbn("90077-88765");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaLivroTituloEPrecoTest(){
        List<Livro> lista = repository.findByTituloAndPreco("UFO", BigDecimal.valueOf(100.00));
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarLivrosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var autores = repository.listarAutoresDosLivros();
        autores.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivros(){
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.MISTERIO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionalParamTest(){
        var resultado = repository.findByGeneroPositionalParameters("preco", GeneroLivro.MISTERIO);
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest(){
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacaoTest(){
        repository.updateDataPublicacao(LocalDate.of(2014, 9, 21));
    }

}