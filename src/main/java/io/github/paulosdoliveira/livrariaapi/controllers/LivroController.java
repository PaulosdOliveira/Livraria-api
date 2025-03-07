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
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PreAuthorize("hasRole('ROLE_ADMNISTRADOR')")
    @PostMapping
    public ResponseEntity cadastrarLivro(
            @RequestParam() String titulo,
            @RequestParam String descricao,
            @RequestParam String genero,
            @RequestParam String ISBN,
            @RequestParam String dataString,
            @RequestParam Float preco,
            @RequestParam String idString,
            @RequestParam MultipartFile arquivo) throws IOException {
        LocalDate dataPublicacao = LocalDate.parse(dataString.replaceAll("\"" , ""));
        UUID idAutor = UUID.fromString(idString.replaceAll("\"", ""));
        var dados = new LivroCadastroDTO(titulo, descricao, genero, ISBN, dataPublicacao, preco, idAutor, arquivo);
        service.salvarLivro(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<LivroCartaoDTO> buscarPorTitulo(
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "genero", required = false) String genero,
            @RequestParam(name = "ano", required = false) Integer ano
    ) {
        return service.buscaComFiltro(titulo, genero, ano, false);
    }

    @GetMapping("/foto/{idLivro}")
    public ResponseEntity<byte[]> renderizarImagem(@PathVariable UUID idLivro) {
        var arquivo = service.buscarImagem(idLivro);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(arquivo.length);
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDispositionFormData("inline; fileName=\"" + "Foto" + "\"", "Foto");
        return new ResponseEntity<>(arquivo, headers, HttpStatus.OK);
    }

    @PutMapping("/{idLivro}")
    public ResponseEntity alterarDados(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Float preco,
            @RequestParam(required = false) String ISBN,
            @RequestParam(required = false) GeneroLivro genero,
            @RequestParam(required = false) LocalDate dataPublicacao,
            @RequestParam(required = false) UUID idAutor,
            @RequestParam(required = false) MultipartFile arquivo,
            @PathVariable UUID idLivro
    ) throws IOException {
        var dados = new LivroNovosDadosDTO(titulo,descricao,preco, genero, ISBN, dataPublicacao, idAutor, arquivo);
        service.alterarInformacoes(dados, idLivro);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable UUID id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}
