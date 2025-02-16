package io.github.paulosdoliveira.livrariaapi.controllers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.CadastroUsuarioDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.LoginUsuariDTO;
import io.github.paulosdoliveira.livrariaapi.services.UsuarioService;
import io.github.paulosdoliveira.livrariaapi.validations.UsuarioValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioValidator validator;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDTO dados) {
        validator.validar(dados.getEmail());
        service.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public void logar(@RequestBody @Valid LoginUsuariDTO dados){
         service.login(dados);
    }

}
