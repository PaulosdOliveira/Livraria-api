package io.github.paulosdoliveira.livrariaapi.validations;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.exceptions.AutorDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorValidator {

    @Autowired
    private AutorRepository repository;

    public void validar(AutorDTO dados){
        boolean autorJaCadastrado = repository.existsByNomeAndDataNascimento(dados.getNome(), dados.getDataNascimento());
        if(autorJaCadastrado) throw  new AutorDuplicadoException("Este autor já está cadastrado");
    }
}
