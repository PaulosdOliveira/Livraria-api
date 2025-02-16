package io.github.paulosdoliveira.livrariaapi.validations;

import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.CadastroUsuarioDTO;
import io.github.paulosdoliveira.livrariaapi.exceptions.EmailDuplicadoEXception;
import io.github.paulosdoliveira.livrariaapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    @Autowired
    private UsuarioService service;

    public void validar(String email){
        boolean emailJaCadastrado = service.existsByEmail(email);
        if(emailJaCadastrado) throw new EmailDuplicadoEXception("Este email já esta sendo usado");
    }
}
