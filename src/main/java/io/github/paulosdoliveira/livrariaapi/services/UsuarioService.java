package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.CadastroUsuarioDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.LoginUsuariDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.UsuarioMapper;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    public Usuarios login(LoginUsuariDTO dados) {
        var usuario = repository.findByEmail(dados.getEmail());
        if (usuario == null) throw new UsernameNotFoundException("Usuário não encontrado");
        String senhaDigitada = dados.getSenha();
        String senhaSalva = usuario.getSenha();
        if (encoder.matches(senhaDigitada, senhaSalva)) {

        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    public void cadastrar(CadastroUsuarioDTO dados){
        var usuario = mapper.CadatroParaEntidade(dados);
        repository.save(usuario);
    }


    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }
}
