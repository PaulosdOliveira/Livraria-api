package io.github.paulosdoliveira.livrariaapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table
public class Autor {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 90)
    private String breveDescricao;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column()
    private String UrlFoto;

    @Column()
    private boolean ativo = true;

    private Long livrosVendidos;



    public Autor() {

    }

    public Autor(UUID id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Autor(String nome) {
        this.nome = nome;
    }




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getLivrosVendidos() {
        return livrosVendidos;
    }

    public void setLivrosVendidos(Long livrosVendidos) {
        this.livrosVendidos = livrosVendidos;
    }
}
