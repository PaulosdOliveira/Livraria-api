package io.github.paulosdoliveira.livrariaapi.model;

import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 40)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroLivro genero;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column(nullable = false)
    private LocalDate dataPublicacao;

    @JoinColumn
    @ManyToOne
    private Autor autor;

    private boolean ativo;

    @Column
    private Long vendas;

    @CreatedDate
    @Column
    private LocalDate dataPostagem;

    @Column
    private byte[] imagem;


    public Livro(String titulo, boolean ativo, GeneroLivro genero) {
        this.titulo = titulo;
        this.ativo = ativo;
        this.genero = genero;
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
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

    public GeneroLivro getGenero() {
        return genero;
    }

    public void setGenero(GeneroLivro genero) {
        this.genero = genero;
    }

    public Long getVendas() {
        return vendas;
    }

    public void setVendas(Long vendas) {
        this.vendas = vendas;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString(){
        return titulo + " id:" + id;
    }
}
