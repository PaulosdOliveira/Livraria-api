package io.github.paulosdoliveira.livrariaapi.validations;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.exceptions.LivroDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LivroValidator {

    @Autowired
    private LivroRepository repository;

    public void validar(LivroCadastroDTO dados) {
        boolean isbnExiste = repository.existsByISBN(dados.getISBN());
        boolean tituloExiste = repository.existsByTitulo(dados.getTitulo());
        if (isbnExiste) throw new LivroDuplicadoException("Este ISBN já está cadastrado");
        if (tituloExiste) throw new LivroDuplicadoException("Já existe um livro cadastrado com esse titulo");
    }
}
