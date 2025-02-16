package io.github.paulosdoliveira.livrariaapi.dto.livro.autor;

import java.time.LocalDate;

public class AutorDTO {

    private String nome;
    private String descricaoBreve;
    private LocalDate dataNascimento;
    private String UrlFoto;

    public AutorDTO() {
    }

    public AutorDTO(String nome, String descricaoBreve, LocalDate dataNascimento) {
        this.nome = nome;
        this.descricaoBreve = descricaoBreve;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoBreve() {
        return descricaoBreve;
    }

    public void setDescricaoBreve(String descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
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
}
