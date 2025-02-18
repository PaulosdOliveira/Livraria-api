package io.github.paulosdoliveira.livrariaapi.model;


import io.github.paulosdoliveira.livrariaapi.model.enums.ExtensaoFoto;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private byte[] arquivo;

    @Column
    private String nome;

    @Enumerated(EnumType.STRING)
    private ExtensaoFoto extesao;

    private Long tamanho;


    @JoinColumn
    @OneToOne
    private Autor autor;

    public Foto() {
    }

    public Foto( byte[] arquivo, String nome, ExtensaoFoto extesao, Autor autor, Long tamanho) {
        this.arquivo = arquivo;
        this.nome = nome;
        this.extesao = extesao;
        this.autor = autor;
        this.tamanho = tamanho;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ExtensaoFoto getExtesao() {
        return extesao;
    }

    public void setExtesao(ExtensaoFoto extesao) {
        this.extesao = extesao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public String getFileName(){
        return this.getNome() + "." + this.getExtesao();
    }


}
