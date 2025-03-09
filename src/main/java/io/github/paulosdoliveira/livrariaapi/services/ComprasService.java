package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.compras.ComprasDTO;
import io.github.paulosdoliveira.livrariaapi.mappers.LivroMapper;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.Usuarios;
import io.github.paulosdoliveira.livrariaapi.model.compras.Compras;
import io.github.paulosdoliveira.livrariaapi.model.compras.ComprasPK;
import io.github.paulosdoliveira.livrariaapi.repositories.AutorRepository;
import io.github.paulosdoliveira.livrariaapi.repositories.ComprasRepository;
import io.github.paulosdoliveira.livrariaapi.validations.CompraValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository repository;

    @Autowired
    private CompraValidator validator;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroMapper livroMapper;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void comprar(String tituloLivro) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioService.findByEmail(email);
        var livro = livroService.buscarPorTitulo(tituloLivro);
        validator.validar(usuario, livro);
        var compra = new Compras(usuario, livro);
        repository.save(compra);
        livro.setVendas(livro.getVendas() + 1);
        UUID idAutor = livro.getAutor().getId();
        autorRepository.vendeuLivro(idAutor);
    }

    public List<LivroCartaoDTO> getLivrosComprados(UUID id) {
        var resultado = repository.getLivrosComprados(id);
        var lista = resultado.stream().map(livroMapper::toCartao).toList();
        return lista;
    }


    public boolean livroJaComprado(Usuarios usuarioLogado, Livro livro){
        var compraRegistrada = new ComprasPK(usuarioLogado, livro);
        return repository.existsById(compraRegistrada);
    }
}
