package io.github.paulosdoliveira.livrariaapi.dto.livro.usuario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CadastroUsuarioDTO {

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Campo obrigatório")
    private String email;

    @NotBlank(message = "Campo obrigatório")
    private String senha;

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
}


