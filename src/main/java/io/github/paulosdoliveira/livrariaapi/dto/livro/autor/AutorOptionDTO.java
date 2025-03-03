package io.github.paulosdoliveira.livrariaapi.dto.livro.autor;

import java.util.UUID;

public class AutorOptionDTO {
    private String nome;
    private UUID id;

    public AutorOptionDTO(String nome, UUID id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + "\n"
                + "Id: " + id;
    }
}
