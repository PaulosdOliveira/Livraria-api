package io.github.paulosdoliveira.livrariaapi.model.compras;

import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import jakarta.persistence.*;


@Entity
@Table
public class Compras {

    @EmbeddedId
    private ComprasPK id = new ComprasPK();


    public Compras(Usuarios usuario, Livro livro) {
        this.id.setUsuario(usuario);
        this.id.setLivro(livro);
    }

    public Compras() {
    }

    public ComprasPK getId() {
        return id;
    }

    public void setId(ComprasPK id) {
        this.id = id;
    }
}
