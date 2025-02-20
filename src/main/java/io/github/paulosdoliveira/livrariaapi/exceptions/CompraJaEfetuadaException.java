package io.github.paulosdoliveira.livrariaapi.exceptions;

public class CompraJaEfetuadaException extends RuntimeException {

    public CompraJaEfetuadaException(String erro) {
        super(erro);
    }
}
