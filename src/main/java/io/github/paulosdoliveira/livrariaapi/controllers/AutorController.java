package io.github.paulosdoliveira.livrariaapi.controllers;

import io.github.paulosdoliveira.livrariaapi.dto.livro.autor.AutorDTO;
import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.services.AutorService;
import io.github.paulosdoliveira.livrariaapi.services.FotoService;
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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarAutor(
            @RequestParam(required = true, value = "nome") String nome,
            @RequestParam(required = true, value = "descricaoBreve") String descricaoBreve,
            @RequestParam(required = true, value = "dataNascimento") LocalDate dataNascimento,
            @RequestParam(required = false, value = "arquivo") MultipartFile arquivo
    ) throws IOException {
        var dto = new AutorDTO(nome, descricaoBreve, dataNascimento);
        service.cadastraAutor(dto, arquivo);
    }

    @GetMapping
    public List<Autor> buscaAleatoria() {
        return service.buscaAleatoria();
    }

    @GetMapping("/foto")
    public ResponseEntity<byte[]> buscarFoto(@RequestParam(value = "id") UUID id) {
        var foto = service.buscarFoto(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(foto.getExtesao().getMediaType());
        headers.setContentLength(foto.getTamanho());
        headers.setContentDispositionFormData("inline; fileName=\"" + foto.getFileName() + "\"", foto.getFileName());
        return new ResponseEntity<>(foto.getArquivo(), headers, HttpStatus.OK);
    }

    @PutMapping
    public void mudarFoto(@RequestParam(name = "arquivo") MultipartFile arquivo, @RequestParam(name = "idAutor") UUID idAutor) throws IOException {
        if (arquivo != null || idAutor != null) {
            service.mudarFoto(arquivo, idAutor);
        }
    }


}
