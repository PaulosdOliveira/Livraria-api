package io.github.paulosdoliveira.livrariaapi.exceptions.autor;

public class AutorInativoException extends RuntimeException{
    public AutorInativoException(String erro){
        super(erro);
    }
}
