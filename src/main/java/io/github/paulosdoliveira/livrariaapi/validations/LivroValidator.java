package io.github.paulosdoliveira.livrariaapi.validations;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.exceptions.LivroDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.exceptions.autor.AutorInativoException;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.repositories.LivroRepository;
import io.github.paulosdoliveira.livrariaapi.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LivroValidator {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorService autorService;

    public Autor validar(LivroCadastroDTO dados) {
        boolean isbnExiste = repository.existsByISBN(dados.getISBN());
        boolean tituloExiste = repository.existsByTitulo(dados.getTitulo());
        var autor = autorService.buscarAutorAtivoPorId(dados.getIdAutor());
        if (isbnExiste) lancarErro("Este ISBN já está cadastrado");
        if (tituloExiste) lancarErro("Já existe um livro cadastrado com esse titulo");
        if(autor == null) throw new AutorInativoException("Autor  não existe");
        return autor;
    }

    public LivroDuplicadoException lancarErro(String erro){
        throw new LivroDuplicadoException(erro);
    }
}
