package io.github.paulosdoliveira.livrariaapi.controllers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorOptionDTO;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.services.AutorService;
import io.github.paulosdoliveira.livrariaapi.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @Autowired
    private LivroService livroService;


    @PostMapping
    public ResponseEntity cadastrarAutor(
            @RequestParam(required = true, value = "nome") String nome,
            @RequestParam(required = true, value = "breveDescricao") String breveDescricao,
            @RequestParam(required = true, value = "dataNascimento") String dataString,
            @RequestParam(required = false, value = "arquivo") MultipartFile arquivo
    ) throws IOException {
        var dataNascimento = LocalDate.parse(dataString);
        var dto = new AutorDTO(nome, breveDescricao, dataNascimento);
        service.cadastraAutor(dto, arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Autor> buscaFiltrada(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "ano", required = false) Integer ano
    ) {
        return service.consultaFiltrada(nome, ano);
    }

    @GetMapping("/foto")
    public ResponseEntity<byte[]> buscarFoto(@RequestParam(value = "id") UUID idFoto) {
        var foto = service.buscarFoto(idFoto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(foto.getExtesao().getMediaType());
        headers.setContentLength(foto.getTamanho());
        headers.setContentDispositionFormData("inline; fileName=\"" + foto.getFileName() + "\"", foto.getFileName());
        return new ResponseEntity<>(foto.getArquivo(), headers, HttpStatus.OK);
    }

    @GetMapping("/letra")
    public List<AutorDTO> buscaLetrada(@RequestParam Character letra) {
        return service.buscaLetrada(letra);
    }

    @GetMapping("/nomes")
    public List<AutorOptionDTO> buscarNomes(){
        return service.criarOptionAutor();
    }

    @PatchMapping("/{idAutor}")
    public void mudarFoto(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String breveDescricao,
            @RequestParam(required = false) MultipartFile arquivo,
            @RequestParam(required = false) LocalDate dataNascimento,
            @PathVariable() UUID idAutor) throws IOException {
        service.editarInformacoes(nome, breveDescricao, dataNascimento, arquivo, idAutor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAutor(@PathVariable UUID id) {
        var deletado = service.deletarAutor(id);
        if (deletado) livroService.deletarEmCascata(id);
        return ResponseEntity.noContent().build();
    }




}
