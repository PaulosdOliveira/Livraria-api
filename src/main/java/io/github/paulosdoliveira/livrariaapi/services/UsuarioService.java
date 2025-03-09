package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.CadastroUsuarioDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.usuario.LoginUsuariDTO;
import io.github.paulosdoliveira.livrariaapi.jwt.JwtService;
import io.github.paulosdoliveira.livrariaapi.mappers.UsuarioMapper;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.model.token.AccessToken;
import io.github.paulosdoliveira.livrariaapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    public AccessToken login(LoginUsuariDTO dados) {
        var usuario = repository.findByEmail(dados.getEmail());
        if (usuario == null) throw new UsernameNotFoundException("Usuário não encontrado");
        String senhaDigitada = dados.getSenha();
        String senhaSalva = usuario.getSenha();
        boolean senhasBatem = encoder.matches(senhaDigitada, senhaSalva);
        if (senhasBatem) {
            AccessToken token = jwtService.gerarToken(usuario);
            return token;
        }
        throw new UsernameNotFoundException("Email ou senha incorretos");
    }

    public void cadastrar(CadastroUsuarioDTO dados) {
        var usuario = mapper.CadatroParaEntidade(dados);
        String senhaCryptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCryptografada);
        repository.save(usuario);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public  Usuarios findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Usuarios buscarPorId(UUID id) {
        System.out.println("********************************************************");
        return repository.findById(id).orElse(null);
    }
}
