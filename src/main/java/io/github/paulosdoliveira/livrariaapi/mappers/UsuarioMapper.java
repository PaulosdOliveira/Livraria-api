package io.github.paulosdoliveira.livrariaapi.mappers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.CadastroUsuarioDTO;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.model.enums.PerfilUsuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuarios CadatroParaEntidade(CadastroUsuarioDTO dados){
        var usuario = new Usuarios();
        usuario.setEmail(dados.getEmail());
        usuario.setNome(dados.getNome());
        usuario.setSenha(dados.getSenha());
        usuario.setPerfil(PerfilUsuario.USUARIO);
        return usuario;
    }


}
