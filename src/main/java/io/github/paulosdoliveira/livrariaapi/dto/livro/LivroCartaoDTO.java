package io.github.paulosdoliveira.livrariaapi.dto.livro;

import io.github.paulosdoliveira.livrariaapi.model.Autor;

import java.util.Date;


public class LivroCartaoDTO {
    private String titulo;
    private Date dataPublicacao;
    private Autor autor;

    public LivroCartaoDTO(String titulo, Date dataPublicacao, Autor autor) {
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
    }

    public LivroCartaoDTO() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
}
