package io.github.paulosdoliveira.livrariaapi.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn
    @OneToOne
    private Usuarios usuario;

    @JoinColumn
    @OneToOne
    private Livro livro;

    public Compras() {
    }

    public Compras( Usuarios usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
