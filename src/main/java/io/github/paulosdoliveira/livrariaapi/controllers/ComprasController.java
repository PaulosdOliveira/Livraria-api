package io.github.paulosdoliveira.livrariaapi.controllers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.compras.ComprasDTO;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.services.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("compras")
public class ComprasController {

    @Autowired
    private ComprasService service;

    @PostMapping("/{tituloLivro}")
    public ResponseEntity comprar(@PathVariable String tituloLivro) {
        service.comprar(tituloLivro);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<LivroCartaoDTO>  getLivrosComprados() {
        String idUsuario = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        var livros = service.getLivrosComprados(UUID.fromString(idUsuario));
        return livros;
    }


}
