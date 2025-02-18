package io.github.paulosdoliveira.livrariaapi.dto.livro;

import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


public class LivroCadastroDTO {

    @NotBlank(message = "Campo obrigatório")
    private String titulo;

    @NotNull(message = "Campo obrigatório")
    private GeneroLivro genero;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotBlank(message = "Campo obrigatório")
    private String ISBN;

    @NotNull(message = "Campo obrigatório")
    private LocalDate dataPublicacao;

    @NotNull(message = "Campo obrigatório")
    private UUID idAutor;


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

    public UUID getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(UUID idAutor) {
        this.idAutor = idAutor;
    }


}
