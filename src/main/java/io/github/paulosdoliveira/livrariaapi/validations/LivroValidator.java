package io.github.paulosdoliveira.livrariaapi.validations;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.exceptions.LivroDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.exceptions.autor.AutorInativoException;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import io.github.paulosdoliveira.livrariaapi.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LivroValidator {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorService autorService;

    public Autor validar(Livro livro, UUID idAutor) {
        if (idAutor == null) {
            var autor = autorService.buscarAutorAtivoPorId(idAutor);
            if (isbnExiste(livro)) lancarErro("Este ISBN já está cadastrado");
            if (tituloExiste(livro)) lancarErro("Já existe um livro cadastrado com esse titulo");
            if (autor == null) throw new AutorInativoException("Autor  não existe");
            livro.setAutor(autor);
            return autor;
        }
        return null;

    }

    public boolean isbnExiste(Livro livro) {
        var livroPertensente = repository.findByTitulo(livro.getTitulo());
        if (livro.getId() == null) {
            return livroPertensente.isPresent();
        }
        return livroPertensente.isPresent() && livroPertensente.get().getId() != livro.getId();
    }

    public boolean tituloExiste(Livro livro) {
        var livroPertensente = repository.findByTitulo(livro.getTitulo());
        if (livro.getId() == null) {
            return livroPertensente.isPresent();
        }
        return livroPertensente.isPresent() && livroPertensente.get().getId() != livro.getId();
    }


    public LivroDuplicadoException lancarErro(String erro) {
        throw new LivroDuplicadoException(erro);
    }
}
