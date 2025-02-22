package io.github.paulosdoliveira.livrariaapi.validations;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.exceptions.autor.AutorDuplicadoException;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorValidator {

    @Autowired
    private AutorRepository repository;

    public void validar(Autor dados) {
        if(dadosRepetidos(dados))
            throw  new AutorDuplicadoException("Este autor já está cadastrado");
    }

    private boolean dadosRepetidos(Autor autor) {
        var autorJaCadastrado = repository.findByNomeAndDataNascimento(autor.getNome(), autor.getDataNascimento());
        if(autor.getId() == null) return autorJaCadastrado.isPresent();
        return autorJaCadastrado.isPresent() && autorJaCadastrado.get().getId() != autor.getId();
    }
}
