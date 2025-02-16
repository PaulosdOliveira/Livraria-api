package io.github.paulosdoliveira.livrariaapi.validations;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.exceptions.LivroDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

public class LivroValidator {

    @Autowired
    private LivroService service;

    public void validar(LivroCadastroDTO dados) {
        boolean isbnExiste = service.existsByIsbn(dados.getISBN());
        boolean tituloExiste = service.existsByTitulo(dados.getTitulo());
        if (isbnExiste) throw new LivroDuplicadoException("Este ISBN já está cadastrado");
        if (tituloExiste) throw new LivroDuplicadoException("Já existe um livro cadastrado com esse titulo");
    }
}
