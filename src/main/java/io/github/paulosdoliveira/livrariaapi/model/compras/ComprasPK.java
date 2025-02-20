package io.github.paulosdoliveira.livrariaapi.model.compras;

import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ComprasPK {

    @JoinColumn
    @ManyToOne
    private Usuarios usuario;

    @JoinColumn
    @ManyToOne
    private Livro livro;

    public ComprasPK() {
    }

    public ComprasPK(Usuarios usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
