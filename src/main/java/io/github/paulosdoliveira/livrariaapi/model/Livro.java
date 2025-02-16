package io.github.paulosdoliveira.livrariaapi.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false , unique = true, length = 40)
    private String titulo;

    @Column(nullable = false , length = 150)
    private String descricao;

    @Column(nullable = false , unique = true)
    private String ISBN;

    @Column(nullable = false)
    private Date dataPublicacao;

    @JoinColumn
    @ManyToOne
    private Autor autor;

    private boolean ativo;

    public Livro(String titulo) {
        this.titulo = titulo;
    }
    public Livro() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
