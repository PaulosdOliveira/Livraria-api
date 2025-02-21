package io.github.paulosdoliveira.livrariaapi.controllers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCadastroDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroCartaoDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.LivroNovosDadosDTO;
import io.github.paulosdoliveira.livrariaapi.model.Livro;
import io.github.paulosdoliveira.livrariaapi.model.enums.GeneroLivro;
import io.github.paulosdoliveira.livrariaapi.services.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PreAuthorize("hasRole('ROLE_ADMNISTRADOR')")
    @PostMapping
    public ResponseEntity cadastrarLivro(@RequestBody @Valid LivroCadastroDTO dados) {
        service.salvarLivro(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public Page<LivroCartaoDTO> buscarPorTitulo(
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "genero", required = false) GeneroLivro genero,
            @RequestParam(name = "ano", required = false) Integer ano
    ) {
        return service.buscaComFiltro(titulo, genero, ano, false);
    }

    @PutMapping("/{idLivro}")
    public ResponseEntity alterarDados(@RequestBody LivroNovosDadosDTO dados, @PathVariable UUID idLivro) {
        service.alterarInformacoes(dados, idLivro);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable UUID id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}
