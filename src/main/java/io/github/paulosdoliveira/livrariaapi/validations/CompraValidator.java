package io.github.paulosdoliveira.livrariaapi.validations;


import io.github.paulosdoliveira.livrariaapi.exceptions.CompraJaEfetuadaException;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.model.compras.ComprasPK;
import io.github.paulosdoliveira.livrariaapi.repositories.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompraValidator {

    @Autowired
    private ComprasRepository repository;

    public void validar(Usuarios usuario, Livro livro){
        var compraPK = new ComprasPK(usuario, livro);
        boolean compraJaEfetuada = repository.existsById(compraPK);
        if(compraJaEfetuada) throw new CompraJaEfetuadaException("Este usuário já possui esse livro");
    }
}
