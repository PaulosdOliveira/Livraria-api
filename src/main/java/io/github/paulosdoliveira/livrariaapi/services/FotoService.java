package io.github.paulosdoliveira.livrariaapi.services;

import io.github.paulosdoliveira.livrariaapi.model.Autor;
import io.github.paulosdoliveira.livrariaapi.model.Foto;
import io.github.paulosdoliveira.livrariaapi.model.enums.ExtensaoFoto;
import io.github.paulosdoliveira.livrariaapi.repositories.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;


@Service
public class FotoService {


    @Autowired
    private FotoRepository repository;


    public void salvarFoto(MultipartFile arquivo, Autor autor) throws IOException {
        var imagem = arquivo.getBytes();
        var nomeFoto = arquivo.getOriginalFilename();
        var extensao = ExtensaoFoto.valueOf(MediaType.valueOf(arquivo.getContentType()));
        var tamanho = arquivo.getSize();
        var foto = new Foto(imagem, nomeFoto, extensao, autor, tamanho);
        repository.save(foto);
    }

    public void mudarFoto(MultipartFile arquivo, UUID idFoto) throws IOException {
        var imagem = arquivo.getBytes();
        var nomeFoto = arquivo.getOriginalFilename();
        var extensao = ExtensaoFoto.valueOf(MediaType.valueOf(arquivo.getContentType()));
        var tamanho = arquivo.getSize();
        var foto = new Foto(idFoto, imagem, nomeFoto, extensao, tamanho);
        repository.save(foto);
    }

    public Foto buscarFoto(Autor autor) {
        var foto = repository.findByAutor(autor).orElse(null);
        return foto;
    }


}
