package io.github.paulosdoliveira.livrariaapi.dto.livro;

import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import jakarta.persistence.GeneratedValue;

import java.time.LocalDate;


public class LivroCartaoDTO {
    private String titulo;
    private GeneroLivro genero;
    private LocalDate dataPublicacao;
    private String nomeAutor;

    public LivroCartaoDTO(String titulo, LocalDate dataPublicacao, String nomeAutor) {
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.nomeAutor = nomeAutor;
    }

    public LivroCartaoDTO() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public GeneroLivro getGenero() {
        return genero;
    }

    public void setGenero(GeneroLivro genero) {
        this.genero = genero;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
}
