package io.github.paulosdoliveira.livrariaapi.model;

import io.github.paulosdoliveira.livrariaapi.model.enums.PerfilUsuario;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String nome;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(length = 300, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;


    /*@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Compras> livrosComprados;*/


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public List<Compras> getLivrosComprados() {
        return livrosComprados;
    }

    public void setLivrosComprados(List<Compras> livrosComprados) {
        this.livrosComprados = livrosComprados;
    }
}
