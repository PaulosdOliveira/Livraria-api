package io.github.paulosdoliveira.livrariaapi.model.enums;

import java.util.Arrays;

public enum GeneroLivro {
    DRAMA("Drama"),
    ROMANCE("ROMANCE"),
    CIENCIA("CIENCIA"),
    TERROR("TERROR"),
    COMEDIA("COMEDIA"),
    SUSPENSE("SUSPENSE");

    GeneroLivro(String genero) {
    }
    
    public static GeneroLivro valueOfString(String genero){
        GeneroLivro generoLivro = Arrays.stream(values())
                .filter(String -> String.equals(genero))
                .findFirst()
                .orElse(null);
        return generoLivro;
    }
}
