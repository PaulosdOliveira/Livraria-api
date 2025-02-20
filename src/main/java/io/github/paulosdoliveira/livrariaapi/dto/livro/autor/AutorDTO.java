package io.github.paulosdoliveira.livrariaapi.dto.livro.autor;

import java.time.LocalDate;

public class AutorDTO {

    private String nome;
    private String breveDescricao;
    private LocalDate dataNascimento;
    private String UrlFoto;
    private Long livrosVendidos;

    public AutorDTO() {
    }

    public AutorDTO(String nome, String breveDescricao, LocalDate dataNascimento) {
        this.nome = nome;
        this.breveDescricao = breveDescricao;
        this.dataNascimento = dataNascimento;
        this.livrosVendidos = 0L;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBreveDescricao() {
        return breveDescricao;
    }

    public void setBreveDescricao(String breveDescricao) {
        this.breveDescricao = breveDescricao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUrlFoto() {
        return UrlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        UrlFoto = urlFoto;
    }

    public Long getLivrosVendidos() {
        return livrosVendidos;
    }

    public void setLivrosVendidos(Long livrosVendidos) {
        this.livrosVendidos = livrosVendidos;
    }
}
